class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

        List<Integer> arr = new ArrayList<>();
        arr.add(intervals[0][1] - 1);
        arr.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int s = intervals[i][0], e = intervals[i][1];
            int n = arr.size();
            int last = arr.get(n - 1), secondLast = arr.get(n - 2);

            if (s > last) {
                arr.add(e - 1);
                arr.add(e);
            } else if (s > secondLast) {
                arr.add(e);
            }
        }
        return arr.size();
    }
}
