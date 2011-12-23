package graph;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Vertex {

	private String label;
	private Point point;
	private ArrayList<Vertex> neighbours;
	private Color color;

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

	public ArrayList<Vertex> getNeighbours() {
		if (this.neighbours == null) {
			this.neighbours = new ArrayList<Vertex>();
		}
		return this.neighbours;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

}
