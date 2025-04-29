package Graph.Problems;

import java.util.*;

public class GraphValidTree {

    private boolean  validTreeDFS(int n, int[][] edges){
        if(edges.length != n-1) return false;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i = 0;i<n;i++){
            graph.put(i, new ArrayList<>());
        }
        for(int i[] : edges){
            graph.get(i[0]).add(i[1]);
            graph.get(i[1]).add(i[0]);
        }
        Set<Integer> visited = new HashSet<>();
        if(!(dfs(graph, visited, 0, -1))){
            return false;
        }
        return visited.size() == n;
    }
    private boolean dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int src, int parent){
        if(visited.contains(src)){
            return false;
        }
        visited.add(src);
        for(int i: graph.get(src)){
            if(i == parent){
                continue;
            }
            if(!dfs(graph, visited, i, src)){
                return false;
            }
        }
        return true;
    }
    private boolean validTreeBFS(int n, int[][] edges){
        if(edges.length != n-1) return false;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int i = 0;i<n;i++){
            graph.put(i, new ArrayList<>());
        }
        for(int i[] : edges){
            graph.get(i[0]).add(i[1]);
            graph.get(i[1]).add(i[0]);
        }
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, -1});
        visited.add(0);
        while(!q.isEmpty()){
            int[] curr = q.remove();
            int node = curr[0];
            int parent = curr[1];

            for(int i : graph.get(node)){
                if(i == parent){
                    continue;
                }
                if(visited.contains(i)){
                    return false;
                }
                visited.add(i);
                q.add(new int[] {i, node});
            }
         }
        return visited.size() == n;
    }

    private boolean validTreeDisjointSet(int n, int[][] edges){
        if(edges.length != n-1) return false;

        UnionFind unionFind = new UnionFind(n);

        for(int i [] : edges){
            if(!unionFind.union(i[0], i[1])){
                return false;
            }
        }
        return  true;
    }

    public static void main(String[] args) {
        GraphValidTree graphValidTree = new GraphValidTree();
        int n  = 5;
        int[][] edges = {{0,1},{1,2},{2,3},{3,4}};

        System.out.println("\n:::::::Graph Valid Tree:::::::\n");

        // T(n) : O(N + E), S(n) : O(N + E)
        System.out.println("Result of Cycle Detection approach with DSF: " + graphValidTree.validTreeDFS(n, edges));

        edges = new int[][] {{0, 1}, {0,2}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println("Result of Cycle Detection approach with BSF: " + graphValidTree.validTreeBFS(n, edges));

        // α(N) is the Inverse Ackermann Function.
        // T(n) : O(N⋅α(N)) , S(n) : O(n)
        edges =  new int[][] {{0,1},{1,2},{2,3},{3,4}};
        System.out.println("Result of Disjoint Set approach: " + graphValidTree.validTreeDisjointSet(n, edges));

    }
}
