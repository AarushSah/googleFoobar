import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Cell {
    int row;
    int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Solution {
    private static final int wl = 1, nn = -1;

    private static int[][] findPathBFS(int[][] grid, int row, int col) {
        Queue<Cell> queue = new LinkedList<>();
        int height = grid.length, width = grid[0].length;
        int[][] costs = new int[height][width];
        Cell[] neighbors = new Cell[]{new Cell(1, 0), new Cell(-1, 0), new Cell(0, -1), new Cell(0, 1)};

        for (int[] r : costs) {
            Arrays.fill(r, nn);
        }
        costs[row][col] = 1;

        queue.add(new Cell(row, col));
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            for (Cell n : neighbors) {

                int r = n.row + cell.row, c = n.col + cell.col;

                boolean inBounds = r >= 0 && r < height && c >= 0 && c < width;
                if (inBounds && costs[r][c] == nn) {

                    costs[r][c] = costs[cell.row][cell.col] + 1;

                    if (grid[r][c] != wl) {
                        queue.add(new Cell(r, c));
                    }
                }
            }
        }

        return costs;
    }

    public static int solution(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] topBottom = findPathBFS(grid, 0, 0);
        int[][] bottomTop = findPathBFS(grid, n - 1, m - 1);

        int cost = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (topBottom[i][j] != nn && bottomTop[i][j] != nn) {
                    cost = Math.min(cost, topBottom[i][j] + bottomTop[i][j] - 1);
                }
            }
        }

        return cost;
    }
}