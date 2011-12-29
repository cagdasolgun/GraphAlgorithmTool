package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import testdata.TestDataVocabulary;
import testdata.TestDataVocabulary.Vertex1;
import testdata.TestDataVocabulary.Vertex4;
import util.GraphUtils;

public class GraphAlgorithmsTest {

	@Ignore
	@Test
	public void testGraphBipartite() throws Exception {
		// get test graph
		Graph graph = TestDataVocabulary.getSampleGraph();
		// print adjacenties
		System.out.println(graph.toString());
		// assert that if graph is bipartite
		// TODO not implemented!
		assertTrue(GraphAlgorithms.checkBipartite(graph));

	}

	@Test
	public void testShortestPath() throws Exception {
		// get sample graph
		Graph graph = TestDataVocabulary.getSampleGraph();
		// print adjacenties
		System.out.println(graph.toString());
		// find shortest path
		List<Vertex> shortestPath = GraphAlgorithms.getShortestPath(graph,
				graph.findVertex(Vertex1.V1), graph.findVertex(Vertex4.V4));
		// assert shortest path value
		assertEquals(7, GraphUtils.getPathWeight(graph, shortestPath));
		// print path
		GraphUtils.pathToString(shortestPath);

	}

	@Test
	public void testShortestPathWithAnotherTestData() throws Exception {

		// create vertices
		Vertex a = new Vertex("a", TestDataVocabulary.randomPointGenerator());
		Vertex b = new Vertex("b", TestDataVocabulary.randomPointGenerator());
		Vertex c = new Vertex("c", TestDataVocabulary.randomPointGenerator());
		Vertex d = new Vertex("d", TestDataVocabulary.randomPointGenerator());
		Vertex e = new Vertex("e", TestDataVocabulary.randomPointGenerator());
		Vertex f = new Vertex("f", TestDataVocabulary.randomPointGenerator());
		// create edges
		UndirectedEdge ab = new UndirectedEdge(4, a, b);
		UndirectedEdge ac = new UndirectedEdge(2, a, c);
		UndirectedEdge bc = new UndirectedEdge(1, b, c);
		UndirectedEdge bd = new UndirectedEdge(5, b, d);
		UndirectedEdge cd = new UndirectedEdge(8, c, d);
		UndirectedEdge ce = new UndirectedEdge(10, c, e);
		UndirectedEdge de = new UndirectedEdge(2, d, e);
		UndirectedEdge df = new UndirectedEdge(6, d, f);
		UndirectedEdge ef = new UndirectedEdge(3, e, f);
		// create graph
		Graph graph = new Graph("G");
		graph.addEdge(ab);
		graph.addEdge(ac);
		graph.addEdge(bc);
		graph.addEdge(bd);
		graph.addEdge(cd);
		graph.addEdge(ce);
		graph.addEdge(de);
		graph.addEdge(df);
		graph.addEdge(ef);
		// find shortest path from a to e
		List<Vertex> shortestPath = GraphAlgorithms
				.getShortestPath(graph, a, e);
		// assert shortest path value
		assertEquals(10, GraphUtils.getPathWeight(graph, shortestPath));
		// print path
		GraphUtils.pathToString(shortestPath);

	}
}
