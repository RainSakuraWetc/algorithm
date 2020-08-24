package com.wetc.datastructures.sort;

import java.util.Arrays;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/13 15:03
 * @Desc 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        shellSort2(arr);
    }

    //希尔排序时， 对有序序列在插入时采用交换法
    public static void shellSort(int[] arr) {

        //分析排序流程：
        //每轮排序都进行分组，分组进行排序，每次组内进行交换排序，
        //每次循环都缩小增量，尽量让数值小的数排在前面
        int temp = 0;
        int count = 0;
        for (int gap = arr.length/2; gap >0 ; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j +gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }

        /*int temp = 0;
        int count = 0;
        //第一轮排序，将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j +5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        //第二轮排序，将10个数据分成5/2 = 2组
        for (int i = 2; i < arr.length; i++) {

            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j +2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }*/

    }

    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {
        // 增量gap, 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }

            }
        }
    }
}
