package backtrack;

import com.google.common.collect.Lists;
import util.PrintUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jeremy
 * @Date: 2020/10/1 22:33
 */
public class PhoneNumber {
    private static Map<Character, Character[]> map = new HashMap<>();

    static {

        map.put('2', new Character[]{'a', 'b', 'c'});
        map.put('3', new Character[]{'d', 'e', 'f'});
        map.put('4', new Character[]{'g', 'h', 'i'});
        map.put('5', new Character[]{'j', 'k', 'l'});
        map.put('6', new Character[]{'m', 'n', 'o'});
        map.put('7', new Character[]{'p', 'q', 'r', 's'});
        map.put('8', new Character[]{'t', 'u', 'v'});
        map.put('9', new Character[]{'w', 'x', 'y', 'z'});
    }

    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber();
        List<String> res = phoneNumber.letterCombinations("23");
        PrintUtil.print(res);
    }

    /**
     * LeetCode 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例:
     * <p>
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return res;
        }

        char[] chars = digits.toCharArray();
        int n = digits.length();
        backtrack(chars, 0, n, new StringBuilder(), res);
        return res;
    }

    private void backtrack(char[] chars, int start, int n, StringBuilder stringBuilder, List<String> res) {
        if (stringBuilder.length() == n) {
            res.add(stringBuilder.toString());
            return;
        }
        for (int i = start; i < n; i++) {
            char ch = chars[i];
            for (char letter : map.get(ch)) {
                backtrack(chars, i + 1, n, stringBuilder.append(letter), res);
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }
    }
}
