package com.ccic.service.webcall.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ccic.component.RedisLock;
import com.ccic.config.domain.JSONResult;
import com.ccic.config.exception.DaoException;
import com.ccic.config.exception.TException;
import com.ccic.config.httpclient.HttpResult;
import com.ccic.config.httpclient.HttpService;
import com.ccic.config.pub.MyConst;
import com.ccic.dao.alarm.AlarmInfoRecordDao;
import com.ccic.dao.webcall.WebCallDao;
import com.ccic.entity.alarm.AlarmRecordInfo;
import com.ccic.entity.webcall.*;
import com.ccic.service.alarm.AlarmInfoService;
import com.ccic.service.webcall.SendMessageService;
import com.ccic.utils.AlarmUtils;
import com.ccic.utils.DateUtils;
import com.ccic.utils.MD5Util;
import com.ccic.webservice.sms.bean.RequestHeadDTO;
import com.ccic.webservice.sms.bean.ResponseHeadDTO;
import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageRequest;
import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageRequestDTO;
import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageResponse;
import com.ccic.webservice.sms.sendSimpleMessage.bean.SendSimpleMessageResponseDTO;
import com.ccic.webservice.sms.sendSimpleMessage.intf.SendSimpleMessageServiceServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by TaoHang on 2019/8/13.
 */

@Service
public class SendMessageServiceImpl implements SendMessageService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final static Integer MIN_INTERVAL_TIME = 150; //最少间隔时间
    private final static Integer EMAIL_SEND_SUCCESS = 2;//邮件发送成功
    private final static Integer EMAIL_SEND_FAIL= 3;//邮件发送失败
    @Autowired
    private HttpService httpService;
    @Autowired
    private WebCallDao webCallDao;
    @Autowired
    private AlarmInfoRecordDao alarmInfoRecordDao;
    @Value("${sysParam.encryptKey}")
    private String encryptKey;
    @Value("${sysParam.callCenterID}")
    private int enterpriseId;
    @Value("${sysParam.url}")
    private String url;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.from}")
    private String from;
    @Value("${sms.url}")
    private String smsUrl;
    @Value("${weChat.url}")
    private String wenChatUrl;
    @Value("${spring.profiles.active}")
    private String env;


    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;
    @Autowired
    RedisLock redisLock;
    @Autowired
    private AlarmInfoService alarmInfoService;
    String id;

    @Override
    public JSONResult sendWarningInfo(SendMessage sendMessage) {
        log.info("------------------发送告警TemplateId:{} 开始----------------",sendMessage.getTemplateId());

        log.info("sendMessage request param process:{}",sendMessage);
        WebCallResult webCallResult = null;

        AlarmRecordInfo alarmRecordInfo = null;

        String params = AlarmUtils.INSTANCE.mapToJsonString(sendMessage.getParams());
        log.info("paramsBytes:{}",params.getBytes().length);
        if(params.getBytes().length<=3000){
            //将本次告警信息保存到数据库
            id = alarmInfoService.saveAlarmInfo(sendMessage);
        }else{
            log.info("告警内容过大，不予存库");
        }
        try {
            if (StringUtils.isEmpty(sendMessage.getTemplateId())) {
                throw new TException("获取告警消息模板ID为空！");
            }

            //根据模板id获取模板内容
            TemplateConfig templateConfig = getTemplateConfig(sendMessage.getTemplateId());

            if (templateConfig.getSimilarity().floatValue()<=1) {
                log.info("开始查询计算相似度");
                //根据模板id获取该模板上一次发送的告警记录
                alarmRecordInfo = alarmInfoRecordDao.findLastRecordByTemplateId(sendMessage.getTemplateId());

                log.info("最新alarmRecordInfo:{}", alarmRecordInfo);

                if (null != alarmRecordInfo) {
                 //   String params = AlarmUtils.INSTANCE.mapToJsonString(sendMessage.getParams());
                    //获取本次告警内容参数与上一次告警内容参数的相似度
                    float similarityRatio = AlarmUtils.INSTANCE.getSimilarityRatio(alarmRecordInfo.getParamsInfo(), params);
                    log.info("相似度similarityRatio:{}", similarityRatio);
                    //获取获取上一次告警邮件createTime与当前时间间隔秒数
                    long interval = AlarmUtils.INSTANCE.getInterval(alarmRecordInfo.getCreateTime());
                    log.info("上次发送邮件的时间:{}", alarmRecordInfo.getCreateTime());
                    log.info("间隔时间interval:{}", interval);

                    //如果150秒内发送过告警内容参数相似度大于等于0.95的告警消息，则本次告警消息视为重复将不再发送
                    if (interval <= MIN_INTERVAL_TIME) {
                        log.info("发送告警时间间隔太短");
                        log.info("告警内容相似度阈值:{}", templateConfig.getSimilarity());
                        if (similarityRatio >= templateConfig.getSimilarity().floatValue()) {
                            log.info("告警内容相似度太高:{}", similarityRatio);
                            alarmInfoRecordDao.updateSendFlagById(EMAIL_SEND_FAIL, id);
                            throw new TException("类似的告警消息,取消发送");
                        }
                    }
                }
             }
            if (Objects.isNull(templateConfig)) {
                throw new TException("获取告警消息模板为空！");
            }
            if (MyConst.TEMPLATE_CONFIG_STATE_NO.getValue().equals(templateConfig.getState())) {
                throw new TException("获取告警消息模板状态无效！ ");
            }
            //获取告警通知类型
            if (!StringUtils.isEmpty(templateConfig.getSystemInfoType())) {
                log.info(templateConfig.getSystemInfoType());
                List<String> listType = strToList(templateConfig.getSystemInfoType());
                for (String type : listType) {
                    if (MyConst.SEND_MESSAGE_TYPE_1.getValue().equals(type)) {
                        WebCall webCall = new WebCall();
                        String data = "";
                        webCall.setVid(MyConst.VID_KEY.getValue());
                        webCall.setTimestamp(getTimestamp());
                        webCall.setSign(encryption(enterpriseId, webCall.getTimestamp()));
                        webCall.setValidateType(2);
                        //获取模板中的电话转数组
                        if (!StringUtils.isEmpty(templateConfig.getTelephone())) {
                            log.info("电话方式");
                            List<String> listPhone = strToList(templateConfig.getTelephone());
                            for (String phone : listPhone) {
                                webCall.setTel(phone);//取模板中配置的电话
                                String message = appendTemplate(templateConfig.getTemplateInfo(),sendMessage.getParams(),templateConfig.getMailType());
                                webCall.setUser_message(message);//拼接后的message
                                webCall.setUser_message1(message);//拼接后的message
                                webCall.setIvrId(617);
                                webCall.setParamNames(MyConst.PARAM_NAMES.getValue());
                                webCall.setParamTypes(MyConst.PARAM_TYOES.getValue());
                                webCall.setEnterpriseId(enterpriseId);
                                checkWebCallParam(webCall);
                                StringBuilder urlPrefix = new StringBuilder().append(url);
                                //获取数据库主键id 请求信息存库
                                String serialNo = webCallDao.getSerialNo();
                                sendMessage.setId(serialNo);
                                sendMessage.setCallNumber(phone);
                                sendMessage.setTemplateId(templateConfig.getTemplateId());
                                sendMessage.setParam(message);
                                sendMessage.setMessageType(MyConst.SEND_MESSAGE_TYPE_1.getValue());
                                addMessageInfo(sendMessage, data,params);
                                boolean flag = messageLock(message, sendMessage.getTemplateId(), templateConfig.getTimeout(), phone);
                                log.info("电话flag:{}",flag);
                                if (flag) {
                                    if (!StringUtils.isEmpty(templateConfig.getTimStamp())) {
                                        if (MyConst.CHECH_TIME_STAMP.getValue().equals(templateConfig.getTimStamp())) {
                                            webCall.setUser_message1(getDate());//拼接后的message
                                        }
                                    }
                                    data = dataAppend(urlPrefix, webCall);
                                    String result = sendInfo(data);
                                    log.info("通话接口返回:" + result);
                                    webCallResult = JSONObject.parseObject(result, WebCallResult.class);
                                    //更新数据数据状态
                                    updateMessageInfo(sendMessage, webCallResult);
                                }
                            }
                        }
                    }
                    if (MyConst.SEND_MESSAGE_TYPE_2.getValue().equals(type)) {
                        WebCall webCall = new WebCall();
                        String data = "";
                        //获取模板中的电话转数组
                        if (!StringUtils.isEmpty(templateConfig.getMail())) {
                            List<String> listMail = strToList(templateConfig.getMail());
                            for (String mail : listMail) {
                                webCall.setTel(mail);//取模板中配置的电话
                                String message = appendTemplate(templateConfig.getTemplateInfo(), sendMessage.getParams(),templateConfig.getMailType());
                                //获取数据库主键id 请求信息存库
                                String serialNo = webCallDao.getSerialNo();
                                sendMessage.setId(serialNo);
                                sendMessage.setParam(message);
                                sendMessage.setCallNumber(mail);
                                sendMessage.setClId(from);
                                sendMessage.setMessageType(MyConst.SEND_MESSAGE_TYPE_2.getValue());
                                addMessageInfo(sendMessage, data,params);
                                boolean flag = messageLock(message, sendMessage.getTemplateId(), templateConfig.getTimeout(), mail);
                                log.info("邮件flag:{}",flag);
                                if (flag) {
                                    if (!StringUtils.isEmpty(templateConfig.getTimStamp())) {
                                        if (MyConst.CHECH_TIME_STAMP.getValue().equals(templateConfig.getTimStamp())) {
                                            message = message +"\n"+ getDate();
                                        }
                                    }
                                    if(templateConfig.getMailType().equals(MyConst.SIMPLE_MAIL.getValue())){
                                        sendMail(templateConfig, message, mail);
                                    }else if(templateConfig.getMailType().equals(MyConst.HTML_MAIL.getValue())){
                                        sendHtmlMail(templateConfig, message, mail);
                                    }
                                    sendMessage.setDescription(MyConst.MAIL_STATE.getValue());
                                    updateMailMessageInfo(sendMessage);
                                }
                            }
                        }
                    }
                    if (MyConst.SEND_MESSAGE_TYPE_3.getValue().equals(type)) {
                        if (!StringUtils.isEmpty(templateConfig.getWeChat())) {
                            String data = "";
                            String message = appendTemplate(templateConfig.getTemplateInfo(), sendMessage.getParams(),templateConfig.getMailType());
                            //获取数据库主键id 请求信息存库
                            String serialNo = webCallDao.getSerialNo();
                            sendMessage.setId(serialNo);
                            sendMessage.setCallNumber(templateConfig.getWeChat());
                            sendMessage.setClId("");
                            sendMessage.setMessageType(MyConst.SEND_MESSAGE_TYPE_3.getValue());
                            addMessageInfo(sendMessage, data,params);
                            boolean flag = messageLock(message, sendMessage.getTemplateId(), templateConfig.getTimeout(), templateConfig.getWeChat());
                            log.info("微信flag:{}",flag);
                            if (flag) {
                                if (!StringUtils.isEmpty(templateConfig.getTimStamp())) {
                                    if (MyConst.CHECH_TIME_STAMP.getValue().equals(templateConfig.getTimStamp())) {
                                        message = message +"\n"+ getDate();
                                    }
                                }
                                log.info("微信号码:{}",templateConfig.getWeChat());
                                String[] arr = templateConfig.getWeChat().split(",");
                                log.info("原微信数组长度：{}",arr.length);
                                if(arr.length>=20){
                                    Object[] subAry = AlarmUtils.INSTANCE.splitAry(arr,15);//分割后的子块数组
                                    for(Object obj: subAry){
                                        log.info("循环发送微信");
                                        String[] aryItem = (String[]) obj;
                                        log.info("分割后微信数组长度：{}",aryItem.length);
                                        SendMessage sendWeChatMessage = sendWeChatMessage(message, aryItem);
                                        if (!Objects.isNull(sendWeChatMessage)) {
                                            sendWeChatMessage.setId(serialNo);
                                            sendWeChatMessage.setTemplateId(templateConfig.getTemplateId());
                                            updateMailMessageInfo(sendWeChatMessage);
                                        } else {
                                            throw new TException("微信发送失败！");
                                        }
                                    }
                                }else{
                                    log.info("微信数组长度：{}",arr.length);
                                    SendMessage sendWeChatMessage = sendWeChatMessage(message, arr);
                                    if (!Objects.isNull(sendWeChatMessage)) {
                                        sendWeChatMessage.setId(serialNo);
                                        sendWeChatMessage.setTemplateId(templateConfig.getTemplateId());
                                        updateMailMessageInfo(sendWeChatMessage);
                                    } else {
                                        throw new TException("微信发送失败！");
                                    }
                                }

                            }
                        }
                    }
                    if (MyConst.SEND_MESSAGE_TYPE_4.getValue().equals(type)) {
                        WebCall webCall = new WebCall();
                        String data = "";
                        if (!StringUtils.isEmpty(templateConfig.getTelephone())) {
                            List<String> listPhone = strToList(templateConfig.getTelephone());
                            for (String phone : listPhone) {
                                webCall.setTel(phone);//取模板中配置的电话
                                String message = appendTemplate(templateConfig.getTemplateInfo(), sendMessage.getParams(),templateConfig.getMailType());
                                //获取数据库主键id 请求信息存库
                                String serialNo = webCallDao.getSerialNo();
                                sendMessage.setId(serialNo);
                                sendMessage.setParam(message);
                                sendMessage.setCallNumber(phone);
                                sendMessage.setClId("");
                                sendMessage.setMessageType(MyConst.SEND_MESSAGE_TYPE_4.getValue());
                                addMessageInfo(sendMessage, data,params);
                                boolean flag = messageLock(message, sendMessage.getTemplateId(), templateConfig.getTimeout(), phone + MyConst.SEND_MESSAGE_TYPE_4);
                                log.info("短信flag:{}",flag);
                                if (flag) {
                                    if (!StringUtils.isEmpty(templateConfig.getTimStamp())) {
                                        if (MyConst.CHECH_TIME_STAMP.getValue().equals(templateConfig.getTimStamp())) {
                                            message = message +"\n"+ getDate();
                                        }
                                    }
                                    SendMessage sendSmsMessage = sendSmsMessage(message, phone);
                                    if (!Objects.isNull(sendSmsMessage)) {
                                        sendSmsMessage.setId(serialNo);
                                        sendSmsMessage.setTemplateId(templateConfig.getTemplateId());
                                        updateMailMessageInfo(sendSmsMessage);
                                    } else {
                                        throw new TException("短信发送失败！");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("发送告警信息失败:", e);
            try {
                log.info("id:{}",id);
                alarmInfoRecordDao.updateSendFlagById(EMAIL_SEND_FAIL,id);
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
            return JSONResult.error(e.getMessage());
        }

        log.info("------------------发送告警TemplateId:{} 结束----------------",sendMessage.getTemplateId());
        return JSONResult.success("success", webCallResult);
    }

    @Override
    public void getCallRecord() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);// 事物隔离级别，开启新事务
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def); // 获得事务状态
        List<CallRecordInfo> cdrList = new ArrayList<>();
        try {
            CallRecordRequest callRecordRequest = new CallRecordRequest();
            long timeStampSec = System.currentTimeMillis() / 1000;
            callRecordRequest.setValidateType(2);
            String timestamp = String.format("%010d", timeStampSec);
            callRecordRequest.setTimestamp(Integer.parseInt(timestamp));
            String sign = encryption(enterpriseId, (long) callRecordRequest.getTimestamp());
            callRecordRequest.setSign(sign);
            String time = DateUtils.INSTANCE.getDayStart();
            int startTime = DateUtils.INSTANCE.StringToTimestamp(time);
            callRecordRequest.setStartTime(startTime);
            callRecordRequest.setEndTime(Integer.parseInt(timestamp));
            callRecordRequest.setStart(0);
            callRecordRequest.setLimit(1000);
            callRecordRequest.setEnterpriseId(enterpriseId);
            StringBuilder urlPrefix = new StringBuilder().append(url);
            urlPrefix.append(MyConst.WEB_CALL_QUERY.getValue());
            String data = dataAppend(urlPrefix, callRecordRequest);
            String result = sendInfo(data);
            log.info("通话查询接口返回:" + result);
            CallRecordResult recordResult = JSONObject.parseObject(result, CallRecordResult.class);
            if (!Objects.isNull(recordResult)) {
                Data recordResultData = recordResult.getData();
                if (!Objects.isNull(recordResultData)) {
                    cdrList = recordResultData.getCdrList();
                }
            }
            List<SendMessage> webCallResult = webCallDao.queryMessage(time);
            List<SendMessage> updateInfo = new ArrayList<>();
            if (!webCallResult.isEmpty() || !cdrList.isEmpty()) {
                for (SendMessage webCallMessage : webCallResult) {
                    for (CallRecordInfo cdrl : cdrList) {
                        if (!webCallMessage.getRequestUniqueId().isEmpty()) {
                            if (webCallMessage.getRequestUniqueId().equals(cdrl.getRequestUniqueId())) {
                                webCallMessage.setCallSuccess(cdrl.getStatus());
                                updateInfo.add(webCallMessage);
                            }
                        }
                    }
                }
            }
            log.info("查看list数据" + String.valueOf(updateInfo));
            if (!updateInfo.isEmpty()) {
                webCallDao.updateMessage(updateInfo);
                dataSourceTransactionManager.commit(status);
            }
        } catch (Exception e) {
            log.error("通话记录更新失败:", e);
            dataSourceTransactionManager.rollback(status);
        }
    }

    /**
     * 发送短信
     */
    public SendMessage sendSmsMessage(String message, String number) throws Exception {
        log.info("开始发送短信：" + message + "、号码为" + number);
        SendMessage sendMessage = new SendMessage();
        String[] arr = number.split(",");
        SendSimpleMessageRequest request = new SendSimpleMessageRequest();
        SendSimpleMessageRequestDTO requestBody = new SendSimpleMessageRequestDTO();
        RequestHeadDTO headDTO = new RequestHeadDTO();
        requestBody.setChannel(MyConst.SMS_CHANNEL_TYPE.getValue());
        requestBody.setSubChannel(MyConst.SMS_CHANNEL_SUB_TYPE.getValue());
        requestBody.setMessageType(MyConst.SMS_MESSAGE_TYPE.getValue());
        requestBody.setComcode(MyConst.SMS_COM_CODE.getValue());
        requestBody.setTargetType(MyConst.SMS_TARGET_TYPE.getValue());
        requestBody.setTarget(arr);
        requestBody.setContent(message);
        SendSimpleMessageServiceServiceLocator sendSimpleMessageLocator = new SendSimpleMessageServiceServiceLocator();
        sendSimpleMessageLocator.setSendSimpleMessageServicePortEndpointAddress(smsUrl);//
        headDTO.setConsumerID(MyConst.WECHAT_CONSUMER_ID.getValue());
        request.setRequestHead(headDTO);
        request.setRequestBody(requestBody);
        SendSimpleMessageResponse response = sendSimpleMessageLocator.getSendSimpleMessageServicePort().sendSimpleMessageService(request);
        if (!Objects.isNull(response)) {
            log.info("短信已发送，返回报文：" + JSONObject.toJSON(response));
            SendSimpleMessageResponseDTO body = response.getResponseBody();
            if (!Objects.isNull(body)) {
                sendMessage.setRequestUniqueId(body.getMessageId());
            }
            ResponseHeadDTO head = response.getResponseHead();
            if (!Objects.isNull(head)) {
                sendMessage.setDescription(head.getAppMessage());
            } else {
                sendMessage.setState(MyConst.SMS_STATE.getValue());
            }
        }
        return sendMessage;
    }

    /**
     * 发送企业微信消息
     *
     * @param message
     * @param wxs
     * @return
     */
    public SendMessage sendWeChatMessage(String message, String [] wxs) throws Exception {
        log.info("开始发送微信：" + message );

        SendMessage sendMessage = new SendMessage();
        com.ccic.webservice.wechat.sendSimpleMessage.bean.SendSimpleMessageRequest request = new com.ccic.webservice.wechat.sendSimpleMessage.bean.SendSimpleMessageRequest();
        com.ccic.webservice.wechat.sendSimpleMessage.bean.SendSimpleMessageRequestDTO requestDTO = new com.ccic.webservice.wechat.sendSimpleMessage.bean.SendSimpleMessageRequestDTO();
        com.ccic.webservice.wechat.bean.RequestHeadDTO headDTO = new com.ccic.webservice.wechat.bean.RequestHeadDTO();
        requestDTO.setChannel(MyConst.WECHAT_CHANNEL.getValue());
        requestDTO.setSubChannel(MyConst.WECHAT_Sub_CHANNEL.getValue());
        requestDTO.setMessageType(MyConst.WECHAT_MESSAGE_TYPE.getValue());
        requestDTO.setComcode(MyConst.WECHAT_COMCODE.getValue());
        requestDTO.setTargetType(MyConst.WECHAT_TARGET_TYPE.getValue());
        requestDTO.setTarget(wxs);
        requestDTO.setContent(message);
        headDTO.setConsumerID(MyConst.WECHAT_CONSUMER_ID.getValue());
        com.ccic.webservice.wechat.sendSimpleMessage.intf.SendSimpleMessageServiceServiceLocator sendSimpleMessageLocator = new com.ccic.webservice.wechat.sendSimpleMessage.intf.SendSimpleMessageServiceServiceLocator();
        sendSimpleMessageLocator.setSendSimpleMessageServicePortEndpointAddress(wenChatUrl);
        request.setRequestHead(headDTO);
        request.setRequestBody(requestDTO);
        log.info("微信发送内容jonstr:{}", JSONObject.toJSONString(request));
        com.ccic.webservice.wechat.sendSimpleMessage.bean.SendSimpleMessageResponse response = sendSimpleMessageLocator.getSendSimpleMessageServicePort().sendSimpleMessageService(request);
        if (!Objects.isNull(response)) {
            log.info("微信已发送，返回报文：" + JSONObject.toJSON(response));
            com.ccic.webservice.wechat.sendSimpleMessage.bean.SendSimpleMessageResponseDTO body = response.getResponseBody();
            if (!Objects.isNull(body)) {
                sendMessage.setRequestUniqueId(body.getMessageId());
            }
            com.ccic.webservice.wechat.bean.ResponseHeadDTO head = response.getResponseHead();
            if (!Objects.isNull(head)) {
                sendMessage.setDescription(head.getAppMessage());
            } else {
                sendMessage.setState(MyConst.SMS_STATE.getValue());
            }
        }
        return sendMessage;
    }


    /**
     * 发送邮件
     *
     * @param templateConfig
     * @param message
     * @param mail
     */
    public void sendMail(TemplateConfig templateConfig, String message, String mail) throws Exception{
        log.info("start send mail for templateId: {} ",templateConfig.getTemplateId());
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(mail);

        String subject = templateConfig.getTemplateName();
        if (!org.apache.commons.lang.StringUtils.equalsIgnoreCase(env,"pro")) {
            subject = env + "-" + subject;
            log.info("告警标题：{}",subject);
        }
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        try {
            JavaMailSenderImpl mailSender = mailConfiguration();
            mailSender.send(simpleMailMessage);
            log.info("id:{}",id);
            alarmInfoRecordDao.updateSendFlagById(EMAIL_SEND_SUCCESS,id);
            log.info("邮件发送内容:{}",simpleMailMessage);
            log.info("send mail end for templateId: {} ",templateConfig.getTemplateId());
            log.info("邮件已发送。");
        } catch (Exception e) {
            alarmInfoRecordDao.updateSendFlagById(EMAIL_SEND_FAIL,id);
            log.error("发送邮件时发生异常了！", e);
        }
    }

    /**
     * 发送html格式的邮件
     * @param templateConfig
     * @param message
     * @param mail
     * @throws Exception
     */
    public void sendHtmlMail(TemplateConfig templateConfig, String message, String mail) throws Exception{
        log.info("start send htmlMail for templateId: {} ",templateConfig.getTemplateId());
        JavaMailSenderImpl mailSender = mailConfiguration();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setFrom(from);
        helper.setTo(mail);
        String subject = templateConfig.getTemplateName();
        if (!org.apache.commons.lang.StringUtils.equalsIgnoreCase(env,"pro")) {
            subject = env + "-" + subject;
            log.info("告警标题：{}",subject);
        }
        helper.setSubject(subject);
        helper.setText(message,true);
        try {
            mailSender.send(mimeMessage);
            log.info("id:{}",id);
            alarmInfoRecordDao.updateSendFlagById(EMAIL_SEND_SUCCESS,id);
            log.info("邮件(html格式)发送内容:{}",mimeMessage);
            log.info("send mail end for templateId: {} ",templateConfig.getTemplateId());
            log.info("邮件(html格式)已发送。");
        } catch (Exception e) {
            alarmInfoRecordDao.updateSendFlagById(EMAIL_SEND_FAIL,id);
            log.error("发送邮件(html格式)时发生异常了！", e);
        }
    }


    public JavaMailSenderImpl mailConfiguration() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        try {
            mailSender.setHost(host);
            mailSender.setUsername(username);//发件人邮箱
            mailSender.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mailSender;
    }

    /**
     * 获取当前时间戳10位
     *
     * @return
     */
    private long getTimestamp() throws Exception{
        long timeStampSec = System.currentTimeMillis() / 1000;
        String timestamp = String.format("%010d", timeStampSec);
        return Long.parseLong(timestamp);
    }

    /**
     * 根据模板id获取模板内容
     *
     * @param templateId
     * @return
     * @throws Exception
     */
    private TemplateConfig getTemplateConfig(String templateId) throws Exception {
        log.info("Get template config start");
        TemplateConfig templateConfig = webCallDao.queryTemplateConfig(templateId);
        if (StringUtils.isEmpty(templateConfig)) {
            throw new TException("获取告警消息模板内容为空！");
        }
        log.info("template config id {},send type {} ",templateConfig.getTemplateId(),templateConfig.getSystemInfoType());
        log.info("Get template config end");
        return templateConfig;
    }

    /**
     * 调用第三方请求信息存库
     *
     * @param webCallMessage
     * @param urlInfo
     */
    public void addMessageInfo(SendMessage webCallMessage, String urlInfo,String params) throws Exception{
        if(params.getBytes().length<=3000){
            log.info("告警明细信息记录start");
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);// 事物隔离级别，开启新事务
            TransactionStatus status = dataSourceTransactionManager.getTransaction(def); // 获得事务状态
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                webCallMessage.setUrlInfo(urlInfo);
                webCallMessage.setInputTime(df.format(new Date()));
                webCallMessage.setUpdateTime(df.format(new Date()));
                webCallMessage.setState(MyConst.WEB_CALL_MESSAGE_SEND_STATE_N.getValue());
                webCallMessage.setParams(webCallMessage.getParams());
                webCallDao.addMessageInfo(webCallMessage);
                dataSourceTransactionManager.commit(status);
            } catch (Exception e) {
                dataSourceTransactionManager.rollback(status);
                log.error("通话记录流水数据存库失败:", e);
            }
        log.info("告警明细信息记录end");
        }else{
            log.info("告警内容过大，不予存库");
            return;
        }
    }

    /**
     * 调用第三方请求状态更新
     *
     * @param webCallMessage
     * @param webCallResult
     */
    public void updateMessageInfo(SendMessage webCallMessage, WebCallResult webCallResult) throws Exception{
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);// 事物隔离级别，开启新事务
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def); // 获得事务状态
        try {
            webCallMessage.setDescription(webCallResult.getDescription());
            webCallMessage.setResultCode(String.valueOf(webCallResult.getResult()));
            if (MyConst.WEB_CALL_MESSAGE_SEND_STATE_OK.getValue().equals(webCallMessage.getResultCode())) {
                webCallMessage.setClId(webCallResult.getClid());
                webCallMessage.setRequestUniqueId(webCallResult.getRequestUniqueId());
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            webCallMessage.setUpdateTime(df.format(new Date()));
            webCallMessage.setState(MyConst.WEB_CALL_MESSAGE_SEND_STATE_Y.getValue());
            Integer iRet = webCallDao.updateMessageInfo(webCallMessage);
            if (iRet != 1) {
                throw new DaoException("状态更新错误！");
            }
            dataSourceTransactionManager.commit(status);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(status);
            log.error("通话记录流水数据状态更新失败:", e);
        }
    }

    /**
     * 调用第三方请求状态更新
     *
     * @param sendMessage
     */
    public void updateMailMessageInfo(SendMessage sendMessage)throws Exception {
        log.info("update sendMessage:{}",sendMessage);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);// 事物隔离级别，开启新事务
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def); // 获得事务状态
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sendMessage.setUpdateTime(df.format(new Date()));
            sendMessage.setState(MyConst.WEB_CALL_MESSAGE_SEND_STATE_Y.getValue());
            Integer iRet = webCallDao.updateMessageInfo(sendMessage);
            log.info("iRet:{}",iRet);
           /* if (iRet != 1) {
                throw new DaoException("状态更新错误！");
            }*/
            dataSourceTransactionManager.commit(status);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(status);
            log.error("告警邮件数据状态更新失败:", e);
        }
    }

    /**
     * sign值拼接
     * validateType=2，sign=MD5({enterpriseId}+{timestamp}+{部门token值})；
     * 32位全小写
     *
     * @return
     */
    private String encryption(int enterpriseId, long timestamp) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(enterpriseId);
        stringBuilder.append(timestamp);
        stringBuilder.append(encryptKey);
        String sign = MD5Util.INSTANCE.getMD5(stringBuilder.toString(), false, 32);
        return sign;
    }

    /**
     * webCall校验必选参数
     *
     * @throws Exception
     */
    private void checkWebCallParam(WebCall webCall) throws Exception {
        if (StringUtils.isEmpty(webCall)) {
            throw new TException("告警信息为空！", -1);
        } else {
            if (StringUtils.isEmpty(webCall.getValidateType())) {
                throw new TException("验证类型为空！", -1);
            }
            if (StringUtils.isEmpty(webCall.getTimestamp())) {
                throw new TException("当前时间戳为空！", -1);
            }
            if (StringUtils.isEmpty(webCall.getSign())) {
                throw new TException("验证值为空！", -1);
            }
            if (StringUtils.isEmpty(webCall.getTel())) {
                throw new TException("电话号码为空！", -1);
            }
            if (StringUtils.isEmpty(webCall.getIvrId())) {
                throw new TException("回呼接通后进入的ivrId为空！", -1);
            }
            if (StringUtils.isEmpty(webCall.getParamNames())) {
                throw new TException("动态附带参数为空！", -1);
            }
            if (StringUtils.isEmpty(webCall.getParamTypes())) {
                throw new TException("动态参数类型为空！", -1);
            }
            if (StringUtils.isEmpty(webCall.getUser_message())) {
                throw new TException("自定义文本描述为空！", -1);
            }
        }
    }


    /**
     * 消息拼接
     *
     * @param templateMessageInfo
     * @param params
     * @return
     * @throws Exception
     */
    private String appendTemplate(String templateMessageInfo, Map<String, Object> params,String mailType) throws Exception {
        if (StringUtils.isEmpty(templateMessageInfo)) {
            throw new TException("消息模板为空！", -1);
        }
        if (params != null && params.size() > 0) {
            if(mailType.equals(MyConst.SIMPLE_MAIL.getValue())){
                for (String key : params.keySet()) {
                    templateMessageInfo = templateMessageInfo.replace("{" + key + "}", (params.get(key).toString()));
                }
            }else if(mailType.equals(MyConst.HTML_MAIL.getValue())){
                JSONObject jsonObject = new JSONObject(params);
                JSONArray jsonArray = jsonObject.getJSONArray("lists");
                log.info("jsonArray.size:{}",jsonArray.size());
                StringBuilder tr = new StringBuilder();

                for(int i=0;i<jsonArray.size();i++){
                    Map<String, Object> map = jsonArray.getJSONObject(i);
                    StringBuilder td = new StringBuilder();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        td.append("<td>"+entry.getValue()+"</td>");
                    }
                    tr.append("<tr>"+td+"</tr>");
                }
                log.info("tr:{}",tr);
                templateMessageInfo = templateMessageInfo.replace("<tr></tr>",tr);
            }
        }
        if (!StringUtils.isEmpty(templateMessageInfo)) {
            templateMessageInfo.replace(",", "，");
        }
        return templateMessageInfo;
    }

    /**
     * url数据拼接
     *
     * @param object
     * @return
     * @throws Exception
     */
    private String dataAppend(StringBuilder urlPrefix, Object object) throws Exception {
        log.info("数据拼接请求参数：" + urlPrefix, object.toString());
        StringBuilder urlInfo = new StringBuilder().append(urlPrefix);
        urlInfo.append("?");
        if (!StringUtils.isEmpty(object)) {
            BeanMap beanMap = BeanMap.create(object);
            for (Object key : beanMap.keySet()) {
                if (!StringUtils.isEmpty(beanMap.get(key)) && !"0".equals(beanMap.get(key).toString())) {
                    urlInfo.append(key);
                    urlInfo.append("=");
                    urlInfo.append(beanMap.get(key));
                    urlInfo.append("&");
                }
            }
            urlInfo.deleteCharAt(urlInfo.length() - 1);
        }
        log.info("拼接URL：" + urlInfo);
        return urlInfo.toString();
    }

    /**
     * 数据发送
     *
     * @param url
     * @return
     * @throws Exception
     */
    private String sendInfo(String url) throws Exception {
        log.info("调用第三方接口请求数据:" + url);
        URL sendUrl = new URL(url);
        URI uri = new URI(sendUrl.getProtocol(), null, sendUrl.getHost(), sendUrl.getPort(), sendUrl.getPath(), sendUrl.getQuery(), null);
        HttpResult resultBody = httpService.doPost(uri.toString());
        String result = resultBody.getBody();
        log.info("调用第三方接口返回数据:" + result);
        return result;
    }

    /**
     * 分隔模板中的号码
     *
     * @param telephone
     * @return
     */
    private List<String> strToList(String telephone) throws Exception {
        String[] arr = telephone.split(","); // 用,分割
        List<String> list = Arrays.asList(arr);
        if (list.isEmpty()) {
            throw new TException("电话号码为空！", -1);
        }
        return list;
    }

    /**
     * 分隔模板中的号码
     *
     * @param number
     * @return
     */
    private List<String> arrToList(String[] number) throws Exception {
        List<String> list = Arrays.asList(number);
        if (list.isEmpty()) {
            throw new TException("电话号码为空！", -1);
        }
        return list;
    }

    /**
     * 设置数据超时时间
     *
     * @param message
     * @param templateId
     * @param minute
     * @return
     */
    public boolean messageLock(String message, String templateId, String minute, String number)throws Exception {
        log.info("messageLock message:{} templateId:{},minute:{},number{}",message,templateId,minute,number);
        boolean flag = false;
        try {
            int timeout = 0;
            if (!StringUtils.isEmpty(minute)) {
                timeout = Integer.parseInt(minute);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(message);
            stringBuilder.append(templateId);
            stringBuilder.append(number);
            String messageId = MD5Util.INSTANCE.getMD5(stringBuilder.toString(), false, 32);
            String time = DateUtils.INSTANCE.getTimeByMinute(timeout);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long timestamp = 0;
            timestamp = sdf.parse(time).getTime();
            flag = redisLock.lock(MyConst.SYSTEM_ID.getValue() + messageId, String.valueOf(timestamp));
        } catch (ParseException e) {
            e.printStackTrace();
            log.error("redis连接失败:", e);
        }
        log.info("messageLock flag:{}",flag);
        return flag;
    }

    /**
     * @param templateId
     * @param minute
     * @param number
     * @return
     * @throws Exception
     */
    public boolean messageLock(String templateId, String minute, String number) throws Exception {
        int timeout = 0;
        if (!StringUtils.isEmpty(minute)) {
            timeout = Integer.parseInt(minute);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(templateId);
        stringBuilder.append(number);
        String messageId = MD5Util.INSTANCE.getMD5(stringBuilder.toString(), false, 32);
        String time = DateUtils.INSTANCE.getTimeByMinute(timeout);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timestamp = sdf.parse(time).getTime();
        boolean flag = redisLock.lock(MyConst.SYSTEM_ID.getValue() + messageId, String.valueOf(timestamp));
        return flag;
    }


    /**
     * 获取当前时间 yyyy年MM月dd日 HH时mm分ss秒
     *
     * @return
     */
    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

}
