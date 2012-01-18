package util;

import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import logger.GraphLogger;
import algorithms.GraphAlgorithms;

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

	public static boolean hasVisitedNeighbour(ArrayList<Vertex> vertexes) {
		Iterator<Vertex> iterator = vertexes.iterator();
		while (iterator.hasNext()) {
			Vertex vertex = (Vertex) iterator.next();
			if (vertex.isVisited()) {
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

	public static UndirectedEdge selectAppropriateEdge(Vertex vertex,
			Graph graph) {
		ArrayList<UndirectedEdge> connectedEdges = graph
				.getConnectedEdges(vertex);
		UndirectedEdge mandatory = null;
		if (!connectedEdges.isEmpty() && connectedEdges != null) {
			for (int i = 0; i < connectedEdges.size(); i++) {
				UndirectedEdge edge = connectedEdges.get(i);
				if (!edge.isVisited()) {
					edge.visit();
					if (GraphAlgorithms.isConnected(graph)) {
						return edge;
					} else {
						mandatory = edge;
						edge.unvisit();
					}
				}
			}
			if (mandatory != null) {
				mandatory.visit();
			}
		}
		if (GraphUtils.isIsolated(graph, vertex)) {
			graph.deleteVertex(vertex);
		}
		return mandatory;
	}

	public static boolean isIsolated(Graph graph, Vertex vertex) {
		ArrayList<UndirectedEdge> connectedEdges = graph
				.getConnectedEdges(vertex);
		if (connectedEdges != null) {
			for (UndirectedEdge item : connectedEdges) {
				if (!item.isVisited()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static ArrayList<Vertex> traverseForEuler(Vertex vertex,
			UndirectedEdge edge, ArrayList<Vertex> visited,
			ArrayList<UndirectedEdge> removedEdges, Graph graph) {
		Vertex reverse = edge.getNeighbour(vertex);
		visited.add(reverse);
		while (!hasVisitedEdge(graph)) {
			UndirectedEdge appropriateEdge = selectAppropriateEdge(reverse,
					graph);
			if (edge != null) {
				removedEdges.add(appropriateEdge);
				traverseForEuler(reverse, appropriateEdge, visited,
						removedEdges, graph);
			} else {
				return null;
			}
		}
		return visited;
	}

	public static boolean hasVisitedEdge(Graph graph) {
		ArrayList<UndirectedEdge> edges = graph.getEdges();
		Iterator<UndirectedEdge> iterator = edges.iterator();
		while (iterator.hasNext()) {
			UndirectedEdge edge = (UndirectedEdge) iterator.next();
			if (edge.isVisited()) {
				return true;
			}
		}
		return false;
	}

	public static int getNumberOfUnvisitedNeighbours(Vertex vertex) {
		int unvisitedCount = 0;
		for (Vertex item : vertex.getNeighbours()) {
			if (!item.isVisited()) {
				unvisitedCount++;
			}
		}
		return unvisitedCount;
	}

	public static ArrayList<Vertex> getUnvisitedNeighbours(Vertex item) {
		ArrayList<Vertex> unvisitedNeighbours = new ArrayList<Vertex>();
		for (Vertex neighbour : item.getNeighbours()) {
			if (!neighbour.isVisited()) {
				unvisitedNeighbours.add(neighbour);
			}
		}
		return unvisitedNeighbours;
	}

	/**
	 * @param visitingVertices
	 * @param maxNumber
	 * @param vertices
	 *            TODO
	 */
	public static void HMPart2(List<Vertex> visitingVertices, int maxNumber,
			ArrayList<Vertex> vertices) {
		Vertex next = null;
		if (vertices.size() > visitingVertices.size()) {
			for (Vertex item : visitingVertices) {
				boolean containsAll = vertices
						.containsAll(item.getNeighbours());
				if (!containsAll) {
					ArrayList<Vertex> unvisitedNeighbours = GraphUtils
							.getUnvisitedNeighbours(item);
					if (maxNumber <= unvisitedNeighbours.size()) {
						maxNumber = unvisitedNeighbours.size();
						next = item;
					}

				}
			}
			if (next != null) {
				next.setVisited(true);
				visitingVertices.add(next);
				maxNumber = -1;
				System.out.println("Selected Vertex : " + next.getLabel());
				HMPart2(visitingVertices, maxNumber, vertices);
			}
		}
	}

	/**
	 * @param vertices
	 * @param visitingVertices
	 *            TODO
	 * @param maxNumber
	 *            TODO
	 */
	public static void HMPart1(ArrayList<Vertex> vertices,
			List<Vertex> visitingVertices, int maxNumber) {
		Vertex next = null;
		for (Vertex item : vertices) {
			ArrayList<Vertex> unvisitedNeighbours = GraphUtils
					.getUnvisitedNeighbours(item);
			System.out.println(item.getLabel() + " has "
					+ unvisitedNeighbours.size() + " unvisited neighbours");
			if (unvisitedNeighbours.size() < maxNumber) {
				maxNumber = unvisitedNeighbours.size();
				next = item;
			}
		}
		if (next != null) {
			next.setVisited(true);
			visitingVertices.add(next);
			maxNumber = 9999;
			System.out.println("Selected Vertex : " + next.getLabel());
			HMPart1(GraphUtils.getUnvisitedNeighbours(next), visitingVertices,
					maxNumber);
		}
	}
}
