package sudoku;

import java.util.ArrayList;
import java.util.List;

class SudokuTree {
	
	private Sudoku s;
	private List<Node> frontier;
	
	public SudokuTree(Sudoku s, Point root) {
		if(root == null || s == null)
			throw new IllegalArgumentException();
		
		this.frontier = new ArrayList<>();
		frontier.add(new Node(root, null));
		this.s = s.copy();
	}
	
	private void expand(Node parent) {
		if(frontier.contains(parent)) {
			s.insert(parent.data);
			int pos = frontier.indexOf(parent);
			List<Node> children = NodeGenerator.generate(parent);
			
			if(!children.isEmpty()) {
				frontier.remove(parent);
				frontier.addAll(pos, children);
			}
		}
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
	
	private static class NodeGenerator {
		
		public NodeGenerator() {
			//
		}
		
		public static List<Node> generate(Node parent) {
			return new ArrayList<>();
		}
		
	}
	
}
