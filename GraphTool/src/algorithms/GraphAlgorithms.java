package algorithms;

import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;
import graph.pair.Pair;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import logger.GraphLogger;
import util.GraphUtils;

public class GraphAlgorithms {

	public static boolean checkBipartite(Graph graph) throws Exception {
		// get all vertices
		ArrayList<Vertex> vertices = graph.getVertices();
		// pick an initial color
		Color initialColor = Color.BLACK;
		// get initial vertex
		Vertex initialVertex = vertices.get(0);
		// paint initial vertex with initial color
		initialVertex.setColor(initialColor);
		// paint initial vertex neighbors
		for (Vertex neighbour : initialVertex.getNeighbours()) {
			neighbour.setColor(Color.RED);
		}
		// get first unpainted neighbor which has a painted neighbor
		Pair pair = graph.getUnpaintedNeighbour();
		while (pair != null) {
			Vertex vertexToPaint = (Vertex) pair.getLeft();
			Color colorToPaintVertex = (Color) pair.getRight();
			GraphLogger.getLogger().info(
					"Current pair :" + vertexToPaint.getLabel() + ","
							+ colorToPaintVertex.toString());

			vertexToPaint.setColor((Color) pair.getRight());

			for (Vertex neighbour : vertexToPaint.getNeighbours()) {
				if (neighbour.getColor() == null) {
					neighbour.setColor((Color) pair.getRight());
				}
			}
			pair = graph.getUnpaintedNeighbour();
		}

		for (Vertex item : graph.getVertices()) {
			for (Vertex neighbour : item.getNeighbours()) {
				System.out.println(item.getLabel() + " " + item.getColor()
						+ "------" + neighbour.getLabel() + " "
						+ neighbour.getColor());
				if (item.getColor().equals(neighbour.getColor())) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * finds shortest path between given vertices
	 * 
	 * @param graph
	 * 
	 * @param from
	 *            is a {@link Vertex} as initial node
	 * @param to
	 *            is a {@link Vertex} as destination
	 * @return {@link List} that contains vertices in path
	 */
	public static List<Vertex> getShortestPath(Graph graph, Vertex from,
			Vertex to) {
		// path vector that contains path
		List<Vertex> path = new ArrayList<Vertex>();
		// value all other vertices as infinite
		graph.setValueToOtherVertices(from);
		// seach destination vertex
		searchPath(graph, from, to, path);
		// return path
		return path;
	}

	/**
	 * searches next vertex to move in shortest path algorithm
	 * 
	 * @param graph
	 *            is a {@link Graph} that we search shortest path
	 * @param from
	 *            is a {@link Vertex} that indicates current node
	 * @param to
	 *            is a {@link Vertex} that indicates destination node
	 * @param path
	 *            is a {@link List} that contains shortest path
	 */
	private static void searchPath(Graph graph, Vertex from, Vertex to,
			List<Vertex> path) {
		GraphLogger.getLogger().info("current node is : " + from.getLabel());
		// add current vertice to path
		path.add(from);
		Vertex sourceVertex = null;
		// check if destination is reached
		if (!from.equals(to)) {
			sourceVertex = from;
			// set current node as visited
			sourceVertex.setVisited(true);
			// get vertex to go
			Vertex vertexToGo = from.getMinimumValuedUnvisitedNeighbour(graph);
			// calculate value if we move to this vertex
			int h = graph.getEdgeWeight(from, vertexToGo) + from.getValue();
			// check if it is shorter
			if (h < vertexToGo.getValue()) {
				vertexToGo.setValue(h);
				from.setVisited(true);
			}
			// search again
			searchPath(graph, vertexToGo, to, path);
		}
	}

	public static boolean isIsomorphic(Graph g1, Graph g2) throws Exception {
		return (GraphUtils.getOrder(g1) == GraphUtils.getOrder(g2)
				&& (GraphUtils.getSize(g1) == GraphUtils.getSize(g2)) && checkDegreeMap(
					g1, g2));
	}

	private static boolean checkDegreeMap(Graph g1, Graph g2) throws Exception {
		return GraphUtils.getDegreeMap(g1).equals(GraphUtils.getDegreeMap(g2));
	}

	public static boolean isConnected(Graph graph) {
		ArrayList<Vertex> vertexes = graph.getVertices();

		if (vertexes != null && !vertexes.isEmpty()) {
			if (vertexes.size() == 1) {
				return true;
			}
			Vertex vertex = vertexes.get(0);
			GraphUtils.visit(graph, vertex);
			if (GraphUtils.hasVisitedNeighbour(graph.getVertices())) {
				GraphUtils.unvisitAllVertexes(vertexes);
				return false;
			}
			GraphUtils.unvisitAllVertexes(vertexes);
			return true;
		}
		GraphUtils.unvisitAllVertexes(vertexes);
		return false;
	}

	public static ArrayList<Vertex> findEuler(Vertex vertex, Graph graph,
			String message) {
		Graph initialGraph = graph;
		ArrayList<Vertex> visited = new ArrayList<Vertex>();
		ArrayList<UndirectedEdge> removedEdges = new ArrayList<UndirectedEdge>();

		UndirectedEdge edge = GraphUtils.selectAppropriateEdge(vertex, graph);
		if (edge == null) {
			return null;
		}
		removedEdges.add(edge);
		visited.add(vertex);
		ArrayList<Vertex> visitedVertices = GraphUtils.traverseForEuler(vertex,
				edge, visited, removedEdges, graph);
		graph = initialGraph;
		return visitedVertices;
	}

	public static ArrayList<Vertex> findHamiltonPathOrCircuit(Graph graph) {
		ArrayList<Vertex> visitingVertices = new ArrayList<Vertex>();
		int maxNumber = 9999;
		GraphUtils.HMPart1(graph.getVertices(), visitingVertices, maxNumber);
		for (Vertex item : graph.getVertices()) {
			item.setVisited(false);
		}
		maxNumber = -1;
		GraphUtils.HMPart2(visitingVertices, maxNumber, graph.getVertices());

		if (visitingVertices.size() < graph.getVertices().size()) {
			System.out.println("Found path :");
			for (Vertex item : visitingVertices) {
				System.out.println(item.getLabel() + " ");
			}
		}

		if (visitingVertices.size() == graph.getVertices().size()) {
			System.out.println("Found Hamilton tour :");
			for (Vertex item : visitingVertices) {
				System.out.print(item.getLabel() + " ");
			}
		}
		return visitingVertices;
	}

}
