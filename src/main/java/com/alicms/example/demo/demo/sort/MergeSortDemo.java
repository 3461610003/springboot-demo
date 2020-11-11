package com.alicms.example.demo.demo.sort;

import java.util.Arrays;

/**
 * <p>
 * 归并排序demo
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/10 14:49
 */
public class MergeSortDemo {
    public static void main(String[] args) {
        int[] arr = {91, 60, 96, 13, 35, 65, 46, 10, 30, 20, 31, 77, 81, 22};
        merge(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


    //归并
    public static void merge(int[] arr, int low, int high) {
        int center = (high + low) / 2;
        if (low < high) {
            //递归，直到low==high，也就是数组已不能再分了，
            merge(arr, low, center);
            merge(arr, center + 1, high);

            //当数组不能再分，开始归并排序
            mergeSort(arr, low, center, high);
            System.out.println(Arrays.toString(arr));
        }
    }

    //排序
    private static void mergeSort(int[] arr, int low, int center, int high) {
        //用于暂存排序后的数组的临时数组
        int[] tempArr = new int[arr.length];
        int i = low, j = center + 1;

        //临时数组的下标
        int index = 0;

        //循环遍历两个数组的数字，将小的插入到临时数组里
        while (i <= center && j <= high) {
            //左边数组的数小，插入到新数组
            if (arr[i] < arr[j]) {
                tempArr[index] = arr[i];
                i++;
            } else {//右边数组的数小，插入到新数组
                tempArr[index] = arr[j];
                j++;
            }
            index++;
        }

        //处理左半边数组多余的数据，将左半边多余的数据直接追加的临时数组的后面
        while (i <= center) {
            tempArr[index] = arr[i];
            i++;
            index++;
        }

        //处理右半边数组多余的数据，将右半边多余的数据直接追加的临时数组的后面
        while (j <= high) {
            tempArr[index] = arr[j];
            j++;
            index++;
        }
        //将临时数组中的数据重新放进原数组
        if (index >= 0) System.arraycopy(tempArr, 0, arr, low, index);
    }
}
