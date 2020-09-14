package backtrack;

import base.tree.Trie;
import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 单词搜索. LeetCode 79, 212
 *
 * @Author: Jeremy
 * @Date: 2020/9/13 12:05
 */
public class WordSearch {
    private int[][] visited;
    private static final int[] X = new int[]{0, 0, 1, -1};
    private static final int[] Y = new int[]{1, -1, 0, 0};

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "CCB";

        WordSearch wordSearch = new WordSearch();
        PrintUtil.print(wordSearch.exist(board, word));

        String[] words = new String[]{"CCB", "ABFD"};
        List<String> res = wordSearch.findWords(board, words);
        PrintUtil.print(res);

        res = wordSearch.findWordsI(board, words);
        PrintUtil.print(res);
    }

    /**
     * LeetCode 79. 单词搜索I
     * <p>
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * <p>
     * 示例:
     * <p>
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *  
     * <p>
     * 提示：
     * <p>
     * board 和 word 中只包含大写和小写英文字母。
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int M = board.length;
        int N = board[0].length;

        visited = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == word.charAt(0) && visited[i][j] == 0) {
                    // System.out.println("found " + word.charAt(0) + " " + i + " " + j + " ");
                    visited[i][j] = 1;
                    if (search(board, M, N, i, j, word, 1)) {
                        return true;
                    }
                    visited[i][j] = 0;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, int M, int N, int x, int y, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        char ch = word.charAt(index);
        for (int i = 0; i < 4; i++) {
            int newX = x + X[i];
            int newY = y + Y[i];
            if (isValid(M, N, newX, newY, visited) && ch == board[newX][newY]) {
                // System.out.println("inner found " + ch + " " + newX + " " + newY + " " + index);
                visited[newX][newY] = 1;
                if (search(board, M, N, newX, newY, word, index + 1)) {
                    return true;
                }
                visited[newX][newY] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int M, int N, int x, int y, int[][] visited) {
        if (x >= M || y >= N || x < 0 || y < 0 || visited[x][y] == 1) {
            return false;
        }
        return true;
    }

    /**
     * LeetCode 212. 单词搜索 II
     * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母在一个单词中不允许被重复使用。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * words = ["oath","pea","eat","rain"] and board =
     * [
     * ['o','a','a','n'],
     * ['e','t','a','e'],
     * ['i','h','k','r'],
     * ['i','f','l','v']
     * ]
     * <p>
     * 输出: ["eat","oath"]
     * 说明:
     * 你可以假设所有输入都由小写字母 a-z 组成。
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    public List<String> findWordsI(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        // Step1: 构造前缀树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        // Step2: 搜索每一个单元格
        int M = board.length;
        int N = board[0].length;
        visited = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = 1;
                StringBuilder sb = new StringBuilder();
                search(board, M, N, i, j, trie, sb.append(board[i][j]), res);
                visited[i][j] = 0;
            }
        }
        return res;
    }

    private void search(char[][] board, int M, int N, int x, int y, Trie trie, StringBuilder sb, List<String> res) {
        if (!trie.startsWith(sb.toString())) {
            return;
        }

        if (trie.search(sb.toString())) {
            String string = sb.toString();
            if (!res.contains(string)) {
                res.add(string);
            }
        }

        // 搜索上下左右四个方向
        for (int i = 0; i < 4; i++) {
            int newX = x + X[i];
            int newY = y + Y[i];
            if (isValid(M, N, newX, newY, visited)) {
                visited[newX][newY] = 1;
                search(board, M, N, newX, newY, trie, sb.append(board[newX][newY]), res);
                sb.deleteCharAt(sb.length() - 1);  // 一定要撤销
                visited[newX][newY] = 0;
            }
        }
    }
}
