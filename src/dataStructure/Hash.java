package dataStructure;

import java.util.Arrays;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/16 17:17 temple
 */

//哈希表。根据不同的冲突解决方式分为：
    //开放寻址：发送冲突时，搜索冲突位置后面的空位，将元素放在空位，数组要达到给定的元素的两到三倍
    //拉链法：发生冲突时，在冲突位置新增链表解决冲突
//算法中一般只有添加和查询操作
//hash取模时，取一个质数，发生冲突的概率较小
public class Hash {

    //拉链法，链表为头插法
    final int N =100003;
    //使用h存储每个节点槽的头指针
    int[] h = new int[N];
    //用数组模拟指针
    int[] ne = new int[N];  //next指针
    int[] e = new int[N];   //存储所有元素的数组
    int idx = 0;                //下标
    void insert(int x) {
        Arrays.fill(h, -1);     //h存储头指针，开始时赋值为-1标志指向空
        int k = (x % N + N) % N;    //排除正负号的影响
        e[idx] = x;         //将目标元素储存
        ne[idx] = h[k];     //让目标元素指向头节点的下一个，头插法
        h[k] = idx++;       //移动头指针
    }

    boolean find(int x) {
        int k = (x % N + N) % N;
        //遍历时，先让i指向头节点，再让i一次指向下一位
        for (int i = h[k]; i != -1; i = ne[i]) {
            if (e[i] == x) {
                return true;
            }
        }
        return false;
    }


    //开放寻址法
    int[] h1 = new int[3 * N];

    boolean find1(int x) {
        int k = (x % N + N) % N;
        while (h1[k] != x) {
            if (h1[k] == -1) {
                return false;
            }
            k++;
        }
        return true;
    }

    void insert1(int x) {
        int k = (x % N + N) % N;
        while (h[k] != -1) {
            k++;
        }
        h[k] = x;
    }


    //字符串前缀哈希法


}
