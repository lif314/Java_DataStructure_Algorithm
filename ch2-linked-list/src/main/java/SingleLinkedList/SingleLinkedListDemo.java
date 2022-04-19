package SingleLinkedList;

/**
 * 需求：- 使用带head节点的单向链表的实现—水浒传英雄**排行榜管理**
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 创建节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 插入
        singleLinkedList.add(heroNode1);
        singleLinkedList.add(heroNode2);
        singleLinkedList.add(heroNode3);
        singleLinkedList.add(heroNode4);
        // 显示
        singleLinkedList.list();
    }
}


// 定义SingleLinkedList管理英雄榜
class SingleLinkedList{
    // 头节点 -- 不能动， 不存储具体的数据
    HeroNode headNode = new HeroNode(0, "", "");

    // 尾插入法: 不考虑顺序插入
    public void add(HeroNode node){
        // 找到最后的节点，将next域指向新节点
        // 因为头节点不能动，因此使用一个变量辅助遍历
        HeroNode temp = headNode;
        // 遍历链表，找到最后
        while (true){
            // 找到最后的节点
            if(temp.next == null){
                break;
            }
            // 向后移动
            temp = temp.next;
        }

        // 此时temp就是最后一个节点
        temp.next = node;
    }

    // 遍历链表
    public void list(){
        // 判断链表是否为空
        if(headNode.next == null){
            System.out.println("链表为空");
        }

        // 因为头节点不能动，因此使用一个变量辅助遍历
        HeroNode tmp = headNode;
        while (tmp.next != null) {
            // 输出节点信息
            tmp = tmp.next;
            System.out.println(tmp.toString());

        }
    }
}


// 定义Node,每个Hero对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    // Java中没有指针，都是引用对象
    public HeroNode  next;

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
