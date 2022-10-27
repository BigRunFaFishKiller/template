package basic;

import java.util.Scanner;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/4 16:11 temple
 */

//归并排序模板
public class MergeSort {
    static int n;
    static int[] arr;
    static int[] temp;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        arr = new int[n];
        temp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        mergeSort(arr, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        mergeSort(arr, l, mid); mergeSort(arr, mid + 1, r);
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[k++]  = arr[i++];
            }
            if (arr[i] > arr[j]) {
                temp[k++]  = arr[j++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];
        for (i = l, j = 0; i <= r; i++, j++) arr[i] = temp[j];
    }
}
