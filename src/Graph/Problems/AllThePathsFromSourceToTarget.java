package Graph.Problems;

import Graph.Edge;

import java.util.*;

public class AllThePathsFromSourceToTarget {

    public static void allPathsSourceTargetBFS(List<Edge>[] graph, int curr,List<List<Integer>> resultList){
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(curr));

        while (!queue.isEmpty()){
           List<Integer> path = queue.poll();
           curr = path.get(path.size()-1);
           if(curr == graph.length-1) {
               resultList.add(new ArrayList(path));
           }
            for(int i = 0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                List<Integer> result = new ArrayList(path);
                result.add(e.getDestination());
                queue.add(result);
            }
        }
//        System.out.println(resultList);
    }

    public static void allPathsSourceTargetDFS(List<Edge>[] graph, int curr, List<Integer> result, List<List<Integer>> resultList) {
        result.add(curr);
        if(curr == graph.length-1){
            resultList.add(new ArrayList(result));
            return;
        }
        for(int i = 0;i<graph[curr].size();i++){
           Edge e = graph[curr].get(i);
           allPathsSourceTargetDFS(graph, e.getDestination(), result, resultList);
           result.remove(result.size()-1);
        }
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge>[] graph = new ArrayList[V];
        for(int i = 0;i< graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[1].add(new Edge(1,3));
        graph[2].add(new Edge(2,3));

        // if the graph is undirected or if graph has cycle then we need  visited boolean array
        List<List<Integer>> resultList = new ArrayList<>();
        allPathsSourceTargetDFS(graph,0,  new ArrayList<>(), resultList);
        System.out.println("First DFS Result: "+ resultList);

        resultList = new ArrayList<>();
        allPathsSourceTargetBFS(graph,0, resultList);
        System.out.println("First BFS Result: "+ resultList);



        V = 5;
        graph = new ArrayList[V];
        for(int i = 0;i< graph.length;i++){
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,3));
        graph[0].add(new Edge(0,4));
        graph[1].add(new Edge(1,3));
        graph[1].add(new Edge(1,2));
        graph[1].add(new Edge(1,4));
        graph[2].add(new Edge(2,3));
        graph[3].add(new Edge(3,4));


        resultList = new ArrayList<>();
        allPathsSourceTargetDFS(graph,0,  new ArrayList<>(), resultList);
        System.out.println("Second DFS Result: "+resultList);

        resultList = new ArrayList<>();
        allPathsSourceTargetBFS(graph,0, resultList);
        System.out.println("Second BFS Result: "+ resultList);
    }
}
