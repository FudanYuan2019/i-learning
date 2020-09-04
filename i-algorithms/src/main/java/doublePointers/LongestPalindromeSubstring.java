package doublePointers;

/**
 * LeetCode 5
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @Author: Jeremy
 * @Date: 2020/9/3 15:04
 */
public class LongestPalindromeSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        String res = "";
        for (int i = 0; i < len; i++) {
            String s1 = palindrome(s, len, i, i);
            String s2 = palindrome(s, len, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    public String palindrome(String s, int len, int left, int right) {
        while(left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        LongestPalindromeSubstring longestPalindromeSubstring = new LongestPalindromeSubstring();
        String res = longestPalindromeSubstring.longestPalindrome("babad");
        System.out.println(res);
    }
}
