package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import testdata.TestDataVocabulary;
import testdata.TestDataVocabulary.Vertex1;
import testdata.TestDataVocabulary.Vertex2;
import testdata.TestDataVocabulary.Vertex3;
import testdata.TestDataVocabulary.Vertex4;
import util.GraphUtils;

public class GraphAlgorithmsTest {

	@Test
	public void testGraphBipartite() throws Exception {
		// get test graph
		Vertex v1 = new Vertex(Vertex1.V1, Vertex1.POINT1);
		Vertex v2 = new Vertex(Vertex2.V2, Vertex2.POINT2);
		Vertex v3 = new Vertex(Vertex3.V3, Vertex3.POINT3);
		Vertex v4 = new Vertex(Vertex4.V4, Vertex4.POINT4);

		UndirectedEdge edge1 = new UndirectedEdge(10, v1, v2);
		UndirectedEdge edge2 = new UndirectedEdge(2, v3, v4);
		UndirectedEdge edge3 = new UndirectedEdge(5, v1, v3);

		Graph graph = new Graph("G");
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);

		// assert that if graph is bipartite
		assertTrue(GraphAlgorithms.checkBipartite(graph));

	}

	@Test
	public void testShortestPathInUndirectedGraph() throws Exception {
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
	public void testShortestPathInUndirectedGraphWithAnotherTestData()
			throws Exception {

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
				.getShortestPath(graph, a, f);
		// assert shortest path value
		assertEquals(13, GraphUtils.getPathWeight(graph, shortestPath));
		// print path
		GraphUtils.pathToString(shortestPath);

	}

	@Ignore
	@Test
	public void testEulerianPathInGraph() throws Exception {

		// create vertices
		Vertex a = new Vertex("a", TestDataVocabulary.randomPointGenerator());
		Vertex b = new Vertex("b", TestDataVocabulary.randomPointGenerator());
		Vertex c = new Vertex("c", TestDataVocabulary.randomPointGenerator());
		Vertex d = new Vertex("d", TestDataVocabulary.randomPointGenerator());
		Vertex e = new Vertex("e", TestDataVocabulary.randomPointGenerator());

		// create edges
		UndirectedEdge ab = new UndirectedEdge(4, a, b);
		UndirectedEdge ac = new UndirectedEdge(2, a, c);
		UndirectedEdge ad = new UndirectedEdge(5, a, d);
		UndirectedEdge ae = new UndirectedEdge(7, a, e);
		UndirectedEdge be = new UndirectedEdge(9, b, e);
		UndirectedEdge cd = new UndirectedEdge(6, c, d);

		Graph graph = new Graph("G");
		graph.addEdge(ab);
		graph.addEdge(ac);
		graph.addEdge(ad);
		graph.addEdge(ae);
		graph.addEdge(be);
		graph.addEdge(cd);

		// check if graph has eulerian path
		assertTrue(GraphUtils.isEulerianPathExists(graph));

		// TODO bitmedi daha!

	}

	@Test
	public void testGraphConnectivity() throws Exception {
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

		if (GraphAlgorithms.isConnected(this, graph)) {
			System.out.println("Graph is connected");
		} else {
			System.out.println("Graph is not connected");
		}
	}

	@Test
	public void testIsomorphisimOfGivenGraphs() throws Exception {

		// build graph 1
		Vertex a = new Vertex("a", TestDataVocabulary.randomPointGenerator());
		Vertex b = new Vertex("b", TestDataVocabulary.randomPointGenerator());
		Vertex c = new Vertex("c", TestDataVocabulary.randomPointGenerator());
		Vertex d = new Vertex("d", TestDataVocabulary.randomPointGenerator());

		UndirectedEdge ab = new UndirectedEdge(0, a, b);
		UndirectedEdge ac = new UndirectedEdge(0, a, c);
		UndirectedEdge cd = new UndirectedEdge(0, c, d);
		UndirectedEdge bd = new UndirectedEdge(0, b, d);

		Graph g1 = new Graph("G1");
		g1.addEdge(ab);
		g1.addEdge(ac);
		g1.addEdge(cd);
		g1.addEdge(bd);

		// build graph 2
		Vertex u = new Vertex("u", TestDataVocabulary.randomPointGenerator());
		Vertex v = new Vertex("v", TestDataVocabulary.randomPointGenerator());
		Vertex y = new Vertex("y", TestDataVocabulary.randomPointGenerator());
		Vertex z = new Vertex("z", TestDataVocabulary.randomPointGenerator());

		UndirectedEdge uy = new UndirectedEdge(0, u, y);
		UndirectedEdge vz = new UndirectedEdge(0, v, z);
		UndirectedEdge uz = new UndirectedEdge(0, u, z);
		UndirectedEdge vy = new UndirectedEdge(0, v, y);

		Graph g2 = new Graph("G2");
		g2.addEdge(uy);
		g2.addEdge(vz);
		g2.addEdge(uz);
		g2.addEdge(vy);

		assertTrue(GraphAlgorithms.isIsomorphic(g1, g2));

	}

	@Test
	public void testPathFinding() throws Exception {
		// get test graph
		Vertex v1 = new Vertex(Vertex1.V1, Vertex1.POINT1);
		Vertex v2 = new Vertex(Vertex2.V2, Vertex2.POINT2);
		Vertex v3 = new Vertex(Vertex3.V3, Vertex3.POINT3);
		Vertex v4 = new Vertex(Vertex4.V4, Vertex4.POINT4);

		UndirectedEdge edge1 = new UndirectedEdge(10, v1, v2);
		UndirectedEdge edge2 = new UndirectedEdge(2, v3, v4);
		UndirectedEdge edge3 = new UndirectedEdge(5, v1, v3);

		Graph graph = new Graph("G");
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);

		ArrayList<Vertex> vertices = graph.getVertices();

		ArrayList<ArrayList<Vertex>> pathMap = GraphUtils.getPaths(vertices, 3);

		for (ArrayList<Vertex> arrayList : pathMap) {
			for (Vertex vertex : arrayList) {
				System.out.println(vertex.getLabel());
			}
			System.out.println("\n");

		}

	}
}
