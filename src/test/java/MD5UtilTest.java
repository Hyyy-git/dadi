import com.ccic.component.RedisLock;
import com.ccic.utils.DateUtils;
import com.ccic.utils.MD5Util;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by acer on 2019/8/13.
 */

public class MD5UtilTest {
/*
    @Test
    public void getMD5() throws Exception {

        boolean flag = messageLock("test","xb",0);
        System.out.println(flag);

        String t  = getTimeByHour(3);
        System.out.println(t);




        new WebCallServiceImpl().getCallRecord();

        String time = DateUtils.INSTANCE.getDayStart();
        System.out.println(time);
        int a1 = DateUtils.INSTANCE.StringToTimestamp(time);
        System.out.println(a1);

        WebCall webCall = new WebCall();
        Gson gson = new Gson();


        String params = "{id='', templateId='', clId='', params='测试模板配置功能', description='', requestUniqueId='', resultCode='', urlInfo='', inputTime='', updateTime='', state='null'}";

        Map<String, Object> map = new HashMap<>();
        map = gson.fromJson(params, new TypeToken<Map<String, Object>>() {
        }.getType());
        String a = "测试id模板params,你好requestUniqueId猜测是";
        for (String key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key));
            a =  a.replace(key, (map.get(key).toString()));
            System.out.println(a);
        }


        System.out.println(new Date());

        long timeStampSec = System.currentTimeMillis() / 1000;
        String timestamp = String.format("%010d", timeStampSec);
        webCall.setTimestamp(Long.parseLong(timestamp));
        webCall.setSign("test");
        webCall.toString();
        gson.toJson(webCall);
        System.out.println(webCall.toString());
        StringBuilder stringBuilder = new StringBuilder();

        WebCallServiceImpl webCallService = new WebCallServiceImpl();


        if (!StringUtils.isEmpty(webCall)) {
            BeanMap beanMap = BeanMap.create(webCall);
            stringBuilder.append("?");
            for (Object key : beanMap.keySet()) {
                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(beanMap.get(key));
                stringBuilder.append("&");
                System.out.println(stringBuilder.toString());
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            System.out.println(stringBuilder);
        }


//        String sign = webCallService.encryption(webCall);
//        System.out.println(sign);


        System.out.println(System.currentTimeMillis());


//        String str = MD5Util.INSTANCE.getMD5("", false, 16);
//        System.out.println(str);
    }
*/

    public static void getIp(String tdy218[]) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String hostname = InetAddress.getLocalHost().getHostName();
            System.out.println("getLocalHost()返回:" + InetAddress.getLocalHost());
            System.out.println("HostName:" + hostname + "\n" + "IP:" + ip);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @param hour
     * @return
     */
    public static String getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }
    public boolean messageLock(String message, String templateId, int hour) throws Exception {
        RedisLock redisLock = new RedisLock();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message);
        stringBuilder.append(templateId);
        String messageId = MD5Util.INSTANCE.getMD5(stringBuilder.toString(), false, 32);
        String time = DateUtils.INSTANCE.getTimeByHour(hour);
        int timestamp = DateUtils.INSTANCE.StringToTimestamp(time);
        boolean flag = redisLock.lock(messageId, String.valueOf(timestamp));
        return flag;
    }
}



