package com.eduardo.shortestPath.tree;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class SearchShortedTree {

	Node root;

	public SearchShortedTree(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public int minDistance() {
		return depthFirstSearch(this.root, 0);
	}

	/**
	 * S DFS is not the best strategy for this type of search (for shortest path),
	 * as we have to go through all the nodes to make sure we have found the
	 * shortest destination node.
	 * 
	 * @param node  The current node
	 * @param depth The current depth of the {@code SearchShortedTree}
	 */
	private int depthFirstSearch(Node node, int depth) {
		List<Node> children = node.getChildren();

		// base case
		if (node.getValue() == TreeBuilder.DESTINY_CHAR) { // it's a destiny node
			return depth;
		} else if (node.isLeaf()) { // it's a useless leaf
			return Integer.MAX_VALUE; // to discard value
		} else { // not the base case
			return children.stream().map(child -> {
				return depthFirstSearch(child, depth + 1);
			}).min(Integer::compare).get();
		}

	}

	/**
	 * 
	 * @requires this.root is never the destination
	 * @return
	 */
	public int minDistanceBFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.addAll(this.root.getChildren());
		return breathFirstSearch(queue, 1);
	}

	/**
	 * A BFS is more suited for a search for the shortest path, because the first
	 * destination node in the tree to be found is the closest one, and we can
	 * terminate the search. <br>
	 * <b>Worst case scenario:</b> there's only one destination {@code Node}, and
	 * it's the farthest (most to the right) and deepest child of the tree.
	 * 
	 * @param queue "A queue to keep track of the child nodes that were encountered
	 *              but not yet explored"
	 * @param depth The current depth of the {@code SearchShortedTree}
	 * @return The number of steps on the shortest path to the destination
	 */
	private int breathFirstSearch(Queue<Node> queue, int depth) {
		Queue<Node> newQueue = new ArrayDeque<>();
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			if (current.getValue() == 'd') {
				return depth;
			}
			newQueue.addAll(current.getChildren());
		}
		if (newQueue.isEmpty()) {
			return -1; // there's no more down levels and didn't found destination
		}
		return breathFirstSearch(newQueue, depth + 1);

	}

}
