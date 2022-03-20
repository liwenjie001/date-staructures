package ml.lwj.recursion;

public class Queue8 {
    // 定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array,保存皇后放置位置的结果，比如 arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        // 测试一把，八皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有多少种解法：" + count);
    }

    // 编写一个方法，放置第N个皇后
    // 特别注意：check 是每一次递归时，进入到check中都有for循环，因此会有回溯
    private void  check(int n){
        if (n == max){ // n= 8 放第九个皇后了，也就是第八个皇后已经放好了
            print();
            return;
        }
        // 依此放入皇后，并判断是否冲突
        for (int i =0;i<max;i++){
            // 先把当前皇后n ，放到改行的第一列
            array[n] = i;
            // 判断当放置第N个皇后到I列时，是否冲突
            if (judge(n)){ // 不冲突
                // 接着放N+1个皇后
                check(n+1);
            }
            // 如果冲突，就继续执行array[n] = i; 既将第n个皇后，放置在本行的后移的一个位置
        }
    }



    // 查看当我们防止第N个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    /**
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge( int n){
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 判断第N个皇后是否和前面n-1皇后在同一列
            // Math.abs(n-i) == Math.abs(array[n]-array[i]) 判断是否在同意斜线
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    // 写一个方法，可以将皇后摆放的位置打印输出
    private  void  print(){
        count ++ ;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
