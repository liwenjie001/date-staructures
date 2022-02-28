package ml.lwj.stack;

public class Calculator {
    public static void main(String[] args) {
        // 根据之前的思路，完成表达式的运算
        String expression = "70+2*6-4";
        // 创建两个栈，数栈、符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义相关的变量
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        char oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到char 保存到ch中
        String keepNum = ""; // 用于拼接多位数
        // 开始while循环的扫描expression
        while (true) {
            // 依此得到expression 中的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么做相应的处理
            if (operStack.isOper(ch)) {//如果是运算符
                // 判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符
                    //就在符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后在将当前的操作符号入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 把运算的结果入数栈
                        numStack.push(res);
                        // 把当前的操作符入符号栈
                        operStack.push(ch);
                    } else { // 如果当前的操作优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    // 如果为空直接入栈
                    operStack.push(ch);
                }

            } else {
//                numStack.push(ch -48);
                // 分析思路
                // 1. 当处理多位数时，不能发现一个数就立即入栈，因为他可能是多位数
                // 2. 在处理数，需要向expression的表达式的index后再看一位，如果是数字就继续扫描，如果是符号就入栈
                // 3. 因此我们需要定义一个变量 ，字符串变量，用于拼接
                keepNum += ch;
                //如果ch已经是expression 最后一位就直接入数栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个字符是否是数字；
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        // 重要的 keepNum 清空
                        keepNum = "";
                    }
                }
            }
            // 让index + 1 ，并判断是否扫描到expression最后了
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行；
        while (true) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char) operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println("表达式" + expression + "=" + numStack.pop());

    }
}

// 先创建一个栈，直接使用前面创建好的，需要扩展功能
class ArrayStack2 {
    private int maxSize;// 栈的大小
    private int[] stack;// 数组，数组模拟栈，数据就放在该数组中
    private int top = -1; // top 表示栈顶，初始化为-1

    // 构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈 ,将栈顶的数据返回
    public int pop() {
        // 先判断栈是否空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int vlaue = stack[top];
        top--;
        return vlaue;
    }

    // 增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
    public int peek() {
        return stack[top];

    }

    // 遍历栈,遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("炸空，没有数据~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d", i, stack[i]);
        }
    }

    // 返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示，数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假设目前的表达式只有加减乘除
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, char oper) {
        int res = 0;// 用于存放结算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1; // 注意顺序
                break;
        }
        return res;
    }


}