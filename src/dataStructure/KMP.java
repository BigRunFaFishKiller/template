package dataStructure;

import java.util.Scanner;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/10 15:51 temple
 */


//KMP算法

public class KMP {

    public static void main(String[] args) {
        int i = kmp("aaabacababc", "abacababc");
        System.out.println(i);
    }

    //寻找next数组
    private static int[] findNext(String str) {
        if (str.length() == 1) {
            return new int[] {0};
        }
        int[] next = new int[str.length()];
        next[0] = 0;
        //最长的相等前后缀
        int prefixNum = 0;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == next[prefixNum]) {
                prefixNum++;
                next[i] = prefixNum;
            } else if (prefixNum == 0) {
                next[i] = prefixNum;
            } else {
                next[i] = prefixNum - 1;
            }
        }
        return next;
    }

    public static int kmp(String str, String sub) {
        int[] next = findNext(sub);
        int i = 0, j = 0;
        while (i < str.length() && j < sub.length()) {
            if (str.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else if (j > 0) {
                j = next[j - 1];
            } else {
                i++;
            }
        }
        if (j == sub.length()) {
            return i - j;
        } else {
            //不存在则返回-1
            return -1;
        }
    }

}
