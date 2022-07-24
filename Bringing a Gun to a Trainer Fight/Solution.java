//FAILED TEST CASES 1 AND 5
//TEST CASES 2, 3, 4, 6, 7, 8, 9, 10 PASSED

import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {

    }

    public static int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {
        int totalx = distance / dimensions[0] + 1;
        int totaly = distance / dimensions[1] + 1;

        Map<Location, Integer> guards = new HashMap<>();
        int dsq = distance * distance;
        for (int i = -totalx; i <= totalx; i++) {
            for (int j = -totaly; j <= totaly; j++) {

                Location g = getLocation(dimensions, trainer_position, i, j).rel(your_position);

                if (g.dist() <= dsq) {
                    Location bearing = g.bearing();
                    if (!guards.containsKey(bearing) || guards.get(bearing) > g.dist()) {
                        guards.put(bearing, g.dist());
                    }
                }

            }
        }
        for (int i = -totalx; i <= totalx; i++) {
            for (int j = -totaly; j <= totaly; j++) {

                Location me = getLocation(dimensions, your_position, i, j).rel(your_position);
                if (me.dist() > 0 && me.dist() <= dsq) {
                    Location bearing = me.bearing();
                    if (guards.containsKey(bearing) && guards.get(bearing) > me.dist()) {
                        guards.remove(bearing);
                    }
                }
            }
        }
        return guards.size();
    }

    private static Location getLocation(int[] dimensions, int[] pos, int boxx, int boxy) {
        int xg = project(dimensions[0], pos[0], boxx);
        int yg = project(dimensions[1], pos[1], boxy);
        return new Location(xg, yg);
    }

    private static int project(int size, int coord, int box) {

        if (box % 2 != 0) {
            coord = size - coord;
        }

        return box * size + coord;
    }


    private static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int dist() {
            return x * x + y * y;
        }

        public Location bearing() {
            int c = gcd(x, y);
            return new Location(x / c, y / c);
        }

        private static int gcd(int a, int b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x &&
                    y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Location rel(int[] position) {
            return new Location(this.x - position[0], this.y - position[1]);
        }
    }
}
