package dataStructure;

import java.util.Scanner;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/12 19:17 temple
 */

//并查集，快速将两个集合合并，询问两个元素是否在一个集合
//基本原理：每个集合用一棵树表示，树根的编号即为整个集合的编号，用一个数组存储数据，下标x代表值为x的元素，p[x]数组存储其父节点的值
    //判断根节点：p[x] == x
    //寻找根节点：while(p[x] != x) x = p[x]
    //合并集合：x为一个树根，y为另外一个树根，p[x] = y
//优化：查询完某个节点后，将路径上的所有点指向根节点
//一般做题时，会额外维护一些信息
public class UnionFind {

    static final int N = 10010;
    static int[] p = new int[N];

    //返回x的祖先节点，并将路径优化
    static int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        //初始化将每个节点均指向自己
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        //中间操作，主要是构建集合
        int x = input.nextInt(), y = input.nextInt();
        //合并
        p[find(x)] = find(y);
    }

}
