package main;

import graph.Graph;
import graph.UndirectedEdge;
import graph.Vertex;
import gui.ShapeCreator;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logger.GraphLogger;
import net.miginfocom.swing.MigLayout;
import algorithms.GraphAlgorithms;

public class BipartiteMain extends JFrame {

	private Graph graph;
	private boolean isThereSelectedNode;
	static int vertexCount = 0;
	private Vertex selectedVertex;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Label label = new Label();
	protected boolean mouseReleased = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BipartiteMain frame = new BipartiteMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BipartiteMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenuItem mntmNewGraph = new JMenuItem("New Graph");
		mntmNewGraph.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				paintComponents(getGraphics());
				graph = new Graph(JOptionPane
						.showInputDialog("Graph Adı Girin:"));
				GraphLogger.getLogger().info(
						"Created a graph named : " + graph.getLabel());
				label.setVisible(true);
				label.setText("Graph : " + graph.getLabel());
				menuBar.setVisible(false);
			}
		});
		menuBar.add(mntmNewGraph);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[438px]", "[][21px]"));

		JButton btnBipartite = new JButton("Bi-Partite");
		btnBipartite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (graph != null) {
						Graph temp = graph;
						if (GraphAlgorithms.checkBipartite(temp)) {
							ShapeCreator.drawGraph(temp,
									(Graphics2D) getGraphics());
							JOptionPane.showMessageDialog(null,
									"Graph bi-partite!");
						} else {
							ShapeCreator.drawGraph(graph,
									(Graphics2D) getGraphics());
							paintAll(getGraphics());
							JOptionPane.showMessageDialog(null,
									"Graph bi-partite değil!");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnBipartite, "cell 0 0");
		contentPane.add(label, "cell 0 1,growx,aligny top");
		contentPane.setDoubleBuffered(true);
		label.setVisible(false);

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				paintComponents(getGraphics());
				if (graph != null) {
					Vertex v = graph.hasVertex(e.getPoint());
					if (selectedVertex != null) {
						if (v != null) {
							UndirectedEdge edge = new UndirectedEdge(
									selectedVertex, v);
							try {
								graph.addEdge(edge);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
					ShapeCreator.drawGraph(graph, (Graphics2D) getGraphics());
					if (selectedVertex != null) {
						selectedVertex = null;
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				paintComponents(getGraphics());
				try {
					if (isThereSelectedNode) {
						isThereSelectedNode = false;
						for (Vertex item : graph.getVertices()) {
							if (item.isSelected()) {
								item.setSelected(false);
							}
						}
					}
					if (graph != null) {
						selectedVertex = graph.hasVertex(e.getPoint());
						if (selectedVertex != null) {
							isThereSelectedNode = true;
							selectedVertex.setSelected(true);
						} else {
							isThereSelectedNode = false;
							for (Vertex item : graph.getVertices()) {
								item.setSelected(false);
							}
							graph.addVertex(new Vertex(String
									.valueOf(vertexCount++), e.getPoint()));
						}
						ShapeCreator.drawGraph(graph,
								(Graphics2D) getGraphics());

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

	}
}
