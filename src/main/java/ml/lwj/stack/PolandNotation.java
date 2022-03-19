package ml.lwj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 完成将中缀表达式转换成后缀表达式
        // (3+4)*5-6 =》 3 4 + 5 * 6 -
        //  因为直接对字符串进行操作，不方便，因此先将字符串 转成中缀表达式的list

        String expression = "(3+4)*5-6";

        List<String> strings = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list"+strings);
        List<String> stringList = parseSuffixExpreesionList(strings);
        System.out.println("后缀表达式对应的list"+stringList);


        // 先定义一个逆波兰表达式
        // (3+4)*5-6 =》 3 4 + 5 * 6 -
//        String suffixExpression = "3 4 + 5 * 6 -";
        //  思路
        // 1. 先将 "3 4 + 5 * 6 -" 放到ArrayList 中
        // 2. 放到ArrayList 传递给一个方法，配合栈完成计算
//        List<String> list = getListString(suffixExpression);
//        int calculate = calculate(list);
//        System.out.println(calculate);
    }
    // 将中缀表达式转成对应的list
    public  static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i = 0;// 这是一个指针，用于遍历中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符就放到c中
        do {
            // 如果c是一个非数字,就需要加入到ls
            if ((c=s.charAt(i))<48 || (c=s.charAt(i))>57){
                ls.add(""+c);
                i++;
            } else { // 如果是一个数，需要考虑多位数问题
                str = "";// 先将str赋值位空串
                while (i<s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57 ){
                    str +=c;// 拼接
                    i ++;
                }
                ls.add(str);
            }
        } while (i<s.length());
        return ls;
    }

    // 将中缀表达式转换成后缀表达式

    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        // 定义两个栈
        Stack<String> s1 = new Stack<String>();// 符号栈
        List<String> s2 = new ArrayList<>();
        for(String item :ls) {
            // 如果是一个数就加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            } else if(item.equals(")")){
                // 如果是右括号 ")" ，则依此弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); //!!! 将（ 弹出s1栈，消除小括号
            } else {
                // 当item的优先级小于等于s1栈顶运算符的优先级，将s1栈顶的于运算符弹出并家入到s2中
                // 问题： 我们缺少以哦个比较优先级高低的方法
                while (s1.size() !=0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符一次弹出并加入到s2中
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2; // 注意因为是存放到list，因此按顺输出就是对应的后缀表达式对应的list
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

// 编写一个类 Operation 可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int SUM = 2;
    private static int DIV = 2;
    // 写一个方法，返回对应的优先级数字
    public static int getValue (String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = SUM;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}