import java.util.*;

class Solution {
    public double largestTriangleArea(int[][] points) {
        // Step 1: Build convex hull (Monotone Chain algorithm)
        List<int[]> hull = convexHull(points);

        int h = hull.size();
        if (h < 3) return 0.0;

        double maxArea = 0.0;

        // Step 2: Rotating Calipers for maximum area triangle
        for (int i = 0; i < h; i++) {
            int k = (i + 2) % h; // third point
            for (int j = i + 1; j < h; j++) {
                // Move k to maximize area(i,j,k)
                while (true) {
                    double curr = area(hull.get(i), hull.get(j), hull.get(k));
                    double next = area(hull.get(i), hull.get(j), hull.get((k + 1) % h));
                    if (next > curr) {
                        k = (k + 1) % h;
                    } else {
                        break;
                    }
                }
                maxArea = Math.max(maxArea, area(hull.get(i), hull.get(j), hull.get(k)));
            }
        }

        return maxArea;
    }

    // Shoelace formula for triangle area
    private double area(int[] a, int[] b, int[] c) {
        return 0.5 * Math.abs(
            a[0] * (b[1] - c[1]) +
            b[0] * (c[1] - a[1]) +
            c[0] * (a[1] - b[1])
        );
    }

    // Convex Hull (Monotone Chain, O(n log n))
    private List<int[]> convexHull(int[][] points) {
        Arrays.sort(points, (p, q) -> p[0] == q[0] ? p[1] - q[1] : p[0] - q[0]);

        List<int[]> lower = new ArrayList<>();
        for (int[] p : points) {
            while (lower.size() >= 2 && cross(lower.get(lower.size() - 2), lower.get(lower.size() - 1), p) <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(p);
        }

        List<int[]> upper = new ArrayList<>();
        for (int i = points.length - 1; i >= 0; i--) {
            int[] p = points[i];
            while (upper.size() >= 2 && cross(upper.get(upper.size() - 2), upper.get(upper.size() - 1), p) <= 0) {
                upper.remove(upper.size() - 1);
            }
            upper.add(p);
        }

        lower.remove(lower.size() - 1);
        upper.remove(upper.size() - 1);
        lower.addAll(upper);

        return lower;
    }

    // Cross product (for convex hull)
    private long cross(int[] o, int[] a, int[] b) {
        return (long) (a[0] - o[0]) * (b[1] - o[1]) - (long) (a[1] - o[1]) * (b[0] - o[0]);
    }
}
