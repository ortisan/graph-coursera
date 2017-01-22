package graphs.week1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static boolean[] visited;

    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        visited = new boolean[adj.length];
        int numComponents = 0;
        for (int vertex = 0; vertex < adj.length; vertex++) {
            if (!visited[vertex]) {
                explore(adj, vertex);
                numComponents++;
            }
        }
        return numComponents;
    }

    private static void explore(ArrayList<Integer>[] adj, int startVertex) {
        visited[startVertex] = true;
        ArrayList<Integer> neighbors = adj[startVertex];
        for (Integer neighbor : neighbors) {
            if (!visited[neighbor]) {
                explore(adj, neighbor);
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
        System.out.println(numberOfComponents(adj));
    }
}

