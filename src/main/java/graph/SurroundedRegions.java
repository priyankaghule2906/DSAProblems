package graph;

public class SurroundedRegions {

    public static char[][] solve(char[][] board) {

        // traverse the boundaries to check if there is any O if yes we find all the connected regions with it and mark them
        // traverse remaining board and replace O with X
        int n = board.length;
        int m = board[0].length;
        for(int i=0;i<n;i++){
            if(board[i][0] == 'O') {
                dfs(board, i, 0);
            }

            if(board[i][m-1]=='O') {
                dfs(board, i, m-1);
            }
        }

        for(int j =0;j<m;j++){
            if(board[0][j] == 'O'){
                dfs(board, 0, j);
            }
            if(board[n-1][j] == 'O'){
                dfs(board, n-1, j);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(board[i][j]=='O'){
                    board[i][j] = 'X';
                } else if(board[i][j]=='T'){
                    board[i][j] = 'O';
                }
            }
        }



        return board;
    }

    static void dfs(char[][] board, int row, int col){

       if(!isValid(row, col, board)) return;

        board[row][col] = 'T';
        dfs(board, row+1, col);
        dfs(board, row-1, col);
        dfs(board, row, col+1);
        dfs(board, row, col-1);
    }

    static boolean isValid(int row, int col, char[][] board){
        int n = board.length;
        int m = board[0].length;
        if (row < 0 || row >= n || col <0 || col >= m || board[row][col]!='O') return  false;

        return true;
    }

    public static void main(String[] args) {
        char[][] mat = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };

        // Function call to replace surrounded 'O's with 'X's
        char[][] ans = solve(mat);

        int n = ans.length;
        int m = ans[0].length;

        // Output
        System.out.println("The updated matrix is:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }

    }
}
