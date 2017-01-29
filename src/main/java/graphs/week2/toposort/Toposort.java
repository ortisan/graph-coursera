package graphs.week2.toposort;

import java.util.*;

// ASSUMING THIS IS A DAG
public class Toposort {

    static boolean[] visited;
    static Stack<Integer> stack;

    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        visited = new boolean[adj.length];
        stack = new Stack<Integer>();

        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]) {
                dfs(adj, i);
            }
        }

        ArrayList<Integer> order = new ArrayList<>(stack);
        Collections.reverse(order);
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int s) {
        visited[s] = true;
        List<Integer> neighbors = adj[s];
        for (Integer n : neighbors) {
            if (!visited[n]) {
                dfs(adj, n);
            }

        }
        stack.push(s);
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
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

