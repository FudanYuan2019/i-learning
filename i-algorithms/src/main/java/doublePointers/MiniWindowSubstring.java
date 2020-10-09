package doublePointers;

import util.PrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/10/9 11:39
 */
public class MiniWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        MiniWindowSubstring miniWindowSubstring = new MiniWindowSubstring();
        String res = miniWindowSubstring.minWindow(s, t);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 76. 最小覆盖子串
     * 给你一个字符串 S、一个字符串 T 。
     * 请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
     * <p>
     * 示例：
     * <p>
     * 输入：S = "ADOBECODEBANC", T = "ABC"
     * 输出："BANC"
     * <p>
     * 提示：
     * <p>
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        int len = s.length();
        int match = 0;
        int minSize = Integer.MAX_VALUE;
        int start = -1;
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> expected = new HashMap<>();
        for (char ch : t.toCharArray()) {
            expected.put(ch, expected.getOrDefault(ch, 0) + 1);
        }

        while (right < len) {
            char ch = s.charAt(right);
            if (t.contains(String.valueOf(ch))) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.get(ch).equals(expected.get(ch))) {
                    match++;
                }
            }
            right++;

            while (match == expected.size()) {
                if (right - left < minSize) {
                    start = left;
                    minSize = right - left;
                }
                char leftCh = s.charAt(left);
                if (map.containsKey(leftCh)) {
                    map.put(leftCh, map.get(leftCh) - 1);
                    if(map.get(leftCh) < expected.get(leftCh)) {
                        match--;
                    }
                }
                left++;
            }

        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(start, start + minSize);
    }
}
