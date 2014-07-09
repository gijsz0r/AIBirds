package mcts.tree;

import java.util.ArrayList;

public class TreeNode {

	private TreeNode parent;
	private ArrayList<TreeNode> children;
	private boolean isRoot;

	public TreeNode(TreeNode parent) {
		this.parent = parent;
		isRoot = false;
//		if (parent != null)
//			parent.addChild(this);
		this.children = new ArrayList<TreeNode>();
	}

	public TreeNode getParent() {
		return this.parent;
	}

	public ArrayList<TreeNode> getChildren() {
		return this.children;
	}

	public boolean isInternal() {
		return !children.isEmpty();
	}

	public boolean isExternal() {
		return children.isEmpty();
	}

	public void setRoot() {
		this.isRoot = true;
	}

	public boolean isRoot() {
		return this.isRoot;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public void addChild(TreeNode child) {
		this.children.add(child);
		//child.setParent(this);
		//System.out.println("Child's parent: " + child.getParent());
	}
}
