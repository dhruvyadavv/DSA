class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Map<Integer, List<Integer>> timeToIndexes = new TreeMap<>();
        for (int i = 0; i < meetings.length; i++) {
            timeToIndexes.putIfAbsent(meetings[i][2], new ArrayList<>());
            timeToIndexes.get(meetings[i][2]).add(i);
        }

        UF uf = new UF(n);
        uf.union(0, firstPerson);

        for (int time : timeToIndexes.keySet()) {
            Set<Integer> pool = new HashSet<>();

            for (int idx : timeToIndexes.get(time)) {
                int x = meetings[idx][0];
                int y = meetings[idx][1];
                uf.union(x, y);
                pool.add(x);
                pool.add(y);
            }

            for (int p : pool) {
                if (!uf.connected(0, p)) {
                    uf.reset(p);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.connected(i, 0)) ans.add(i);
        }
        return ans;
    }

    private static class UF {
        int[] parent, rank;

        UF(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        void union(int p, int q) {
            int rp = find(p);
            int rq = find(q);
            if (rp == rq) return;

            if (rank[rp] < rank[rq]) parent[rp] = rq;
            else {
                parent[rq] = rp;
                rank[rp]++;
            }
        }

        boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        void reset(int p) {
            parent[p] = p;
            rank[p] = 0;
        }
    }
}
