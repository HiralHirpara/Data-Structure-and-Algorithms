package Graph.Problems;

import java.util.*;


public class CourseScheduleII {
    private int[] cycleDetectionDFS(int numCourses, int[][] prerequisite){
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for(int i= 0;i<numCourses;i++){
            adjList.put(i, new ArrayList<>());
        }

        for(int[] i : prerequisite){
            adjList.get(i[0]).add(i[1]);
        }

        boolean[] cycle = new boolean[numCourses];
        Set<Integer> visit = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for(int i = 0;i<numCourses;i++){
            if(!dfs(adjList, i, cycle, result, visit)){
                return new int[0];
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    private static boolean dfs( Map<Integer, List<Integer>> adjList, int src, boolean[] cycle, List<Integer> result, Set<Integer> visit){
        if(cycle[src]){
            return false;
        }
        if(visit.contains(src)){
            return true;
        }
        cycle[src] = true;
        for(int i : adjList.get(src)){
            if(!dfs(adjList, i, cycle, result, visit)){
                return false;
            }
        }
        cycle[src] = false;
        visit.add(src);
        result.add(src);
        return true;
    }

    private int[] topologicalSort(int numCourses, int[][] prerequisite){
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] inDegreee = new int[numCourses];

        for(int i = 0;i<numCourses;i++){
            adjList.put(i, new ArrayList<>());
        }

        for(int i[] : prerequisite){
            inDegreee[i[1]]++;
            adjList.get(i[0]).add(i[1]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i= 0 ;i<inDegreee.length; i++){
            if(inDegreee[i] == 0){
                q.add(i);
            }
        }
        int finish  = 0;
        int[] result = new int[numCourses];

        while (!q.isEmpty()){
            int curr = q.remove();
           result[numCourses-finish-1] = curr;
            finish++;
            for(int i: adjList.get(curr)){
                inDegreee[i]--;
                if(inDegreee[i] == 0){
                    q.add(i);
                }
            }
        }

        return numCourses != finish ? new int[0] : result;
    }

    public static void main(String[] args) {
        CourseScheduleII courseScheduleII= new CourseScheduleII();
        int numCourses = 5;
        int[][] prerequisite = {{0,1},{0,2},{1,3},{1,4},{2,4},{3,4}};

        // T(n) : (V + E), S(n) : O(V+E)
        System.out.println("Result of Cycle Detection approach with DSF: " + Arrays.toString(courseScheduleII.cycleDetectionDFS(numCourses, prerequisite)));

        // T(n) : (V + E), S(n) : O(V+E)
        System.out.println("Result of Topological sort approach: " + Arrays.toString(courseScheduleII.topologicalSort(numCourses, prerequisite)));
    }

}
