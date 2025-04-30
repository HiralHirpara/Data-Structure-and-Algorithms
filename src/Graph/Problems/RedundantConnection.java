package Graph.Problems;

import java.util.*;

public class RedundantConnection {
    private int[] findRedundantConnectionDFS(int[][] edges) {
        int n = edges.length;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0;i<=n;i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            boolean[] visited = new boolean[n+1];
            if(dfs(graph, visited, edge[0], -1)){
                return edge;
            }
        }
        return new int[0];
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int src, int parent){
        if(visited[src]){
            return true;
        }
        visited[src] = true;
        for(int i : graph.get(src)){
            if(i == parent){
                continue;
            }
            if(dfs(graph, visited, i, src)){
                return true;
            }
        }
        return false;
    }
    Set<Integer> nodes;
    int cycleStart;
    private int[] findRedundantConnectionDFSOptimal(int[][] edges){
        int n = edges.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i = 0;i<=n;i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n+1];
        nodes = new HashSet<>();
        cycleStart = -1;
        dfsOptimal(graph, visited, 1, -1);

//        System.out.println(nodes);
        for(int i = n-1;i>= 0;i--){
            int x = edges[i][0], y = edges[i][1];
            if(nodes.contains(x) && nodes.contains(y)){
                return new int[] {x, y};
            }
        }
        return  new int[0];
    }
    private boolean dfsOptimal(Map<Integer, List<Integer>> graph, boolean[] visited, int src, int parent){
        if(visited[src]){
            cycleStart = src;
            return true;
        }
        visited[src] = true;
        for(int i : graph.get(src)){
            if(i == parent){
                continue;
            }
            if(dfsOptimal(graph, visited, i, src)){
                if(cycleStart != -1) nodes.add(src);
                if(src == cycleStart) {
                    cycleStart = -1;
                }
                return true;
            }
        }
        return false;
    }

    private int[] findRedundantConnectionDisjointSet(int[][] edges){
        int n = edges.length;
        UnionFind unionFind = new UnionFind(n+1);

        for(int[] edge: edges){
            if(!unionFind.union(edge[0],edge[1])){
                return edge;
            }
        }

        return new int[0];
    }
    public static void main(String[] args) {
        RedundantConnection redundantConnection = new RedundantConnection();

        int[][] edges = {{1,2},{2,3},{3,4},{1,3}};

        System.out.println("\n:::::::Redundant Connection:::::::\n");

        // T(n) : O(E * (V+E)), S(n) :O(V+E)
        System.out.println("Result of DFS approach: "+ Arrays.toString(redundantConnection.findRedundantConnectionDFS(edges)));

        // T(n) : O((V+E)), S(n) :O(V+E)
        System.out.println("Result of DFS optimal approach: "+ Arrays.toString(redundantConnection.findRedundantConnectionDFSOptimal(edges)));

        // T(n) : O(V+(E * α(V))), S(n) :O(V)
        System.out.println("Result of Disjoint Set approach: "+ Arrays.toString(redundantConnection.findRedundantConnectionDisjointSet(edges)));
    }
}
