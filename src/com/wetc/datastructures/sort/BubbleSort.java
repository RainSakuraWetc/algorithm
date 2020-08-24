package com.wetc.datastructures.sort;

import java.util.Arrays;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/13 11:51
 * @Desc 冒泡排序算法
 */
public class BubbleSort {
    public static void main(String[] args) {

        int arr[] = {3, 9, -1, 10, 20};
        bubbleSort(arr);
        //分析冒泡排序的流程：可以看到只是循环的次数减少了，并且外层需要循环
        //比如5趟排序，那么外层的大循环就是 i = 4次，内层循环的大小就是 (- 1 - i),也就是减去外层循环的次数
        //第一次排序，将最大的数放到最后
        /*int arr[] = {3, 9, -1, 10, -2};
        int temp = 0;
        for (int j = 0; j < arr.length -1 - 0; j++) {
            // 如果前面的数比后面的数大，则交换
            if (arr[j] >arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        //第二次排序，将第二大的数放到倒数第二
        for (int j = 0; j < arr.length -1 - 1; j++) {
            // 如果前面的数比后面的数大，则交换
            if (arr[j] >arr[j+1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }*/

    }

    // 将前面额冒泡排序算法，封装成一个方法
    public static void bubbleSort(int arr[]) {
        // 冒泡排序 的时间复杂度 O(n^2), 自己写出
        int temp = 0; // 临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后的数组");
            System.out.println(Arrays.toString(arr));

            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag!!!, 进行下次判断
            }
        }
    }

}
