package Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/22 20:29 temple
 */

//图的存储
//邻接表，储存有向图
//领接矩阵，存储稠密图
public class Graph {

    public static void main(String[] args) {
        Graph g = new Graph();
        g.add(1, 2);
        g.dfs(2);
    }

    int n = 10010;
    int m = n * 2;
    //数组模拟指针，头插法
    int[] h;    //每个点的头指针
    int[] e;    //储存节点
    int[] ne;   //储存下一个节点的指针
    int idx;
    boolean[] is; //记录该点是否以及被遍历

    public Graph() {
        h = new int[n];
        e = new int[m];
        ne = new int[m];
        is = new boolean[n];
        //-1表示空节点
        Arrays.fill(h, -1);
    }

    //插入一个a指向b的边
    public void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    //图的深度优先遍历
    //从点u开始搜索
    void dfs(int u) {
        is[u] = true;
        System.out.println(u);
        //遍历u的所有出边，i是对应点在e中的索引
        for(int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (!is[j]) {
                dfs(j);
            }
        }
    }

    //图的宽度优先遍历
    //从点u开始搜索
    void bfs(int u) {
        is[u] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(u);
        while (!queue.isEmpty()) {
            int head = queue.poll();
            System.out.println(head);
            for (int i = h[head]; i != -1; i = ne[i]) {
                int j = e[i];
                if (!is[j]) {
                    is[j] = true;
                    queue.add(j);
                }
            }
        }
    }

    //有向无环图的的拓扑序列
    //拓扑序列：若一个由图中的所有点构成的序列A满足，对于有向图的每条边(x, y)，x在A中都出现在y前，则称A是该图的拓扑排序。
    //即图的边在拓扑序列中均为序列前的点指向后的点，且拓扑序列不唯一
    //例如：1->2, 2->3, 1->3, 则1 2 3是该图的拓扑序列
    //算法：
    //1、从图中取出一个没有前驱的顶点输出，删除以该店为起点的边
    //2、重复上述步骤直到最后一个顶点被输出。如果还有顶点未被输出，说明该图有环



    //最短路径

}
