package com.ccic.utils;

import com.google.gson.JsonObject;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public enum AlarmUtils {

    INSTANCE;

    /**
     * 获取两字符串的相似度
     */
    public static float getSimilarityRatio(String str, String target) {

        int max = Math.max(str.length(), target.length());

        return 1 - (float) compare(str, target) / max;

    }

    private static int compare(String str, String target) {

        int d[][]; // 矩阵

        int n = str.length();

        int m = target.length();

        int i; // 遍历str的

        int j; // 遍历target的

        char ch1; // str的

        char ch2; // target的

        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1

        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }

        d = new int[n + 1][m + 1];

        // 初始化第一列
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }

        // 初始化第一行
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) {
            // 遍历str
            ch1 = str.charAt(i - 1);

            // 去匹配target
            for (j = 1; j <= m; j++) {

                ch2 = target.charAt(j - 1);

                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小

                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);

            }
        }
        return d[n][m];
    }

    /**
     * 获取最小的值
     */
    private static int min(int one, int two, int three) {

        return (one = one < two ? one : two) < three ? one : three;

    }

    /**
     * 获取createTime与当前时间间隔秒数
     * @param createTime
     * @return
     */
    public static long getInterval(Date createTime){
        Date now = new Date();
        long interval = (now.getTime()-createTime.getTime())/1000;
        return interval;
    }

    /**
     * 将map转换为json字符串
     * @param map
     * @return
     */
    public static String mapToJsonString(Map map){
      JSONObject jsonObject =  JSONObject.fromObject(map);
        String result = jsonObject.toString();
        return result;
    }



    /**
     * splitAry方法<br>将string数组切割成指定大小
     * @param ary 要分割的数组
     * @param subSize 分割的块大小
     * @return
     *
     */
    public static Object[] splitAry(String[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize: ary.length / subSize + 1;

        List<List<String>> subAryList = new ArrayList<List<String>>();

        for (int i = 0; i < count; i++) {
            int index = i * subSize;
            List<String> list = new ArrayList<String>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }
            subAryList.add(list);
        }

        Object[] subAry = new Object[subAryList.size()];

        for(int i = 0; i < subAryList.size(); i++){
            List<String> subList = subAryList.get(i);
            String[] subAryItem = new String[subList.size()];
            for(int j = 0; j < subList.size(); j++){
                subAryItem[j] = subList.get(j);
            }
            subAry[i] = subAryItem;
        }

        return subAry;
    }

}
