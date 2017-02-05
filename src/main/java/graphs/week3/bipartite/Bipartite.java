package graphs.week3.bipartite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {

    private static int bipartite(ArrayList<Integer>[] adj) {
        int s = 0;

        Integer[] groups = new Integer[adj.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        groups[s] = 1;

        while (!queue.isEmpty()) {
            Integer v = queue.remove();
            ArrayList<Integer> adjsVertex = adj[v];
            for (Integer adjV : adjsVertex) {
                if (groups[adjV] == null) {
                    queue.add(adjV);
                    groups[adjV] = 1 - groups[v];
                }

                if (groups[v] == groups[adjV]) {
                    return 0;
                }
            }
        }

        return 1;
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
        System.out.println(bipartite(adj));
    }
}

