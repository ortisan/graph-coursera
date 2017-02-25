package graphs.week5.clustering;

import com.sun.javafx.geom.Edge;

import java.util.*;

public class Clustering {

    private static Map<Vertex, Set<Vertex>> sets = new HashMap<>();

    private static double clustering(int[] x, int[] y, int k) {

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

        List<UndirectedWeightEdge> edgesOfTree = new ArrayList<>();

        for (UndirectedWeightEdge edge : edges) {
            Vertex u = edge.getV1();
            Vertex v = edge.getV2();

            Set<Vertex> setOfU = sets.get(u);
            Set<Vertex> setsOfV = sets.get(v);

            if (!setOfU.equals(setsOfV)) {
                edgesOfTree.add(edge);
                union(u, v);
            }
        }

        // Order desc to get the k-2 edge of max distances
        Collections.sort(edgesOfTree, (e1, e2) -> Double.compare(e2.getWeight(), e1.getWeight()));
        if (k < 2) {
            k = 2;
        }
        UndirectedWeightEdge maxK1Edge = edgesOfTree.get(k - 2);

        return maxK1Edge.getWeight();
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
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

            Clustering.Vertex vertex = (Clustering.Vertex) o;

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
        private Clustering.Vertex v1;
        private Clustering.Vertex v2;
        private double weight;

        public UndirectedWeightEdge(Clustering.Vertex v1, Clustering.Vertex v2, double weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        public Clustering.Vertex getV1() {
            return v1;
        }

        public Clustering.Vertex getV2() {
            return v2;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Clustering.UndirectedWeightEdge that = (Clustering.UndirectedWeightEdge) o;
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

