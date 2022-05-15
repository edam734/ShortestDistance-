package com.eduardo.shortestPath.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Node> children;
	private Node parent = null;
	private char value;
	private String uid;

	public Node(char value, int line, int column) {
		super();
		this.children = new ArrayList<>();
		this.value = value;
		this.uid = new StringBuilder().append(line).append(column).toString();
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

	public boolean addChildren(Node node) {
		node.addParent(this);
		return children.add(node);
	}

	private void addParent(Node node) {
		this.parent = node;
	}

	public boolean isAlreadyInTree(Node leaf) {
		if (leaf == null) { // could be root's parent
			return false;
		}
		if (!this.equals(leaf)) {
			return this.isAlreadyInTree(leaf.parent);
		}
		return true;
	}

	@Override
	public String toString() {
		return "Node [uid=" + uid + ", value=" + value + "]";
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

	public boolean isLeaf() {
		return getChildren().isEmpty();
	}

}
