package doublePointers;

import util.PrintUtil;

import java.util.HashSet;

/**
 * @Author: Jeremy
 * @Date: 2020/9/22 11:48
 */
public class LongestSubstring {
    public static void main(String[] args) {
        String str = "pwwkew";
        LongestSubstring longestSubstring = new LongestSubstring();
        int maxLen = longestSubstring.lengthOfLongestSubstring(str);
        PrintUtil.print(maxLen);
    }

    /**
     * LeetCode 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        int len = s.length();
        HashSet<Character> set = new HashSet<>();
        int max = 0;
        while (i < len) {
            while (j < len && !set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
            }

            int l = j - i;
            if (l > max) {
                max = l;
            }
            set.remove(s.charAt(i));
            i++;
        }
        return max;
    }
}
