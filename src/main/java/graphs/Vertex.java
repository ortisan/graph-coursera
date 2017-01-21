package graphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by marcelo on 21/01/17.
 */
public class Vertex {

    private String name;

    private Set<Vertex> neighbors;

    private boolean visited;

    public Vertex(String name) {
        this.name = name;
        this.neighbors = new HashSet<>();
    }

    public void addNeighbors(Vertex... neighbors) {
        for (Vertex neighbor : neighbors) {
            this.neighbors.add(neighbor);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return name != null ? name.equals(vertex.name) : vertex.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "graphs.Vertex{" +
                "name='" + name + '\'' +
                '}';
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vertex> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Vertex> neighbors) {
        this.neighbors = neighbors;
    }
}
