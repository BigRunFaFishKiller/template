package dataStructure;

import java.sql.NClob;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/12 18:44 temple
 */

//前缀树，高效存储、查找字符串“集合”的数据结构
public class Trie {
    final int N = 100010;
    //假定最多100010个节点，字符串所有字符均为小写字母，因此每个节点只有26个相连的点
    int[][] son = new int[N][26];
    int[] cnt = new int[N]; //表示以当前节点结尾的字符共有多少个
    int idx;        //表示当前是哪一个节点，每次插入一个点，都会为其分配一个下标，用数组模拟指针

    void insert(String str) {
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            int u = str.charAt(i) - 'a';
            //判断第一个节点是否存在该字母，存在则转到对应的节点，不存在则新建该节点
            if (!(son[p][u] == 0)) {
                son[p][u] = ++idx;
            }
            p = son[p][u];
        }
        cnt[p] ++;
    }
    //询问一个字符串在集合中出现了多少次
    int query(String str) {
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            int u = str.charAt(i) - 'a';
            if (!(son[p][u] == 0)) {
                return 0;
            }
            p = son[p][u];
        }
        return cnt[p];
    }
}
