package ml.lwj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // (3+4)*5-6 =》 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";
        //  思路
        // 1. 先将 "3 4 + 5 * 6 -" 放到ArrayList 中
        // 2. 放到ArrayList 传递给一个方法，配合栈完成计算
        List<String> list = getListString(suffixExpression);
        int calculate = calculate(list);
        System.out.println(calculate);
    }
    // 将一个后缀表达式，依此将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        String[] s = suffixExpression.split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String ele :s){
            arrayList.add(ele);
        }
        return arrayList;
    }
    // 逆波兰表达式运算
    public static int calculate(List<String> ls){
        // 创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        // 遍历 ls
        for(String item:ls){
            // 这里使用正则表达式取出数
            if (item.matches("\\d+")){ // 匹配的是多位数
                // 入栈
                stack.push(item);
            } else {
                // pop出两个数并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                } else if(item.equals("-")){
                    res = num1 - num2;
                } else  if (item.equals("*")){
                    res = num1 * num2;

                } else if (item.equals("/")){
                    res = num1/num2;
                } else {
                    throw  new RuntimeException("运算符有问题");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
