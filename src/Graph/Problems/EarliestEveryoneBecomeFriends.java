package Graph.Problems;

public class EarliestEveryoneBecomeFriends {
    private int earliestAcq(int[][] logs, int n) {
        UnionFind unionFind = new UnionFind(n);
        int groupCount = n;
        for (int i[] : logs){
            if(unionFind.union(i[1], i[2])){
                groupCount--;

                if(groupCount == 1){
                    return i[0];
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] logs = {{20190101,0,1},{20190104,3,4},{20190107,2,3},{20190211,1,5},{20190224,2,4},{20190301,0,3},{20190312,1,2},{20190322,4,5}};

        EarliestEveryoneBecomeFriends becomeFriends = new EarliestEveryoneBecomeFriends();
        System.out.println("\n:::::::The Earliest Moment When Everyone Become Friends:::::::\n");

        // T(n): O(N + M log M +M α(N)) , S(n) :  O(N + log M)
        System.out.println("Result of Disjoint Set approach: "+ becomeFriends.earliestAcq(logs, n));

        /*
        * Time Complexity :
        *  Sorting : O(M log M)
        *  Initialize the array of group IDs : O(N)
        *  Union function : O(M α(N))
        * */
    }
}
