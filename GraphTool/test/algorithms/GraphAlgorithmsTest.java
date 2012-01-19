package algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
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

		String message = "";
		ArrayList<Vertex> pathOrCircuit = GraphAlgorithms.findEuler(a, graph,
				message);

		if (pathOrCircuit != null) {
			System.out.println(message);
			Iterator<Vertex> iterator = pathOrCircuit.iterator();
			while (iterator.hasNext()) {
				Vertex vertex = (Vertex) iterator.next();
				System.out.print("->" + vertex.getLabel());
			}
		} else {
			System.out.println(message);
		}

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

		if (GraphAlgorithms.isConnected(graph)) {
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

	@Test
	public void testHamiltonPath() throws Exception {
		// get test graph
		Vertex A = new Vertex("A", TestDataVocabulary.randomPointGenerator());
		Vertex B = new Vertex("B", TestDataVocabulary.randomPointGenerator());
		Vertex C = new Vertex("C", TestDataVocabulary.randomPointGenerator());
		Vertex D = new Vertex("D", TestDataVocabulary.randomPointGenerator());
		Vertex E = new Vertex("E", TestDataVocabulary.randomPointGenerator());
		Vertex F = new Vertex("F", TestDataVocabulary.randomPointGenerator());
		Vertex G = new Vertex("G", TestDataVocabulary.randomPointGenerator());

		UndirectedEdge e1 = new UndirectedEdge(A, G);
		UndirectedEdge e2 = new UndirectedEdge(A, F);
		UndirectedEdge e3 = new UndirectedEdge(A, E);
		UndirectedEdge e4 = new UndirectedEdge(A, C);
		UndirectedEdge e5 = new UndirectedEdge(B, F);
		UndirectedEdge e6 = new UndirectedEdge(B, E);
		UndirectedEdge e7 = new UndirectedEdge(B, C);
		UndirectedEdge e8 = new UndirectedEdge(C, D);
		UndirectedEdge e9 = new UndirectedEdge(D, G);
		UndirectedEdge e10 = new UndirectedEdge(D, F);
		UndirectedEdge e11 = new UndirectedEdge(D, E);

		Graph graph = new Graph("G");

		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		graph.addEdge(e4);
		graph.addEdge(e5);
		graph.addEdge(e6);
		graph.addEdge(e7);
		graph.addEdge(e8);
		graph.addEdge(e9);
		graph.addEdge(e10);
		graph.addEdge(e11);

		ArrayList<Vertex> pathOrCircuit = GraphAlgorithms
				.findHamiltonPathOrCircuit(graph);

		ArrayList<Vertex> expectedPath = new ArrayList<Vertex>();
		expectedPath.add(G);
		expectedPath.add(A);
		expectedPath.add(F);
		expectedPath.add(B);
		expectedPath.add(E);
		expectedPath.add(D);
		expectedPath.add(C);

		assertEquals(expectedPath, pathOrCircuit);

	}

	@Ignore
	@Test
	public void testGraphColoring() throws Exception {
		// get test graph
		Vertex A = new Vertex("A", TestDataVocabulary.randomPointGenerator());
		Vertex B = new Vertex("B", TestDataVocabulary.randomPointGenerator());
		Vertex C = new Vertex("C", TestDataVocabulary.randomPointGenerator());
		Vertex D = new Vertex("D", TestDataVocabulary.randomPointGenerator());
		Vertex E = new Vertex("E", TestDataVocabulary.randomPointGenerator());
		Vertex F = new Vertex("F", TestDataVocabulary.randomPointGenerator());
		Vertex G = new Vertex("G", TestDataVocabulary.randomPointGenerator());

		UndirectedEdge e1 = new UndirectedEdge(A, G);
		UndirectedEdge e2 = new UndirectedEdge(A, F);
		UndirectedEdge e3 = new UndirectedEdge(A, E);
		UndirectedEdge e4 = new UndirectedEdge(A, C);
		UndirectedEdge e5 = new UndirectedEdge(B, F);
		UndirectedEdge e6 = new UndirectedEdge(B, E);
		UndirectedEdge e7 = new UndirectedEdge(B, C);
		UndirectedEdge e8 = new UndirectedEdge(C, D);
		UndirectedEdge e9 = new UndirectedEdge(D, G);
		UndirectedEdge e10 = new UndirectedEdge(D, F);
		UndirectedEdge e11 = new UndirectedEdge(D, E);

		Graph graph = new Graph("G");

		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		graph.addEdge(e4);
		graph.addEdge(e5);
		graph.addEdge(e6);
		graph.addEdge(e7);
		graph.addEdge(e8);
		graph.addEdge(e9);
		graph.addEdge(e10);
		graph.addEdge(e11);
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLACK);
		colors.add(Color.BLUE);
		colors.add(Color.GREEN);
		colors.add(Color.ORANGE);
		colors.add(Color.PINK);
		colors.add(Color.RED);
		colors.add(Color.YELLOW);

		ArrayList<Vertex> vertices = graph.getVertices();
		ArrayList<Vertex> unColoredVertices = graph.getUnColoredVertices();
		colorGraph(vertices, unColoredVertices, colors, graph);

		Iterator<Vertex> iterator = graph.getVertices().iterator();
		while (iterator.hasNext()) {
			Vertex vertex = (Vertex) iterator.next();
			System.out.println("Name : " + vertex.getLabel() + " Colour : "
					+ vertex.getColor());

		}

	}

	/**
	 * @param vertices
	 * @param unColoredVertices
	 * @param colors
	 * @param graph
	 */
	private void colorGraph(ArrayList<Vertex> vertices,
			ArrayList<Vertex> unColoredVertices, ArrayList<Color> colors,
			Graph graph) {
		ArrayList<Vertex> unColored = generateDecreasingOrderDegrees(vertices);
		int currentColor = 0;
		while (unColoredVertices.size() > 0) {
			Vertex first = unColored.get(0);
			unColored.remove(first);
			first.setColor(colors.get(currentColor));
			ArrayList<Vertex> coloredWithCurrent = new ArrayList<Vertex>();
			coloredWithCurrent.add(first);
			ArrayList<Vertex> toBeRemoved = new ArrayList<Vertex>();
			for (int i = 0; i < unColored.size(); i++) {
				Vertex vertex = unColored.get(i);
				if (hasNoAdjacency(vertex, coloredWithCurrent)) {
					vertex.setColor(colors.get(currentColor));
					coloredWithCurrent.add(vertex);
					toBeRemoved.add(vertex);
				}
			}
			for (int i = 0; i < toBeRemoved.size(); i++) {
				unColored.remove(toBeRemoved.get(i));
			}
			currentColor++;
			unColoredVertices = graph.getUnColoredVertices();
		}
	}

	private boolean hasNoAdjacency(Vertex vertex,
			ArrayList<Vertex> coloredWithCurrent) {
		Iterator<Vertex> iterator = coloredWithCurrent.iterator();
		while (iterator.hasNext()) {
			Vertex controlVertex = (Vertex) iterator.next();
			if (controlVertex.getNeighbours().contains(vertex)) {
				return false;
			}
		}

		return true;
	}

	public int colorGraph(Graph graph) {
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLACK);
		colors.add(Color.BLUE);
		colors.add(Color.GREEN);
		colors.add(Color.ORANGE);
		colors.add(Color.PINK);
		colors.add(Color.RED);
		colors.add(Color.YELLOW);

		ArrayList<Vertex> vertices = graph.getVertices();
		ArrayList<Vertex> unColored = generateDecreasingOrderDegrees(vertices);
		int currentColor = 0;
		while (graph.getUnColoredVertices().size() > 0) {
			Vertex first = unColored.get(0);
			unColored.remove(first);
			first.setColor(colors.get(currentColor));
			ArrayList<Vertex> coloredWithCurrent = new ArrayList<Vertex>();
			coloredWithCurrent.add(first);
			ArrayList<Vertex> toBeRemoved = new ArrayList<Vertex>();
			for (int i = 0; i < unColored.size(); i++) {
				Vertex vertex = unColored.get(i);
				if (vertex.getNeighbours().contains(coloredWithCurrent)) {
					vertex.setColor(colors.get(currentColor));
					coloredWithCurrent.add(vertex);
					toBeRemoved.add(vertex);
				}
			}
			for (int i = 0; i < toBeRemoved.size(); i++) {
				unColored.remove(toBeRemoved.get(i));
			}
			currentColor++;
		}
		return currentColor;
	}

	private ArrayList<Vertex> generateDecreasingOrderDegrees(
			ArrayList<Vertex> vertexes) {

		for (int i = 0; i < vertexes.size() - 1; i++) {

			for (int j = 1; j < vertexes.size() - i; j++) {
				if (vertexes.get(j).getNeighbours().size() > vertexes
						.get(j - 1).getNeighbours().size()) {
					Vertex temp = vertexes.get(j - 1);
					vertexes.set(j - 1, vertexes.get(j));
					vertexes.set(j, temp);

				}
			}

		}
		return vertexes;
	}

	/**
	 * @param colors
	 * @param current
	 * @param graph
	 *            TODO
	 * @param conflictEdges
	 *            TODO
	 * @param coloredVertices
	 *            TODO
	 */
	@SuppressWarnings("unused")
	private void colorNeighbour(ArrayList<Color> colors, Vertex current,
			Graph graph, ArrayList<UndirectedEdge> conflictEdges,
			ArrayList<Vertex> coloredVertices) {
		System.out.println("current vertex is " + current.getLabel()
				+ ". colored with " + current.getColor());
		ArrayList<Vertex> uncoloredNeighbours = GraphUtils
				.getUncoloredNeighbours(current);
		if (uncoloredNeighbours.size() > 0) {
			ArrayList<Color> temp = colors;
			temp.remove(current.getColor());
			for (Color color : temp) {
				System.out.print(color + " - ");
			}
			Vertex next = uncoloredNeighbours.get(0);
			next.setColor(temp.get(0));
			colorNeighbour(colors, next, graph, conflictEdges, coloredVertices);
		}
	}

}
