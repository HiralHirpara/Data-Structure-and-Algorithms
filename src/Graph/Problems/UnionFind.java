package Graph.Problems;

public class UnionFind {
        private int[] root;
        private int[] rank;

        public UnionFind(int n){
            root = new int[n];
            rank = new int[n];

            for(int i = 0; i<n; i++){
                root[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int x){
            if(x == root[x])
                return x;
            return root[x] = find(root[x]);
        }
        public boolean union(int x, int y){
            int x1 = find(x);
            int y1 = find(y);

            if(x1 != y1){
                if(rank[x1] < rank[y1]){
                    root[x1] = root[y1];
                }else if(rank[x1] > rank[y1]){
                    root[y1] = root[x1];
                }else{
                    root[y1] = root[x1];
                    rank[x1] += 1;
                }
                return true;
            }
            return  false;
        }
}
