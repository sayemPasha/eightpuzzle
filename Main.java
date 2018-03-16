package eightpuzzle;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        int board[][] = generate_random_board();

        State initial = new State(board);

        System.out.println("Random board: ");
        System.out.println(initial);

        Node goal = Node.A_star(initial);

        if (goal != null) {
            System.out.println("\nSolution sequence:\n");
            Node.print_soln(goal);
        } else
            System.out.println("No solution found.");
    }

    static int[][] generate_random_board() {

        int[][] board = new int[3][3];
        ArrayList<Integer> permutation = new ArrayList<Integer>();

        for (int i = 0; i < 9; ++i)
            permutation.add(i);

        Collections.shuffle(permutation);

        int k = 0;
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                board[i][j] = permutation.get(k);
                k++;
            }

        return board;
    }
}

class Test{
    public static void main(String[] args) {

        //int board[][] = { {7,2,4}, {5,0,6}, {8,3,1}};
        int board[][] = { {1,2,3}, {4,5,6}, {7,8,0}};

        State initial = new State(board);

        System.out.println("Random board: ");
        System.out.println(initial);

        System.out.println(initial.manhattan_distance());
        System.out.println(initial.misplaced_tiles());
        System.out.println(initial.tiles_out_of_row_or_column());

        System.out.println(initial);
        //move
        System.out.println(initial.move_up());
        System.out.println(initial.move_down());
        System.out.println(initial.move_left());
        System.out.println(initial.move_right());

        System.out.println(initial.goal_test());
    }
}