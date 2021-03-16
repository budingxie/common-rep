package com.py.algorithm.sort;

import com.py.algorithm.logarithmic.LogarithmicUtil;

import java.util.Arrays;

/**
 * description
 *
 * @author budingxie
 * @version 1.0.0
 * @date 2021/3/15
 */
public class BaseSort {

    public static void main(String[] args) {
        BaseSort baseSort = new BaseSort();
        for (int i = 0; i < 5000; i++) {
            // 生成数组
            int[] array = LogarithmicUtil.generateRandomArray(10, 10);
            int[] arr = LogarithmicUtil.copyArray(array);
            int[] arr0 = LogarithmicUtil.copyArray(array);
            int[] arr1 = LogarithmicUtil.copyArray(array);
            int[] arr2 = LogarithmicUtil.copyArray(array);
            int[] arr3 = LogarithmicUtil.copyArray(array);
            int[] arr4 = LogarithmicUtil.copyArray(array);
            int[] arr5 = LogarithmicUtil.copyArray(array);

            // 排序
            LogarithmicUtil.comparator(arr);
            baseSort.bubbleSort(arr0);
            baseSort.selectSort(arr1);
            baseSort.insertSort(arr2);
            baseSort.binaryInsertSort(arr3);
            baseSort.shellSort(arr4);
            baseSort.quickSort(arr5);

            // 对比
            if (!LogarithmicUtil.isEqual(arr, arr0)) {
                System.out.println("arr0 not equal arr =========error");
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr0));
                break;
            }
            if (!LogarithmicUtil.isEqual(arr, arr1)) {
                System.out.println("arr1 not equal arr =========error");
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr1));
                break;
            }
            if (!LogarithmicUtil.isEqual(arr, arr2)) {
                System.out.println("arr2 not equal arr =========error");
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr2));
                break;
            }
            if (!LogarithmicUtil.isEqual(arr, arr3)) {
                System.out.println("arr3 not equal arr =========error");
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr3));
                break;
            }

            if (!LogarithmicUtil.isEqual(arr, arr4)) {
                System.out.println("arr3 not equal arr =========error");
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr3));
                break;
            }
            if (!LogarithmicUtil.isEqual(arr, arr5)) {
                System.out.println("arr3 not equal arr =========error");
                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(arr3));
                break;
            }
        }
        int[] a1 = {1, 2, 4, 5, 0, 0, 0, 0, 0, 0};
        int[] a2 = {3, 6, 7, 8};

        merge(a1, 4, a2, 4);
        System.out.println(Arrays.toString(a1));
        System.out.println("finished");
    }

    /**
     * 冒泡排序
     * 1.两两比较，如果后者比前者大则交换位置
     * 2.每遍历一圈最大的数就会冒到最后，则确定了本轮比较中的最大值放到最后不动
     * 3.循环1、2直至遍历完所有
     *
     * @param array 数组
     */
    private void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 1.找到所有数中最大值下标
     * 2.找到最大值的下标与最后一个位置的数值交换位置，这样每次找到的最大值则固定到最后
     * 3.循环1、2操作直至遍历找到所有
     *
     * @param array 数组
     */
    private void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // 默认下标为0
            int maxIndex = 0;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[maxIndex]) {
                    // 找到比自己大的下标
                    maxIndex = j;
                }
            }
            // 将找到最大的数与最后一个数字交换位置
            int temp = array[array.length - i - 1];
            array[array.length - i - 1] = array[maxIndex];
            array[maxIndex] = temp;
        }
    }

    /**
     * 插入排序
     * 1.从位置1的数值n开始，将前面已经遍历过的数值集合看成数组m，则将n往m中插入
     * 2.n插入到集合m中时从后往前比较，如果比n大则往后移一位，如果比较到比n小，则当前位置就是插入n的位置
     * 3.通过1、2的操作则可以保证每次插入n后m的集合都是排好的序列
     * 4.循环1、2、3操作将集合中所有数值均插入一遍即排序完成
     *
     * @param array 数组
     */
    private void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            // 在前面已近遍历过的数字中比较 若小于则往后移
            for (j = i - 1; j >= 0; j--) {
                if (temp < array[j]) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    /**
     * 二分排序 O(nlogn)
     * 1.从位置1的数值为n，将前面已经遍历过的数值集合看成数组m，则将n往m中插入
     * 2.n插入到集合m中时采用二分法，先比较m中中间的数值，如果比n大则继续比较后面一半集合的中间的数值，
     * 直至比较到拆分的集合中左边一半或者右边一半没有值为止，则当前中间值的位置即为n插入到m中的位置
     * 3.通过1、2的操作则可以保证每次插入n后m的集合都是排好的序列
     * 4.循环1、2、3操作将集合中所有数值均插入一遍即排序完成
     *
     * @param array 数组
     */
    private void binaryInsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int left = 0, right = i - 1;
            int mid;
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 将遍历到比他大的数字全部往后移一位
            for (int j = i - 1; j >= left; j--) {
                array[j + 1] = array[j];
            }
            array[left] = temp;
        }
    }


    /**
     * 希尔排序 O(nlogn)
     * 1.定义一个增量m，比较的数字集合总数为n，则将集合分成n/m组，每组进行插入排序
     * 2.随后m递减，多次比较之后就可得出排序后的集合
     *
     * @param array 数组
     */
    private void shellSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int m = array.length;
        while (true) {
            // 本次怎了的变化方式为 m/2
            m = m / 2;
            // 分组后的数组下标为n/m的 模
            for (int i = 0; i < m; i++) {
                for (int j = i + m; j < array.length; j += m) {
                    // 每组内部进行插入排序（此处使用直接插入排序方式，也可使用二分法插入）
                    int temp = array[j];
                    int k;
                    // 在前面已经遍历过的数字中比较若小于则往后移
                    for (k = j - m; k >= i; k -= m) {
                        if (temp < array[k]) {
                            array[k + m] = array[k];
                        } else {
                            break;
                        }
                    }
                    array[k + m] = temp;
                }
            }
            if (m == 1) {
                break;
            }
        }
    }

    /**
     * 快速排序 O(nlogn)
     * 1.快速排序的思想主要是先设置一个基准点m，这里我们假设每次设置的基准点都是每一组的第一个数值
     * 2.拿着基准点m在集合中进行比较，找到它应该放置的位置
     * 3.比较方式主要是定义集合中最左边的下标left，最右边的下标right，从左边开始比较，比m小则left++，找到比m大的则停住，
     * 将left下标的值赋值成right下标的值，然后同理比较right，比m大的则right–，找到比m小的就赋值成left下标的值。当left==right之后则比较完成
     * 4.经过步骤3的比较之后则可以找到m点排序所在的位置，然后集合被分成前后两半，各自按照1、2、3的方式排序，递归至全部拆分比较完成后即排序完成
     *
     * @param array 数组
     */
    private void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(0, array.length - 1, array);
    }

    private void quickSort(int low, int high, int[] array) {
        if (low >= high) {
            return;
        }
        int mid = getMiddle(low, high, array);
        quickSort(low, mid - 1, array);
        quickSort(mid + 1, high, array);
    }

    private int getMiddle(int low, int high, int[] array) {
        int temp = array[low];
        while (low < high) {
            while (low < high && array[high] >= temp) {
                high--;
            }
            array[low] = array[high];

            while (low < high && array[low] <= temp) {
                low++;
            }
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }


    /**
     * 快排
     *
     * @param array
     */
    private static void print(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int left = 0, right = array.length - 1;
        sort(left, right, array);
    }

    private static void sort(int left, int right, int[] array) {
        if (left < right) {
            int[] p = partition(array, left, right);
            sort(left, p[0] - 1, array);
            sort(p[1] + 1, right, array);
        }
    }

    public static int[] partition(int[] arr, int left, int right) {
        int basic = arr[right];
        int less = left - 1;
        int more = right + 1;
        while (left < more) {
            if (arr[left] < basic) {
                swap(arr, ++less, left++);
            } else if (arr[left] > basic) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (m >= 0 && n >= 0) {
            nums1[p--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }

        // 将 Nums2 中 小于 nums1 的元素赋值到 nums1 数组中
        while (n >= 0) {
            nums1[p--] = nums2[n--];
        }
    }

}
