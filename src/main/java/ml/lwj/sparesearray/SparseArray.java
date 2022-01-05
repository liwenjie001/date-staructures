package ml.lwj.sparesearray;

public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0 ： 表示没有棋子 1 表示黑子 2 表示蓝色的子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 4;
        // 输出原始的二维数组
        for(int[] row : chessArr){
            for(int data :row){
                System.out.printf("%d\t" ,data);
            }
            System.out.println();
        }
        // 将二维数组转换为稀疏数组
        // 1. 先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j]!=0){
                    sum ++;
                }
            }
        }
//        System.out.println(sum);
        // 创建对应的稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // 遍历数组，将非0的值存放到 稀疏数组当中
        int count = 0; // count 用于记录第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j]!=0){
                    count ++ ;
                    sparseArray[count][0] = i ;
                    sparseArray[count][1] = j ;
                    sparseArray[count][2] = chessArr[i][j];
                }
            }
        }

        // 输出稀疏数据的
        System.out.println("得到的稀疏数组为~~~~~");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }

    }
}
