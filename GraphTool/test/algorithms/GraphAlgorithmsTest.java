package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.Graph;
import graph.Vertex;

import java.util.List;

import logger.GraphLogger;

import org.junit.Test;

import testdata.TestDataVocabulary;
import testdata.TestDataVocabulary.Vertex1;
import testdata.TestDataVocabulary.Vertex4;
import util.GraphUtils;

public class GraphAlgorithmsTest {

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
}
