package graphs.week5.connecting_points;

import java.util.*;

public class ConnectingPoints {

    private static Map<Vertex, Set<Vertex>> sets = new HashMap<>();

    private static double minimumDistance(int[] x, int[] y) {

        List<UndirectedWeightEdge> edges = new ArrayList<>();

        for (int i = 0; i < x.length; i++) {
            Vertex vertex1 = new Vertex(i);

            sets.put(vertex1, makeSet(vertex1));

            for (int j = i + 1; j < x.length; j++) {
                double dist = getDistanceBetweenPoints(x[i], y[i], x[j], y[j]);
                Vertex vertex2 = new Vertex(j);
                edges.add(new UndirectedWeightEdge(vertex1, vertex2, dist));

                sets.put(vertex2, makeSet(vertex2));
            }
        }

        // Kruskal algo

        Collections.sort(edges, Comparator.comparingDouble(UndirectedWeightEdge::getWeight));

        double result = 0.;

        for (UndirectedWeightEdge edge : edges) {
            Vertex u = edge.getV1();
            Vertex v = edge.getV2();

            Set<Vertex> setOfU = sets.get(u);
            Set<Vertex> setsOfV = sets.get(v);

            if (!setOfU.equals(setsOfV)) {

                result += edge.getWeight();

                union(u, v);
            }
        }

        // Set<Vertex> X = sets.get(new Vertex(0));

        return result;
    }

    private static double getDistanceBetweenPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static Set<Vertex> makeSet(Vertex vertex) {
        Set<Vertex> set = new HashSet<>();
        set.add(vertex);
        return set;
    }

    private static void union(Vertex u, Vertex v) {
        Set<Vertex> setOfU = sets.get(u);
        setOfU.addAll(sets.get(v));

        for (Vertex v1 : setOfU) {
            sets.replace(v1, setOfU);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }

    static class Vertex {
        private int id;

        public Vertex(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            return id == vertex.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "id=" + id +
                    '}';
        }
    }

    static class UndirectedWeightEdge {
        private Vertex v1;
        private Vertex v2;
        private double weight;

        public UndirectedWeightEdge(Vertex v1, Vertex v2, double weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        public Vertex getV1() {
            return v1;
        }

        public Vertex getV2() {
            return v2;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UndirectedWeightEdge that = (UndirectedWeightEdge) o;
            // Order doesnt matter
            return (v1.equals(that.v1) && v2.equals(that.v2)) || (v1.equals(that.v2) && v2.equals(that.v1));
        }

        @Override
        public int hashCode() {
            // Order doesnt matter.
            int min = Math.min(v1.hashCode(), v2.hashCode());
            int max = Math.max(v1.hashCode(), v2.hashCode());
            int result = min;
            result = 31 * result + max;
            return result;
        }

        @Override
        public String toString() {
            return "UndirectedWeightEdge{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    ", weight=" + weight +
                    '}';
        }
    }
}

