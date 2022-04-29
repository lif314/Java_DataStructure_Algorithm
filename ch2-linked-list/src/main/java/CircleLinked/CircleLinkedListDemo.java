package CircleLinked;

/**
 * 单向环形链表
 *
 * Josephu问题：不带头节点的环形链表
 * n: n个人
 * k： 从k开始报数1 2 3 ... m
 * m：数到m的人出队列
 *
 */

public class CircleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.list();

        // 约瑟夫出队列
        System.out.println("约瑟夫出队列：");
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}


/**
 * 构建单向环形链表【约瑟夫问题】
 * 1、创建第一个节点，使用first指向该节点，并形成环形
 * 2、只有辅助cur指向最后一个节点，然后遍历后插入新的节点
 * 3、newNode.next = first;
 *    cur.next = newNode;
 *    cur = newNode;
 */
class CircleSingleLinkedList{

    // 创建first节点: 不能动
    private Boy first = new Boy(-1);

    // 添加小孩节点
    public void add(int nums){ // 一共nums个节点
        if(nums < 1){
            System.out.println("nums值不正确");
            return;
        }

        // 创建循环链表
        Boy cur = null;   // 辅助指针
        for (int i = 1; i <= nums; i++) {
            Boy newBoy = new Boy(i);
            if(i == 1){
                // 创建第一个节点需要first
                first = newBoy;
                first.setNext(first); // 构成环形
                // 让cur指向当前最后小孩
                cur = first;
            }else{
                // 只与cur有关
                cur.setNext(newBoy);
                newBoy.setNext(first);
                cur = newBoy;
            }

        }
    }

    // 遍历
    public void list(){
        if(first == null){
            System.out.println("链表为空");
            return;
        }

        // 使用辅助指针遍历
        Boy cur = first;
        while (true){
            System.out.println("节点标号：" + cur.getNo());
            if(cur.getNext() == first){
                // 遍历结束
                break;
            }
            cur = cur.getNext();  //后移
        }
    }

    /**
     * 1、使用first标识链表
     * 2、辅助指针helper，指向环形链表的最后节点【first前面一个节点，方便删除节点】
     * 3、让first和helper移动k-1次
     * 4、n,k,m: 当报数时，让first和helper同时移动m-1次
     * 5、把当前first指向的节点删除：first = first.next; helper.next = first;
     */
    public void countBoy(int startNo, int countNum, int nums){
        // 校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("输入数据无效");
            return;
        }

        // 指向 最后一个节点
        Boy helper = first;
        while (helper.getNext() != first){
            helper = helper.getNext();
        }


        // 让first和helper移动k-1次
        for(int j = 0; j < startNo - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }

        // 当报数时，让first和helper同时移动m-1次
        // 循环操作，直到只有一个人
        while (true){
            if(helper == first){
                System.out.println("存活节点:" + first.getNo());
                // 只有一个人
                break;
            }

            for(int i = 0; i < countNum - 1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            // 此时first指向的节点就是出圈的节点
            System.out.println("出圈节点：" + first.getNo());
            // 删除出圈节点
            first = first.getNext();
            helper.setNext(first);
        }
    }
}




// 节点信息
class Boy {
   private int no;

   private Boy next; // 后指针

    // 构造器
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




