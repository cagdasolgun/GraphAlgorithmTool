package testdata;

import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;

import java.awt.Point;
import java.util.Random;

public class TestDataVocabulary {

	public static class Vertex1 {

		public static final String V1 = "v1";
		public static final int X1 = 72;
		public static final int Y1 = 54;
		public static final Point POINT1 = new Point(X1, Y1);

		public static Vertex getVertex() {
			return new Vertex(V1, POINT1);
		}

	}

	public static class Vertex2 {

		public static final String V2 = "v2";
		public static final int X2 = 12;
		public static final int Y2 = 45;
		public static final Point POINT2 = new Point(X2, Y2);

		public static Vertex getVertex() {
			return new Vertex(V2, POINT2);
		}

	}

	public static class Edge1 {

		public static final int WEIGHT = 10;
		public static final Vertex v1 = Vertex1.getVertex();
		public static final Vertex v2 = Vertex2.getVertex();

		public static UndirectedEdge getEdge() throws Exception {
			return new UndirectedEdge(WEIGHT, v1, v2);
		}

	}

	public static class Vertex3 {

		public static final String V3 = "v3";
		public static final int X1 = 22;
		public static final int Y1 = 15;
		public static final Point POINT3 = new Point(X1, Y1);

		public static Vertex getVertex() {
			return new Vertex(V3, POINT3);
		}

	}

	public static class Vertex4 {

		public static final String V4 = "v4";
		public static final int X1 = 52;
		public static final int Y1 = 16;
		public static final Point POINT4 = new Point(X1, Y1);

		public static Vertex getVertex() {
			return new Vertex(V4, POINT4);
		}

	}

	public static class Edge2 {

		public static final int WEIGHT = 2;
		public static final Vertex v1 = Vertex3.getVertex();
		public static final Vertex v2 = Vertex4.getVertex();

		public static UndirectedEdge getEdge() throws Exception {
			return new UndirectedEdge(WEIGHT, v1, v2);
		}

	}

	/**
	 * @return
	 * @throws Exception
	 */
	public static Graph getSampleGraph() throws Exception {
		Vertex v1 = new Vertex(Vertex1.V1, Vertex1.POINT1);
		Vertex v2 = new Vertex(Vertex2.V2, Vertex2.POINT2);
		Vertex v3 = new Vertex(Vertex3.V3, Vertex3.POINT3);
		Vertex v4 = new Vertex(Vertex4.V4, Vertex4.POINT4);

		UndirectedEdge edge1 = new UndirectedEdge(10, v1, v2);
		UndirectedEdge edge2 = new UndirectedEdge(2, v3, v4);
		UndirectedEdge edge3 = new UndirectedEdge(5, v1, v3);

		Graph graph = new Graph("G");
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);
		return graph;
	}

	public static Point randomPointGenerator() {
		Random r = new Random();
		int x = r.nextInt();
		int y = r.nextInt();
		return new Point(x, y);
	}

}
