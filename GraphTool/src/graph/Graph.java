package graph;

import java.util.ArrayList;

import util.GraphUtils;

public class Graph {

	private String label;
	private ArrayList<Vertex> vertices = null;
	private ArrayList<Edge> edges = null;

	public Graph(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	public void addVertex(Vertex vertex) throws Exception {
		GraphUtils.vertexLabelAvailibility(getVertices(), vertex);
		getVertices().add(vertex);
	}

	private ArrayList<Vertex> getVertices() {
		if (this.vertices == null) {
			this.vertices = new ArrayList<Vertex>();
		}
		return this.vertices;
	}

	public void addEdge(Edge edge) {
		getEdges().add(edge);
	}

	private ArrayList<Edge> getEdges() {
		if (this.edges == null) {
			this.edges = new ArrayList<Edge>();
		}
		return this.edges;
	}

	public boolean hasVertex(Vertex vertex) {
		return getVertices().contains(vertex);
	}

	public boolean hasEdge(Edge edge) {
		return getEdges().contains(edge);
	}
}
