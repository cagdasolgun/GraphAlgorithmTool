package graph;

import java.awt.Point;

public class Vertex {

	private String label;
	private Point point;

	public Vertex(String label, Point point) {
		this.label = label;
		this.point = point;
	}

	public String getLabel() {
		return this.label;
	}

	public Point getPoint() {
		return this.point;
	}

}
