package Graph;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Edge {

    private int source;
    private int destination;
    private int weight;

    public Edge(int source, int destination){
        this.source = source;
        this.destination = destination;
    }

    public Edge(int source, int destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
