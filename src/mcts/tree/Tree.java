package mcts.tree;

import java.util.ArrayList;

public class Tree {

	private TreeNode root;
	private ArrayList<TreeNode> elements;

	public Tree(TreeNode root) {
		this.root = root;
		this.root.setRoot();
		this.elements = new ArrayList<>();
	}

	public TreeNode root() {
		return this.root;
	}

	public TreeNode parent(TreeNode child) {
		return child.getParent();
	}

	public ArrayList<TreeNode> children(TreeNode parent) {
		return parent.getChildren();
	}

	public boolean isInternal(TreeNode node) {
		return node.isInternal();
	}

	public boolean isExternal(TreeNode node) {
		return node.isExternal();
	}

	public boolean isRoot(TreeNode node) {
		return node.isRoot();
	}

	public int size(Tree T, TreeNode v) {
		return elements(T, v).size();
	}

	public ArrayList<TreeNode> elements(Tree T, TreeNode v) {
		elements.clear();
		preOrder(T, v);
		return elements;
	}

	public void preOrder(Tree T, TreeNode v) {
		elements.add(v);
		for (int i = 0; i < T.children(v).size(); i++)
			preOrder(T, T.children(v).get(i));
	}

	public int depth(Tree T, TreeNode v) {
		if (T.isRoot(v))
			return 0;
		else
			return 1 + depth(T, T.parent(v));
	}

	public int height(Tree T, TreeNode v) {
		if (T.isExternal(v))
			return 0;
		else {
			int h = 0;
			for (int i = 0; i < T.children(v).size(); i++)
				h = Math.max(h, height(T, T.children(v).get(i)));
			return 1 + h;
		}
	}

	public TreeNode addChild(TreeNode parent, TreeNode child) {
		parent.addChild(child);
		return child;
	}

}
