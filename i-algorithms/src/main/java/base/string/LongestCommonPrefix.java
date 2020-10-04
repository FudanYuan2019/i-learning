package base.string;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/1 22:55
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strings = new String[]{"flower","flow","flight"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String res = longestCommonPrefix.longestCommonPrefix(strings);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     * <p>
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     * <p>
     * 所有输入只包含小写字母 a-z 。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int i = 0;
        while (true) {
            if (i >= strs[0].length()) {
                break;
            }
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    return strs[0].substring(0, i);
                }
                if (ch != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
            i++;
        }
        return strs[0].substring(0, i);
    }
}
