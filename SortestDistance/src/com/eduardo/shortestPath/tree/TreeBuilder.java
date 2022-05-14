package com.eduardo.shortestPath.tree;

public class TreeBuilder {

	char[][] grid;
	Node destiny;

	public TreeBuilder(char[][] grid) {
		this.grid = grid;
		this.destiny = getDestinyNode();
	}

	public SearchShortedTree buildSearchTree() {

		Node root = getRootNode();
		SearchShortedTree tree = new SearchShortedTree(root);

		int startLine = Integer.valueOf(root.getUid().substring(0, 1));
		int startColumn = Integer.valueOf(root.getUid().substring(1, 2));
		addAdjacentNode(null, root, startLine, startColumn);

		return tree;
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

	private void addAdjacentNode(Node previous, Node current, int line, int column) {

		if (current != null && !isObstacle(line, column)) {

			addNodeToTree(previous, current, line, column);

			if (!current.equals(destiny)) {
				// verifica acima
				Node aboveNode = getAdjacentNode(current, line - 1, column);
				if (aboveNode != null && !aboveNode.isAlreadyInTree(current)) {
					printShit(current, aboveNode, "acima");
					addAdjacentNode(current, aboveNode, line - 1, column);
				}

				// verifica à esquerda
				Node leftNode = getAdjacentNode(current, line, column - 1);
				if (leftNode != null && !leftNode.isAlreadyInTree(current)) {
					printShit(current, leftNode, "esquerda");
					addAdjacentNode(current, leftNode, line, column - 1);
				}

				// verifica abaixo
				Node belowNode = getAdjacentNode(current, line + 1, column);
				if (belowNode != null && !belowNode.isAlreadyInTree(current)) {
					printShit(current, belowNode, "abaixo");
					addAdjacentNode(current, belowNode, line + 1, column);
				}

				// verifica à direita
				Node rightNode = getAdjacentNode(current, line, column + 1);
				if (rightNode != null && !rightNode.isAlreadyInTree(current)) {
					printShit(current, rightNode, "direita");
					addAdjacentNode(current, rightNode, line, column + 1);
				}

			}
		}
	}

	private void printShit(Node parent, Node current, String dir) {
		System.out.println("# " + (parent != null ? parent.getUid() : "null") + " -> "
				+ (current != null ? current.getUid() : "null") + " (" + dir + ")");

	}

	public Node addNodeToTree(Node parent, Node current, int line, int column) {
		if (parent != null && current != null) {
			boolean added = parent.addChildren(current);
			if (added) {
				System.out.println("adicionou " + current.getUid() + " a " + parent.getUid());
			}
		}
		return current;
	}

	private Node getAdjacentNode(Node parent, int line, int column) {
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
