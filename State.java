package eightpuzzle;

import java.util.Objects;

public class State {
    int board[][], bR, bC;

    public State(int[][] board) {
        super();
        this.board = board;

        search_blank();
    }

    public void search_blank() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                if (board[i][j] == 0) {
                    bR = i;
                    bC = j;

                    return;
                }
        }
    }

    public State move_up() {

        if (bR == 0)
            return null;

        int newBoard[][] = new int[3][3];

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                newBoard[i][j] = board[i][j];

        int temp = newBoard[bR][bC];
        newBoard[bR][bC] = newBoard[bR - 1][bC];
        newBoard[bR - 1][bC] = temp;

        return new State(newBoard);
    }

    public State move_down() {

        // Complete here
        if(bR == 2){
            return null;
        }
        int newBoard[][] = new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                newBoard[i][j] = board[i][j];
            }
        }

        int temp = newBoard[bR][bC];
        newBoard[bR][bC] = newBoard[bR+1][bC];
        newBoard[bR+1][bC] = temp;

        return new State(newBoard);
    }

    public State move_left() {

        // Complete here
        if(bC == 0){
            return null;
        }
        int newBoard[][] = new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                newBoard[i][j] = board[i][j];
            }
        }

        int temp = newBoard[bR][bC];
        newBoard[bR][bC] = newBoard[bR][bC-1];
        newBoard[bR][bC-1] = temp;

        return new State(newBoard);
    }

    public State move_right() {

        // Complete here
        if(bC == 2){
            return null;
        }
        int newBoard[][] = new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                newBoard[i][j] = board[i][j];
            }
        }

        int temp = newBoard[bR][bC];
        newBoard[bR][bC] = newBoard[bR][bC+1];
        newBoard[bR][bC+1] = temp;

        return new State(newBoard);
    }

    public boolean goal_test() {

        // Complete here
        int cnt = 1;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(i == 2 && j == 2){
                    continue;
                }
                if(cnt++ != board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {

        String s = "";

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j)
                s += board[i][j] + " ";

            s += "\n";
        }

        return s;
    }

    public int manhattan_distance() {

        int h = 0;
        // Complete here
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int row, col, x = board[i][j];
                if(x == 0)continue; //nothing for zero
                if(x%3 == 0){
                    row = x/3-1;
                    col = 2;
                }
                else{
                    row = x/3;
                    col = x%3 - 1;
                }
                h += (Math.abs(row-i) + Math.abs(col-j));
            }
        }

        return h;
    }

    public int misplaced_tiles() {

        int h = 0;

        // Complete here
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int row, col, x = board[i][j];
                if(x == 0)continue; //nothing for zero
                if(x%3 == 0){
                    row = x/3-1;
                    col = 2;
                }
                else{
                    row = x/3;
                    col = x%3 - 1;
                }
                h += (i==row && j == col ? 0 : 1);
            }
        }

        return h;
    }

    public int tiles_out_of_row_or_column() {

        int h = 0;

        // Complete here
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int row, col, x = board[i][j];
                if(x == 0)continue; //nothing for zero
                if(x%3 == 0){
                    row = x/3-1;
                    col = 2;
                }
                else{
                    row = x/3;
                    col = x%3 - 1;
                }
                h += (i == row ? 0:1);
                h += (j == col ? 0:1);
            }
        }

        return h;
    }

    @Override
    public boolean equals(Object obj) {

        State rhs = (State) obj;

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j)
                if (board[i][j] != rhs.board[i][j])
                    return false;

        return true;
    }

    @Override
    public int hashCode() {

        // return Arrays.deepHashCode(board);

        return Objects
                .hash(board[0][0], board[0][1], board[0][2], board[1][0],
                        board[1][1], board[1][2], board[2][0], board[2][1],
                        board[2][2]);
    }
}
