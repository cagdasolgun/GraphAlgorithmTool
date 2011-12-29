package graph;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Logger;

import logger.GraphLogger;

public class Vertex {

	private String label;
	private Point point;
	private ArrayList<Vertex> neighbours;
	private Color color;
	private int value;
	private boolean visited = false;
	private Logger logger = GraphLogger.getLogger();

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

	public void setValue(int value) {
		this.value = value;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * finds minimum weighted neighbour which is not visited already
	 * 
	 * @param graph
	 * 
	 * @return {@link Vertex}
	 */
	public Vertex getMinimumValuedUnvisitedNeighbour(Graph graph) {
		int h = 99999;
		Vertex vertex = null;
		for (Vertex item : getNeighbours()) {
			logger.info("Current neighbour : " + item.getLabel() + ", value : "
					+ item.getValue() + ", visited : " + item.getVisited());
			if (!item.getVisited()) {
				int edgeWeight = graph.getEdgeWeight(this, item);
				logger.info(this.getLabel() + " - " + item.getLabel()
						+ " weigth = " + edgeWeight);
				if (edgeWeight < h) {
					h = edgeWeight;
					vertex = item;
				}
			}
		}
		return vertex;
	}

	public int getValue() {
		return this.value;
	}

	private boolean getVisited() {
		return this.visited;
	}

}
