package algorithms;

import graph.Graph;
import graph.Vertex;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Logger;

import logger.GraphLogger;

public class GraphAlgorithms {

	public static boolean checkBipartite(Graph graph) throws Exception {
		// get all vertices
		ArrayList<Vertex> vertices = graph.getVertices();
		// pick an initial color
		Color initialColor = Color.BLACK;
		// get initial vertex
		int vertexIndex = 0;
		Vertex initialVertex = vertices.get(vertexIndex);
		// paint initial vertex with initial color
		initialVertex.setColor(initialColor);

		for (Vertex item : vertices) {
			// TODO recursive function to search all verties
		}

		return false;
	}

	private static void paintNeighbour(Vertex vertex, Vertex item) {
		GraphLogger.getLogger().info(
				"Vertex[" + vertex.getLabel() + "] that colored with ["
						+ vertex.getColor().toString() + "]");
		Color black = Color.BLACK;
		Color red = Color.RED;
		if (vertex.getColor().equals(black)) {
			item.setColor(red);
			GraphLogger.getLogger().info(
					"Neighbour[" + item.getLabel() + "] is now painted with ["
							+ red + "]\n");
		} else {
			item.setColor(black);
			GraphLogger.getLogger().info(
					"Neighbour[" + item.getLabel() + "] is now painted with ["
							+ black + "]\n");
		}
	}

}
