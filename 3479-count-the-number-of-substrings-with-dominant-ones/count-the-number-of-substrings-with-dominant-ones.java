class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        java.util.ArrayList<Integer> Z = new java.util.ArrayList<>();
        Z.add(-1);
        for (int i = 0; i < n; i++) if (s.charAt(i) == '0') Z.add(i);
        Z.add(n);
        int m = Z.size() - 2;

        long ans = 0, len = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') len++;
            else { ans += len * (len + 1) / 2; len = 0; }
        }
        ans += len * (len + 1) / 2;

        for (int k = 1; (long) k * (k + 1) <= n; k++) {
            long need = (long) k * (k + 1);
            for (int t = 1; t <= m - k + 1; t++) {
                int Lmin = Z.get(t - 1) + 1, Lmax = Z.get(t);
                int Rmin0 = Z.get(t + k - 1), Rmax = Z.get(t + k) - 1;
                if (Lmin > Lmax || Rmin0 > Rmax) continue;

                long totalR = Rmax - Rmin0 + 1;
                if (totalR <= 0) continue;

                long thresh = Rmin0 - (need - 1);
                int A = Lmin, B = Lmax;

                int s1 = A, e1 = (int) Math.min(B, thresh);
                if (s1 <= e1) ans += (long)(e1 - s1 + 1) * totalR;

                int s2 = (int) Math.max(A, thresh + 1), e2 = B;
                if (s2 <= e2) {
                    long C = (long) Rmax - need + 2;
                    int upper = (int) Math.min(e2, C - 1);
                    if (s2 <= upper) {
                        long cnt = upper - s2 + 1;
                        long sum = (long)(s2 + upper) * cnt / 2;
                        ans += cnt * C - sum;
                    }
                }
            }
        }
        return (int) ans;
    }
}
