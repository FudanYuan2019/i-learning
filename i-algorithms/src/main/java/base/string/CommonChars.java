package base.string;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/10/14 10:56
 */
public class CommonChars {
    public static void main(String[] args) {
        String[] strings = new String[]{"bella","label","roller"};
        CommonChars commonChars = new CommonChars();
        List<String> res = commonChars.commonChars(strings);
        PrintUtil.print(res);

        strings = new String[]{"cool","lock","cook"};
        res = commonChars.commonChars(strings);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 1002. 查找常用字符
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * <p>
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     * <p>
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     *
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if (A == null || A.length == 0) {
            return res;
        }

        // 最终结果集一定会是第一个字符串的子集
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : A[0].toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (String str : A) {
            Map<Character, Integer> tmpMap = new HashMap<>();
            for (char ch : str.toCharArray()) {
                if (map.containsKey(ch)) {
                    tmpMap.put(ch, tmpMap.getOrDefault(ch, 0) + 1);
                }
            }
            List<Character> removed = new ArrayList<>();
            for (Character ch : map.keySet()) {
                if (!tmpMap.containsKey(ch)) {
                    removed.add(ch);
                } else if (!tmpMap.get(ch).equals(map.get(ch))) {
                    map.put(ch, Math.min(tmpMap.get(ch), map.get(ch)));
                }
            }

            for (Character ch : removed) {
                map.remove(ch);
            }
        }
        for (Character ch : map.keySet()) {
            for (int i = 0; i < map.get(ch); i++) {
                res.add(String.valueOf(ch));
            }
        }
        return res;
    }
}
