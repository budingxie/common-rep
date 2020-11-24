package com.py.algorithm.insertion;

import java.util.Arrays;

/**
 * description：最坏情况，时间复杂度 O(n^2)
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/24
 */
public class InsertionMain {

    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 0, 6};
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                // 交换
                arr[j] = arr[j] ^ arr[j + 1];
                arr[j + 1] = arr[j] ^ arr[j + 1];
                arr[j] = arr[j] ^ arr[j + 1];
                /*
                int tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
                */
            }
        }
    }
}
