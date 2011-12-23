package graph;

public class Edge {

	private int weight;
	private Pair pair;

	public Edge(int weight, Vertex v1, Vertex v2) throws Exception {
		this.weight = weight;
		this.setPair(new Pair(v1, v2));
	}

	public boolean has(Vertex vertex) {
		return getPair().has(vertex);
	}

	public int getWeight() {
		return this.weight;
	}

	public Pair getPair() {
		return pair;
	}

	public void setPair(Pair pair) {
		this.pair = pair;
	}

}
