package gui;

import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class ShapeCreator {

	/**
	 * @param graphics
	 *            TODO
	 * @param point
	 *            TODO
	 * @param label
	 *            TODO
	 */
	public static void drawVertex(Graphics2D graphics, Point point, String label) {
		int x = (int) (point.getX());
		int y = (int) (point.getY());
		graphics.drawString(label, x + 10, y + 10);
		graphics.drawOval(x, y, 10, 10);
	}

	private static void fillVertex(Graphics2D graphics, Point point,
			String label) {
		int x = (int) (point.getX());
		int y = (int) (point.getY());
		graphics.drawString(label, x + 10, y + 10);
		graphics.fillOval(x, y, 10, 10);
	}

	public static void drawGraph(Graph graph, Graphics2D graphics) {
		for (Vertex item : graph.getVertices()) {
			if (item.isSelected()) {
				fillVertex(graphics, item.getPoint(), item.getLabel());
			} else if (item.getColor() != null) {
				graphics.setColor(item.getColor());
				fillVertex(graphics, item.getPoint(), item.getLabel());
				graphics.setColor(Color.BLACK);
			} else {
				drawVertex(graphics, item.getPoint(), item.getLabel());
			}
		}

		for (UndirectedEdge item : graph.getEdges()) {
			drawEdge(graphics, item.getPair().getLeft(), item.getPair()
					.getRight());
		}

	}

	public static UndirectedEdge drawEdge(Graphics2D graphics2d,
			Vertex selectedVertex, Vertex v) {
		Point from = selectedVertex.getPoint();
		Point to = v.getPoint();
		graphics2d.drawLine((int) (from.getX() + 5), (int) from.getY() + 5,
				(int) to.getX() + 5, (int) to.getY() + 5);

		return new UndirectedEdge(selectedVertex, v);
	}
}
