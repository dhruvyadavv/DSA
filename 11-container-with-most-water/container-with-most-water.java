class Solution {
    public int maxArea(int[] height) {
        int maxwater = 0;
        int n = height.length;
        int lp = 0;
        int rp = n - 1;
        while (lp < rp) {
            int w = rp - lp;
            int ht = Math.min(height[rp], height[lp]);
            int currentwater = w * ht;
            maxwater = Math.max(currentwater, maxwater);
            if (height[lp] < height[rp]) {
                lp++;
            } else {
                rp--;
            }
        }
        return maxwater;

    }
}