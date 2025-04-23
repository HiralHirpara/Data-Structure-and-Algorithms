package Graph;

import java.util.ArrayList;

public class AdjacencyListWithArrayList {

    public static void main(String[] args) {
        // Array of Arraylist for Graph AdjacencyList implementation
        ArrayList<Edge>[] graph = new ArrayList[4];

        for(int i = 0;i< graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,3));
        graph[2].add(new Edge(2,0));

        graph[3].add(new Edge(3,0));
        graph[3].add(new Edge(3,1));
    }
}
