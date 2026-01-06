class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int neg=0;
        long sum=0;
        int minvalue=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                sum+=Math.abs(matrix[i][j]);
                minvalue=Math.min(minvalue,Math.abs(matrix[i][j]));
                if(matrix[i][j]<0){
                    neg++;
                }
            }
        }
         if(neg%2==0){
                    return sum;
                }
                return sum-2*minvalue;
    }
}