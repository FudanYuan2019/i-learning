package dp.string;

/**
 * @Author: Jeremy
 * @Date: 2020/9/3 15:09
 */
public class KMP {
    /**
     * 暴力搜索
     *
     * @param text
     * @param pattern
     * @return
     */
    public int search(String text, String pattern) {
        if (text == null || "".equals(text) || pattern == null || "".equals(pattern)) {
            return 0;
        }
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            int j;
            for (j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == pattern.length()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * KMP
     *
     * @param text
     * @param pattern
     * @return
     */
    public int kmp(String text, String pattern) {
        if (text == null || "".equals(text) || pattern == null || "".equals(pattern)) {
            return 0;
        }
        int m = text.length();
        int n = pattern.length();
        int i = 0, j = 0;
        int[] next = getNext(pattern);
        while (i < m && j < n) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == n) {
            return i - n;
        }
        return -1;
    }

    private int[] getNext(String pattern) {
        int[] ret = new int[pattern.length()];
        ret[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pattern.length() - 1) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                ret[++j] = ++k;
            } else {
                k = ret[k];
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String text = "abcdabdcabc";
        String pattern = "abd";

        KMP kmp = new KMP();
        int index = kmp.search(text, pattern);
        System.out.println(index);

        index = kmp.kmp(text, pattern);
        System.out.println(index);
    }
}
