package basic;

/**
 * @Author:大润发杀鱼匠
 * @Date:2022/10/7 17:06 temple
 */

//位运算
public class BitOperation {

    //n的二进制表示中第k位是几
    //先将第k位移动到最后一位
    //取出最后一位
    public int kth(int num, int k) {
        return num >> (k - 1) & 1;
    }

    //lowBit：返回数字最后一个1所表示的数字，例如10:1010，其最后一个1表示的数为10,即2
    //例如：求一个二进制数有多少个1
    //对该数进行lowbit，然后让该数减去lowbit的数字，直到该数等于0，求lowbit次数即可
    public int lowBit(int n) {
        return n & (-n);
    }

}
