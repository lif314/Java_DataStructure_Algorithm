package queue.arrayqueue;

import java.util.Scanner;

/**
 * 使用数组模拟环形队列
 */
public class CircleQueueMain {
    public static void main(String[] args) {
        System.out.println("===测试ArrayQueue===");
        // 队列有效数据个数为maxSize - 1
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        char key = ' '; // 接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看对头数据");
            System.out.println("p(print): 打印队列空间");


            // 接受一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleArrayQueue.showQueue();
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    System.out.println("===程序退出===");
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int num = scanner.nextInt();
                    circleArrayQueue.addQueue(num);
                    break;
                case 'p':
                    circleArrayQueue.printQueue();
                    break;
                case 'g':
                    // 取数数据，会出现异常
                    try {
                        int queue = circleArrayQueue.getQueue();
                        System.out.println("取出的数据是：" + queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int headQueue = circleArrayQueue.headQueue();
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



/**
 * 数组模拟环形队列
 */
class CircleArrayQueue{
    private int maxSize; // 表示数组的最大容量

    private int front;  // 队列头，指向队首元素

    private  int rear;  // 队列尾，指向队尾后一个元素

    private int[] arr;  // 该数组用于存放数据，模拟环形队列

    // 创建队列的构造器
    public CircleArrayQueue(int maxSize){
        // 初始化工作
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    // 判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
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

        arr[rear] = n; // 存入队列之中
        // 必须考虑取模
        rear = (rear + 1) % maxSize;  // 尾指针rear向后移动
    }

    // 从队列中获取数据
    public int getQueue(){
        // 从front取出数据
        if(isEmpty()){
            // 通过抛出异常进行处理
            throw new RuntimeException("队列已空，不能取出数据");
        }

        int val = arr[front];  // front指向队列头部的第一个元素
        // front后移, 考虑取模
        front = (front + 1) % maxSize;
        return val;
    }

    // 显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列已空，没有数据");
            return;
        }

        // 从front开始遍历
        for(int i = front; i < front + size() ; i++){
            // 逐一环形 i % maxSize
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 打印队列
    public void printQueue(){
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }


    // 求出环形队列的有效数据
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列头部数据，不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列已空，没有数据");
        }
        return arr[front]; // // front指向队列头部的第一个数据
    }
}
