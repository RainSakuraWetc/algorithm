package com.wetc.datastructures.linkedlist;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/5 9:51
 * @Desc 解决约瑟夫问题
 */
public class Josepfu {
    public static void main(String[] args) {

    }
}

/**
 * 问题：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 *      数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，
 *      直到所有人出列为止，由此产生一个出队编号的序列。
 * 思路：1.创建辅助指针helper，指向循环链表的最后
 *      2.报数时，first和helper同时移动m-1次
 *      3.first出圈，first = first.next;
 *                  helper.next = first;
 */

/**
 * 创建一个环形的单向链表
 * 思路：
 *  1.构建单向环形链表
 *      a.先创建一个节点，让first指向该节点形成环形
 *      b.后面每新增一个节点，就把该节点加入到环形中
 *  2.遍历环形链表
 *      a.设置辅助指针，指向first节点
 *      b.遍历该环形链表，到辅助指针.next = first即可结束
 */
class CircleSingleLinkedList {

    // 创建一个first节点,当前没有编号
    private Boy first = null;

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }

        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        for (int i = 1; i < nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                curBoy.setNext(boy);//
                boy.setNext(first);//
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {// 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy后移
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最开始有多少哥小孩再圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建要给辅助指针,帮助完成小孩出圈
        Boy helper = first;
        // 需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }

        //获取初始的出圈位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //循环链表出圈，循环次数为countNum -1
        while (true) {
            if (helper.getNext() == first) {
                break;
            }

            //first 和 helper同时移动countNum-1次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);

        }
    }


}

// 创建一个Boy类，表示一个节点
class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}
