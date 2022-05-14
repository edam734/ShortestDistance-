package com.eduardo.shortestPath.main;

import com.eduardo.shortestPath.tree.Node;
import com.eduardo.shortestPath.tree.SearchShortedTree;
import com.eduardo.shortestPath.tree.TreeBuilder;

public class Main {

	public static void main(String[] args) {
		char[][] grid = { { '0', '*', '0', 's' }, { '*', '0', '*', '*' }, { '0', '*', '*', '*' },
				{ 'd', '*', '*', '*' } };
		char[][] grid2 = { { '0', 's' }, { '*', '0',  } };
		char[][] grid3 = { { '0', '*', 's' }, { 'd', '0', '*' }, };

		TreeBuilder tb = new TreeBuilder(grid3);
		SearchShortedTree st = tb.buildSearchTree();
		System.out.println(st.getRoot().toString());
		int output = st.minDistance();
		System.out.println(output);
		
		// teste
//		Node n1 = new Node(null, 'a', 1, 0);
//		Node n2 = new Node(n1, 'b', 4, 5);
//		Node n3 = new Node(n2, 'c', 5, 5);
//		Node n4 = new Node(n3, 'd', 1, 1);
//		System.out.println(Node.isInParentTree2(n3, n4));
	}
}
