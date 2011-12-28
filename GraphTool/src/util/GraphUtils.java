package util;

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

}
