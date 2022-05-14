package com.eduardo.shortestPath.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {

	char[][] grid;
	Node destiny;
	int addedCount = 0;
	int size;
	List<Node> visited;
	int i = 0;

	public TreeBuilder(char[][] grid) {
		this.grid = grid;
		this.size = grid[0].length * grid.length;
		this.visited = new ArrayList<>();
		this.destiny = getDestinyNode();
	}

	public SearchShortedTree buildSearchTree() {

		Node root = getRootNode();
		SearchShortedTree tree = new SearchShortedTree(root);

		int startLine = Integer.valueOf(root.getUid().substring(0, 1));
		int startColumn = Integer.valueOf(root.getUid().substring(1, 2));
		addAdjacentNode(root, startLine, startColumn);

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

	private void addAdjacentNode(Node current, int line, int column) {
		addAdjacentNodeAuxNew(null, current, line, column); // start with root
	}

	private void addAdjacentNodeAux(Node previous, Node current, int line, int column) {

		if (/* addedCount < size */ i < 500 && current != null
				&& !current.isAlreadyInTree(previous)/* && !wasNodeVisited(current) */) {
			i++;
			current = addNodeToTree(previous, current, line, column);

			if (!current.equals(destiny)) {
				// verifica acima
				int aboveLine = line - 1;
				Node aboveNode = getNextNode(current, aboveLine, column);
				current.increment();
				printShit(current, aboveNode, "acima");
				addAdjacentNodeAux(current, aboveNode, aboveLine, column);

				// verifica à esquerda
				int leftColumn = column - 1;
				Node leftNode = getNextNode(current, line, leftColumn);
				current.increment();
				printShit(current, leftNode, "esquerda");
				addAdjacentNodeAux(current, leftNode, line, leftColumn);

				// verifica abaixo
				int belowLine = line + 1;
				Node belowNode = getNextNode(current, belowLine, column);
				current.increment();
				printShit(current, belowNode, "abaixo");
				addAdjacentNodeAux(current, belowNode, belowLine, column);

				// verifica à direita
				int rightColumn = column + 1;
				Node rightNode = getNextNode(current, line, rightColumn);
				current.increment();
				printShit(current, rightNode, "direita");
				addAdjacentNodeAux(current, rightNode, line, rightColumn);

//			visited.add(current);
			}
		}

	}

	private void addAdjacentNodeAuxNew(Node previous, Node current, int line, int column) {

		if (i < 500 && current != null) {
			current = addNodeToTree(previous, current, line, column);
			i++;
			if (!current.equals(destiny)) {
				// verifica acima
				int aboveLine = line - 1;
				Node aboveNode = getNextNode(current, aboveLine, column);
				if (aboveNode != null && !aboveNode.isAlreadyInTree(current)) {
					current.increment();
					printShit(current, aboveNode, "acima");
					addAdjacentNodeAuxNew(current, aboveNode, aboveLine, column);
				} else {
//					System.out.println(current.getUid() + " já pertence à árvore de " + aboveNode.getUid());
				}

				// verifica à esquerda
				int leftColumn = column - 1;
				Node leftNode = getNextNode(current, line, leftColumn);
				if (leftNode != null && !leftNode.isAlreadyInTree(current)) {
					current.increment();
					printShit(current, leftNode, "esquerda");
					addAdjacentNodeAuxNew(current, leftNode, line, leftColumn);
				} else {
//					System.out.println(current.getUid() + " já pertence à árvore de " + leftNode.getUid());
				}

				// verifica abaixo
				int belowLine = line + 1;
				Node belowNode = getNextNode(current, belowLine, column);
				if (belowNode != null && !belowNode.isAlreadyInTree(current)) {
					current.increment();
					printShit(current, belowNode, "abaixo");
					addAdjacentNodeAuxNew(current, belowNode, belowLine, column);
				} else {
//					System.out.println(current.getUid() + " já pertence à árvore de " + belowNode.getUid());
				}

				// verifica à direita
				int rightColumn = column + 1;
				Node rightNode = getNextNode(current, line, rightColumn);
				if (rightNode != null && !rightNode.isAlreadyInTree(current)) {
					current.increment();
					printShit(current, rightNode, "direita");
					addAdjacentNodeAuxNew(current, rightNode, line, rightColumn);
				} else {
//					System.out.println(current.getUid() + " já pertence à árvore de " + rightNode.getUid());
				}

//			visited.add(current);
			}
		}
	}

	private boolean wasNodeVisited(Node node) {
		return visited.contains(node) && node.wasVisited();
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
				this.addedCount++;
			}
		}
		return current;
	}

	private Node getNextNode(Node parent, int line, int column) {
		Node node = null;
		try {
			node = new Node(grid[line][column], line, column);
		} catch (IndexOutOfBoundsException e) {

		}
		return node;
	}

}
