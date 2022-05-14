package com.eduardo.shortestPath.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeBuilder {

	char[][] grid;
	int addedCount = 0;
	int size;
	List<Node> visited;
	int i = 0;

	public TreeBuilder(char[][] grid) {
		this.grid = grid;
		this.size = grid[0].length * grid.length;
		visited = new ArrayList<>();
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

		Node startNode = null;

		boolean found = false;
		int l = 0;
		while (l < grid.length && !found) {
			int c = 0;
			while (c < grid[0].length && !found) {
				if (grid[l][c] == 's') {
					found = true;
					startNode = new Node(grid[l][c], l, c);
				}
				c++;
			}
			l++;
		}
		return startNode;
	}

	private void addAdjacentNode(Node parent, Node currentNode, int line, int column) {

		if (/*addedCount < size*/ i < 500 && currentNode != null && !wasNodeVisited(currentNode)) {
			i++;
			addNodeToTree(parent, currentNode, line, column);

			// verifica acima
			int aboveLine = line - 1;
			Node aboveNode = getNextNode(currentNode, aboveLine, column);
			currentNode.increment();
			printShit(currentNode, aboveNode, "acima");
			addAdjacentNode(currentNode, aboveNode, aboveLine, column);

			// verifica à esquerda
			int leftColumn = column - 1;
			Node leftNode = getNextNode(currentNode, line, leftColumn);
			currentNode.increment();
			printShit(currentNode, leftNode, "esquerda");
			addAdjacentNode(currentNode, leftNode, line, leftColumn);

			// verifica abaixo
			int belowLine = line + 1;
			Node belowNode = getNextNode(currentNode, belowLine, column);
			currentNode.increment();
			printShit(currentNode, belowNode, "abaixo");
			addAdjacentNode(currentNode, belowNode, belowLine, column);

			// verifica à direita
			int rightColumn = column + 1;
			Node rightNode = getNextNode(currentNode, line, rightColumn);
			currentNode.increment();
			printShit(currentNode, rightNode, "direita");
			addAdjacentNode(currentNode, rightNode, line, rightColumn);
			
			visited.add(currentNode);
		}

	}
	
	private boolean wasNodeVisited(Node node) {
		return visited.contains(node) && node.wasVisited();
	}

	private void printShit(Node parent, Node current, String dir) {
		System.out.println("# " + (parent != null ? parent.getUid() : "null") + " -> "
				+ (current != null ? current.getUid() : "null") + " (" + dir + ")");

	}

	public void addNodeToTree(Node parent, Node current, int line, int column) {

//		char val = grid[line][column];
//		Node node = new Node(parent, val, line, column);

		if (parent != null && current != null) {
			boolean added = parent.addChildren(current);
			if (added) {
				System.out.println("adicionou " + current.getUid() + " a " + parent.getUid());
				this.addedCount++;
			}
		}
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
