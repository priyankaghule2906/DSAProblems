package hashing;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EqualRowColumnPair {

    @Test
    public void test(){
        int[][] ints = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        System.out.println(equalPairsOptimized(
                ints));
    }

    public int equalPairsOptimized(int[][] grid) {
        int n = grid.length;
        int count = 0;
        Map<String, Integer> rowMap = new HashMap<>();

        // Store all rows as strings in the hashmap
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(grid[i][j]).append(",");
            }
            String rowStr = row.toString();
            rowMap.put(rowStr, rowMap.getOrDefault(rowStr, 0) + 1);
        }

        // Compare columns with the rows stored in the hashmap
        for (int j = 0; j < n; j++) {
            StringBuilder col = new StringBuilder();
            for (int i = 0; i < n; i++) {
                col.append(grid[i][j]).append(",");
            }
            String colStr = col.toString();
            count += rowMap.getOrDefault(colStr, 0);
        }

        return count;
    }

    public int equalPairs(int[][] grid) {
        int count =0;
        int n = grid.length;


        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                boolean isEqual = true;
                for(int k=0;k<n;k++){
                    if(grid[i][k] != grid[k][j]){
                        isEqual= false;
                        break;
                    }
                }
                if(isEqual){
                    count++;
                }
            }

        }

        return count;
    }
}
