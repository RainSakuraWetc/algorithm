package com.wetc.datastructures.sort;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/13 14:04
 * @Desc 选择排序算法
 */
public class SelectSort {

    public static void main(String[] args) {

    }

    /**
     * 选择排序，每次都选从选择数组中选取最小值和选择数组的最初值进行交换，选择数组即为
     * 内层循环数组，每次循环之后数组阈值减1
     * @param arr
     */
    public static void selectSort(int[] arr) {

        //分析选择排序的流程：可以看到只是内层循环的次数减少了，和冒泡不同的是初始的大小改变，而
        //冒泡的是最终大小改变。并且外层需要循环arr -1 次
        //比如5趟排序，那么外层的大循环就是 i = 4次，内存循环的初始大小改为i+1
        //选择排序时间复杂度是 O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { // 说明假定的最小值，并不是最小
                    min = arr[j]; // 重置min
                    minIndex = j; // 重置minIndex
                }
            }

            // 将最小值，放在arr[0], 即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

            //System.out.println("第"+(i+1)+"轮后~~");
            //System.out.println(Arrays.toString(arr));// 1, 34, 119, 101
        }

        /*//第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length;  j++) {
            if (min > arr[j]) {
                min = arr[j];
                minIndex = j;
            }
        }
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        //System.out.println("第"+(i+1)+"轮后~~");
        //System.out.println(Arrays.toString(arr));

        //第一轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length;  j++) {
            if (min > arr[j]) {
                min = arr[j];
                minIndex = j;
            }
        }
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }


        //System.out.println("第"+(i+1)+"轮后~~");
        //System.out.println(Arrays.toString(arr));*/

    }
}
