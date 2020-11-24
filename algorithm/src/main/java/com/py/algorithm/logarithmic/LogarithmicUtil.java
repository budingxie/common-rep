package com.py.algorithm.logarithmic;

import java.util.Arrays;

/**
 * description：对数器
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/24
 */
public class LogarithmicUtil {

    /**
     * 实现一个随机样本产生器
     *
     * @param maxSize  数组长度[0,maxSize]
     * @param maxValue 产生[-maxValue,maxValue]的元素
     * @return 数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 实现一个绝对正确但是复杂度不好的方法B，使用的是JDK提供的Arrays类的排序方法
     *
     * @param arr 数组
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 拷贝数组
     *
     * @param arr 原数组
     * @return 新数组
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 实现比对的方法
     * 查看两个数组元素是否，相等
     *
     * @param arr1 数组
     * @param arr2 数组
     * @return 是否一致
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        // arr1为null；arr2不为null。数组不相等
        if (arr1 == null && arr2 != null) {
            return false;
        }
        // arr1不为null；arr2为null。数组不相等
        if (arr1 != null && arr2 == null) {
            return false;
        }
        // 两个数组都是null。数组相等
        if (arr1 == null && arr2 == null) {
            return true;
        }
        // 长度不相等，数组一定不等
        if (arr1.length != arr2.length) {
            return false;
        }
        // 比较每个同位置的元素
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}