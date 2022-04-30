package calculator;


import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * 使用栈实现简易计算器: 中缀表达式
 *
 * 1、栈的使用
 *      - 数栈：存放数据 numStack
 *      - 符号栈：存放运算符 opStack
 *
 * 2、思路
 *      - 通过index(索引)扫描字符串，如果该值是数字，则加到数栈中
 *      - 如果是运算符，则：
 *          - 如果当前符号栈为空，则直接入栈
 *          - 如果符号栈不为空，则比较符号的优先级
 *          【后面pop的数 op 前面pop的数】
 *              - 如果当前的操作符小于或者等于栈中的操作符，则需要从数栈中pop两个数，在符号栈中
 *               pop出一个符号进行运算，将得到的结果入数栈后，将当前的操作符入符号栈
 *              - 如果当前操作符大于符号栈中，则直接入符号栈
 *      - 当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数字和操作符进行计算
 *      - 最后在栈中只剩下一个数字，就是表达式的结果
 */
public class CalculatorDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入计算式(*,+,/,-): ");
        String str = scanner.nextLine();
        Calculator calculator = new Calculator(str);
        System.out.println("计算结果：" + calculator.getRes());
    }
}


// 计算器
class Calculator{

    private String expression;  // 计算的串

    // 数栈
    ArrayStack<Integer> numStack = new ArrayStack<Integer>(Integer.class, 20);

    // 符号栈
   ArrayStack<Character>  operStack = new ArrayStack<Character>(Character.class, 20);

    public Calculator(String expression){
        this.expression = expression;
    }

    // 判断是否为操作符
    private boolean isOper(char op){
        // 假设只存在 + - * / 四种运算符
        return op == '/' || op == '*' || op == '+' || op == '-';
    }

    //比较优先级：优先级有程序员自动指定
    private int priority(char op){
        if(op == '/' || op == '*'){
            return 1;
        }else if(op == '+' || op == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    // 简单运算
    private int cal(int num1, int num2, char op){
        int res = 0;
        switch (op){
            case '*': res = num2 * num1; break;
            case '/': res = num2 / num1; break;
            case '+': res = num2 + num1; break;
            case '-': res = num2 - num1; break;
        }
        return res;
    }

    /**
     * 获取运算结果
     * @return 运算结果
     */
    public int getRes(){
        // 两个操作数
        int num1 = 0;
        int num2 = 0;

        // 一个操作符
        char op = ' ';

        String keepNum = ""; // 处理多位数

        // 计算结果
        int res = 0;

        // index: 索引指针
        for(int index = 0; index < expression.length(); index++){
            // 当前处理到的字符 -- 连续数字的处理方式
            char curChar = expression.charAt(index);
            if(isOper(curChar)){  // 是符号
                //如果符号栈为空，则直接入栈
                if(operStack.isEmpty()){
                    operStack.push(curChar);
                } else { // 栈不为空
                    if(priority(curChar) <= priority(operStack.getTop())){
                        // 如果当前操作符小于等于符号栈顶元素的优先级，则pop两个数和符号栈顶元素
                        // 进行计算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        op = operStack.pop();
                        // 计算
                        res = cal(num1, num2, op);
                        // 将计算结果压入数栈中，同时当前操作符入符号栈
                        numStack.push(res);
                        operStack.push(curChar);
                    }else{
                        // 如果当前操作符优先级大于栈顶优先级，则直接如符号栈
                        operStack.push(curChar);
                    }
                }
            }else{ // 不是符号，是数字
                // 直接压入数栈中,将字符串改为数组压入符号栈中
               //  numStack.push(curChar - '0');

                // 如果是连续的数字，需要向后再看一位
                // 如果下一位是数字，则继续看下一位
                // 如果下一位是符号，则入栈

                // 处理多位数
                keepNum += curChar;
                if(index == expression.length() - 1){
                    // 最后一位，直接入栈
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    // 判断下一个字符是否为数字
                    if(isOper(expression.charAt(index + 1))){
                        // 是字符，数字入栈
                        numStack.push(Integer.parseInt(keepNum));

                        // keepNum 清空
                        keepNum = "";
                    }
                }
            }
        }

        // 扫描完毕，对符号栈进行处理
        // 如果符号栈不为空，则需要依次取出进行计算
        while (!operStack.isEmpty()){
            num1 = numStack.pop();
            num2 = numStack.pop();
            op = operStack.pop();
            // 计算
            res = cal(num1, num2, op);
            // 将计算结果压入数栈中，同时当前操作符入符号栈
            numStack.push(res);
        }

        // 计算结束，数栈中最后一个元素为计算结果
        return numStack.pop();
    }
}



// 数组模拟栈  --- 改为泛型栈
class ArrayStack<T>{
    private int maxSize; /// 栈的大小
    private T[] stack; // 数组

    private int top = -1; // 栈顶


    // 构造器
    public ArrayStack(Class<T> type, int maxSize){
        this.maxSize = maxSize;
        stack = (T[]) Array.newInstance(type, maxSize);
    }

    // 沾满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈
    public void push(T value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }

        top++;
        stack[top] = value;
    }
    // 出栈
    public T pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        T value = stack[top];
        top--;
        return value;
    }

    //获取栈顶元素
    public T getTop(){
        return stack[top];
    }

    // 从栈顶遍历栈
    public void list(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        for(int i = top; i >= 0; i--){
            System.out.println(stack[i]);
        }
    }

}







