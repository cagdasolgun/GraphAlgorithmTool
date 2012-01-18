package graph.pair;

import graph.Vertex;

public class VertexPair extends Pair {

	public VertexPair(Vertex v1, Vertex v2) {
		super(v1, v2);
	}

	@Override
	public Vertex getLeft() {
		return (Vertex) super.getLeft();
	}

	@Override
	public Vertex getRight() {
		return (Vertex) super.getRight();
	}

	public VertexPair() {
	}

	@Override
	public String toString() {
		return "Pair [v1=" + ((Vertex) left).getLabel() + ", v2="
				+ ((Vertex) right).getLabel() + "]";
	}

}
