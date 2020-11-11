package com.alicms.example.demo.demo.sort;

import java.util.Arrays;

/**
 * <p>
 * 堆排序案例
 * 时间复杂度为：O(nlogn)
 * 空间复杂度：O(1)
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/10 14:07
 */
public class HeapSortDemo {

    public static void main(String[] args) {
//        int[] ints = {4, 6, 8, 5, 9};
        int[] ints = {91, 60, 96, 13, 35, 65, 46, 10, 30, 20, 31, 77, 81, 22};
        sort(ints);
        System.out.println("排序后：" + Arrays.toString(ints));
        int[] heapArr = {91, 60, 96, 13, 35, 65, 46, 10, 30, 20, 31, 77, 81, 22};
        heapSort(heapArr);
        System.out.println(Arrays.toString(heapArr));
    }

    public static void sort(int[] arr) {
        int length = arr.length;
        //从最后一个非叶节点开始构建大顶堆
        for (int i = length / 2 - 1; i >= 0; i--) {
            maximumHeap(i, arr, length);
            System.out.println(Arrays.toString(arr));
        }
        //从最小的叶子节点开始与根节点进行交换并重新构建大顶堆
        for (int i = length - 1; i >= 0; i--) {
            System.out.println(Arrays.toString(arr));
            swap(arr, i);
            length--;
            maximumHeap(0, arr, length);
        }
    }

    /**
     * 构建大顶堆
     * @param i 开始下标
     * @param arr 数组
     * @param length 长度
     */
    private static void maximumHeap(int i, int[] arr, int length) {
        int temp = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            //如果右孩子大于做孩子，则指向右孩子
            if (j + 1 < length && arr[j + 1] > arr[j]) {
                j++;
            }
            //如果最大的孩子大于当前节点，则将大孩子赋给当前节点，修改当前节点为其大孩子节点，再向下走。
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        //将temp放到最终位置
        arr[i] = temp;
    }

    /**
     * 交换数组下标i、0的两个数
     *
     * @param arr 数组
     * @param i   数组下标i
     */
    private static void swap(int[] arr, int i) {
        int temp = arr[i];
        arr[i] = arr[0];
        arr[0] = temp;
    }

    /**
     * 创建堆，
     * @param arr 待排序列
     */
    public static void heapSort(int[] arr) {
        //创建堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            System.out.println(Arrays.toString(arr));
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            System.out.println(Arrays.toString(arr));
            //将堆顶元素与末尾元素进行交换
            swap(arr, i);

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整堆
     * @param arr 待排序列
     * @param parent 父节点
     * @param length 待排序列尾元素索引
     */
    private static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= arr[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            arr[parent] = arr[lChild];

            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        arr[parent] = temp;
    }

}
