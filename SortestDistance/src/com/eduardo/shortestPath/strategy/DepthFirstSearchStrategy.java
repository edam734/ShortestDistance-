package com.eduardo.shortestPath.strategy;

import java.util.List;

import com.eduardo.shortestPath.tree.Node;
import com.eduardo.shortestPath.tree.TreeBuilder;

/**
 * 
 * @author Eduardo Amorim
 *
 */
public class DepthFirstSearchStrategy implements SearchStrategy {

	@Override
	public int minDistance(Node root) {
		return depthFirstSearch(root, 0);
	}

	/**
	 * The DFS is not the best strategy for this type of search (for shortest path),
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
}
