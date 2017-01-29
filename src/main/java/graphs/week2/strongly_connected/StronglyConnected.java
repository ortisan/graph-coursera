package graphs.week2.strongly_connected;

import java.util.*;

public class StronglyConnected {
    static boolean[] visited;
    static Stack<Integer> stack;

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj, ArrayList<Integer>[] reverse) {

        visited = new boolean[adj.length];
        stack = new Stack<Integer>();
        for (int i = 0; i < adj.length; i++) {
            if (visited[i] == false) {
                dfs(reverse, i);
            }
        }

        Iterator it = stack.iterator();
        visited = new boolean[adj.length];

        int numberStrongly = 0;

        while (it.hasNext()) {
            int v = stack.pop();
            if (!visited[v]) {
                dfs(adj, v);
                numberStrongly++;
            }
        }

        return numberStrongly;

    }

    private static void dfs(ArrayList<Integer>[] adj, int s) {
        visited[s] = true;
        List<Integer> neighbors = adj[s];
        if (neighbors.size() > 0) {
            for (Integer n : neighbors) {
                if (!visited[n]) {
                    dfs(adj, n);
                }

            }
        }
        stack.push(s);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] reverse = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            reverse[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            reverse[y - 1].add(x - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, reverse));
    }
}

