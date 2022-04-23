package LinkedStack;

/**
 * 链栈
 */
public class LinkedStackDemo {

    public static void main(String[] args) {

        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(1);
        linkedStack.push(3);
        linkedStack.push(5);
        linkedStack.push(12);

        System.out.println("初始栈：");
        linkedStack.list();

        System.out.println("出栈: ");
        int pop = linkedStack.pop();
        System.out.println(pop);

        System.out.println("出栈后栈：");
        linkedStack.list();
    }

}

// 链栈
class LinkedStack{
    // 栈顶指针
    private StackNode top;
    // 栈中节点的个数
    private int size;

    // 初始化栈
    public LinkedStack(){
        this.top = null;
        this.size  =  0;
    }

    // 获取栈顶指针
    public StackNode getTop() {
        return top;
    }

    // 判断栈是否为空
    public  boolean empty(){
        return top == null && size == 0;
    }

    // 入栈操作
    public void push(int data){
        StackNode node = new StackNode(data);

        if(empty()){
            // 如果栈为空
            top = new StackNode();
            top.next = node;
            size++;
        }else{
            // 栈中已经有数据
            node.next = top.next;
            top.next = node;
            size++;
        }
    }

    // 出栈操作
    public int pop(){
        if(empty()){
            System.out.println("栈已空");
            return -1;
        }else{
            StackNode nextNode = top.next;
            int data = nextNode.data;
            top.next = nextNode.next;
            // java中不需要手动清除内存
            // 只要不存在引用关系就会被垃圾回收机制回收
            nextNode.next = null;
            size--;
            return data;
        }
    }

    // 打印栈
    public void list(){
        if(empty()){
            System.out.println("栈已空");
            return;
        }
        StackNode temp = top.next;
        while (temp != null){
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }
}



// 链栈节点
class StackNode{
    int data;
    StackNode next;

    public StackNode(int data){
        this.data = data;
    }

    public StackNode(){

    }
}
