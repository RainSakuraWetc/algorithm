package com.wetc.datastructures.sort;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/13 14:31
 * @Desc 插入排序算法
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 59, 1};
        insertSort(arr);

    }


    public static void insertSort(int[] arr) {

        //分析插入流程：
        //首选选择第二个数，和第一个数比较，如果比它小就进行交换
        //整体看，选择比较的数和前面相邻的数进行比较，循环找到最小的位置进行插入
        //可以看出，比较的次数是arr-1，初始比较的是arr[1]

        int insertVal = 0;
        int insertIndex = 0;
        for(int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1; // 即arr[1]的前面这个数的下标

            // 给insertVal 找到插入的位置
            // 说明
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将 arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];// arr[insertIndex]
                insertIndex--;
            }
            // 当退出while循环时，说明插入的位置找到, insertIndex + 1
            if(insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

            //System.out.println("第"+i+"轮插入");
            //System.out.println(Arrays.toString(arr));
        }

        /*//定义待插入的值
        int insertValue = arr[1];
        int insertIndex = 1 - 1;

        //给inserValue找到插入位置
        // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
        // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        // 3. 就需要将 arr[insertIndex] 后移
        while (insertIndex > 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex] = arr[insertIndex+1];
            insertIndex--;
        }

        // 当退出while循环时，说明插入的位置找到, insertIndex + 1
        arr[insertIndex + 1] = insertValue;*/


    }
}
