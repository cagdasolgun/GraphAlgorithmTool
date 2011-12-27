package algorithms;

import graph.Edge;
import graph.Graph;

import org.junit.Test;

import testdata.TestDataVocabulary.Edge1;
import testdata.TestDataVocabulary.Edge2;
import testdata.TestDataVocabulary.Vertex1;
import testdata.TestDataVocabulary.Vertex3;

public class GraphAlgorithmsTest {

	@Test
	public void testGraphBipartite() throws Exception {
		Edge edge1 = Edge1.getEdge();
		Edge edge2 = Edge2.getEdge();
		Edge edge3 = new Edge(5, Vertex1.getVertex(), Vertex3.getVertex());

		Graph graph = new Graph("G");
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);

		System.out.println(graph.toString());

		GraphAlgorithms.checkBipartite(graph);

	}

}
