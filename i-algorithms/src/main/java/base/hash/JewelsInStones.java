package base.hash;

import util.PrintUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Jeremy
 * @Date: 2020/10/2 20:00
 */
public class JewelsInStones {

    public static void main(String[] args) {
        String J = "Aa";
        String S = "Aaabbbbb";

        JewelsInStones jewelsInStones = new JewelsInStones();
        int count = jewelsInStones.numJewelsInStones(J, S);
        PrintUtil.print(count);
    }

    /**
     * 771. 宝石与石头
     * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。
     * S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * <p>
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     * <p>
     * 示例 1:
     * <p>
     * 输入: J = "aA", S = "aAAbbbb"
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: J = "z", S = "ZZ"
     * 输出: 0
     * 注意:
     * <p>
     * S 和 J 最多含有50个字母。
     * J 中的字符不重复。
     *
     * @param J
     * @param S
     * @return
     */
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (Character ch : J.toCharArray()) {
            set.add(ch);
        }

        int count = 0;
        for (Character ch : S.toCharArray()) {
            if (set.contains(ch)) {
                count++;
            }
        }
        return count;
    }
}
