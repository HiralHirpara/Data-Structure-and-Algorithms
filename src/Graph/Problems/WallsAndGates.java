package Graph.Problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    private final int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private final int INF = 2147483647;
    private boolean[][] visit;
    private int ROWS;
    private int COLS;
//    visit = new boolean[ROWS][COLS]; // only for DFS Approach
    public void wallsAndGates(int[][] grid) {
        ROWS = grid.length;
        COLS = grid[0].length;

         for(int i =0;i<ROWS;i++){
             for(int j = 0;j<COLS;j++){
                 if(grid[i][j] == INF){
//                     grid[i][j] = dfs(grid, i, j); // DFS + Backtracking
                     grid[i][j] = bfs(grid, i, j); // BFS
                 }
             }

         }
    }
    private int dfs(int[][] grid, int r, int c){
        if(r < 0 || r >= ROWS || c < 0 || c >= COLS ||
        grid[r][c] == -1 || visit[r][c]){
            return INF;
        }
        if(grid[r][c]== 0){
            return 0;
        }
        visit[r][c] = true;
        int res = INF;
        for(int dir[] : directions){
            int curr = dfs(grid, r + dir[0], c + dir[1]);
            if(curr != INF){
                res = Math.min(res, 1 + curr);
            }
        }
        visit[r][c] = false;
        return res;
    }

    private int bfs(int[][] grid, int r, int c){
        Queue<int[]> q = new LinkedList<>();
        visit = new boolean[ROWS][COLS];
        q.add(new int[]{r, c});
        visit[r][c] = true;
        int steps = 0;
        while (!q.isEmpty()){
            int qsize = q.size();
            for(int i = 0;i<qsize;i++){
                 int[] curr = q.remove();
                 int row = curr[0];
                 int col = curr[1];

                 if(grid[row][col] == 0){
                     return steps;
                 }
                 for(int[] dir  : directions){
                     int nr = row + dir[0], nc = col + dir[1];
                     if(nr >= 0 && nc >= 0 && nr < ROWS && nc < COLS && grid[nr][nc] != -1 && !visit[nr][nc]){
                         q.add(new int[]{nr, nc});
                         visit[nr][nc] = true;
                     }
                 }
            }
            steps++;
        }
        return INF;
    }

    public static void main(String[] args) {
        int[][] grid = {{2147483647,-1,0,2147483647},
                        {2147483647,2147483647,2147483647,-1},
                        {2147483647,-1,2147483647,-1},
                        {0,-1,2147483647,2147483647}};



        WallsAndGates wallsAndGates = new WallsAndGates();
        wallsAndGates.wallsAndGates(grid);
        System.out.println("Result is :");
        for(int[] g : grid){
            System.out.println(Arrays.toString(g));
        }

    }
}
