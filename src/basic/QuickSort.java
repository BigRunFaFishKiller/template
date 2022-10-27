package basic;

import java.util.Scanner;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/4 15:44 temple
 */

//快速排序模板
public class QuickSort {

    static int n;
    static int[] arr;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        quickSort(arr, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = arr[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (arr[i] < x);
            do j--; while (arr[j] > x);
            if (i < j) {
                int t = 0;
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        quickSort(arr, l, j);
        quickSort(arr, j + 1, r);
    }
}


