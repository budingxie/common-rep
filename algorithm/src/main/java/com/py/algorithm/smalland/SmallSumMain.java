package com.py.algorithm.smalland;

import com.py.algorithm.logarithmic.LogarithmicUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * description：
 * 小和问题
 * 在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组的小和。
 * <p>
 * <p>
 * 逆序对问题
 * 给一列数a1,a2,…,an，求它的逆序对数，即有多少个有序对(i,j)，使得 i < j 但 ai > aj。
 * <p>
 * <p>
 * 荷兰国旗问题
 * 给定一个数组，一个数num，把数组中小于num放在左边，等于num放在中间，大于num的放在右边；最后返回等于区域的左右下标。
 * <p>
 * <p>
 * 小和解题思路：在归并数组的过程中，进行求小和。时间复杂度：n * log2(n)--n 乘 log以2为底 n 的对数
 * 逆序对解题思路：在归并数组的过程中，进行求逆序对。时间复杂度：n * log2(n)--n 乘 log以2为底 n 的对数
 * 荷兰国旗解题思路：定义左右指针，-1，length。然后遍历数组，左右区域向中间靠拢。时间复杂度：O(n)
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/11/25
 */
public class SmallSumMain {

    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 5000; i++) {
            int[] arr = LogarithmicUtil.generateRandomArray(10, 100 + i);
            int[] arr1 = LogarithmicUtil.copyArray(arr);
            int[] arr2 = LogarithmicUtil.copyArray(arr);
            int[] arr3 = LogarithmicUtil.copyArray(arr);
            int[] arr4 = LogarithmicUtil.copyArray(arr);
            int[] arr5 = LogarithmicUtil.copyArray(arr);
            int[] arr6 = LogarithmicUtil.copyArray(arr);
            // 小和问题
            int sum = smallSum(arr1);
            int absolute = LogarithmicUtil.smallSum(arr2);
            if (sum != absolute) {
                System.out.println("sum not equal absolute =========error");
                System.out.println(Arrays.toString(arr));
            }
            // 逆序对问题
            int reverseSum = reverseSum(arr3);
            int absoluteReverseSum = LogarithmicUtil.reverseSum(arr4);
            if (reverseSum != absoluteReverseSum) {
                System.out.println("reverseSum not equal absoluteReverseSum =========error");
                System.out.println(Arrays.toString(arr));
            }
            // 荷兰国旗问题
            int num = random.nextInt();
            int[] du1 = LogarithmicUtil.dutchFlag(arr5, num);
            int[] du2 = dutchFlag(arr6, num);
            if (!LogarithmicUtil.isEqual(du1, du2)) {
                System.out.println("dutchFlag =========error");
                System.out.println("num: " + num);
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(du1));
                System.out.println(Arrays.toString(du2));
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
        if (arr == null) {
            return 0;
        }
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
        if (arr == null) {
            return 0;
        }
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
                // start, 归并加速求逆序对
                // left, mid; mid+1,right
                int i = 0;
                int p1 = left;
                int p2 = mid + 1;
                while (p1 <= mid && p2 <= right) {
                    if (arr[p1] <= arr[p2]) {
                        tmp[i++] = arr[p1++];
                    } else {
                        sum = sum + (mid - p1 + 1);
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

    public static int[] dutchFlag(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int left = -1, right = arr.length, i = 0, tmp;
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
}
