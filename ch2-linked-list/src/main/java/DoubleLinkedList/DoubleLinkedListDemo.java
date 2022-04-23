package DoubleLinkedList;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

    }
}



// 双向链表
class DoubleLinkedList{

    // 头节点
    HeroNode headNode =  new HeroNode(0, "", "");

    // 获取头节点
    public HeroNode getHeadNode(){
        return this.headNode;
    }







}



// 节点信息
class HeroNode{
    int no;
    String name;
    String nickname;

    HeroNode pre;  //  前指针
    HeroNode next; // 后指针

    // 构造器
    public HeroNode(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
}




