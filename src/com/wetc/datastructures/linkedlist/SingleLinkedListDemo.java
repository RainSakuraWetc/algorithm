package com.wetc.datastructures.linkedlist;

import java.util.Stack;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/4 17:07
 * @Desc 单链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

    }

    /**
     * 面试题1：求单链表的有效节点的个数
     */
    public static int getLength(HeroNode head) {

        if(head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

    /**
     * 面试题2：查找单链表中的倒数第k个结点
     * 思路：
     *    1. 编写一个方法，接收head节点，同时接收一个index
     * 	  2. index 表示是倒数第index个节点
     * 	  3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 	  4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     * 	  5. 如果找到了，则返回该节点，否则返回nulll
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if(head.next == null) {
            return null;
        }

        int size = getLength(head);

        if(index <=0 || index > size) {
            return null;
        }

        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 面试题3：链表反转
     * 思路：头插法
     *  1.定义一个新节点
     *  2.从头到尾遍历，每遍历一个节点，就取出放在新链表的前端(新节点的后面)
     *  3.将原来链表头指向：head.next = 新节点.next
     */
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null) {
            return ;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原来的链表
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur 连接到新的链表上
            cur = next;//让cur后移
        }

        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 面试题4：从尾到头打印链表
     * 思路：
     *  方式1，将链表反转再进行遍历，但是会破坏链表原有的结构
     *  方式2，利用栈这个数据结构，将各节点压入栈种，利用先进后出的原则，实现了逆序打印
     */
    public static void reversePrint(HeroNode head) {
        if(head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while(cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }
}

class SingleLinkedList {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 	1. 找到当前链表的最后节点
     * 	2. 将最后这个节点的next 指向 新的节点
     */
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;

        //遍历链表，找到最终节点
        while (true) {
            //找到链表的最后
            if(temp.next == null) {
                break;
            }

            //如果没有找到最后，则将temp后移
            temp = temp.next;
        }

        //退出while循环之后，即找到了最后节点
        temp.next = heroNode;
    }

    /**
     * 第二种添加方式，根据指定方式进行添加(即不一定添加到链表的尾部)
     * 思路，
     *  1. 找到新添加的节点位置，
     *  2. 新节点.next = temp.next;
     *  3. temp.next = 新节点
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false
        while (true) {
            if(temp.next == null) {//说明temp已经在链表的最后
                break; //
            }

            if(temp.next.no > heroNode.no) {
                break; //位置找到，就再temp的后面进行插入
            }else if (temp.next.no == heroNode.no) {
                flag = true;//说明编号已经存在
                break;
            }

            temp = temp.next; //后移，遍历当前链表
        }

        if(flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if(head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while(true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if(temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if(flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * 思路
     *  1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     *  2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除节点的
        while(true) {
            if(temp.next == null) { //已经到链表的最后
                break;
            }
            if(temp.next.no == no) {
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }
        //判断flag
        if(flag) { //找到
            //可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
