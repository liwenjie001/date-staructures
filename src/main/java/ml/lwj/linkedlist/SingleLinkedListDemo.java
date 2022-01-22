package ml.lwj.linkedlist;

import static ml.lwj.linkedlist.SingleLinkedList.*;

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
//        singleLinkedList.del(1);
//        singleLinkedList.list();
//        System.out.println(getLength(singleLinkedList.getHead()));
//        // 测试一下 看看是否得到倒数第K个节点
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),1);
//        System.out.println(res);

        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况");
        singleLinkedList.list();
        System.out.println("反转单链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
    }


}
// 定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动 ，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");
    // 返回头节点
    public HeroNode getHead() {
        return head;
    }
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
     // 获取到单链表的节点的个数（如果是带头节点的链表，需要不统计头节点）

    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int length = 0;
        // 这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur !=null){
            length ++;
            cur = cur.next;
        }
        return length;
    }
    // 查到单链表中的倒数第K个节点【新浪面试题】
    // 思路
    // 1. 编写一个方法，接收 head节点 ，同时接受一个index
    // 2. index 表示是倒数第indexx个节点
    // 3. 先把链表从头到尾遍历一下，得到链表的总长度
    // 4. 得到size后，我们从链表的第一个开始遍历 （size - index） 个 就可以得到了
    // 5. 如果找到了返回该节点，否者就返回空
    public static HeroNode findLastIndexNode(HeroNode head,int index) {
        // 判断如果为空，返回null
        if (head.next == null){
            return null;
        }
        // 第一个遍历得到链表的长度（节点的个数）
        int size = getLength(head);
        // 第二次遍历 size - index 位置 ，就是我们倒数的第K个节点
        // 先做一个index的校验
        if (index<=0 || index> size){
            return null;
        }
        // 定义一个辅助遍历,for循环定位到倒数的index
        HeroNode cur = head.next;

        for (int i = 0; i < size -index; i++) {
            cur = cur.next;
        }

        return cur;
    }
    // 将当链表进行反转
    public static void reverseList(HeroNode head){
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        // 定义一个辅助的指针（变量），帮助我们遍历里原来的链表
        HeroNode cur = head.next;

        HeroNode next = null; // 指向当前节点【cur】的下一个节点
        HeroNode reversHead = new HeroNode(0,"","");
        // 遍历原来的链表，并从头到尾遍历原来的链表，没遍历一个节点，就将其取出，并放在新的链表reversHead的最前端
        while (cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reversHead.next; // 将cur的下一个节点执行新的链表的最前端
            reversHead.next = cur;
            cur = next ;// 让cur后移
        }
        // 将head.next 指向 reverseHead.next ,实现单链表的反转
        head.next = reversHead.next;
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
