package Graph.Problems;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
public class CloneGraph {
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
         return dfs(node, map);
//        return bfs(node, map);
    }

    private static Node bfs(Node node, Map<Node, Node> map){
           if(node == null){
               return null;
           }

           Queue<Node> q = new LinkedList<>();
           q.add(node);
           map.put(node, new Node(node.val));

           while(!q.isEmpty()){
               Node curr = q.remove();

               for(Node n : curr.neighbors){
                   if(!map.containsKey(n)){
                       Node newNode = new Node(n.val);
                       map.put(n, newNode);
                       q.add(n);
                   }
                   curr.neighbors.add(map.get(n));
               }
           }
           return map.get(node);
    }
    private static Node dfs(Node node, Map<Node, Node> map){
        if(node == null){
            return null;
        }
        if(map.containsKey(node)){
            return map.get(node);
        }
        Node newNode = new Node(node.val);
        map.put(node, newNode);

        for(Node n : node.neighbors){
            // System.out.println(node.val+":::"+n.val+"::");
            newNode.neighbors.add(dfs(n, map));
        }
        return newNode;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        CloneGraph cloneGraph = new CloneGraph();
        Node result = cloneGraph.cloneGraph(node1);

        if(result != node1){
            System.out.println("It's deep copy object");
        }else{
            System.out.println("It's shallow copy object");
        }
    }
}
