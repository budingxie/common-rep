package com.py.algorithm.merge;

import com.py.algorithm.logarithmic.LogarithmicUtil;

import java.util.Arrays;

/**
 * description：归并排序，递归版本；时间复杂度：O(N*log2(N))；空间：O(N)
 * {4, 3, 2, 1}
 * 逻辑：把数组均分，0~1；2~3
 * 再均分0~0；1~1；2~2；3~3
 * ...
 * 如 0~0; 1~1
 * 4；3两个位置分别有序
 * 进行merge操作
 * 变成3,4；就是0~1有序
 * 同理：2~3就变成：1,2有序
 * 最后进行：0~1；2~3两个区域
 * 进行merge操作
 * 变成：0~3有序，1，2，3，4
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/25
 */
public class MergeMain {

    public static void main(String[] args) {

        for (int i = 0; i < 5000; i++) {
            int[] arr = LogarithmicUtil.generateRandomArray(10, 100);
            int[] arr0 = LogarithmicUtil.copyArray(arr);
            int[] arr1 = LogarithmicUtil.copyArray(arr);
            int[] arr2 = LogarithmicUtil.copyArray(arr);
            LogarithmicUtil.comparator(arr0);
            mergeSort_1(arr1);
            mergeSort_2(arr2);
            if (!LogarithmicUtil.isEqual(arr0, arr1)) {
                System.out.println("arr0 not equal arr1 =========error");
                System.out.println(Arrays.toString(arr));
            }
            if (!LogarithmicUtil.isEqual(arr0, arr2)) {
                System.out.println("arr0 not equal arr2 =========error");
                System.out.println(Arrays.toString(arr));
            }
            if (!LogarithmicUtil.isEqual(arr1, arr2)) {
                System.out.println("arr1 not equal arr2 =========error");
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("finished");
    }

    /**
     * 归并排序，非递归版本
     *
     * @param arr 数组
     */
    public static void mergeSort_2(int[] arr) {
        int size = 1, left, mid, right;
        while (size < arr.length) {
            left = 0;
            while (left + size < arr.length) {
                mid = left + size - 1;
                right = mid + size;
                if (right > arr.length - 1) {
                    right = arr.length - 1;
                }
                merge(arr, left, mid, right);
                left = right + 1;
            }
            size *= 2;
        }
    }

    /**
     * 归并排序，递归版本
     *
     * @param arr 数组
     */
    public static void mergeSort_1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sortProcess(arr, 0, arr.length - 1);
    }

    public static void sortProcess(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        // 优先级：+ 优先 >>
        int mid = left + ((right - left) >> 1);
        sortProcess(arr, left, mid);
        sortProcess(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmpArr = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            tmpArr[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            tmpArr[i++] = arr[p1++];
        }

        while (p2 <= right) {
            tmpArr[i++] = arr[p2++];
        }
        for (i = 0; i < tmpArr.length; i++) {
            arr[left + i] = tmpArr[i];
        }
    }
}