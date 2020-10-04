package backtrack;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: Jeremy
 * @Date: 2020/10/1 21:51
 */
public class Parentheses {
    public static void main(String[] args) {
        String s = "()[]{}";
        Parentheses parentheses = new Parentheses();
        boolean valid = parentheses.isValid(s);
        PrintUtil.print(valid);

        List<String> res = parentheses.generateParenthesis(3);
        PrintUtil.print(res);
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "()"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     * <p>
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     * <p>
     * 输入: "{[]}"
     * 输出: true
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }

        int len = s.length();
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));

        int p = 1;
        while (p < len) {
            char ch = s.charAt(p);
            if (!stack.isEmpty() && ((stack.peek() == '(' && ch == ')')
                    || (stack.peek() == '[' && ch == ']')
                    || (stack.peek() == '{' && ch == '}'))) {
                stack.pop();
            } else {
                stack.push(ch);
            }
            p++;
        }

        return stack.isEmpty();
    }

    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，
     * 用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     * 示例：
     * <p>
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs(0, 0, n, new StringBuilder(), res);
        return res;
    }

    private void dfs(int left, int right, int max, StringBuilder s, List<String> res) {
        if (s.length() == 2 * max) {
            res.add(s.toString());
            return;
        }

        if (left < max) {
            dfs(left + 1, right, max, s.append("("), res);
            s.deleteCharAt(s.length() - 1);
        }

        if (right < left) {
            dfs(left, right + 1, max, s.append(")"), res);
            s.deleteCharAt(s.length() - 1);
        }
    }
}
