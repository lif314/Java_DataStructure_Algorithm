package DoubleLinkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        // 添加
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        System.out.println("创建测试=====");
        doubleLinkedList.list();

        // 修改
        HeroNode heroNode5 = new HeroNode(4, "修改林冲", "修改豹子头");
        doubleLinkedList.update(heroNode5);
        System.out.println("更新测试=====");
        doubleLinkedList.list();

        // 删除
        System.out.println("删除测试====");
        doubleLinkedList.delete(4);
        doubleLinkedList.list();
    }
}
// TODO 顺序添加

// 双向链表: 并不是循环双向链表
class DoubleLinkedList {
    // 头节点：带表头节点
    HeroNode headNode = new HeroNode(0, "", "");

    // 获取头节点
    public HeroNode getHeadNode() {
        return this.headNode;
    }

    // 遍历双向链表
    public void list() {
        if (headNode == null || headNode.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 遍历链表可以前向遍历，也可以后向遍历
        HeroNode temp = headNode.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }


    // 插入新的节点
    public void add(HeroNode newNode) {
        HeroNode temp = headNode;

        while (temp.next != null) {
            temp = temp.next;
        }

        // 插入一个新的节点
        temp.next = newNode;
        newNode.pre = temp;
    }


    // 更新节点的内容
    public void update(HeroNode updateNode) {
        if (headNode == null || headNode.next == null) {
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = null;
        boolean flag = false;
        for (temp = headNode.next; temp != null; temp = temp.next) {
            if (temp.no == updateNode.no) {
                // 进行更新
                temp.name = updateNode.name;
                temp.nickname = updateNode.nickname;
                flag = true;
                break;
            }
        }

        if (!flag) {
            System.out.println("未找到给节点，更新失败！");
        }
    }

    // 删除节点：双向链表，可以直接找到要删除的节点
    // temp.pre.next = temp.next;
    // temp.next.pre = temp.pre;
    public void delete(int no) {
        if (headNode == null || headNode.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 可以直接删除
        HeroNode temp = headNode.next;
        while (temp != null) {
            if (temp.no == no) {
                // 删除节点
                temp.pre.next = temp.next;

                // 如果删除的最后节点，就不需要执行下面
                if (temp.next != null) {
                    // 防止空指针异常
                    temp.next.pre = temp.pre;
                }

            }

            temp = temp.next;
        }

    }
}


// 节点信息
class HeroNode {
    int no;
    String name;
    String nickname;

    HeroNode pre;  //  前指针
    HeroNode next; // 后指针

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}




