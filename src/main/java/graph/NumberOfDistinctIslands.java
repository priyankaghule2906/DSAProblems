package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfDistinctIslands {
    // Main function to count the number of distinct islands

    public static int numDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Set<String> distinctIslands = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    List<String> shape = new ArrayList<>();
                    dfs(grid, i, j, i, j, shape);
                    distinctIslands.add(String.join(",", shape));
                }
            }
        }

        return distinctIslands.size();
    }

    // DFS helper function to explore the grid
    private static void dfs(int[][] grid, int baseX, int baseY, int x, int y, List<String> shape) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) {
            return;
        }

        grid[x][y] = 0;  // Mark the cell as visited
        shape.add((x - baseX) + ":" + (y - baseY)); // Record relative position

        // Explore in all 4 directions
        dfs(grid, baseX, baseY, x + 1, y, shape);
        dfs(grid, baseX, baseY, x - 1, y, shape);
        dfs(grid, baseX, baseY, x, y + 1, shape);
        dfs(grid, baseX, baseY, x, y - 1, shape);
    }

    // Test cases
    public static void main(String[] args) {
        // Test Case 1: Basic example
        int[][] grid1 = {
//                {1, 1, 0, 0, 0},
//                {1, 1, 0, 0, 0},
//                {0, 0, 0, 1, 1},
//                {0, 0, 0, 1, 1}
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        };
        System.out.println("Test Case 1: " + numDistinctIslands(grid1)); // Output: 1

        /*// Test Case 2: Two distinct islands
        int[][] grid2 = {
                {1, 1, 0, 0},
                {1, 0, 0, 1},
                {0, 0, 1, 1},
                {0, 0, 0, 0}
        };
        System.out.println("Test Case 2: " + numDistinctIslands(grid2)); // Output: 2

        // Test Case 3: No islands
        int[][] grid3 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println("Test Case 3: " + numDistinctIslands(grid3)); // Output: 0

        // Test Case 4: All cells are part of one big island
        int[][] grid4 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println("Test Case 4: " + numDistinctIslands(grid4)); // Output: 1

        // Test Case 5: Multiple identical islands
        int[][] grid5 = {
                {1, 0, 1, 0},
                {1, 0, 1, 0},
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {1, 0, 1, 0}
        };
        System.out.println("Test Case 5: " + numDistinctIslands(grid5)); // Output: 1

        // Test Case 6: Complex islands
        int[][] grid6 = {
                {1, 1, 0, 1},
                {1, 0, 0, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 0}
        };
        System.out.println("Test Case 6: " + numDistinctIslands(grid6)); // Output: 4*/
    }
}

