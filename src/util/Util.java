package util;

import java.util.Arrays;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/7 17:45 temple
 */

//常用操作
public class Util {

    //数组排序去重
    public static int[] sortDistinct(int[] arr) {
        return Arrays.stream(arr).sorted().distinct().toArray();
    }


}
