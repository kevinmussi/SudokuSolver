package sudoku.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sudoku.Point;
import sudoku.Sudoku;
import sudoku.SudokuSolver;

public class SudokuFrame extends JFrame { //NOSONAR
	
	private static final long serialVersionUID = 3529207056554925659L;

	private Sudoku s;
	
	private SudokuButton[][] buttons;
	
	public SudokuFrame(int rows, int cols) {
		this.s = new Sudoku(rows, cols);
		this.buttons = new SudokuButton[rows*cols][rows*cols];
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(
				SudokuButton.DIMENSION*rows*cols + RectanglesPanel.LOW_DIMENSION*(rows+1),
				SudokuButton.DIMENSION*rows*cols + RectanglesPanel.LOW_DIMENSION*(cols+1) + 50));
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(
				SudokuButton.DIMENSION*rows*cols + RectanglesPanel.LOW_DIMENSION*(rows+1),
				SudokuButton.DIMENSION*rows*cols + RectanglesPanel.LOW_DIMENSION*(cols+1) + 50));
		contentPane.setVisible(true);
		this.setContentPane(contentPane);
		
		for(int i = 0; i < rows*cols; i++) {
			for(int j = 0; j < rows*cols; j++) {
				SudokuButton button = new SudokuButton(this,
						j*SudokuButton.DIMENSION + (Math.floorDiv(j, cols)+1)*RectanglesPanel.LOW_DIMENSION,
						i*SudokuButton.DIMENSION + (Math.floorDiv(i, rows)+1)*RectanglesPanel.LOW_DIMENSION,
						i+1, j+1);
				contentPane.add(button);
				buttons[i][j] = button;
			}
		}
		
		JButton solve = new JButton("SOLVE");
		solve.setBounds(this.getWidth()-220, this.getHeight()-45, 100, 40);
		solve.setFont(new Font("Calibri", Font.BOLD, 18));
		solve.setVisible(true);
		solve.setOpaque(true);
		solve.addActionListener(event -> solve());
		this.add(solve);
		
		JButton clear = new JButton("CLEAR");
		clear.setBounds(this.getWidth()-110, this.getHeight()-45, 100, 40);
		clear.setFont(new Font("Calibri", Font.BOLD, 18));
		clear.setVisible(true);
		clear.setOpaque(true);
		clear.addActionListener(event -> clear());
		this.add(clear);
		
		RectanglesPanel r = new RectanglesPanel();
		contentPane.add(r);
		r.setBounds(0, 0, this.getWidth(), this.getHeight());
		r.setVisible(true);
		
		for(int i = 0; i <= rows; i++) {
			r.addVerticalRectangle(i*(cols*SudokuButton.DIMENSION + RectanglesPanel.LOW_DIMENSION), this.getHeight()-50);
		}
		
		for(int i = 0; i <= cols; i++) {
			r.addHorizontalRectangle(i*(rows*SudokuButton.DIMENSION + RectanglesPanel.LOW_DIMENSION), this.getWidth());
		}
		
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	void insertValue(Point p) {
		s.insert(p);
	}
	
	int getRows() {
		return s.rows;
	}
	
	int getCols() {
		return s.cols;
	}
	
	private void solve() {
		System.out.println(s);
		
		s = new SudokuSolver(s).solve();
		
		System.out.println(s);
		
		for(int i = 0; i < s.rows*s.cols; i++) {
			for(int j = 0; j < s.rows*s.cols; j++) {
				buttons[i][j].setText(Integer.toString(s.get(i+1, j+1)));
			}
		}
	}
	
	private void clear() {
		s.clear();
		
		for(int i = 0; i < s.rows*s.cols; i++) {
			for(int j = 0; j < s.rows*s.cols; j++) {
				buttons[i][j].setText("");
			}
		}
	}
	
}
