package graph;

import graph.pair.Pair;
import graph.pair.VertexPair;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import logger.GraphLogger;
import algorithms.GraphAlgorithms;

public class Graph {

	private String label;
	private ArrayList<Vertex> vertices = null;
	private ArrayList<UndirectedEdge> edges = null;

	public Graph(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	public void addVertex(Vertex vertex) throws Exception {
		getVertices().add(vertex);
	}

	public ArrayList<Vertex> getVertices() {
		if (this.vertices == null) {
			this.vertices = new ArrayList<Vertex>();
		}
		return this.vertices;
	}

	public void addEdge(UndirectedEdge edge) throws Exception {
		Vertex v1 = edge.getPair().getLeft();
		Vertex v2 = edge.getPair().getRight();
		if (!getVertices().contains(v1)) {
			getVertices().add(v1);
		}
		if (!getVertices().contains(v2)) {
			getVertices().add(v2);
		}
		getEdges().add(edge);
	}

	public ArrayList<UndirectedEdge> getEdges() {
		if (this.edges == null) {
			this.edges = new ArrayList<UndirectedEdge>();
		}
		return this.edges;
	}

	public boolean hasVertex(Vertex vertex) {
		return getVertices().contains(vertex);
	}

	public boolean hasEdge(UndirectedEdge edge) {
		return getEdges().contains(edge);
	}

	public boolean isBipartite() throws Exception {
		return GraphAlgorithms.checkBipartite(this);
	}

	@Override
	public String toString() {
		String toString = "";
		for (UndirectedEdge item : getEdges()) {
			VertexPair pair = item.getPair();
			toString += "[" + this.getLabel() + "," + pair.getLeft().getLabel()
					+ "," + pair.getRight().getLabel() + "," + item.getWeight()
					+ "]\n";
		}

		for (Vertex item : getVertices()) {
			for (Vertex neighbour : item.getNeighbours()) {
				toString += "\n " + item.getLabel() + " -> "
						+ neighbour.getLabel();
			}
		}
		return toString;
	}

	/**
	 * set infinite value to other vertices in graph
	 * 
	 * @param vertex
	 *            is initial {@link Vertex} in shortest path
	 */
	public void setValueToOtherVertices(Vertex vertex) {
		vertex.setValue(0);
		for (Vertex item : getVertices()) {
			// set value if it is not initial vertex
			if (!item.equals(vertex)) {
				item.setValue(99999);
			}
			GraphLogger.getLogger().info(
					item.getLabel() + " is labeled as " + item.getValue());
		}

	}

	/**
	 * gets edge weight which is between given vertices
	 * 
	 * @param v1
	 * @param v2
	 * @return weight of edge between given vertices
	 */
	public int getEdgeWeight(Vertex v1, Vertex v2) {
		for (UndirectedEdge item : getEdges()) {
			if (item.getPair().has(v1) && item.getPair().has(v2)) {
				return item.getWeight();
			}
		}
		return -1;
	}

	/**
	 * finds vertex with its label
	 * 
	 * @param label
	 *            is a {@link String} that labels a {@link Vertex}
	 * @return {@link Vertex} that labeled with given label
	 */
	public Vertex findVertex(String label) {
		for (Vertex item : getVertices()) {
			if (item.getLabel().equals(label)) {
				return item;
			}
		}
		return null;
	}

	public Pair getUnpaintedNeighbour() throws Exception {
		for (Vertex item : getVertices()) {
			for (Vertex neighbour : item.getNeighbours()) {
				if (neighbour.getColor() == null && item.getColor() != null) {
					Color colorToPaint = null;
					if (item.getColor().equals(Color.BLACK)) {
						colorToPaint = Color.RED;
					} else {
						colorToPaint = Color.BLACK;
					}
					return new Pair(neighbour, colorToPaint);
				}
			}
		}
		return null;
	}

	public ArrayList<UndirectedEdge> getConnectedEdges(Vertex vertex) {
		ArrayList<UndirectedEdge> containingEdges = new ArrayList<UndirectedEdge>();
		for (UndirectedEdge item : getEdges()) {
			if (item.has(vertex)) {
				containingEdges.add(item);
			}
		}
		return containingEdges;
	}

	public void deleteVertex(Vertex vertex) {
		getVertices().remove(vertices);
		getEdges().removeAll(getConnectedEdges(vertex));
	}

	public ArrayList<Vertex> getUnColoredVertices() {
		ArrayList<Vertex> uncoloredVertices = new ArrayList<Vertex>();
		for (Vertex item : getVertices()) {
			if (item.getColor() == null) {
				uncoloredVertices.add(item);
			}
		}
		return uncoloredVertices;
	}

	public Vertex hasVertex(Point point) {
		for (Vertex item : getVertices()) {
			boolean a = item.getPoint().getX() - 10 <= point.getX();
			boolean b = item.getPoint().getX() + 10 >= point.getX();
			boolean c = item.getPoint().getY() - 10 <= point.getY();
			boolean d = item.getPoint().getY() + 10 >= point.getY();
			if (a && b && c && d) {
				return item;
			}
		}
		return null;
	}
}
