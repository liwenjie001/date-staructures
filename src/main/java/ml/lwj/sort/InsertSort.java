package ml.lwj.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度 O(n^2)
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        insertSort(arr);
    }
    // 插入排序
    public static void insertSort(int[] arr){
        for (int i = 1 ; i < arr.length; i++) {
            // 定义待插入的数
            int insertVal =arr[i];
            int insertIndex = i -1;// 既arr[1] 的前面这个数的下标

            // 给insertVal 找到插入的位置
            while (insertIndex >=0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 这里判断是否需要赋值
            if (insertIndex + 1 !=i ){
                arr[insertIndex + 1] = insertVal;
            }
            System.out.println(Arrays.toString(arr));
        }

    }
}
