package Graph.Problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
    private final int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private final int INF = 2147483647;
    private boolean[][] visit;
    private static int ROWS;
    private static int COLS;
    private final int GATE = 0;

    public void wallsAndGatesDFS(int[][] grid) {
        visit = new boolean[ROWS][COLS]; // only for DFS Approach
         for(int i =0;i<ROWS;i++){
             for(int j = 0;j<COLS;j++){
                 if(grid[i][j] == INF){
                     grid[i][j] = dfs(grid, i, j); // DFS + Backtracking
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

    public void wallsAndGatesBFS(int[][] grid) {
        for(int i =0;i<ROWS;i++){
            for(int j = 0;j<COLS;j++){
                if(grid[i][j] == INF){
                    grid[i][j] = bfs(grid, i, j); // BFS via INF
                }
            }
        }
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

    public void wallsAndGatesBFSviaGATES(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        for(int i =0;i<ROWS;i++){
            for(int j = 0;j<COLS;j++){
                if(grid[i][j] == GATE){
                   q.add(new int[] {i, j}); // BFS via GATES
                }
            }
        }
        if(q.size() == 0){
             return;
        }
        while (!q.isEmpty()){
            int[] node = q.remove();
            int row = node[0];
            int col = node[1];
            for(int[] dir : directions){
                int nr = row + dir[0];
                int nc = col + dir[1];
                if (nr >= ROWS || nc >= COLS || nr < 0 ||
                        nc < 0 || grid[nr][nc] != Integer.MAX_VALUE) {
                    continue;
                }
                q.add(new int[] { nr, nc });

                grid[nr][nc] = grid[row][col] + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{2147483647,-1,0,2147483647},
                        {2147483647,2147483647,2147483647,-1},
                        {2147483647,-1,2147483647,-1},
                        {0,-1,2147483647,2147483647}};

        ROWS = grid.length;
        COLS = grid[0].length;

        WallsAndGates wallsAndGates = new WallsAndGates();
        wallsAndGates.wallsAndGatesDFS(grid); // T(n) : O((m×n)²) , S(n) : O(m×n) for visit

        System.out.println("\n:::::::Walls And Gates:::::::\n");
        System.out.println("DFS Result is :");
        for(int[] g : grid){
            System.out.println(Arrays.toString(g));
        }
        grid = new int[][]{{0, -1}, {1, 2}};

        ROWS = grid.length;
        COLS = grid[0].length;
        System.out.println("\nBFS via INF Result is :");
        wallsAndGates.wallsAndGatesDFS(grid); // T(n) : O((m×n)²) , S(n) : O(m×n) per run
        for(int[] g : grid){
            System.out.println(Arrays.toString(g));
        }
        grid = new int[][]{{0, -1}, {1, 2}};

        ROWS = grid.length;
        COLS = grid[0].length;
        System.out.println("\nBFS via GATES Result is :");
        wallsAndGates.wallsAndGatesBFSviaGATES(grid); // T(n) : O(m×n) , S(n) : O(m×n) for queue
        for(int[] g : grid){
            System.out.println(Arrays.toString(g));
        }

    }
    /*
    Why we can't use DFS?
        - DFS can go deep before trying other routes. So it might fill a room with a longer path before it discovers a shorter one later.


    Why BFS works well?
        - You want the shortest distance to the nearest gate.
        - BFS is made for this — it explores all nodes level-by-level, so the first time you reach a room is the shortest path.


    What's the difference between doing BFS from INF cells vs BFS from gates (0s) in the problem?
        1. BFS from Gates (Correct Approach)
            - BFS from all gates simultaneously ensures each room gets filled with the shortest path to any gate.
                Because BFS expands level by level, the first time a room is reached, it's guaranteed to be the shortest path.
            - Efficient, correct, minimal updates.

        2. BFS from each INF (wrong or inefficient)
            - We have to run a separate BFS for each INF, which is super slow.
            - We might check the same cells many times.
            - It's harder to guarantee you found the nearest gate unless you carefully track distances and stop early.
            - Much slower and more complicated.

     */
}
