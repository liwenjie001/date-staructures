package ml.lwj.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //  先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        // 显示一把
//        singleLinkedList.list();

        // 加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero3);
        // 测试修改节点的代码
//        singleLinkedList.list();
//        HeroNode newHeroNode = new HeroNode(7,"小卢","玉麒麟~~");
//
//        singleLinkedList.update(newHeroNode);
//        singleLinkedList.list();
        // 删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.list();
    }

}
// 定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动 ，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");
    // 添加节点到单向链表
    // 思路，当不考虑编号顺序时
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next 指向新的节点
    public void add ( HeroNode headNode){
        // 因为head节点不能动，因为需要一个辅助遍历 temp
        HeroNode tmp = head;
        // 遍历链表，找到最后
        while (true){
            if (tmp.next == null){
                break;
            }
            // 如果没有找到就将temp后移
            tmp = tmp.next;
        }
        // 当退出while循环时，temp 就指向链表的最后
        tmp.next = headNode;
    }
    // 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    // （如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        // 因为单链表，因此我们找到的temp是位于添加位置的前一个节点，否者拿不到
        HeroNode temp = head;
        boolean flag = false; // 标记添加的编号是否存在，默认为false不存在
        while (true){
            if (temp.next== null){ // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no){ // 说明希望添加的heroNode的编号以及存在
                flag = true; //说明编号存在
                break;
            }
            temp =temp.next; // 后移，遍历当前的链表

        }

        // 判断flag 的值
        if (flag) { // 不能添加，说明编号存储
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入 \n",heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }


    }

    // 显示链表【遍历】
    public void  list(){
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode tmp = head.next;
        while (true){
            // 判断是否到链表最后
            if (tmp == null){
                break;
            }
            // 输出节点的信息
            System.out.println(tmp);
            // 将tmp后移
            tmp = tmp.next;
        }
    }
    // 修改节点的信息，根据编号来修改，既no编号不能改
    // 根据newHeroNode 的no来修改
    public void  update(HeroNode newHeroNode){
        // 判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据NO编号修改
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true){
            if (temp==null){
                break; // 表示链表已经遍历完了
            }
            if (temp.no== newHeroNode.no){
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;

        }
        // 根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { // 没有找到

            System.out.printf("没有找到编号 %d 的节点，不能修改",newHeroNode.no);
        }
    }
    // 删除节点
    // 思路
    // 1. head 不能动，因此我们需要一个temp辅助节点找到待伤处节点的前一个节点
    // 2. 在比较时，是temp.next.no 和 需要被删除的no比较
     public void del(int no) {
        HeroNode temp = head;
        boolean falg = false; // 标志是否找到待删除节点的
         while (true){
             if (temp.next==null){ // 已经到链表的最后
                break;
             }
             if (temp.next.no == no) {
                // 找到了待伤处节点的前一个节点
                 falg = true;
                 break;
             }
             temp = temp.next; // temp 后移

         }
         if (falg){ // 找到 可以伤处
             temp.next = temp.next.next;
         } else {
             System.out.printf("要删除的 %d 不存在\n",no);
         }

     }

}

// 定义HeroNode , 每个HeroNode 对象就是一个节点

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    // 构造器
    public HeroNode (int hNo,String hName,String hNickname ){
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;

    }
    // 为了显示方便 重写 toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}
