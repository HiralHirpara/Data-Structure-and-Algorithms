package Graph.Problems;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
    private final int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int orangesRottingBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int freshOrange = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1)
                    freshOrange++;
                if (grid[i][j] == 2) {
                    q.add(new int[] { i, j });
                }
            }
        }

        int min = 0;
        while (freshOrange > 0 && !q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int curr[] = q.remove();
                int r = curr[0];
                int c = curr[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && grid[nr][nc] == 1) {
                        freshOrange--;
                        grid[nr][nc] = 2;
                        q.add(new int[] { nr, nc });
                    }
                }
            }
            min++;
        }
        return freshOrange == 0 ? min : -1;
    }
    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};

        RottingOranges rottingOranges = new RottingOranges();

        System.out.println("\n:::::::Rotting Oranges:::::::\n");
        System.out.println("BFS Result is : "+ rottingOranges.orangesRottingBFS(grid));  // T(n) : O(m * n), S(n): O(m * n)
    }
}
