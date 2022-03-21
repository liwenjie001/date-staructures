package ml.lwj.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度：O(n^2)
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 选择排序
    public static void selectSort(int[] arr) {
        // 算法的思想：先简单在复杂；把复杂的算法拆分成简单问题在逐步解决
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = 1 + i; j < arr.length; j++) {
                if (min > arr[j]) { // 说明假定的最小值并不是最小值
                    min = arr[j]; // 重置min
                    minIndex = j; // 重置minIndex
                }
            }
            // 将最小值，放在arr[0]，既交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
