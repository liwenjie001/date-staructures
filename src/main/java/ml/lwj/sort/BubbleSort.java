package ml.lwj.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 从小到大排序
 * 冒泡排序的时间复杂度为O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};
        // 冒泡排序的演变过程
        // 1. 第一躺排序，就是将最大的排序到最后
        int temp = 0;
        boolean flag = false;// 标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length -1 -i; j++) {
                // 如果前面比后面的数大，则交换
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag){ // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag 进行下次判断
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
