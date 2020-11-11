package com.alicms.example.demo.demo.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <p>
 * 快速排序案例
 *
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/10 15:42
 */
public class QuickSortDemo {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(4, 2, 6, 8, 5, 9);
//        List<Integer> list = intStream.boxed().sorted().collect(Collectors.toList());
        int[] list = intStream.toArray();
        quickSort(list, 0, list.length - 1);
        System.out.println(Arrays.toString(list));
    }

    /**
     * 快速排序
     *
     * @param arr   排序的数组
     * @param left  数组的前针
     * @param right 数组后针
     */
    private static void quickSort(int[] arr, int left, int right) {
        //如果left等于right，即数组只有一个元素，直接返回
        if (left >= right) {
            return;
        }
        //设置最左边的元素为基准值
        int key = arr[left];
        //数组中比key小的放在左边，比key大的放在右边，key值下标为i
        int i = left;
        int j = right;
        while (i < j) {
            //j向左移，直到遇到比key小的值
            while (arr[j] >= key && i < j) {
                j--;
            }
            //i向右移，直到遇到比key大的值
            while (arr[i] <= key && i < j) {
                i++;
            }
            //i和j指向的元素交换
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = key;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    public static void quickSort2(int[] arr, int start, int end) {

        //当开始位置小于结束位置时（数组有数据）  进行排序  也就是递归入口
        if (start < end) {
            //首先找到基准数  作为比较的标准数  取数组开始位置   从哪里开始  用哪个数当标准数 这个就是标准数
            int stard = arr[start];
            //记录需要进行排序的下标
            int low = start;
            int high = end;
            //循环比对比标准数大和小的数字
            while (low < high) {
                //如果标准数小于右边的数字  把右边的游标卡尺向左移动
                while (low < high && stard <= arr[high]) {
                    high--;
                }
                //如果标准数大于 右边的数字
                //用低位数字替换右边数字
                arr[low] = arr[high];
                //如果左边的数字比标准数小
                while (low < high && arr[low] <= stard) {
                    low++;
                }
                //如果左边的数字比标准数大
                //用左边数字替换右边数字
                arr[high] = arr[low];
            }
            //把标准数赋给高或者低所在的元素
            arr[low] = stard;
            //处理所有比标准数小的数字
            quickSort2(arr, start, low);
            //处理所有比标准数大的数字
            quickSort2(arr, low + 1, end);
        }
    }

}
