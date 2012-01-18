package graph;

import graph.pair.VertexPair;

public class UndirectedEdge {

	private int weight;
	private VertexPair pair;
	private boolean visited;

	public UndirectedEdge(int weight, Vertex v1, Vertex v2) throws Exception {
		this.weight = weight;
		this.setPair(new VertexPair(v1, v2));
		v1.getNeighbours().add(v2);
		v2.getNeighbours().add(v1);
	}

	public UndirectedEdge(Vertex v1, Vertex v2) {
		this.weight = 0;
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

	public boolean isVisited() {
		return visited;
	}

	void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void visit() {
		getPair().getLeft().getNeighbours().remove(getPair().getRight());
		getPair().getRight().getNeighbours().remove(getPair().getLeft());
		this.setVisited(true);
	}

	public void unvisit() {
		getPair().getLeft().getNeighbours().add(getPair().getRight());
		getPair().getRight().getNeighbours().add(getPair().getLeft());
		this.setVisited(true);
	}

	public Vertex getNeighbour(Vertex vertex) {
		VertexPair vertexPair = getPair();
		return vertex.equals(vertexPair.getLeft()) ? vertexPair.getRight()
				: vertexPair.getLeft();
	}
}
