package arraystack;


/**
 * 使用数组模拟栈的实现
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);

        System.out.println("入栈==");
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.list();

        System.out.println("出栈");
        System.out.println("出栈元素：" + arrayStack.pop());
        System.out.println("栈空间===");
        arrayStack.list();

    }
}


// 数组模拟栈
class ArrayStack{
    private int maxSize; /// 栈的大小
    private int[] stack; // 数组

    private int top = -1; // 栈顶

    public ArrayStack(int maxSize){
        // 初始化
        this.maxSize = maxSize;
        stack = new int[maxSize];
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
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }

        top++;
        stack[top] = value;
    }
    // 出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
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
