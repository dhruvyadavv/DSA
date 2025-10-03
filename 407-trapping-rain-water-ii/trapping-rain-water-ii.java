class Solution {
    public static class Pair{
        int val;
        int i;
        int j;
        Pair(int val,int i,int j){
            this.val = val;
            this.i = i;
            this.j = j;
        }
    }
    public int trapRainWater(int[][] a) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((x,y)->x.val-y.val);
        int ans = 0;
        int n = a.length;
        int m = a[0].length;
        boolean vis[][] = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j =0;j<m;j++){
                if(i==0 || i==n-1 || j==0 || j==m-1){
                    vis[i][j] = true;
                    pq.add(new Pair(a[i][j],i,j));
                }
            }
        }
        int dr[][] = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!pq.isEmpty()){
            Pair temp = pq.remove();
            int val = temp.val;
            int x = temp.i;
            int y = temp.j;
            for(int dir[]:dr){
                int rw = x + dir[0];
                int cl = y + dir[1];
                if(rw>=0 && rw<n && cl>=0 && cl<m && !vis[rw][cl]){
                    vis[rw][cl] = true;
                    ans +=Math.max(0,val-a[rw][cl]);
                    pq.add(new Pair(Math.max(val,a[rw][cl]),rw,cl));
                }
            }
        }
        return ans;
    }
}