package stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Author: Jeremy
 * @Date: 2020/9/5 17:58
 */
public class UncompressString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String res = new UncompressString().uncompress(str);
        System.out.println(res);
    }

    public String uncompress(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Stack<Integer> indexStack = new Stack<>();
        StringBuilder subStringBuilder = new StringBuilder();
        int i = 0;
        int lastIndex = -1;
        while(i < str.length()) {
            if (str.charAt(i) == '[') {
                indexStack.push(i);
            } else if (str.charAt(i) == '|') {
                indexStack.push(i);
            } else if (str.charAt(i) == ']') {
                if (!indexStack.isEmpty()) {
                    int index1 = indexStack.pop();
                    int index2 = indexStack.pop();
                    int num = Integer.parseInt(str.substring(index2 + 1, index1));
                    if (lastIndex == -1) {
                        lastIndex = i;
                    }
                    String subStr = str.substring(index1 + 1, lastIndex) + subStringBuilder.toString();
                    subStringBuilder = new StringBuilder();
                    while(num-- > 0) {
                        subStringBuilder.append(subStr);
                    }
                    if (indexStack.isEmpty()) {
                        stringBuilder.append(subStringBuilder);
                    }
                    lastIndex = index2;
                }
            } else if (indexStack.isEmpty()) {
                stringBuilder.append(str.charAt(i));
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
