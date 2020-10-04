package backtrack;

import util.PrintUtil;

/**
 * LeetCode 37. 数独问题
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * @Author: Jeremy
 * @Date: 2020/9/13 17:40
 */
public class SudokuSolver {
    public static void main(String[] args) {
        String[] boardString = new String[]{
                "53..7....",
                "6..195...",
                ".98....6.",
                "8...6...3",
                "4..8.3..1",
                "7...2...6",
                ".6....28.",
                "...419..5",
                "....8..79"
        };

        SudokuSolver sudokuSolver = new SudokuSolver();
        char[][] board = sudokuSolver.initBoard(boardString);
        PrintUtil.print(board);
        PrintUtil.newLine();

        board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        boolean isValid = sudokuSolver.isValidSudoku(board);
        PrintUtil.print(isValid);
        PrintUtil.newLine();

        sudokuSolver.solveSudoku(board);
        PrintUtil.print(board);
    }

    /**
     * LeetCode 36. 有效的数独
     * 判断一个 9x9 的数独是否有效。
     * 只需要根据以下规则，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int M = board.length;
        int N = board[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (!isValid(board, M, N, i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {
        int M = board.length;
        int N = board[0].length;

        dfs(board, M, N, 0, 0);
    }

    private boolean dfs(char[][] board, int M, int N, int x, int y) {
        if (y == N) {
            return dfs(board, M, N, x + 1, 0);
        }

        if (x == M) {
            return true;
        }

        if (board[x][y] != '.') {
            return dfs(board, M, N, x, y + 1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            if (isValid(board, M, N, x, y, ch)) {
                board[x][y] = ch;
                if (dfs(board, M, N, x, y + 1)) {
                    return true;
                } else {
                    board[x][y] = '.';
                }
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int M, int N, int x, int y, char ch) {
        // 判断相同列是否存在重复数字
        for (int i = 0; i < M; i++) {
            if (board[i][y] == ch && i != x) {
                return false;
            }
        }

        // 判断相同行是否存在重复数字
        for (int j = 0; j < N; j++) {
            if (board[x][j] == ch && j != y) {
                return false;
            }
        }

        // 判断九宫格内是否存在相同数字
        int xLower = x / 3 * 3;
        int xUpper = xLower + 3;
        int yLower = y / 3 * 3;
        int yUpper = yLower + 3;
        for (int i = xLower; i < xUpper; i++) {
            for (int j = yLower; j < yUpper; j++) {
                if (board[i][j] == ch && i != x && j != y) {
                    return false;
                }
            }
        }

        return true;
    }

    private char[][] initBoard(String[] boardString) {
        int N = boardString.length;
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String string = boardString[i];
            char[] line = string.toCharArray();
            board[i] = line;
        }
        return board;
    }
}
