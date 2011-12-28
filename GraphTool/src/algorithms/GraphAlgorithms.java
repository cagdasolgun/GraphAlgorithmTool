package algorithms;

import graph.Graph;
import graph.Vertex;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import util.GraphUtils;

import logger.GraphLogger;

public class GraphAlgorithms {

	private static Logger logger = GraphLogger.getLogger();

	public static boolean checkBipartite(Graph graph) throws Exception {
		// get all vertices
		ArrayList<Vertex> vertices = graph.getVertices();
		// pick an initial color
		Color initialColor = Color.BLACK;
		// get initial vertex
		int vertexIndex = 0;
		Vertex initialVertex = vertices.get(vertexIndex);
		// paint initial vertex with initial color
		initialVertex.setColor(initialColor);

		for (Vertex item : vertices) {
			// TODO recursive function to search all verties
		}

		return false;
	}

	private static void paintNeighbour(Vertex vertex, Vertex item) {
		GraphLogger.getLogger().info(
				"Vertex[" + vertex.getLabel() + "] that colored with ["
						+ vertex.getColor().toString() + "]");
		Color black = Color.BLACK;
		Color red = Color.RED;
		if (vertex.getColor().equals(black)) {
			item.setColor(red);
			GraphLogger.getLogger().info(
					"Neighbour[" + item.getLabel() + "] is now painted with ["
							+ red + "]\n");
		} else {
			item.setColor(black);
			GraphLogger.getLogger().info(
					"Neighbour[" + item.getLabel() + "] is now painted with ["
							+ black + "]\n");
		}
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
}
