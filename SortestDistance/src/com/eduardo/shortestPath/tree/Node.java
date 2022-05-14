package com.eduardo.shortestPath.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Node> children;
	private Node parent = null;
	private char value;
	private String uid;
	private int count = 0;

	public Node(char value, int line, int column) {
		super();
		this.children = new ArrayList<>();
		this.value = value;
		this.uid = line + "" + column;
	}

	public boolean addChildren(Node node) {
		if (!isInParentTree(this, node)) {
			node.addParent(this);
			return children.add(node);
		} 
		return false;
	}
	
	private void addParent(Node node) {
		this.parent = node;
	}

	public boolean isInParentTree(Node n1, Node n2) {
		
		if (n1 == null) { // is root
			return false;
		}
//		System.out.println("current: " + n1.toSimpleString());
//		System.out.println("child: " + n2.toSimpleString());
		if (!n1.equals(n2)) {
			return isInParentTree(n1.parent, n2);
		}
		return true;
	}
	
	public boolean wasVisited() {
		return this.count == 4;
	}
	
	public void increment() {
		this.count++;
	}

	public List<Node> getChildren() {
		return children;
	}

	public char getValue() {
		return value;
	}

	public String getUid() {
		return uid;
	}

	@Override
	public String toString() {
		return "Node [children=" + children + ", uid=" + uid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	public String toSimpleString() {
		return "Node [value=" + value + ", uid=" + uid + "]";
		
	}
	

}
