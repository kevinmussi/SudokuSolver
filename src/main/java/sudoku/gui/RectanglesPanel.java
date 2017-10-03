package sudoku.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class RectanglesPanel extends JPanel {
	
	private static final long serialVersionUID = 1236676772318320163L;
	
	static final int LOW_DIMENSION = 4;
	
	private static class Rectangle {
		final int x;
		final int y;
		final int width;
		final int height;
		final Color color;

		public Rectangle(int x, int y, int width, int height, Color color) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.color = color;
		}
		
		public Rectangle(int x, int y, int width, int height) {
			this(x, y, width, height, Color.BLACK);
		}
	}
	
	private final LinkedList<Rectangle> rectangles = new LinkedList<>();
	
	void addVerticalRectangle(int x, int highdimension) {
		rectangles.add(new Rectangle(x, 0, LOW_DIMENSION, highdimension));
		repaint();
	}
	
	void addHorizontalRectangle(int y, int highdimension) {
		rectangles.add(new Rectangle(0, y, highdimension, LOW_DIMENSION));
		repaint();
	}
	
	void clearRectangles() {
		rectangles.clear();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    for(Rectangle rect: rectangles) {
	        g.setColor(rect.color);
	        g.fillRect(rect.x, rect.y, rect.width, rect.height);
	    }
	}
	
}
