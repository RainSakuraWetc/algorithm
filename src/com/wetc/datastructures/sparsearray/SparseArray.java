package com.wetc.datastructures.sparsearray;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/3 14:58
 * @Desc 稀疏数组转二维数组，二维数组转稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {

        /**
         * 二维数组转稀疏数组的思路
         * 1.遍历二维数组，得到有效数据的个数
         * 2.根据sum就可以创建稀疏数组sparesearray,int[sum+1][3]
         *  2.1 列数为3的理解：三列分别表示的是，二维数组的行，列，以及行列对应的值。
         *  2.2 行数为sum+1的理解：sum代表的是有效数据的个数，+1代表的是第一行多存储一行：数组的
         *      行数列数，sum的值(有效数据的个数)。
         * 3.将二维数组的有效数据存储到稀疏数组
         */

        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 将二维数组 转 稀疏数组的思
        // 1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //创建稀疏数组，遍历原始数组，把数组进行填充
        int sparseArr[][] = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0; //count 用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();


        /**
         * 稀疏数组转二维数组的思路
         * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，
         *    比如上面的  chessArr2 = int [11][11]
         * 2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
         */

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];

        }
    }
}
