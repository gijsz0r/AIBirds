package mcts.mcts;

import java.util.ArrayList;
import java.util.Random;

import mcts.tree.TreeNode;

public class MCTSTreeNode extends TreeNode {

	private int counter;
	private double UCT;
	private final double C = Math.sqrt(2);
	private Playable game;
	private double randomDouble;
	private final int maxScore = 35000;
	
	public MCTSTreeNode(MCTSTreeNode parent, Playable game) {
		super(parent);
		this.game = game;
		counter = 0;
		setUCT();
		Random random = new Random();

		randomDouble = random.nextDouble() * 0.000001;
	}
	
	public String toString()
	{
		String a = "";
		a += getGame().toString();
		a += "Counter: " + counter + "\n";
		a+= "UCT: " + UCT;
		return a;
	}

	private void setUCT() {
		if (getParent() == null) {
			UCT = 0;
		}
		else if (this.getCounter() == 0) {
			UCT = 100 + randomDouble;
		}
		else
			UCT =  (double) getAverage() / maxScore
					+ C
					* Math.sqrt(Math.log(((MCTSTreeNode) getParent())
							.getCounter()) / getCounter())
					+ randomDouble;
	}

	public int getCounter() {
		return counter;
	}

	public double getUCT() {
		setUCT();
		return UCT;
	}

	public void visit() {
		counter++;
	}

	public Playable getGame() {
		return this.game;
	}

	public ArrayList getPossibleChildren() {
		return game.getPossibleChildren();
	}

	public double getAverage() {
		return game.getAverage();
	}

	public void addValue(int v) {
		game.addValue(v);
	}

	public boolean isTerminal() {
		return game.isTerminal();
	}

	public MCTSTreeNode bestChildUCT() {
		ArrayList<TreeNode> children = this.getChildren();
		//System.out.println("Choosing from: " + children.size() + " kids, yo");
		if (children.isEmpty())
			return null;
		else {
			TreeNode bestChild = children.get(0);
			for (int i = 1; i < children.size(); i++) {
				//System.out.println("Current best child: " + ((MCTSTreeNode) bestChild).getUCT());
				//System.out.println("Considering child: " + ((MCTSTreeNode) children.get(i)).getUCT());
				if (((MCTSTreeNode) children.get(i)).getUCT() > ((MCTSTreeNode) bestChild)
						.getUCT()) {
					//System.out.println("Updating child");
					bestChild = children.get(i);
				}
			}
			return (MCTSTreeNode) bestChild;
		}
	}

}
