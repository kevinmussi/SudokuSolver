package sudoku;

public class Sudoku {
	
	private int rows;
	private int cols;
	private int mat[][];
	
	public Sudoku(int rows, int cols) {
		if(rows < 1 || cols < 1)
			throw new IllegalArgumentException();
		
		this.rows = rows;
		this.cols = cols;
		this.mat = new int[rows*cols][rows*cols];
		
		erase();
	}
	
	void erase() {
		for(int i = 0; i < rows*cols; i++) {
			for(int j = 0; j < rows*cols; j++) {
				mat[i][j] = 0;
			}
		}
	}
	
	void insert(int val, int row, int col) {
		if(val < 1 || val > 9 || row < 1 || row > rows*cols || col < 1 || col > rows*cols)
			throw new IllegalArgumentException();
		
		mat[row-1][col-1] = val;
	}
	
	void remove(int row, int col) {
		if(row < 1 || row > rows*cols || col < 1 || col > rows*cols)
			throw new IllegalArgumentException();
		
		mat[row-1][col-1] = 0;
	}
	
	@Override
	public String toString() { //NOSONAR
		StringBuilder str = new StringBuilder();
		
		for(int k = 0; k < cols; k++) {
			addLine(str);
			
			for(int l = 0; l < rows; l++) {
				str.append("|");
				
				for(int i = 0; i < rows; i++) {
					for(int j = 0; j < cols; j++) {
						str.append(" ");
						//if(mat[k*l][i*j] != 0)
							str.append(mat[k*l][i*j]);
						//else
							//str.append(" ");
						str.append(" ");
					}
					str.append("|");
				}
				
				str.append("\n");
			}
		}
		
		addLine(str);
		
		return str.toString();
	}
	
	private void addLine(StringBuilder str) {
		for(int i = 0; i < rows; i++) {
			str.append("—");
			for(int j = 0; j < cols; j++)
				str.append("———");
		}
		str.append("—\n");
	}
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku(3, 4);
		s.insert(3, 1, 4);
		s.insert(2, 7, 8);
		System.out.println(s.toString());
	}
	
}
