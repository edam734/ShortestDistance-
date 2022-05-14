package com.eduardo.shortestPath.tree;

public class TreeBuilder {

	char[][] grid;
	Node root;
	Node destiny;
	
	private final static char START_CHAR = 's';
	private final static char DESTINY_CHAR = 'd';

	public TreeBuilder(char[][] grid) {
		this.grid = grid;
		this.root = getFirstNodeByValue(START_CHAR);
		this.destiny = getFirstNodeByValue(DESTINY_CHAR);
	}

	public SearchShortedTree buildSearchTree() {
		int startLine = Integer.valueOf(this.root.getUid().substring(0, 1));
		int startColumn = Integer.valueOf(this.root.getUid().substring(1, 2));
		addNeighborNode(null, this.root, startLine, startColumn);

		return new SearchShortedTree(this.root);
	}


	private Node getFirstNodeByValue(char signal) {
		Node desired = null;

		boolean found = false;
		int l = 0;
		while (l < this.grid.length && !found) {
			int c = 0;
			while (c < this.grid[0].length && !found) {
				if (this.grid[l][c] == signal) {
					found = true;
					desired = new Node(signal, l, c);
				}
				c++;
			}
			l++;
		}
		return desired;
	}

	private void addNeighborNode(Node previous, Node current, int line, int column) {
		if (current != null && !isObstacle(line, column)) {
			if (previous != null) { // could be root's parent (null)
				previous.addChildren(current);
			}

			if (!current.equals(this.destiny)) {
				goToNeighborNode(current, line - 1, column); // up
				goToNeighborNode(current, line, column - 1); // left
				goToNeighborNode(current, line + 1, column); // down
				goToNeighborNode(current, line, column + 1); // right
			}
		}
	}

	private void goToNeighborNode(Node current, int line, int column) {
		Node adjacentNode = getNeighborNode(current, line, column);
		if (adjacentNode != null && !adjacentNode.isAlreadyInTree(current)) {
			addNeighborNode(current, adjacentNode, line, column);
		}
	}

	private Node getNeighborNode(Node parent, int line, int column) {
		Node node = null;
		try {
			node = new Node(this.grid[line][column], line, column);
		} catch (IndexOutOfBoundsException e) {

		}
		return node;
	}

	private boolean isObstacle(int line, int column) {
		return this.grid[line][column] == '0';
	}

}
