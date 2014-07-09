package mcts.main;

import mcts.games.TicTacToe;
import mcts.mcts.MCTS;
import mcts.mcts.MCTSTreeNode;
import mcts.tree.Tree;

public class Main {

	public static void main(String[] args) {
		//System.out.println("Initializing tic tac toe");
		TicTacToe ttt = new TicTacToe();


		//System.out.println("Initialization tic tac toe complete");

		//System.out.println("Initializing root");
		MCTSTreeNode root = new MCTSTreeNode(null, ttt);
		root.setRoot();
		//System.out.println(root.isRoot());
		//System.out.println("Initialization root complete");

		//System.out.println("Initializing tree");
		Tree tree = new Tree(root);
		//System.out.println("Initialization tree complete");

		//System.out.println("Initializing MCTS");
		MCTS mcts = new MCTS();
		//System.out.println("Initialization MCTS complete");

		//System.out.println("Running MCTS");
		
		MCTSTreeNode nextMove = mcts.run(tree, root, 1000);
		System.out.println("Running MCTS complete. Next move:");
		System.out.println(nextMove.getGame());
		for (int i = 0; i < root.getChildren().size(); i++)
			System.out.println(((MCTSTreeNode) root.getChildren().get(i)).getCounter());
	}

}
