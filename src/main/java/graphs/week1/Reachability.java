package graphs.week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reachability {
    private static boolean[] visited;

    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        visited = new boolean[adj.length];
        explorer(adj, x);
        if (visited[y]) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void explorer(ArrayList<Integer>[] adj, int startVertex) {
        visited[startVertex] = true;
        ArrayList<Integer> neighbors = adj[startVertex];
        for (Integer neighbor : neighbors) {
            if (!visited[neighbor]) {
                explorer(adj, neighbor);
            }
        }
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}

