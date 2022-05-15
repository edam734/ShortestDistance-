package com.eduardo.shortestPath.strategy;

import java.util.ArrayDeque;
import java.util.Queue;

import com.eduardo.shortestPath.tree.Node;
import com.eduardo.shortestPath.tree.TreeBuilder;

/**
 * 
 * @author Eduardo Amorim
 *
 */
public class BreathFirstSearchStrategy implements SearchStrategy {

	@Override
	public int minDistance(Node root) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.addAll(root.getChildren());
		return breathFirstSearch(queue, 1);
	}

	/**
	 * The BFS is more suited for a search for the shortest path, because the first
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
			if (current.getValue() == TreeBuilder.DESTINY_CHAR) {
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
