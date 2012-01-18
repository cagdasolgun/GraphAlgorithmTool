package util;

import graph.Graph;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	public static HashMap<Integer, Integer> getDegreeMap(Graph graph)
			throws Exception {
		HashMap<Integer, Integer> degreeMap = new HashMap<Integer, Integer>();
		for (Vertex item : graph.getVertices()) {
			int size = item.getNeighbours().size();
			if (degreeMap.get(size) == null) {
				degreeMap.put(size, 1);
			} else {
				degreeMap.put(size, degreeMap.get(size) + 1);
			}
		}
		return degreeMap;
	}

	public static boolean hasNotVisitedNeighbour(ArrayList<Vertex> vertexes) {
		Iterator<Vertex> iterator = vertexes.iterator();
		while (iterator.hasNext()) {
			Vertex vertex = (Vertex) iterator.next();
			if (!vertex.isVisited()) {
				return true;
			}
		}
		return false;
	}

	public static void unvisitAllVertexes(ArrayList<Vertex> vertexes) {
		Iterator<Vertex> iterator = vertexes.iterator();
		while (iterator.hasNext()) {
			Vertex unVisited = (Vertex) iterator.next();
			unVisited.setVisited(false);
		}
	}

	public static void visit(Graph graph, Vertex vertex) {
		vertex.setVisited(true);
		ArrayList<Vertex> neighbours = vertex.getNeighbours();
		Iterator<Vertex> iterator = neighbours.iterator();
		if (neighbours != null) {
			while (iterator.hasNext()) {
				Vertex neighbour = (Vertex) iterator.next();
				if (!neighbour.isVisited()) {
					visit(graph, neighbour);
				}
			}
		}
	}

	public static ArrayList<ArrayList<Vertex>> getPaths(
			ArrayList<Vertex> vertices, int desiredPathLength) {
		desiredPathLength++;
		ArrayList<Vertex> path = null;
		ArrayList<ArrayList<Vertex>> foundPaths = new ArrayList<ArrayList<Vertex>>();
		for (Vertex vertex : vertices) {
			System.out.println("----------");
			path = new ArrayList<Vertex>();
			path.add(vertex);
			traverseGraph(desiredPathLength, vertex, path);
			foundPaths.add(path);
		}
		return foundPaths;
	}

	/**
	 * @param desiredPathLength
	 * @param vertex
	 * @param path
	 */
	private static void traverseGraph(int desiredPathLength, Vertex vertex,
			ArrayList<Vertex> path) {
		System.out.println("Now in = " + vertex.getLabel());
		for (Vertex neighbour : vertex.getNeighbours()) {
			if (!path.contains(neighbour) && path.size() != desiredPathLength) {
				path.add(neighbour);
				traverseGraph(desiredPathLength, neighbour, path);
			}
		}
		if (path.size() != desiredPathLength) {
			System.out.println("Before Remove last<--->");
			for (Vertex item : path) {
				System.out.println(item.getLabel());
			}
			System.out.println("<--->");
			path.remove(path.size() - 1);
			System.out.println("After Remove last<--->");
			for (Vertex item : path) {
				System.out.println(item.getLabel());
			}
			System.out.println("<--->");

		}
	}
}
