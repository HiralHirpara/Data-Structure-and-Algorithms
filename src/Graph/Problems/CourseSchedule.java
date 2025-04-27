package Graph.Problems;

import java.util.*;


public class CourseSchedule {

    private boolean cycleDetectionDFS(int numCourses, int[][] prerequisite){
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i = 0 ;i<numCourses;i++){
            adjList.put(i, new ArrayList<>());
        }
        for(int i[] : prerequisite){
            adjList.get(i[0]).add(i[1]);
        }
        boolean[] visited = new boolean[numCourses];
        for(int i = 0;i<numCourses;i++){
            if(!dfs(adjList, i, visited)){
                return false;
            }
        }
        return true;
    }
    private static boolean dfs(Map<Integer, List<Integer>> adjList, int source, boolean[] visited){
        if(visited[source]){
            return false;
        }
        if(adjList.get(source).size() == 0){
            return true;
        }
        visited[source] = true;
        for(int i : adjList.get(source)){
            if(!dfs(adjList, i, visited)){
                return false;
            }
        }
        visited[source] = false;
        adjList.put(source, new ArrayList<>());
        return true;
    }

    private boolean topologicalSort(int numCourses, int[][] prerequisite){
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int i = 0 ;i<numCourses;i++){
            adjList.put(i, new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        for(int i[] : prerequisite){
            inDegree[i[1]]++;
            adjList.get(i[0]).add(i[1]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0;i<inDegree.length;i++){
            if(inDegree[i] == 0){
                q.add(i);
            }
        }
        int finishCourses = 0;
        while (!q.isEmpty()){
            int curr = q.remove();
            finishCourses++;
            for(int pre : adjList.get(curr)){
                inDegree[pre]--;
                if(inDegree[pre]== 0){
                    q.add(pre);
                }
            }
        }
        return finishCourses == numCourses;
    }
    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();

        int numCourses = 5;
        int[][] prerequisite = {{0,1},{0,2},{1,3},{1,4},{2,4},{3,4}};

        // T(n) : (V + E), S(n) : O(V+E)
        System.out.println("Result of Cycle Detection approach with DSF: " + courseSchedule.cycleDetectionDFS(numCourses, prerequisite));

        // T(n) : (V + E), S(n) : O(V+E)
        System.out.println("Result of Topological sort approach: " + courseSchedule.topologicalSort(numCourses, prerequisite));
    }
}
