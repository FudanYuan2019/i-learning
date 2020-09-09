package doublePointers;

/**
 * 过滤敏感词
 *
 * 题目描述：
 * 考虑实现一个敏感词过滤的功能，给定指定的敏感单词、句子、替换词，把句子中的敏感单词词全部替换成替换词。
 * <p>
 * 注意替换过程中敏感词需要忽略字母顺序，例如you这个敏感词，句子里如果出现oyu、uyo等，也同样需要进行替换，以免有人利用不影响人类理解的错误字母顺序钻空子。
 * <p>
 * 输入描述
 * 输入为三行内容：
 * <p>
 * 第一行是敏感单词
 * <p>
 * 第二行是待检测的句子
 * <p>
 * 第三行是替换的目标词
 * <p>
 * 输出描述
 * 输出替换好敏感词的句子
 * <p>
 * <p>
 * 样例输入
 * you
 * i love you,oyu love me
 * jack
 * 样例输出
 * i love jack,jack love me
 *
 * @Author: Jeremy
 * @Date: 2020/9/9 11:40
 */
public class KeywordFilter {

    public static void main(String[] args) {
        String sentence = "i love yuo, oyu love me";
        String keyword = "you";
        String target = "jack";
        KeywordFilter keywordFilter = new KeywordFilter();
        String res = keywordFilter.findKeywordAndReplace(sentence, keyword, target);
        System.out.println(res);
    }

    public String findKeywordAndReplace(String sentence, String keyword, String target) {
        int i = 0;
        int j = 0;
        int count = keyword.length();
        StringBuilder stringBuilder = new StringBuilder();
        while (j < sentence.length()) {
            while (j < sentence.length() && sentence.charAt(j) >= 'a' && sentence.charAt(j) <= 'z') {
                if (keyword.contains(String.valueOf(sentence.charAt(j)))) {
                    count--;
                }
                j++;
            }
            if (count == 0) {
                stringBuilder.append(target);
            } else {
                stringBuilder.append(sentence, i, j);
            }
            if (j < sentence.length()) {
                stringBuilder.append(sentence.charAt(j));
            }
            count = keyword.length();
            j++;
            i = j;
        }
        return stringBuilder.toString();
    }
}
