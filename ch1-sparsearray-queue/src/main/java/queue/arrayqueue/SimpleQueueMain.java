package queue.arrayqueue;

import java.util.Scanner;

/**
 * 使用数组模拟简单队列
 *  1、编写ArrayQueue类
 */
public class SimpleQueueMain {

    public static void main(String[] args) {
        System.out.println("===测试ArrayQueue===");

        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' '; // 接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看对头数据");


            // 接受一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    System.out.println("===程序退出===");
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int num = scanner.nextInt();
                    arrayQueue.addQueue(num);
                    break;
                case 'g':
                    // 取数数据，会出现异常
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.println("取出的数据是：" + queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = arrayQueue.headQueue();
                        System.out.println("查看队头数据：" + headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }

    }
}


class ArrayQueue{
    private int maxSize; // 表示数组的最大容量

    private int front;  // 队列头

    private  int rear;  // 队列尾

    private int[] arr;  // 该数组用于存放数据，模拟队列

    // 创建队列的构造器
    public ArrayQueue(int maxSize){
        // 初始化工作
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;  // 指向队列头部【数据的前一个位置】，并不包含数据，
        rear = -1;   // 指向队列尾部具体数据【就是队列最后一个数据】
    }

    // 判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能加入");
            return;
        }

        rear++;  // 尾指针rear向后移动
        arr[rear] = n; // 存入队列之中
    }

    // 从队列中获取数据
    public int getQueue(){
        // 从front取出数据
        if(isEmpty()){
            // 通过抛出异常进行处理
            throw new RuntimeException("队列已空，不能取出数据");
        }

        front++;  // front后移 -1 --> 0
        return arr[front];  // front指向队列头部的前一个数据
    }

    // 显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列已空，没有数据");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列头部数据，不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列已空，没有数据");
        }
        // front本身指向不改变
        return arr[front + 1]; // // front指向队列头部的前一个数据
    }
}



