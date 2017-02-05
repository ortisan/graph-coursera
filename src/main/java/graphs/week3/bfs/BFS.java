package graphs.week3.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    private static int distance(ArrayList<Integer>[] adj, int s, int t) {

        Integer[] distance = new Integer[adj.length];
        Queue<Integer> queue = new LinkedList<>();
        distance[s] = 0;

        queue.add(s);

        while (!queue.isEmpty()) {
            Integer v = queue.remove();
            ArrayList<Integer> adjsVertex = adj[v];
            for (Integer adjV : adjsVertex) {
                if (distance[adjV] == null) {
                    queue.add(adjV);
                    distance[adjV] = distance[v] + 1;
                }
            }
        }

        Integer distanceT = distance[t];
        if (distanceT != null) {
            return distanceT;
        } else {
            return -1;
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
        System.out.println(distance(adj, x, y));
    }

}

