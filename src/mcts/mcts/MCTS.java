package mcts.mcts;

import java.util.ArrayList;
import java.util.Random;

import mcts.tree.Tree;
import mcts.tree.TreeNode;

public class MCTS {

	Random random;

	public MCTS() {
		// System.out.println("Initializing Random object");
		this.random = new Random();
		// System.out.println("Initialization Random object complete");
	}

	public MCTSTreeNode run(Tree t, MCTSTreeNode startNode, int iterations) {

		// System.out.println("Start node:");
		// System.out.println(startNode.getGame());

		for (int i = 0; i < iterations; i++) {
			// System.out.println("\n" + "Selecting the leaf node");
			MCTSTreeNode leafNode = select(startNode);
			// System.out.println("Leaf node selected: ");
			// System.out.println(leafNode.getGame());

			MCTSTreeNode nextNode;
			if (!leafNode.isTerminal()) {

				// System.out.println("Leaf node is not terminal, so expand");
				nextNode = expand(t, leafNode);
				// System.out.println("Expanded into next node:");
				// System.out.println(nextNode.getGame());

				// System.out.println("Randomly playing out with this node");
				int playOut = playOut(nextNode);

				// System.out.println("Is this node a root? " +
				// nextNode.isRoot());
				// System.out.println("Is its parent a root? "
				// + nextNode.getParent().isRoot());
				backpropagate(playOut, nextNode);

				// System.out.println("End of iteration");
				// System.out
				// .println("Looking at whole tree, its games and values");
				ArrayList<TreeNode> elements = t.elements(t, startNode);
				for (int j = 0; j < elements.size(); j++) {
					// System.out.println(((MCTSTreeNode) elements.get(j))
					// .getGame());
					// System.out.println("Counter: "
					// + ((MCTSTreeNode) elements.get(j)).getCounter());
					// System.out.println("Average value: "
					// + ((MCTSTreeNode) elements.get(j)).getGame()
					// .getAverage());
					// System.out.println("UCT: "
					// + ((MCTSTreeNode) elements.get(j)).getUCT());
				}
			}
		}
		return startNode.bestChildUCT();
	}

	private void backpropagate(int playOut, MCTSTreeNode lastNode) {
		while (!lastNode.isRoot()) {

			lastNode.addValue(playOut);
			lastNode = (MCTSTreeNode) lastNode.getParent();
		}
	}

	private int playOut(MCTSTreeNode nextNode) {
		Playable currentGame = nextNode.getGame();
		int i = 0;
		while (!currentGame.isTerminal()) {
			currentGame = (Playable) currentGame.getPossibleChildren().get(
					random.nextInt(nextNode.getPossibleChildren().size() - i));
			i++;
		}
		// System.out.println("Resulting terminal node:");
		// System.out.println(currentGame);
		// System.out.println("Score: " + currentGame.getValue());
		return currentGame.getValue();
	}

	private MCTSTreeNode expand(Tree t, MCTSTreeNode leafNode) {

		if (leafNode.isRoot()) {
			for (int i = 0; i < leafNode.getPossibleChildren().size(); i++) {
				leafNode.addChild(new MCTSTreeNode(leafNode,
						(Playable) leafNode.getPossibleChildren().get(i)));
			}
			MCTSTreeNode returnNode = ((MCTSTreeNode) leafNode).bestChildUCT();
			returnNode.visit();
			return returnNode;
		} else {

			int randomIndex = random.nextInt(leafNode.getPossibleChildren()
					.size());
			// System.out.println("Random index of expansion: " + randomIndex);
			MCTSTreeNode child = new MCTSTreeNode(leafNode, (Playable) leafNode
					.getPossibleChildren().get(randomIndex));
			child.visit();
			return child;

		}
	}

	private MCTSTreeNode select(MCTSTreeNode startNode) {
		MCTSTreeNode currentNode = startNode;

		while (currentNode.isInternal()) {
			currentNode = currentNode.bestChildUCT();
			currentNode.visit();
		}
		startNode.visit();
		return currentNode;
	}

}
