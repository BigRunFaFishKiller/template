package Graph;

import java.util.*;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/23 19:51 temple
 */



//最短路径，有向无环图
    //单源最短路径：一个点到其他所有点的最短路径
        //所有边都是正权
            //朴素Dijkstra算法：O(n^2)，适合稠密图
            //堆优化Dijkstra算法：O(mlogn)，适合稀疏图
        //存在负权边
            //Bellman-Ford算法：O(nm)
            //SPFA算法：一般O(m)，最差O(nm)
    //多源汇最短路径：任意两点间的最短路径
        //Floyd算法：O(n^3)

public class ShortestPath {

    //表示从a到b的边以及其权重
    static class Edge{
        int a;
        int b;
        int w;
        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    //朴素Dijkstra算法
    //思路：贪心
    //初始化1号点到其他点的距离，1号为0，其余点位无穷，使用dist集合储存1到已经确定的点的最小距离
    //迭代，找到不在dist中的，距离1号点最近的点t，将t加入集合
    //使用t更新其他确定点的距离，即比较1号点到x的距离和1号点到t再到x的距离
    //
    //举例：A到B为2，B到C为1，A到C为4，初始化点的距离，A到A为0，A到B为无穷，A到C为无穷
    //第一次迭代：A到A的距离为0，为不在集合中的距离A最近的点，将其放入S集合，更新A到B、C的距离为2、4
    //第二次迭代：B到A的距离为2，为不在集合中的距离A最近的点，将其放入S集合，更新通过B，A到C的最小距离为3
    //第三次迭代：C到A的距离为3，为不在集合中的距离A最近的点，将其放入S集合，没有没有放入集合的点，结束
    //n为点数，m为边数，g为图的邻接矩阵，返回从1号点到其他所有点的最小距离，索引从0开始。将起始点设为矩阵第一个点
    public int[] dijkstra(int n, int[][] g) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        //判断该点是否已经加入集合
        boolean[] is = new boolean[n];
        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 0; j < n; j++) {
                //遍历所有点，如果j这个点未被加入dist集合，且t未被更新或者当前有更小的未被加入的点，都将该点加入集合
                //用t确保第一个未被加入的点会被放入集合
                if (!is[j] && (t ==-1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            is[t] = true;
            for (int j = 0; j < n; j++) {
                //比较从1号直接到某个点和从1号到t在到某个点的距离
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        return dist;
    }

    //堆优化Dijkstra算法
    //优化：
    //将不在集合中的点放入堆，每次从中取出的点一定是距离最小的没有放入集合中的点，可以将寻找最小的没有加入集合的时间复杂度从O(n^2)优化为O(1)
    //从堆中取出一个数的时间复杂度为logn，更新1号点到其他点的距离时，和图的边的数量有关，因此时间复杂度可以优化为O(mlogn)\
    //使用邻接表储存图，w表示权重，点的索引从0开始
    public int[] heapDijkstra(int n, int[] h, int[] e, int[] ne, int[] w) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] is = new boolean[n];
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        //初始时离1号点最近的点是其本身
        heap.add(new int[]{0, 0});
        while (!heap.isEmpty()) {
            int[] t = heap.poll();
            //distance为1号点到该点的距离
            int point = t[0], distance = t[1];
            if (is[point]) continue;
            //使用当前点更新到其他点的距离
            //因为在邻接矩阵中，某个点的链表只连接这个点的出边，因此不会用之前点的数据更新
            for (int i = h[point]; i != -1; i = ne[i]) {    //i是对应点在e中的索引
                //j存储点的编号
                int j = e[i];
                if (dist[j] > distance + w[i]) {
                    dist[j] = distance +w[i];
                    //将更新后的点放入优先队列，更改过的点即未被确认的点
                    heap.add(new int[]{j, dist[j]});
                }
            }
        }
        return dist;
    }

    //Bellman-Ford算法
    //思路
    //迭代n次，每次循环所有边，循环到某一条边时，用这一条边的权值更新其他点的最短距离
    //例如：1号点到2号点的权值为1，到3号点的权值为1，2号点到3号点的距离为-1，最多经过1条边，求1号点到3号点的权重
    //第1轮循环，1号点到2号点，权值为1，更新1、2号距离为1
    //第2轮循环，1号点到3号点，权值为1，更新1、3号距离为1
    //第3轮循环，2号点到3号点，权值为1，比较1号经过2号到3号的距离和上一次迭代距离，取最小值。此处上一次迭代，1号点通过2号点到3号点的距离为无穷，本次迭代为无穷加一个值，因此不更新
    //n个点，返回1号点到所有点的最短距离，且最多经过k条边。点的索引从0开始
    public int[] bellmanFord(Edge[] edges, int n, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[0] = 0;
        int m = edges.length;
        //备份上一次迭代结果，防止出现因为一条边的更新导致其他边的更新，且此时的最短距离不符合条件
        int[] backup = new int[n];
        for (int i = 0; i < k; i++) {
            backup = Arrays.copyOf(dist, dist.length);
            for (int j = 0; j < m; j++) {
                int a = edges[j].a, b = edges[j].b, w = edges[j].w;
                dist[b] = Math.min(dist[b], backup[a] + w);
            }
        }
        return dist;
    }

    //SPFA算法
    //对Bellman-Ford算法进行优化，前提是不存在负权环
    //将待更新的点放入队列，采用广搜的思路
    //每次优化路路径时不需要对所有的边进行优化，优化被更新的点的出边所对应的点即可
    //点的索引从0开始。将起始点设为矩阵第一个点，采用邻接矩阵的形式
    public int[] SPFA(int n, int[] h, int[] e, int[] ne, int[] w) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[1] = 0;
        boolean[] is = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        //将第一个点放入队列
        queue.add(0);
        is[0] = true;
        while (!queue.isEmpty()) {
            int  t = queue.poll();
            is[t] = true;
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                dist[j] = Math.min(dist[j], dist[t] + w[i]);
                if (!is[j]) {
                    queue.add(j);
                    is[j] = true;
                }
            }
        }
        return dist;
    }

    //Floyd算法
    //不存在负权回路
    //思路：基于动态规划，dp[k, i, j]，表示从i出发只经过1到k这些点到j的距离，dp[k, i, j] = dp[k - 1, i, k] + dp[k - 1, k, j]
    //三层循环，第一层枚举k，第二三层枚举i，j
    //使用邻接矩阵保存图，返回二维数组表示从i到j的最短距离
    public int[][] floyd(int g[][], int n) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            dist[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        return dist;
    }


    public static void main(String[] args) {
        Graph g = new Graph();
        g.add(0, 1);
        g.add(0, 1);
        g.add(1, 2);
        int[] w = new int[3];
        w[0] = 1; w[1] = 1; w[2] = -1;
        ShortestPath test = new ShortestPath();
        int[] dist = test.SPFA(g.n, g.h, g.e, g.ne, w);
        System.out.println(dist[2]);

    }

}
