package com.py.algorithm.smalland;

import com.py.algorithm.logarithmic.LogarithmicUtil;

import java.util.Arrays;

/**
 * description：
 * 小和问题
 * 在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组的小和。
 * <p>
 * 逆序对问题
 * 给一列数a1,a2,…,an，求它的逆序对数，即有多少个有序对(i,j)，使得 i < j 但 ai > aj。
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/25
 */
public class SmallSumMain {

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            int[] arr = LogarithmicUtil.generateRandomArray(10, 100 + i);
            int[] arr1 = LogarithmicUtil.copyArray(arr);
            int[] arr2 = LogarithmicUtil.copyArray(arr);
            int sum = smallSum(arr1);
            int absolute = LogarithmicUtil.smallSum(arr2);
            if (sum != absolute) {
                System.out.println("sum not equal absolute =========error");
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("finished");
    }

    /**
     * 获取指定数组的小和
     *
     * @param arr 数组
     * @return 小和
     */
    public static int smallSum(int[] arr) {
        int sum = 0;
        int[] tmp = new int[arr.length];
        int size = 1, left, mid, right;
        while (size < arr.length) {
            left = 0;
            while (left + size < arr.length) {
                mid = left + size - 1;
                right = mid + size;
                if (right > arr.length - 1) {
                    right = arr.length - 1;
                }
                // start, 归并加速求小和
                // left, mid; mid+1,right
                int i = 0;
                int p1 = left;
                int p2 = mid + 1;
                while (p1 <= mid && p2 <= right) {
                    if (arr[p1] < arr[p2]) {
                        sum = sum + (right - p2 + 1) * arr[p1];
                        tmp[i++] = arr[p1++];
                    } else {
                        tmp[i++] = arr[p2++];
                    }
                }
                while (p1 <= mid) {
                    tmp[i++] = arr[p1++];
                }
                while (p2 <= right) {
                    tmp[i++] = arr[p2++];
                }
                for (i = 0; i < right - left + 1; i++) {
                    arr[left + i] = tmp[i];
                }
                // end
                left = right + 1;
            }
            size = size * 2;
        }
        return sum;
    }


    /**
     * 获取指定数组的逆序对个数
     *
     * @param arr 数组
     * @return 逆序对个数
     */
    public static int reverseSum(int[] arr) {
        int sum = 0;

        return sum;
    }
}
