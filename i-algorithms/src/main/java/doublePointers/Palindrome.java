package doublePointers;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/9/22 16:08
 */
public class Palindrome {
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        boolean res = palindrome.isPalindrome(12321);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 9. 回文数
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 121
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * <p>
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * 进阶:
     * <p>
     * 你能不将整数转为字符串来解决这个问题吗？
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        String string = String.valueOf(x);
        int len = string.length();
        int mid = len / 2;
        if (len % 2 == 0) {
            return isPalindrome(string, mid - 1, mid);
        }
        return isPalindrome(string, mid, mid);
    }

    private boolean isPalindrome(String str, int index1, int index2) {
        while (index1 >= 0 && index2 < str.length()) {
            if (str.charAt(index1) != str.charAt(index2)) {
                return false;
            }
            index1--;
            index2++;
        }

        return true;
    }
}
