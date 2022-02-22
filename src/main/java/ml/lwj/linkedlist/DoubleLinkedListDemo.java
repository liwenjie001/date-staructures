package ml.lwj.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        //先创建几个人物
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        // 创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        // 修改测试
        HeroNode2 hero5 = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(hero5);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();


    }
}
// 创建一个双向链表的类

class DoubleLinkedList {
    // 先初始化一个头节点，头节点不要动 ，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    // 遍历双向链表的方法
    // 显示链表【遍历】
    public void  list(){
        // 判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 tmp = head.next;
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
    // 添加一个节点到双向链表的后面
    public void add ( HeroNode2 heroNode){
        // 因为head节点不能动，因为需要一个辅助遍历 temp
        HeroNode2 tmp = head;
        // 遍历链表，找到最后
        while (true){
            if (tmp.next == null){
                break;
            }
            // 如果没有找到就将temp后移
            tmp = tmp.next;
        }
        // 当退出while循环时，temp 就指向链表的最后
        // 形成一个双向链表
        tmp.next = heroNode;
        heroNode.pre = tmp;
    }
    // 修改双向链表内容
    public void  update(HeroNode2 newHeroNode){
        // 判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据NO编号修改
        // 定义一个辅助变量
        HeroNode2 temp = head.next;
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
    // 从双向链表中删除一个节点
    //  1. 对于双向链表，可以直接找到要删除的节点，找到后自我伤处即可
    public void del(int no) {

        // 判断当前链表是否为空
        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean falg = false; // 标志是否找到待删除节点的
        while (true){
            if (temp == null){ // 已经到链表的最后
                break;
            }
            if (temp.no == no) {
                // 找到了待伤处节点的前一个节点
                falg = true;
                break;
            }
            temp = temp.next; // temp 后移

        }
        if (falg){ // 找到 可以删除
            temp.pre.next = temp.next;
            // 这里我们的代码是有问题的？
            if (temp.next !=null){ // 如果 是最后一个节点，就不需要执行了，否者会出现空指针异常
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 不存在\n",no);
        }

    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点 默认为null
    public HeroNode2 pre; // 指向前一个节点 默认为null

    // 构造器
    public HeroNode2 (int hNo,String hName,String hNickname ){
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }
    // 为了显示方便 重写 toString
    @Override
    public String toString() {
        return "HeroNode2 [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }
}