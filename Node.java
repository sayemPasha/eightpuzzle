package eightpuzzle;

import java.util.HashSet;
import java.util.PriorityQueue;

public class Node implements Comparable<Node> {

    State state;
    Node parent;
    int pathCost;

    public Node(State state, Node parent, int cost) {
        super();
        this.state = state;
        this.parent = parent;
        this.pathCost = cost;
    }

    public static Node A_star(State initial) {

        Node root = new Node(initial, null, 0);

        PriorityQueue<Node> fringe = new PriorityQueue<Node>();

        HashSet<State> closedList = new HashSet<State>();

        fringe.add(root);

        while (!fringe.isEmpty()) {

            Node front = fringe.poll();

            State currentState = front.state;

            // Graph search
            if (closedList.contains(currentState))
                continue;

            closedList.add(currentState);

            if (currentState.goal_test())
                return front;

            State successor;

            successor = front.state.move_up();
            if (successor != null)
                fringe.add(new Node(successor, front, front.pathCost + 1));

            successor = front.state.move_down();
            if (successor != null)
                fringe.add(new Node(successor, front, front.pathCost + 1));

            successor = front.state.move_left();
            if (successor != null)
                fringe.add(new Node(successor, front, front.pathCost + 1));

            successor = front.state.move_right();
            if (successor != null)
                fringe.add(new Node(successor, front, front.pathCost + 1));
        }

        return null;
    }

    public int heuristic() {

        // Use one of the following three heuristic function calls

        return state.manhattan_distance();

        // return state.misplaced_tiles();

        // return state.tiles_out_of_row_or_column();
    }

    public String toString() {
        return state + "\nPath cost = " + pathCost + "\n";
    }

    public static void print_soln(Node n) {

        if (n != null) {
            print_soln(n.parent);
            System.out.println(n);
        }
    }

    @Override
    public int compareTo(Node another) {

        return ((pathCost + heuristic()) - (another.pathCost + another
                .heuristic()));
    }
}
