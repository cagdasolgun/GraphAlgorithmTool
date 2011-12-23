package graph;

import java.util.ArrayList;

import algorithms.GraphAlgorithmsUtils;

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
		getVertices().add(vertex);
	}

	public ArrayList<Vertex> getVertices() {
		if (this.vertices == null) {
			this.vertices = new ArrayList<Vertex>();
		}
		return this.vertices;
	}

	public void addEdge(Edge edge) throws Exception {
		getEdges().add(edge);
		Vertex v1 = edge.getPair().getV1();
		Vertex v2 = edge.getPair().getV2();
		if (!getVertices().contains(v1)) {
			getVertices().add(v1);
		}
		if (!getVertices().contains(v2)) {
			getVertices().add(v2);
		}
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

	public boolean isBipartite() throws Exception {
		return GraphAlgorithmsUtils.checkBipartite(this);
	}
}
