package com.py.algorithm.merge;

import java.util.Arrays;

/**
 * description：归并排序，递归版本
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
        int[] arr = {4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
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