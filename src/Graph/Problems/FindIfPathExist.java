package Graph.Problems;

import Graph.Edge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindIfPathExist {

    public static boolean validPathBFS(List<Edge>[] graph, boolean[] visited, int source, int destination) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()){
//            System.out.println(queue);
            int curr = queue.remove();

            if(curr == destination){
                return true;
            }
            for(int i = 0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                if(!visited[e.getDestination()]){
                    visited[e.getDestination()] = true;
                    queue.add(e.getDestination());
                }
            }
        }
        return false;
    }

    public  static boolean validPathDFS(List<Edge>[] graph, boolean[] visited, int curr, int destination){

        if(curr == destination){
            return  true;
        }
        visited[curr] = true;
//        System.out.print(curr + " ");
        for(int i = 0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!visited[e.getDestination()]){
                if(validPathDFS(graph, visited, e.getDestination(), destination)){
                    return true;
                }
            }
        }
        return  false;
    }

    public static void main(String[] args) {
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

        System.out.println("Find If Path Exist in Graph using BFS approach:");
        boolean result = validPathBFS(graph, new boolean[V] , 0, 4);
        System.out.println(result);

        System.out.println("\nFind If Path Exist in Graph using DFS approach:");
        result = validPathDFS(graph, new boolean[V], 0, 4);
        System.out.println(result);

        System.out.println("\nFind If Path Exist in Graph using DFS approach:");
        result = validPathDFS(graph, new boolean[V], 2, 5);
        System.out.println(result);

//         Time Complexity : O(V + E)
//         Space Complexity : O(V + E)
//          - Adjacency list: O(V+E) elements
//          - Queue/Stack : O(V)/O(E)
//          - Visited array: O(V)
    }
}
