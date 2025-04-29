package Graph.Problems;


import java.util.*;

public class NumberOfConnectedComponentsInUG {

    private int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0;i<n;i++){
            graph.put(i, new ArrayList<>());
        }
        for(int i[] : edges){
            graph.get(i[0]).add(i[1]);
            graph.get(i[1]).add(i[0]);
        }

        boolean[] visited = new boolean[n];
        int result = 0;

        for(int i = 0;i<n;i++){
            if(!visited[i]){
                dfs(graph, visited, i);
                result++;
            }
        }
        return result;
    }
    private static void dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int src){
        visited[src] = true;
        for(int i: graph.get(src)){
            if(!visited[i]){
                dfs(graph, visited, i);
            }
        }
    }
    private int countComponentsBFS(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0;i<n;i++){
            graph.put(i, new ArrayList<>());
        }
        for(int i[] : edges){
            graph.get(i[0]).add(i[1]);
            graph.get(i[1]).add(i[0]);
        }

        boolean[] visited = new boolean[n];
        int result = 0;
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0;i<n;i++){
            if(!visited[i]){
                q.add(i);
                visited[i] = true;
               while (!q.isEmpty()){
                   int curr = q.remove();
                   for(int edge : graph.get(curr)){
                       if(!visited[edge]){
                           visited[edge] = true;
                           q.add(edge);
                       }
                   }
               }
                result++;
            }
        }
        return result;
    }

    private int countComponentsDisjointSet(int n, int[][] edges){
        UnionFind unionFind = new UnionFind(n);
        int count = n;

        for(int i[] : edges){
            if(unionFind.union(i[0], i[1])){
                count--;
            }
        }
        return count;
    }
    public static void main(String[] args) {

        NumberOfConnectedComponentsInUG ng = new NumberOfConnectedComponentsInUG();

        int n = 5;
        int[][] edges = {{0,1},{1,2}, {3,4}};

        System.out.println("\n:::::::Number of Connected Components in an Undirected Graph:::::::\n");

        // T(n) : O(E + V), S(n) : O(E + V)
        System.out.println("Result of DSF approach: " + ng.countComponents(n, edges));

        edges = new int[][] {{0, 1}, {0,2}, {1, 3}, {3, 4}};
        System.out.println("Result of BSF approach: " + ng.countComponentsBFS(n, edges));

        edges = new int[][] {{0, 1}, {3, 4}};

        // T(n) : O(V + E ⋅α(n)), S(n) :  O(V)
        // α(n) : inverse Ackermann function, Union function time complexity
        System.out.println("Result of Disjoint Set approach: " + ng.countComponentsDisjointSet(n, edges));
    }
}
