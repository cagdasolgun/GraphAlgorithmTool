package util;

import graph.Vertex;

import java.util.ArrayList;

public class GraphUtils {

	public static void vertexLabelAvailibility(ArrayList<Vertex> list,
			Vertex vertex) throws Exception {
		for (Vertex item : list) {
			if (item.getLabel().equals(vertex.getLabel())) {
				throw new Exception(
						"You can't insert vertex with same label : "
								+ item.getLabel());
			}
		}
	}

}
