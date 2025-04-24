package Graph;

import java.util.ArrayList;

public class DepthFirstSearch {

    public static  void dfs(ArrayList<Edge>[] graph, boolean vis[], int curr){
        if(vis[curr]){
            return;
        }
        vis[curr] = true;
        System.out.println(curr);
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.getDestination()]){
                dfs(graph, vis, e.getDestination());
            }
        }
    }
    public static void main(String[] args) {
        // Array of Arraylist for Graph AdjacencyList implementation
        int V = 5;
        ArrayList<Edge>[] graph = new ArrayList[V];

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

        dfs(graph, new boolean[V], 0);
    }
}
