package mcts.tree;

import java.util.ArrayList;

public class TreeTester {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(null);
		System.out.println("root " + root);
		Tree tree = new Tree(root);
		TreeNode child1 = new TreeNode(root);
		System.out.println("child1 " + child1);
		TreeNode child2 = new TreeNode(root);
		System.out.println("child2 " + child2);
		TreeNode child3 = new TreeNode(root);
		System.out.println("child 3 " + child3);
		TreeNode grandchild1 = new TreeNode(child1);
		System.out.println("gc1 " + grandchild1);
		TreeNode grandchild2 = new TreeNode(child1);
		System.out.println("gc2 " + grandchild2);
		TreeNode grandgrandchild1 = new TreeNode(grandchild1);
		System.out.println("ggc1 " + grandgrandchild1);
		TreeNode grandchild3 = new TreeNode(child3);
		System.out.println("gc3 " + grandchild3);
		ArrayList<TreeNode> elements = tree.elements(tree, root);
		for (int i = 0; i < elements.size(); i++)
			System.out.println(elements.get(i));
	}
}
