package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

    public static void dfs1(List<Edge>[] graph, boolean visited[], int curr){
        if(visited[curr]){
            return;
        }
        visited[curr] = true; // Each node is visited once : O(V)
        System.out.println(curr);
        for(int i = 0;i<graph[curr].size();i++){  // look at each edge once through the adjacency list : O(E)
            Edge e = graph[curr].get(i);
            if(!visited[e.getDestination()]){
                dfs1(graph, visited, e.getDestination());
            }
        }
    }

    public static void dfs2(List<Edge>[] graph, boolean visited[], Stack<Integer> stack){
        while(!stack.isEmpty()) {
            System.out.println(stack);
            int curr = stack.pop();
            if(!visited[curr]) {
                visited[curr] = true; // Each node is visited once : O(V)
                for (int i = 0; i < graph[curr].size(); i++) {  // look at each edge once through the adjacency list : O(E)
                    Edge e = graph[curr].get(i);
                    if (!visited[e.getDestination()]) {
                        stack.push(e.getDestination());
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        // Array of Arraylist for Graph AdjacencyList implementation
        int V = 5;
        List<Edge>[] graph = new ArrayList[V];

        for(int i = 0;i< graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));
        graph[1].add(new Edge(1,2));

        graph[2].add(new Edge(2,4));
        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,1));

        graph[3].add(new Edge(3,0));
        graph[3].add(new Edge(3,1));

        graph[4].add(new Edge(4,2));

        System.out.println("DFS Technique 1: Recursive");
        dfs1(graph, new boolean[V], 0);  // More efficient technique

        System.out.println("\nDFS Technique 2: Iterative");
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        dfs2(graph, new boolean[V], stack);

        // Time Complexity : O(V + E)
        // Space Complexity : O(V)  : Visited array
    }
}
