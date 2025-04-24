package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public  static void bfs(ArrayList<Edge>[] graph, boolean vis[], Queue<Integer> queue){
        while(!queue.isEmpty()) {
            System.out.println(queue);
            // Pull the current element from Queue
            int curr = queue.poll();

            // check if curr is visited or not, if not then mark it as true in visited array
            if(!vis[curr]){
                vis[curr] = true;
            }
            // loop the curr element's all the edges  element
            for(int i = 0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                // if element is not visited then, add in queue
                if(!vis[e.getDestination()]){
                    queue.offer(e.getDestination());
                }
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

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        bfs(graph, new boolean[V], queue);

    }
}
