package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    public  static void bfs1(List<Edge>[] graph, boolean visited[], Queue<Integer> queue){
        while(!queue.isEmpty()) {
            System.out.println(queue);
            // Pull the current element from Queue
            int curr = queue.poll();

            // check if curr is visited or not, if not then mark it as true in visited array
            if(!visited[curr]) {
                visited[curr] = true;

                // loop the curr element's all the edges  element
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    // if element is not visited then, add in queue
                    if (!visited[e.getDestination()]) {
                        queue.offer(e.getDestination());
                    }
                }
            }
        }
    }

    public  static void bfs2(List<Edge>[] graph, boolean visited[], Queue<Integer> queue){
        while(!queue.isEmpty()) {
            System.out.println(queue);
            // Pull the current element from Queue
            int curr = queue.poll();

            // loop the curr element's all the edges  element
            for(int i = 0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                // if element is not visited then, add in queue
                if(!visited[e.getDestination()]){
                    queue.offer(e.getDestination());
                    visited[e.getDestination()]  = true;
                }
            }
        }
    }

    public static void bfs3(List<Edge>[] graph, int V){
        boolean visited[] = new boolean[V];
       for (int i = 0;i<V;i++) {
            if(!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                while (!queue.isEmpty()) {
                    System.out.println(queue);
                    // Pull the current element from Queue
                    int curr = queue.remove();

                    // loop the curr element's all the edges  element
                    if(!visited[i]) {
                        visited[i] = true;
                         for (int j = 0; j < graph[curr].size(); j++) {
                            Edge e = graph[curr].get(j);
                            // if element is not visited then, add in queue
                            if (!visited[e.getDestination()]) {
                                queue.offer(e.getDestination());
                            }
                        }
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

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        System.out.println("BFS Technique 1:");
        bfs1(graph, visited, queue); // this Technique is more Efficient

        System.out.println("\nBFS Technique 2:");
        queue = new LinkedList<>();
        queue.add(0);
        bfs2(graph, new boolean[V], queue);  // this Technique is more Efficient

        System.out.println("\nBFS Technique 3:");
        bfs3(graph, V);

        // Time Complexity : O(V + E)
        // Space Complexity : O(V)  : Visited array

    }
}
