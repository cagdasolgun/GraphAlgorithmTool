package graph;

public class Pair {

	private Vertex v1;

	private Vertex v2;

	public Pair(Vertex v1, Vertex v2) throws Exception {
		setV1(v1);
		setV2(v2);
		v1.getNeighbours().add(v2);
		v2.getNeighbours().add(v1);
	}

	public boolean has(Vertex vertex) {
		return (getV1().equals(vertex) | getV2().equals(vertex)) ? true : false;
	}

	public Vertex getV2() {
		return v2;
	}

	public void setV2(Vertex v2) {
		this.v2 = v2;
	}

	public Vertex getV1() {
		return v1;
	}

	public void setV1(Vertex v1) {
		this.v1 = v1;
	}
}
