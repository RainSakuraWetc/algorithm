package com.wetc.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wentao.xie
 * @version 1.0
 * @date 2020/8/7 10:44
 * @Desc 逆波兰表达式(后缀表达式)
 */
public class PolandNotation {
    public static void main(String[] args) {

        /*String suffixExpression = "3 4 + 5 * 6 -";
        List<String> list = getListString(suffixExpression);

        int res = calculate(list);
        System.out.println("计算的结果是=" + res);*/

        /**
         * 后缀表达式比较于中缀表达，计算逻辑更为简便
         *      1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
         *      2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
         * 		    即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
         * 		3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
         * 		    即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
         */
        String expression = "1+((2+3)*4)-5";//注意表达式
        List<String> list = toInfixExpressionList(expression);

        List<String> list1 = parseSuffixExpreesionList(list);

        System.out.println(list1);


    }

    //方法：将 中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {

        //定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，我需要加入到ls
            if((c=s.charAt(i)) < 48 ||  (c=s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else { //如果是一个数，需要考虑多位数
                str = ""; //先将str 置成"" '0'[48]->'9'[57]
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;//返回
    }

    /**
     * 中缀表达式转后缀表达式
     * 思路：(前人总结：)
     *  1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     *  2) 从左至右扫描中缀表达式；
     *  3) 遇到操作数时，将其压s2；
     *  4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
     *      1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *      2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     *      3.否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
     *  5) 遇到括号时：  (1) 如果是左括号“(”，则直接压入s1 (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     *  6) 重复步骤2至5，直到表达式的最右边
     *  7) 将s1中剩余的运算符依次弹出并压入s2
     *  8) 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpreesionList(List<String> ls) {

        Stack<String> s1 = new Stack<String>();//符号栈
        List<String> s2 = new ArrayList<String>();//储存中间结果

        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //遇到括号时：如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//消除一对小括号
            } else {
                //运算符处理
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较

                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        while (s1.size()>0) {
            s2.add(s1.pop());
        }
        return s2;
    }
    //将后缀表达式，依次将数据和运算符放入到list中
    public static List<String> getListString(String suffixExpression) {
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele: split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 逆波兰表达式的计算
     *      1)从左至右扫描，将3和4压入堆栈；
     * 		2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 		3)将5入栈；
     * 		4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 		5)将6入栈；
     * 		6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<String>();

        for (String item : ls) {
            if (item.matches("\\d+")) { //匹配多为数
                stack.push(item);
            } else {
                // pop出两个数，并运算， 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res 入栈
                stack.push(String.valueOf(res));

            }
        }
        return Integer.parseInt(stack.pop());
    }

}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}