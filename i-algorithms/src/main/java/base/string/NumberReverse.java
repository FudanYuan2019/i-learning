package base.string;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/9/22 16:00
 */
public class NumberReverse {

    public static void main(String[] args) {
        int x = -321;
        NumberReverse numberReverse = new NumberReverse();
        int y = numberReverse.reverse(x);
        PrintUtil.print(y);
    }

    /**
     * LeetCode 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 123
     * 输出: 321
     *  示例 2:
     * <p>
     * 输入: -123
     * 输出: -321
     * 示例 3:
     * <p>
     * 输入: 120
     * 输出: 21
     * 注意:
     * <p>
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int res = 0;
        int sign = x > 0 ? 1 : -1;
        x = Math.abs(x);
        while (x > 0) {
            int num = x % 10;
            if (sign == 1 && (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > 7))) {
                return 0;
            } else if (sign == -1 && (res > Math.abs(Integer.MIN_VALUE / 10) || (-res == Integer.MIN_VALUE / 10 && num > 8))) {
                return 0;
            }
            res *= 10;
            res += num;
            x /= 10;
        }
        return res * sign;
    }
}
