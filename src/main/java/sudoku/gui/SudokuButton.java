package sudoku.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import sudoku.Point;

public class SudokuButton extends JButton { //NOSONAR
	
	private static final long serialVersionUID = 1747857411338619227L;
	
	private SudokuFrame frame;
	
	static final int DIMENSION = 40;
	
	private int row;
	private int col;
	
	public SudokuButton(SudokuFrame frame, int x, int y, int row, int col) {
		super();
		this.frame = frame;
		this.row = row;
		this.col = col;
		this.setBounds(x, y, DIMENSION, DIMENSION);
		this.setPreferredSize(new Dimension(DIMENSION, DIMENSION));
		this.setForeground(Color.BLACK);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 5, 5, 5)));
		this.setFont(new Font("Calibri", Font.BOLD, 22));
		this.setVisible(true);
		this.addActionListener(event -> showInputDialog());
	}
	
	private void showInputDialog() {
		String s = (String) JOptionPane.showInputDialog(
				frame,
				"Insert a number between 1 and " + frame.getRows()*frame.getCols(),
				"Sudoku Solver",
				JOptionPane.INFORMATION_MESSAGE,
				null, null, "");
		
		if(s == null)
			return;
		
		int n;
		
		try {
			n = Integer.parseInt(s);
		} catch(NumberFormatException e) {
			showDialog("You must enter an integer value!", JOptionPane.WARNING_MESSAGE);
			showInputDialog();
			return;
		}
		
		if(n < 1 || n > frame.getRows()*frame.getCols()) {
			showDialog("The value inserted is out of range!", JOptionPane.WARNING_MESSAGE);
			showInputDialog();
			return;
		}
		
		try {
			frame.insertValue(new Point(n, row, col));
			this.setText(s);
		} catch(IllegalArgumentException e) {
			showDialog("You cannot insert this value in this position!", JOptionPane.ERROR_MESSAGE);
			showInputDialog();
			return;
		}
	}
	
	public static void showDialog(String text, int messageType) {
		JOptionPane.showMessageDialog(null, text, "Warning", messageType);
	}
	
}
