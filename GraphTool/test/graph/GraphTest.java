package graph;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import testdata.TestDataVocabulary.Edge1;
import testdata.TestDataVocabulary.Vertex1;
import testdata.TestDataVocabulary.Vertex2;

public class GraphTest {

	private static final String G1 = "G1";

	@Test
	public void testVertexCreating() throws Exception {
		// create vertex
		Vertex v = new Vertex(Vertex1.V1, Vertex1.POINT1);
		// assert label
		assertEquals(Vertex1.V1, v.getLabel());
		// assert point
		assertEquals(Vertex1.POINT1, v.getPoint());
	}

	@Test
	public void testEdgeCreating() throws Exception {
		// create first vertex
		Vertex v1 = new Vertex(Vertex1.V1, Vertex1.POINT1);
		// create second vertex
		Vertex v2 = new Vertex(Vertex2.V2, Vertex2.POINT2);
		// create an edge
		Edge e = new Edge(Edge1.WEIGHT, v1, v2);
		// assert first vertex in edge
		assertTrue(e.has(v1));
		// assert second vertex in edge
		assertTrue(e.has(v2));
		// assert weight
		assertEquals(10, e.getWeight());
	}

	@Test
	public void testGraphCreating() throws Exception {
		// create vertex1
		Vertex vertex1 = new Vertex("d", new Point(12, 24));
		// create vertex2
		Vertex vertex2 = new Vertex("f", new Point(5, 54));
		// create an edge
		Edge edge1 = Edge1.getEdge();
		// create a graph
		Graph g = new Graph(G1);
		// assert graph label
		assertEquals(G1, g.getLabel());
		// add vertex1 to graph
		g.addVertex(vertex1);
		// add vertex2 to graph
		g.addVertex(vertex2);
		// add edge to graph
		g.addEdge(edge1);
		// assert vertex1 existence in graph
		assertTrue(g.hasVertex(vertex1));
		// assert vertex2 existence in graph
		assertTrue(g.hasVertex(vertex2));
		// assert edge1 existence in graph
		assertTrue(g.hasEdge(edge1));

	}
}
