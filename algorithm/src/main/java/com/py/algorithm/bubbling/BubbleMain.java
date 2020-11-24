package com.py.algorithm.bubbling;

import java.util.Arrays;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/24
 */
public class BubbleMain {

    public static void main(String[] args) {
        int[] arr = {1, 2, 7, 3, 6, 1, 2, 5, 7};
        System.out.println(Arrays.toString(arr));
        bubbleSort_1(arr);
//        bubbleSort_2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 相邻的两个数据相互比较，前面的大，两个交换；前面的小，位置不变
     *
     * @param arr 数组
     */
    public static void bubbleSort_1(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 每次遍历找到最小值的下标，然后放在当前遍历位置的最前面
     *
     * @param arr 数组
     */
    public static void bubbleSort_2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }
    }
}
