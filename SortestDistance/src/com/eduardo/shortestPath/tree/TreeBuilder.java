package com.eduardo.shortestPath.tree;

public class TreeBuilder {

	char[][] grid;
	Node root;
	Node destiny;

	public TreeBuilder(char[][] grid) {
		this.grid = grid;
		this.root = getRootNode();
		this.destiny = getDestinyNode();
	}

	public SearchShortedTree buildSearchTree() {
		int startLine = Integer.valueOf(root.getUid().substring(0, 1));
		int startColumn = Integer.valueOf(root.getUid().substring(1, 2));
		addNeighborNode(null, root, startLine, startColumn);

		return new SearchShortedTree(root);
	}

	private Node getRootNode() {

		Node start = null;

		boolean found = false;
		int l = 0;
		while (l < grid.length && !found) {
			int c = 0;
			while (c < grid[0].length && !found) {
				if (grid[l][c] == 's') {
					found = true;
					start = new Node('s', l, c);
				}
				c++;
			}
			l++;
		}
		return start;
	}

	private Node getDestinyNode() {
		Node destiny = null;

		boolean found = false;
		int l = 0;
		while (l < grid.length && !found) {
			int c = 0;
			while (c < grid[0].length && !found) {
				if (grid[l][c] == 'd') {
					found = true;
					destiny = new Node('d', l, c);
				}
				c++;
			}
			l++;
		}
		return destiny;
	}

	private void addNeighborNode(Node previous, Node current, int line, int column) {
		if (current != null && !isObstacle(line, column)) {
			if (previous != null) { // could be root's parent (null)
				previous.addChildren(current);
			}

			if (!current.equals(destiny)) {
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
			node = new Node(grid[line][column], line, column);
		} catch (IndexOutOfBoundsException e) {

		}
		return node;
	}

	private boolean isObstacle(int line, int column) {
		return grid[line][column] == '0';
	}

}
