package graph.pair;

public class Pair {

	protected Object left;
	protected Object right;

	public Pair(Object left, Object right) {
		setLeft(left);
		setRight(right);
	}

	public Pair() {
	}

	public boolean has(Object obj) {
		return (getLeft().equals(obj) | getRight().equals(obj)) ? true : false;
	}

	public Object getRight() {
		return right;
	}

	public void setRight(Object obj) {
		this.right = obj;
	}

	public Object getLeft() {
		return left;
	}

	public void setLeft(Object obj) {
		this.left = obj;
	}

	@Override
	public boolean equals(Object obj) {
		Pair other = (Pair) obj;
		return other.has(left) && other.has(right)
				&& (getClass() == obj.getClass());
	}

}
