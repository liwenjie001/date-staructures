package ml.lwj.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序 交换法,速度比较满相对插入排序
     * 时间复杂度高 ： O(n^3)
     * @param arr
     */
    public static void shellSort(int[] arr){
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i-gap ; j >=0 ; j -= gap) {
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序 移动法
     * @param arr
     */
    public static void shellSort2(int[] arr){

        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            // 从第gap个元素开始逐个对其所在的组进行插入排序
            for (int i = gap; i < arr.length; i++) {
                int index = i;
                int temp = arr[index];
                if (arr[index] < arr[index -gap]){
                    while (index - gap >=0 && temp < arr[index-gap]){
                        // 移动
                        arr[index] = arr[index - gap];
                        index -= gap;
                    }
                    // 当退出while 循环后就找到了插入的位置
                    arr[index] = temp;
                }
            }
        }
    }



}
