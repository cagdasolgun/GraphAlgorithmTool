package util;

import static org.junit.Assert.assertTrue;
import graph.Graph;
import graph.Vertex;
import graph.pair.Pair;
import graph.pair.VertexPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algorithms.GraphAlgorithms;

import logger.GraphLogger;

public class GraphUtils {

	public static int getPathWeight(Graph graph, List<Vertex> path) {
		int w = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			w += graph.getEdgeWeight(path.get(i), path.get(i + 1));
		}
		GraphLogger.getLogger().info(
				"PATH = " + pathToString(path) + "\n COST = " + w);
		return w;
	}

	/**
	 * @param path
	 * @return
	 * @return
	 */
	public static String pathToString(List<Vertex> path) {
		String s = "{ ";
		for (int i = 0; i < path.size(); i++) {
			s += path.get(i).getLabel();
			if (i != path.size() - 1) {
				s += " , ";
			}
		}
		s += " }";
		return s;
	}

	/**
	 * finds that how many odd degreed vertex in given graph
	 * 
	 * @param graph
	 * @return
	 */
	public static int getOddVerticeCount(Graph graph) {
		int oddDegreeCount = 0;
		for (Vertex item : graph.getVertices()) {
			if (item.getNeighbours().size() % 2 == 1) {
				oddDegreeCount++;
			}
		}
		return oddDegreeCount;
	}

	public static boolean areAllDegreesEven(Graph graph) {
		boolean allEvenDegreed = true;
		for (Vertex item : graph.getVertices()) {
			if (item.getNeighbours().size() % 2 != 0) {
				allEvenDegreed = false;
				break;
			}
		}
		return allEvenDegreed;
	}

	public static boolean isEulerianPathExists(Graph graph) {
		return GraphUtils.checkOddDegreedVerticesForEulerianPath(graph)
				&& GraphUtils.areAllDegreesEven(graph);
	}

	/**
	 * @param graph
	 * @return
	 */
	public static boolean checkOddDegreedVerticesForEulerianPath(Graph graph) {
		return GraphUtils.getOddVerticeCount(graph) == 2
				|| GraphUtils.getOddVerticeCount(graph) == 0;
	}

	public static int getOrder(Graph graph) {
		int order = graph.getVertices().size();
		GraphLogger.getLogger().info(graph.getLabel() + "'s order = " + order);
		return order;
	}

	public static int getSize(Graph graph) {
		int size = graph.getEdges().size();
		GraphLogger.getLogger().info(graph.getLabel() + "'s size = " + size);
		return size;
	}

	public static HashMap<Pair, Integer> getDiameters(Graph graph)
			throws Exception {
		HashMap<Pair, Integer> hashMapOfDiameters = new HashMap<Pair, Integer>();
		List<Pair> pairs = createPairs(graph);
		for (Pair item : pairs) {
			List<Vertex> shortestPath = GraphAlgorithms.getShortestPath(graph,
					(Vertex) item.getLeft(), (Vertex) item.getRight());
			hashMapOfDiameters.put(item, getPathWeight(graph, shortestPath));
		}
		return hashMapOfDiameters;
	}

	private static List<Pair> createPairs(Graph graph) throws Exception {
		List<Pair> pairs = new ArrayList<Pair>();
		VertexPair currentPair = new VertexPair();
		for (Vertex v1 : graph.getVertices()) {
			for (Vertex v2 : graph.getVertices()) {
				if (!v1.equals(v2)) {
					if (!pairs.contains(currentPair)) {
						currentPair = new VertexPair(v1, v2);
						GraphLogger.getLogger().info(
								"Creating pair [" + v1.getLabel() + ","
										+ v2.getLabel() + "] for graph "
										+ graph.getLabel());
						pairs.add(currentPair);
					}
				}
			}
		}
		return pairs;
	}
}
