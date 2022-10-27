package basic;

import util.Util;

import java.util.Arrays;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/7 17:50 temple
 */

//离散化，就是当我们只关心数据的大小关系时，用排名代替原数据进行处理的一种预处理方法
public class Discretization {

    int[] arr;

    public Discretization(int[] arr) {
        //将传递的数组排序去重
        this.arr = Util.sortDistinct(arr);
    }

    //给定一个数，返回他在所有数据中的排名
    public int find(int x) {
        return Arrays.binarySearch(arr, x) + 1;
    }

}
