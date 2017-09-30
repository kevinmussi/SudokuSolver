package sudoku;

public class Point {
	
	private int val;
	private int row;
	private int col;
	
	public Point(int val, int row, int col) {
		this.val = val;
		this.row = row;
		this.col = col;
	}
	
	public int getValue() {
		return val;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + col + ") = " + val;
	}
	
}
