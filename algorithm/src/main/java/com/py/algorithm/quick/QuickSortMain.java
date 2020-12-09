package com.py.algorithm.quick;

import com.py.algorithm.logarithmic.LogarithmicUtil;

import java.util.Arrays;

/**
 * description：快排
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/12/7
 */
public class QuickSortMain {

    public static void main(String[] args) {
        for (int i = 0; i < 5000; i++) {
            int[] arr = LogarithmicUtil.generateRandomArray(10, 100 + i);
            int[] arr1 = LogarithmicUtil.copyArray(arr);
            int[] arr2 = LogarithmicUtil.copyArray(arr);
            LogarithmicUtil.comparator(arr1);
            quickSort(arr2, 0, arr2.length - 1);
            if (!LogarithmicUtil.isEqual(arr1, arr2)) {
                System.out.println("=============quick sort error");
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr1));
                System.out.println(Arrays.toString(arr2));
            }
        }
        System.out.println("finished");
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            // [ )
            int index = L + (int) (Math.random() * (R - L + 1));
            int tmp = arr[R];
            arr[R] = arr[index];
            arr[index] = tmp;
//            int[] p = partition3(arr, L, R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }


    private static int[] partition(int[] arr, int start, int end) {
        int left = start - 1, right = end + 1, i = start, tmp, num = arr[end];
        while (i < right) {
            if (arr[i] < num) {
                ++left;
                tmp = arr[left];
                arr[left] = arr[i];
                arr[i] = tmp;
                i++;
            } else if (arr[i] == num) {
                i++;
            } else if (arr[i] > num) {
                --right;
                tmp = arr[right];
                arr[right] = arr[i];
                arr[i] = tmp;
            }
        }
        return new int[]{left + 1, right - 1};
    }

    private static int[] partition3(int[] arr, int start, int end) {
        int less = start - 1;
        int more = end;
        int tmp;
        while (start < more) {
            if (arr[start] < arr[end]) {
                ++less;
                tmp = arr[less];
                arr[less] = arr[start];
                arr[start] = tmp;
                start++;
            } else if (arr[start] > arr[end]) {
                --more;
                tmp = arr[more];
                arr[more] = arr[start];
                arr[start] = tmp;
            } else {
                start++;
            }
        }
        tmp = arr[more];
        arr[more] = arr[end];
        arr[end] = tmp;
        return new int[]{less + 1, more};
    }
}
