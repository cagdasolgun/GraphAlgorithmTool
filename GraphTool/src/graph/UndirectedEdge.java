package graph;

import graph.pair.VertexPair;

public class UndirectedEdge {

	private int weight;
	private VertexPair pair;

	public UndirectedEdge(int weight, Vertex v1, Vertex v2) throws Exception {
		this.weight = weight;
		this.setPair(new VertexPair(v1, v2));
		v1.getNeighbours().add(v2);
		v2.getNeighbours().add(v1);
	}

	public boolean has(Vertex vertex) {
		return getPair().has(vertex);
	}

	public int getWeight() {
		return this.weight;
	}

	public VertexPair getPair() {
		return pair;
	}

	public void setPair(VertexPair pair) {
		this.pair = pair;
	}

}
