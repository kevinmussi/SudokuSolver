package sudoku;

public class Point {
	
	private int val;
	private int x;
	private int y;
	
	public Point(int val, int x, int y) {
		this.val = val;
		this.x = x;
		this.y = y;
	}
	
	public int getValue() {
		return val;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ") = " + val;
	}
	
}
