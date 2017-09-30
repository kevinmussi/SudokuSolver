package sudoku;

import java.util.ArrayList;
import java.util.List;

class SudokuTree {
	
	private Sudoku s;
	private List<Node> frontier;
	
	public SudokuTree(Sudoku s) {
		if(s == null)
			throw new IllegalArgumentException();
		
		this.frontier = new ArrayList<>();
		frontier.add(null);
		this.s = s.copy();
	}
	
	boolean expand(Node parent) {
		if(frontier.contains(parent)) {
			if(parent != null) {
				s.insert(parent.data);
			}
			
			int pos = frontier.indexOf(parent);
			
			List<Node> children = new ArrayList<>();
			Point p;
			
			try {
				p = s.getFirstEmpty();
			} catch(PointNotFoundException e) {
				return false;
			}
			
			for(int n: s.possibleValues(p.getRow(), p.getCol())) {
				children.add(new Node(new Point(n, p.getRow(), p.getCol()), parent));
			}
			
			if(!children.isEmpty()) {
				frontier.remove(parent);
				frontier.addAll(pos, children);
				return true;
			}
		}
		
		return false;
	}
	
	void contract(Node child) {
		if(child == null)
			return;
		
		Node current = child;
		
		while(current != null && !frontier.contains(current)) {
			s.remove(current.data.getRow(), current.data.getCol());
			current = child.parent;
		}
	}
	
	@Override
	public String toString() {
		return s.toString() + "\n" + frontier.toString();
	}
	
	public class Node {
		
		private Point data;
		private Node parent;
		
		private Node(Point data, Node parent) {
			if(data == null)
				throw new IllegalArgumentException();
			this.data = data;
			this.parent = parent;
		}
		
		public Node getParent() {
			return parent;
		}
		
		public Point getData() {
			return data;
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
		
	}
	
	public static void main(String[] args) {
		SudokuTree t = new SudokuTree(new Sudoku(3, 3));
		t.s.insert(1, 1, 1);
		System.out.println(t);
		
		t.expand(null);
		System.out.println(t);
		Node n = t.frontier.get(0);
		t.expand(n);
		System.out.println(t);
		t.contract(t.frontier.get(0).parent);
		System.out.println(t);
	}
	
}
