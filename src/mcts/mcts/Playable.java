package mcts.mcts;

import java.util.ArrayList;

import ab.demo.other.Shot;

public interface Playable {

	public ArrayList getPossibleChildren();

	public boolean isTerminal();

	public void addValue(int v);

	public double getAverage();

	public int getValue();


}
