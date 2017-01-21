package graphs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marcelo on 21/01/17.
 */
public class Explorer {

    private List<Vertex> visitedOrder;

    public Explorer() {
        this.visitedOrder = new ArrayList<>();
    }

    public void exploreGraph(List<Vertex> graph) {
        for (Vertex vertex : graph) {
            explore(vertex);
        }
    }

    private void explore(Vertex vertex) {
        if (!vertex.isVisited()) {
            vertex.setVisited(true);
            visitedOrder.add(vertex);
            Iterator<Vertex> iterator = vertex.getNeighbors().iterator();
            while (iterator.hasNext()) {
                Vertex neighbor = iterator.next();
                explore(neighbor);
            }
        }
    }

    public List<Vertex> getVisitedOrder() {
        return visitedOrder;
    }

}
