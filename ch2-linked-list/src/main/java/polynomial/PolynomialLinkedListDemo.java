package polynomial;


/**
 * 使用单链表进行多项式计算
 */

public class PolynomialLinkedListDemo {

    public static void main(String[] args) {

        //  多项式A
        PolynomialLinkedList A = new PolynomialLinkedList();
        ItemNode node1 = new ItemNode(0, 4);
        ItemNode node2 = new ItemNode(3, 6);
        ItemNode node3 = new ItemNode(8, 5);
        ItemNode node4 = new ItemNode(12, 4);
        A.add(node1);
        A.add(node2);
        A.add(node3);
        A.add(node4);
        System.out.print("多项式：A = ");
        A.list();

        //  多项式B
        PolynomialLinkedList B = new PolynomialLinkedList();
        ItemNode bnode1 = new ItemNode(0, 5);
        ItemNode bnode2 = new ItemNode(4, 2);
        ItemNode bnode3 = new ItemNode(7, 6);
        ItemNode bnode4 = new ItemNode(12, 3);
        B.add(bnode1);
        B.add(bnode2);
        B.add(bnode3);
        B.add(bnode4);
        System.out.print("多项式：B = ");
        B.list();

        // C = A+B
        PolynomialLinkedList C = polynomialAdd(A, B);
        System.out.print("A + B = C = ");
        if(C != null){
            C.list();
        }else{
            System.out.println("存在无效输入");
        }
    }


    /**
     * 多项式相加
     * @param a 多项式A
     * @param b 多项式B
     * @return c 相加结果 多项式C
     */
    public static PolynomialLinkedList polynomialAdd(PolynomialLinkedList a, PolynomialLinkedList b){
        // 获取A和B的头节点
        if(a == null || b == null){
            System.out.println("无效多项式");
            return null;
        }

        ItemNode aTemp = a.getHeadNode().next;
        ItemNode bTemp = b.getHeadNode().next;
        // A或B有一个多项式无节点
        if(aTemp == null){
            return b;
        }
        if(bTemp == null){
            return a;
        }

        // 多项式正常，按照指数进行合并
        PolynomialLinkedList C = new PolynomialLinkedList();

        while (true){
            // A或B已经遍历结束
            if(aTemp == null || bTemp == null){
                break;
            }

            ItemNode cNode = new ItemNode();
            if(aTemp.exp == bTemp.exp){
                // 指数相等，系数相加
                double coef = aTemp.coef + bTemp.coef;
                if(coef != 0){
                    // 相加后系数不为0
                    cNode.setExp(aTemp.exp);
                    cNode.setCoef(coef);
                    // A和B向后移动
                    aTemp = aTemp.next;
                    bTemp = bTemp.next;
                }else{
                    // 此时该节点不应该加入C中
                    aTemp = aTemp.next;
                    bTemp = bTemp.next;
                    // 系数相加为0，跳过此次计算
                    continue;
                }
            }else if(aTemp.exp < bTemp.exp){
                // A的指数小于B的指数，将A加入C中
                cNode.setExp(aTemp.exp);
                cNode.setCoef(aTemp.coef);
                // A向后移动
                aTemp = aTemp.next;
            }else {
                // A的指数大于B的指数
                cNode.setExp(bTemp.exp);
                cNode.setCoef(bTemp.coef);
                // B向后移动
                bTemp = bTemp.next;
            }
            // 将运算的节点接入C中
            C.add(cNode);

        }

        // 判断是否遍历结束
        while (aTemp != null){
            ItemNode node = new ItemNode();
            node.setExp(aTemp.exp);
            node.setCoef(aTemp.coef);
            C.add(node);
            aTemp = aTemp.next;
        }
        while (bTemp != null){
            ItemNode node = new ItemNode();
            node.setExp(bTemp.exp);
            node.setCoef(bTemp.coef);
            C.add(node);
            aTemp = bTemp.next;
        }

        return C;
    }

}

/**
 * 多项式有序单链表[指数递增]
 */
class PolynomialLinkedList{
    //  链表头节点
    ItemNode headNode = new ItemNode(0, 0);
    // 获取头节点
    public ItemNode getHeadNode() {
        return headNode;
    }

    //  手工有序添加多项式的项
    public void add(ItemNode node){
        if(node == null){
            System.out.println("新添加项为空");
            return;
        }

        // 在链表尾部进行插入
        ItemNode temp = headNode;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 添加新的项
        temp.next = node;
    }

    // 打印多项式
    public void list(){
        if(headNode == null || headNode.next == null){
            System.out.println("该多项式为空");
            return;
        }

        ItemNode temp = headNode.next;
        while (temp != null){
            System.out.print(temp.coef + "x^" + temp.exp);
            if(temp.next != null){
                System.out.print(" + ");
            }
            temp = temp.next;
        }
        // 空行
        System.out.println();
    }
}


/**
 * 多项式单链表节点[项]
 */
class ItemNode{

    // 多项式指数 Exponent
    int exp;
    // 多项式系数 coefficients
    double coef;

    ItemNode next;

    public ItemNode(int exp, double coef){
        this.exp = exp;
        this.coef = coef;
    }

    public ItemNode() {
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }
}
