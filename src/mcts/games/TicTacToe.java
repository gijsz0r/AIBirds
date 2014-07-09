package mcts.games;

import java.util.ArrayList;

import mcts.mcts.Playable;

public class TicTacToe implements Playable {

	private String[][] field;
	private final int fieldSize = 3;
	private boolean firstPlayerTurn;
	private ArrayList<Integer> values;

	public TicTacToe() {
		field = new String[fieldSize][fieldSize];
		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field[i].length; j++)
				field[i][j] = " ";
		this.firstPlayerTurn = true;
		this.values = new ArrayList<Integer>();
	}

	public String toString() {
		String a = "==========================" + "\n";
		for (int i = 0; i < field.length; i++) {
			a = a + "||";
			for (int j = 0; j < field[i].length; j++) {
				a = a + field[i][j] + "||";
			}
			a = a + "\n" + "==========================" + "\n";
		}
		return a;
	}

	public boolean place(int x, int y) {
		if (placePossible(x, y)) {
			field[x][y] = fromBoolean(firstPlayerTurn);
			firstPlayerTurn = !firstPlayerTurn;
			return true;
		}
		return false;
	}

	public boolean placePossible(int x, int y) {
		return field[x][y].equals(" ");
	}

	public String fromBoolean(boolean b) {
		if (b)
			return "X";
		else
			return "O";
	}

	public boolean isOver() {
		return (getGameState() != 0 || isFull());
	}

	public boolean isFull() {
		boolean full = true;
		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field[i].length; j++)
				if (field[i][j].equals(" "))
					return !full;
		return full;
	}

	public int getGameState() {
		if ((field[0][0].equals("X") && field[1][0].equals("X") && field[2][0]
				.equals("X"))
				|| (field[0][1].equals("X") && field[1][1].equals("X") && field[2][1]
						.equals("X"))
				|| (field[0][2].equals("X") && field[1][2].equals("X") && field[2][2]
						.equals("X"))
				|| (field[0][0].equals("X") && field[0][1].equals("X") && field[0][2]
						.equals("X"))
				|| (field[1][0].equals("X") && field[1][1].equals("X") && field[1][2]
						.equals("X"))
				|| (field[2][0].equals("X") && field[2][1].equals("X") && field[2][2]
						.equals("X"))
				|| (field[0][0].equals("X") && field[1][1].equals("X") && field[2][2]
						.equals("X"))
				|| (field[0][2].equals("X") && field[1][1].equals("X") && field[2][0]
						.equals("X")))
			return 1;

		else if ((field[0][0].equals("O") && field[1][0].equals("O") && field[2][0]
				.equals("O"))
				|| (field[0][1].equals("O") && field[1][1].equals("O") && field[2][1]
						.equals("O"))
				|| (field[0][2].equals("O") && field[1][2].equals("O") && field[2][2]
						.equals("O"))
				|| (field[0][0].equals("O") && field[0][1].equals("O") && field[0][2]
						.equals("O"))
				|| (field[1][0].equals("O") && field[1][1].equals("O") && field[1][2]
						.equals("O"))
				|| (field[2][0].equals("O") && field[2][1].equals("O") && field[2][2]
						.equals("O"))
				|| (field[0][0].equals("O") && field[1][1].equals("O") && field[2][2]
						.equals("O"))
				|| (field[0][2].equals("O") && field[1][1].equals("O") && field[2][0]
						.equals("O")))
			return -1;
		else
			return 0;
	}

	@Override
	public ArrayList getPossibleChildren() {
		ArrayList<TicTacToe> possibleChildren = new ArrayList<TicTacToe>();
		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field[i].length; j++)
				if (field[i][j].equals(" ")) {
					TicTacToe newTTT = this.clone();
					newTTT.place(i, j);
					possibleChildren.add(newTTT);
				}
		return possibleChildren;
	}

	@Override
	public boolean isTerminal() {
		return isOver();
	}

	@Override
	public void addValue(int v) {
		values.add(v);
	}

	@Override
	public double getAverage() {
		
		int elements = values.size();
		if (elements == 0)
			return 0;
		else {
			int sum = 0;
			for (int i = 0; i < elements; i++)
				sum += values.get(i);
			return (double)sum / elements;
		}
	}

	@Override
	public int getValue() {
		return getGameState();
	}

	public TicTacToe clone() {
		TicTacToe ttt = new TicTacToe();
		for (int i = 0; i < this.field.length; i++)
			for (int j = 0; j < this.field[i].length; j++)
				ttt.field[i][j] = this.field[i][j];
		ttt.firstPlayerTurn = this.firstPlayerTurn;
		return ttt;
	}
}
