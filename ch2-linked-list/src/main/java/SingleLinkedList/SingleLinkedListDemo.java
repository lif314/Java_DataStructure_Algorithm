package SingleLinkedList;

import java.util.LinkedList;

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
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);

        // 在内存中直接排序
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
        // 显示
        singleLinkedList.list();

        System.out.println("修改2号节点=====");
        HeroNode newNode = new HeroNode(2, "卢俊义==", "玉麒麟==");
        singleLinkedList.update(newNode);
        singleLinkedList.list();

        // 删除节点
        System.out.println("链表删除1号节点====");
        singleLinkedList.detele(1);
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

    // 按序添加新的节点
    public void addByOrder(HeroNode newNode){
        // 因为头节点不能动，需要辅助temp
        // temp位于新插入节点的前一个节点
        HeroNode temp = headNode;

        // 标识该节点是否已经存在
        boolean flag = false;
        while (true){
            if(temp.next == null){
                // 已经到达了链表尾部
                break;
            }else if (temp.next.no > newNode.no){
                // 位置找到，就在temo后谜案插入
                break;
            }else if(temp.no == newNode.no){
                // 该节点已经存在，不需要进行插入
                flag = true;
                break;
            }
            // 后移
            temp = temp.next;
        }

        // 如果已经存在，不能进行插入
        if(flag){
            System.out.println("该节点已经存在！");
        }else {
            // 在temp后面插入节点: 先让新节点指向temp的后一个节点，然后让temp的
            // next域指向新节点
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }


    /**
     * 修改节点信息
     *      根据节点no信息来修改
     */
    public void update(HeroNode newHeroNode){
        // 判断是否为空
        if(headNode.next == null){
            System.out.println("链表为空");
            return;
        }

        // 找到修改节点的位置
        HeroNode temp = headNode;
        while (true){
            if(temp.next == null){
                // 尾部，没有找到该节点
                System.out.printf("没有找到该编号为 %d 节点", newHeroNode.no);
                return;
            }else if(temp.no == newHeroNode.no){
                // 找到需要修改的节点
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
                // 修改成功
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除节点：
     *      被删除的节点只要没有引用，就会被垃圾回收机制自动回收
     */
    public void detele(int no){
        // 找到需要删除节点的前一个节点
        HeroNode temp = headNode;
        // 是否找到待删除节点的前一个节点
        boolean flag = false;

        while (true){
            if(temp.next == null){
                // 遍历结束
                break;
            }else if(temp.next.no == no){
                // 此时temp是待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            // 找到
            temp.next = temp.next.next;
        }else {
            System.out.println("不存在该节点");
        }
    }


    // 遍历链表
    public void list(){
        // 判断链表是否为空
        if(headNode.next == null){
            System.out.println("链表为空");
        }

        // 因为头节点不能动，因此使用一个变量辅助遍历
        HeroNode tmp = headNode.next;
        while (tmp != null) {
            // 输出节点信息
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }
}


// 定义Node,每个Hero对象就是一个节点
class HeroNode{
    public int no;  // 编号
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
