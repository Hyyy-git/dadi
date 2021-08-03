package com.ccic.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 数组
 * @Author: ruanxiaoliang
 * @Create: 2020-10-21 10:12
 **/
public class SplitArray {
    public static void main(String[] args) {
        String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        Object[] objects = splitLongArray(str, 15);

        for (Object o : objects) {
            String[] strs = (String[]) o;
            //System.out.println(strs.length);
            for (String strTmp : strs) {
                System.out.println(strTmp);
            }
        }


    }

    private static Object[] splitLongArray(String[] ary, int subSize) {
        //用数组的长度除以每个数据的内容数 能除尽就是商是数组个数，除不尽就是商+1个
        int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;
        //建立一个list里面的object是list。将原来的大数组分成小的装在list里面
        List<List<String>> subAryList = new ArrayList<List<String>>();
        //按照获取的数组数循环
        for (int i = 0; i < count; i++) {
            //定位每组循环的最大下标
            int index = i * subSize;
            List<String> list = new ArrayList<String>();
            int j = 0;
            //j小于这一组的最大下标 而且小于整个大数组的长度  将这个符合条件的元素加入list.
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }
            //然后再将list加入到大的list里面
            subAryList.add(list);
        }

        System.out.println(subAryList);


        //新建一个对象数组
        Object[] subAry = new Object[subAryList.size()];
        //循环遍历大List
        for (int i = 0; i < subAryList.size(); i++) {
            List<String> subList = subAryList.get(i);
            String[] subAryItem = new String[subList.size()];
            //将小list里面的值遍历添加到小数组里面
            for (int j = 0; j < subList.size(); j++) {
                subAryItem[j] = subList.get(j);
            }
            //将小数组添加到大数组里
            subAry[i] = subAryItem;
        }

        return subAry;
    }

}
