package com.eduardo.shortestPath.main;

import com.eduardo.shortestPath.tree.Node;
import com.eduardo.shortestPath.tree.SearchShortedTree;
import com.eduardo.shortestPath.tree.TreeBuilder;

public class Main {

	public static void main(String[] args) {
		char[][] grid = { { '0', '*', '0', 's' }, { '*', '0', '*', '*' }, { '0', '*', '*', '*' },
				{ 'd', '*', '*', '*' } };
		char[][] grid2 = { { '0', 's' }, { '*', '0',  } };
		char[][] grid3 = { { '*', '*', 's' }, { 'd', '*', '*' }, };

		TreeBuilder tb = new TreeBuilder(grid3);
		SearchShortedTree st = tb.buildSearchTree();
		System.out.println(st.getRoot().toString());
		int output = st.minDistance();
		System.out.println(output);
		
		// teste
//		Node n1 = new Node('a', 1, 0);
//		n1.parent = null;
//		Node n2 = new Node('b', 4, 5);
//		n2.parent = n1;
//		Node n3 = new Node('c', 5, 5);
//		n3.parent = n2;
//		Node n4 = new Node('d', 1, 1);
//		n4.parent = n3;
//		System.out.println(n1.isAlreadyInTree(n3));
	}
}
