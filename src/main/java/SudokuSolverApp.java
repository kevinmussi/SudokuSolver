import java.awt.EventQueue;

import javax.swing.JOptionPane;

import sudoku.gui.SudokuButton;
import sudoku.gui.SudokuFrame;

public class SudokuSolverApp {
	
	private SudokuSolverApp() {
		
	}
	
	private static int showInputDialog(String text) {
		String s = (String) JOptionPane.showInputDialog(
				null,
				text,
				"Sudoku Solver",
				JOptionPane.INFORMATION_MESSAGE,
				null, null, "3");
		
		if(s == null)
			return showInputDialog(text);
		
		int n;
		
		try {
			n = Integer.parseInt(s);
		} catch(NumberFormatException e) {
			SudokuButton.showDialog("You must enter an integer value!", JOptionPane.WARNING_MESSAGE);
			return showInputDialog(text);
		}
		
		if(n < 3) {
			SudokuButton.showDialog("The value inserted must be greater than 2!", JOptionPane.WARNING_MESSAGE);
			return showInputDialog(text);
		}
		
		return n;
	}
	
	public static void main(String[] args) {
		int rows = showInputDialog("Insert the number of rows!");
		int cols = showInputDialog("Insert the number of columns!");
		
		EventQueue.invokeLater(() -> {
			new SudokuFrame(rows, cols);
		});
	}
	
}
