package dataStructure;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/16 20:26 temple
 */

//链表：在算法题中一般不会通过new对象的方式构建链表，使用数组构建静态链表能有效提高速度
public class List {

    //头插法
    final int N = 100010;
    int[] e = new int[N];   //实际存储元素的数组
    int[] ne = new int[N];  //对应元素的next指针
    int idx = 0;            //当前未被使用的最小索引
    int head = -1;          //头指针

    void insert(int x) {
        e[idx] = x;
        ne[idx] = head;
        head = idx++;
    }

    void traverse() {
        for (int i = head; i != -1; i = ne[i]) {
            System.out.println(e[i]);
        }
    }

    public static void main(String[] args) {
        List list = new List();
        for (int i = 0; i < 1000; i++) {
            list.insert(i);
        }
        list.traverse();
    }


}
