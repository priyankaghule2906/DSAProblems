package arrays;

import org.junit.Test;

import java.util.Arrays;

public class SetMatrixZeros {

    @Test
    public void test(){

        int[][]  mat1 =  {{1,1,1},{1,0,1},{1,1,1}};
        int[][]  mat2 =  {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        int[][]  mat3 =  {{1,2,3,4},{5,0,7,8},{0,10,11,12},{13,14,15,0}};
//        setZeroes(mat1);
        setZeroesO(mat3);
    }

    public static void setZeroesO(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int rowColO = 1;

        for (int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                if(matrix[i][j] ==0){
                    matrix[i][0] = 0;
                    if(j==0){
                        rowColO=0;
                    } else {
                        matrix[0][j] = 0;
                    }
                }
            }
        }

        for(int i =1;i<rows;i++){
            for(int j=1;j<cols;j++){
                if (matrix[i][j] != 0) {
                    if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        if(matrix[0][0] == 0){
            Arrays.fill(matrix[0], 0);
        }
        if(rowColO == 0){
            for (int i=0;i<rows;i++){
                matrix[i][0] = 0;
            }
        }


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void setZeroes(int[][] matrix) {
        // this approach take O(m+n) space

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean row[] = new boolean[rows];
        boolean col[] = new boolean[cols];

        for (int i=0; i<rows;i++){
            for(int j=0;j<cols;j++){
                if(matrix[i][j] ==0){
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for(int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if(row[i] || col[j]){
                    matrix[i][j] =0;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}
