package com.ccic.utils;

import com.ccic.config.pub.MyConst;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * Created by TaoHang on 2019/8/13.
 */

public enum MD5Util {
    INSTANCE;

    /**
     * @param pwd     需要加密的字符串
     * @param isUpper 字母大小写(false为默认小写，true为大写)
     * @param bit     加密的类型（16,32,64）
     * @return
     */
    public String getMD5(String pwd, boolean isUpper, Integer bit) {
        String md5 = new String();
        try {
            // 创建加密对象
            MessageDigest md = MessageDigest.getInstance(MyConst.MD5_UTILS_INSTANCE.getValue());
            if (bit == 64) {
                BASE64Encoder bw = new BASE64Encoder();
                String bsB64 = bw.encode(md.digest(pwd.getBytes(MyConst.MD5_UTILS_CODING.getValue())));
                md5 = bsB64;
            } else {
                // 计算MD5函数
                md.update(pwd.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer sb = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        sb.append("0");
                    sb.append(Integer.toHexString(i));
                }
                md5 = sb.toString();
                if (bit == 16) {
                    //截取32位md5为16位
                    String md16 = md5.substring(8, 24).toString();
                    md5 = md16;
                    if (isUpper)
                        md5 = md5.toUpperCase();
                    return md5;
                }
            }
            //转换成大写
            if (isUpper)
                md5 = md5.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }
}
