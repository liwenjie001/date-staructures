package ml.lwj.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 从小到大排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};
        // 冒泡排序的演变过程
        // 1. 第一躺排序，就是将最大的排序到最后

        for (int i = 0; i < arr.length -1; i++) {
            int temp = 0;
            for (int j = 0; j < arr.length -1 -i; j++) {
                // 如果前面比后面的数大，则交换
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
