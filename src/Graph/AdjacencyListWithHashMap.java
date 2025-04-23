package Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyListWithHashMap {

    HashMap<Integer, List<Integer>> adjacencyList;

    public AdjacencyListWithHashMap(){
        adjacencyList = new HashMap<>();
    }

    public void addVertex(int v){
        adjacencyList.put(v, new LinkedList<>());
    }

    public void addEdge(int source, int destination){
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    public void removeEdge(int source, int destination ){
        adjacencyList.get(source).remove(destination);
        adjacencyList.get(destination).remove(source);
    }

    public  void removeVertex(int v){
        adjacencyList.remove(v);
        for (List<Integer> edges : adjacencyList.values()){
            edges.removeIf(n -> n == v); // removes all references to 'v'
        }
    }

    public void printGraph(){
        for(Integer e : adjacencyList.keySet()){
            System.out.println("Vertex is: "+ e + " and edges are :"+ adjacencyList.get(e));
        }
    }
    public static void main(String[] args) {
       AdjacencyListWithHashMap graph = new AdjacencyListWithHashMap();

       // Add Vertex
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        // Add Edges between each Vertex
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2,4);
        graph.addEdge(3,4);
        graph.addEdge(4, 1);

        // print the Graph Vertex and Edges
        graph.printGraph();

        System.out.println("\nUpdated Graph after Vertex removal:----");
        // Remove Vertex 2;
        graph.removeVertex(2);

        // print updated Graph Vertex and Edges
        graph.printGraph();

    }
}
