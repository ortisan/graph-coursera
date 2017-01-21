package graph;

import graphs.Explorer;
import graphs.Vertex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelo on 21/01/17.
 */
public class Week1Tests {

    @Test
    public void testExplorer() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");

        // Vertex has (->) vertexes as neighbors
        // a -> [b]
        // b -> [d,a]
        // c -> [d]
        // d -> [b,c]
        a.addNeighbors(b);
        b.addNeighbors(d, a);
        c.addNeighbors(d);
        d.addNeighbors(b, c);

        List<Vertex> graph = new ArrayList<>();
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);

        Explorer explorer = new Explorer();
        explorer.exploreGraph(graph);

        List<Vertex> visitedVertex = explorer.getVisitedOrder();

        Assert.assertEquals(visitedVertex.size(), graph.size());
        Assert.assertEquals(visitedVertex.get(0), a);
        Assert.assertEquals(visitedVertex.get(1), b);
        Assert.assertEquals(visitedVertex.get(2), d);
        Assert.assertEquals(visitedVertex.get(3), c);
    }

}
