package com.eduardo.shortestPath.tree;

import com.eduardo.shortestPath.strategy.SearchStrategy;

public class SearchShortedTree {

	Node root;
	private SearchStrategy strategy;

	public SearchShortedTree(Node root) {
		this.root = root;
	}

	public Node getRoot() {
		return root;
	}

	public SearchStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategy strategy) {
		this.strategy = strategy;
	}

	public int minDistance() {
		return this.strategy.minDistance(this.root);
	}

}
