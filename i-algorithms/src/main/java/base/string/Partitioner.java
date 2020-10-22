package base.string;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/10/22 11:38
 */
public class Partitioner {
    public static void main(String[] args) {
        Partitioner partitioner = new Partitioner();
        String S = "ababcbacadefegdehijhklij";
        List<Integer> res = partitioner.partitionLabels(S);
        PrintUtil.print(res);
    }

    /**
     * 763. 划分字母区间
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
     * 同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
     * <p>
     * 示例 1：
     * <p>
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     * <p>
     * 提示：
     * <p>
     * S的长度在[1, 500]之间。
     * S只包含小写字母 'a' 到 'z' 。
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        int[] pos = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            pos[ch - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, pos[S.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start + 1);
                start = end + 1;
            }
        }

        return res;
    }
}
