package graph;

public class Edge {

	private int weight;
	private Pair pair;

	public Edge(int weight, Vertex v1, Vertex v2) {
		this.weight = weight;
		try {
			getPair().add(v1);
			getPair().add(v2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Pair getPair() {
		if (this.pair == null) {
			this.pair = new Pair();
		}
		return this.pair;
	}

	public boolean has(Vertex vertex) {
		return getPair().has(vertex);
	}

	public int getWeight() {
		return this.weight;
	}

}
