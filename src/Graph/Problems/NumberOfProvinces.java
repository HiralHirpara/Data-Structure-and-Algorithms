package Graph.Problems;


import java.util.*;

public class NumberOfProvinces {
    private int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = n;
        UnionFind unionFind = new UnionFind(n);

        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                if(isConnected[i][j] == 1){
                    if(unionFind.union(i, j)){
                        count--;
                    }
                }
            }
        }
        return count;
    }

    private int findCircleNumDFS(int[][] isConnected){
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0;i<n;i++){
            if(!visited[i]){
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }
    private void dfs(int [][] graph, boolean[] visited, int src){
        visited[src] = true;
        for(int i = 0;i< graph.length;i++){
            if(graph[src][i] == 1 && !visited[i]){
                dfs(graph,visited, i);
            }
        }
    }

    private int findCircleNumBFS(int[][] isConnected){
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0;i<n;i++){
            if(!visited[i]){
                count++;
                bfs(isConnected, visited, i);
            }
        }
        return count;
    }
    private void bfs(int[][] graph, boolean[] visited, int src){
        Queue<Integer> q = new LinkedList<>();
        q.add(src);
        visited[src] = true;

        while (!q.isEmpty()){
            int curr = q.remove();

            for(int i = 0;i< graph.length;i++){
                if(graph[src][i] == 1 && !visited[i]){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
         NumberOfProvinces numberOfProvinces = new NumberOfProvinces();

        int[][] cities = {{1,1,0},{1,1,0},{0,0,1}};

        System.out.println("\n:::::::Number of Provinces:::::::\n");

        // T(n) = O(n^2), S(n) : O(n);
        System.out.println("Result of Disjoint Set approach: "+numberOfProvinces.findCircleNum(cities));

        // T(n) = O(n^2), S(n) : O(n);
        System.out.println("Result of DFS approach: "+numberOfProvinces.findCircleNumDFS(cities));

        // T(n) = O(n^2), S(n) : O(n);
        System.out.println("Result of BFS approach: "+numberOfProvinces.findCircleNumBFS(cities));
    }
}
