package com.wetc.datastructures.stack;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/5 14:12
 * @Desc 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

    }
}

/**
 * 思路分析：
 *  1.定义top表示栈顶，初始化为-1
 *  2.入栈操作，top++ ,stack[top] = value;
 *  3.出栈，value = stack[top];top--;
 */

class ArrayStack {
    private int maxSize;//  栈的大小
    private int[] stack;//  数组模拟栈，数据放在数组中
    private int top = -1;// top表示栈底，初始化表示为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if(isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈-pop, 将栈顶的数据返回
    public int pop() {
        //先判断栈是否空
        if(isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }
}

