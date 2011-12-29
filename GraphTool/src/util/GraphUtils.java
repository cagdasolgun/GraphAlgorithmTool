package util;

import static org.junit.Assert.assertTrue;
import graph.Graph;
import graph.Vertex;

import java.util.List;

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

}
