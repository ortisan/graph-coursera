package graphs.week2.acyclicity;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {

    static boolean[] visited;
    static boolean[] recurStack;

    private static int acyclic(ArrayList<Integer>[] adj) {

        visited = new boolean[adj.length];
        recurStack = new boolean[adj.length];

        for (int v = 0; v < adj.length; v++) {
            if (explore(adj, v)) {
                return 1;
            }
        }
        return 0;
    }


    private static boolean explore(ArrayList<Integer>[] adj, int startVertex) {
        boolean hasCycle = false;

        visited[startVertex] = true;
        recurStack[startVertex] = true;

        ArrayList<Integer> neighbors = adj[startVertex];
        for (Integer n : neighbors) {
            if (!visited[n]) {
                if (explore(adj, n)) {
                    hasCycle = true;
                    break;
                }
            } else if (recurStack[n]) {
                hasCycle = true;
                break;
            }
        }

        recurStack[startVertex] = false;
        return hasCycle;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }
}

