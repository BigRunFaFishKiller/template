package basic;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/7 16:26 temple
 */

//双指针算法
//核心思路：将嵌套循环的朴素算法优化到O(n)
public class DoublePoint {

    public void doublePoint() {
        int n = 100;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < i && check(i, j)) {
                j++;
            }
            //具体逻辑
        }
    }

    public boolean check(int i, int j) {
        return true;
    }
}

