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

    /**
     * 插入排序，首先假设0位置是有序的；然后1位置加入，和0位置进行排序
     * 这个时候0~1区间是有序的，然后2位置加入，进行排序完成
     * 这个时候0~2区间是有序的，然后3位置加入，进行排序完成
     * ....
     * 假设：0 ~ i-1位置是有序的；这个时候i位置的元素加入，进行排序。
     *
     * 排序规则：
     * i-1 位置和 i 位置比较
     * arr[i-1] <= arr[i]
     * 不需要移动元素：0~i位置已经有序了
     * arr[i-1] > arr[i]
     * 两个交换，
     * 再比较i-2 和 i-1位置
     * 依次下去
     * @param arr 数组
     */
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
