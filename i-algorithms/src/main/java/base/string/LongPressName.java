package base.string;

import util.PrintUtil;

/**
 * @Author: Jeremy
 * @Date: 2020/10/21 10:32
 */
public class LongPressName {
    public static void main(String[] args) {
        String name = "alex";
        String typed = "aaleex";

        LongPressName longPressName = new LongPressName();
        boolean res = longPressName.isLongPressedName(name, typed);
        PrintUtil.print(res);

        name = "pyplrz";
        typed = "ppyypllr";
        res = longPressName.isLongPressedName(name, typed);
        PrintUtil.print(res);

        name = "vtkgn";
        typed = "vttkgnn";
        res = longPressName.isLongPressedName(name, typed);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 925. 长按键入
     * 你的朋友正在使用键盘输入他的名字 name。
     * 偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     * <p>
     * 你将会检查键盘输入的字符 typed。
     * 如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     * <p>
     * 示例 1：
     * <p>
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     * 示例 2：
     * <p>
     * 输入：name = "saeed", typed = "ssaaedd"
     * 输出：false
     * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
     * 示例 3：
     * <p>
     * 输入：name = "leelee", typed = "lleeelee"
     * 输出：true
     * 示例 4：
     * <p>
     * 输入：name = "laiden", typed = "laiden"
     * 输出：true
     * 解释：长按名字中的字符并不是必要的。
     *
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int len1 = name.length();
        int len2 = typed.length();
        int i = 0, j = 0;
        while (j < len2) {
            if (i < len1 && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)){
                j++;
            } else {
                return false;
            }
        }

        return i == len1;
    }
}
