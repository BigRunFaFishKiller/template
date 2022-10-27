package Graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/16 19:59 temple
 */

//DFS深度优先搜索：采用回溯的思想，每次枚举下一层的状态，枚举完后需要返回之前的状态
//BFS宽度优先搜索：
public class DFSBFS {

    //DFS框架
    public void DFS(int u, int n) {
        //满足条件返回
        if (u == n) {
            return;
        }
        //搜索下一层之前 的操作
        DFS(u + 1, n);
        //返回该层的操作，即恢复到之前的状态
    }

    //BSF框架
    public void BSF() {
        Queue queue = new ArrayDeque();
        while (!queue.isEmpty()) {
            Object t = queue.poll();
            //搜索操作
            queue.add(new Object());
        }

        Set<Integer> set = new HashSet<>();


    }

}
