package dataStructure;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/12 19:55 temple
 */

//堆，以小根堆为例，根节点小于左右节点的值，节点x（以1开始）的左儿子为2x，右儿子为2x+1
    //插入一个数：插入数组尾部，再up(idx)，size++
    //求集合中的最小值：heap[1]
    //删除最小数：用数组尾部的数覆盖第一个点，清除尾部，再down(1)，size--
    //删除一个数：用数组尾部的数覆盖第k个点，清除尾部，再同时down(k),up(k)，size--
    //修改一个数：修改一个数，再同时down(k),up(k)
//基本操作：用基本操作完成对heap的操作
    //void down(x)：某个数变大后将数向下移动，选取儿子节点的较小值，两者交换
    //void up(x)：某个数变小后向上移动
public class Heap {

    final int N = 100010;
    int[] h = new int[N];
    int size;

    void down(int x) {
        int t = x;
        //左儿子存在，且大于左儿子，将t指向右儿子
        if (x * 2 <= size && h[x * 2] < h[t]) {
            t = x * 2;
        }
        //右儿子存在，如果进入了上一步，此时的t代表左儿子，相当于比较左右儿子的大小
        if (x * 2 + 1 <= size && h[x * 2 + 1] < h[t]) {
            t = x * 2 + 1;
        }
        if (t != x) {
            int m = h[x];
            h[x] = h[t];
            h[t] = m;
            down(t);
        }
    }

    void up(int x) {
        while (x / 2 != 0 && h[x / 2] > h[x]) {
            int m = h[x];
            h[x] = h[x / 2];
            h[x / 2] = m;
            x /= 2;
        }
    }

}
