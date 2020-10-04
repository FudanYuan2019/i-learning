package backtrack;

import util.PrintUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * N 皇后问题
 *
 * @Author: Jeremy
 * @Date: 2020/9/6 18:23
 */
public class NQueens {

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> res = nQueens.solveNQueens(4);
        for (List<String> subList : res) {
            PrintUtil.print(subList);
        }
    }

    /**
     * LeetCode 51  N 皇后
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * <p>
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * <p>
     * 示例：
     * <p>
     * 输入：4
     * 输出：[
     * [".Q..",  // 解法 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // 解法 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     * <p>
     * 提示：皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> res = new ArrayList<>();

        char[][] board = new char[n][n];
        initBoard(board, n);

        play(board, n, 0, res);
        return res;
    }

    /**
     * LeetCode 52. N皇后II
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 上图为 8 皇后问题的一种解法。
     * <p>
     * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
     * <p>
     * 示例:
     * <p>
     * 输入: 4
     * 输出: 2
     * 解释: 4 皇后问题存在如下两个不同的解法。
     * [
     *  [".Q..",  // 解法 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     * <p>
     *  ["..Q.",  // 解法 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        return solveNQueens(n).size();
    }

    private void initBoard(char[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
    }

    private void play(char[][] board, int n, int row, List<List<String>> res) {
        if (n == row) {
            res.add(printBoard(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                play(board, n, row + 1, res);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'Q') {
                return false;
            } else if (board[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'Q' && Math.abs(row - i) == Math.abs(col - j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<String> printBoard(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] line : board) {
            StringBuilder str = new StringBuilder();
            for (char ch : line) {
                str.append(ch);
            }
            res.add(str.toString());
        }
        return res;
    }
}
