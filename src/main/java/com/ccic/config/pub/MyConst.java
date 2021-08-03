package com.ccic.config.pub;


public enum MyConst {


    MD5_UTILS_INSTANCE("md5", "MD5"),
    MD5_UTILS_CODING("编码格式", "UTF-8"),
    VID_KEY("默认播放语言-普通话", "1"),
    WEB_CALL_QUERY("webcall查詢", "/query"),
    //告警信息模板是否有效
    TEMPLATE_CONFIG_STATE_YES("有效", "Y"),
    TEMPLATE_CONFIG_STATE_NO("无效", "N"),
    //第三方接口调用固定值
    PARAM_NAMES("动态附带参数（值：user_message）以逗号分隔name1,name2… ", "user_message,user_message1"),
    PARAM_TYOES("动态参数类型(默认：2)", "2,2"),
    WEB_CALL_MESSAGE_SEND_STATE_OK("第三方接口调用状态返回：0成功，-1失败", "0"),
    WEB_CALL_MESSAGE_SEND_STATE_N("本地数据是否获取第三方返回结果更新(N=否)", "N"),
    WEB_CALL_MESSAGE_SEND_STATE_Y("本地数据是否获取第三方返回结果更新(Y=是)", "Y"),
    SYSTEM_ID("系统标识","ALARM_"),
    SEND_MESSAGE_TYPE_1("电话","1"),
    SEND_MESSAGE_TYPE_2("邮件","2"),
    SEND_MESSAGE_TYPE_3("微信","3"),
    SEND_MESSAGE_TYPE_4("短信","4"),
    SMS_ID_XB("短信业务系统标识","xb"),
    SMS_CHANNEL_TYPE("发送通道","sms"),
    SMS_CHANNEL_SUB_TYPE("子通道","106"),
    SMS_MESSAGE_TYPE("消息类型-20001通知类型","20001"),
    SMS_COM_CODE("所属机构代码","31170025"),
    SMS_TARGET_TYPE("发送目标类型-1表示个人","1"),
    MAIL_STATE("邮件发送状态","成功"),
    SMS_STATE("短信发送状态","失败"),
    WECHAT_CHANNEL("","weixin"),
    WECHAT_Sub_CHANNEL("","015"),
    WECHAT_MESSAGE_TYPE("","text"),
    WECHAT_COMCODE("","31170000"),
    WECHAT_TARGET_TYPE("","1"),
    WECHAT_CONSUMER_ID("","gdwarningplatform"),
    CHECH_TIME_STAMP("message消息是否加上系统时间（Y=获取系统时间，N=不获取","Y"),
    SEPARATOR("分隔符","，"),
    SIMPLE_MAIL("普通文本格式的邮件","1"),
    HTML_MAIL("HTML格式的邮件","2")
    ;

    private String name;
    private String value;

    private MyConst(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
