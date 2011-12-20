package graph;

import java.util.ArrayList;

import util.GraphUtils;

public class Pair {

	private ArrayList<Vertex> points = new ArrayList<Vertex>();

	public void add(Vertex vertex) throws Exception {
		int size = getPoints().size();
		if (size < 2) {
			GraphUtils.vertexLabelAvailibility(this.getPoints(), vertex);
			getPoints().add(vertex);
		} else if (size == 2) {
			throw new Exception("Pairs can only have 2 elements...");
		}
	}

	public ArrayList<Vertex> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Vertex> points) {
		this.points = points;
	}

	public boolean has(Vertex vertex) {
		return getPoints().contains(vertex);
	}
}
