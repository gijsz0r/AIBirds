package ab.demo;

import java.awt.Point;
import java.util.ArrayList;

import mcts.mcts.Playable;
import ab.demo.other.Shot;

public class PlayOut implements Playable {
	
	private ArrayList<Integer> scores;
	private ArrayList<Shot> shots;
	private int visitCounter;
	
	public PlayOut(ArrayList<Shot> shots)
	{
		this.shots = (ArrayList<Shot>) shots.clone();
		this.scores = new ArrayList<Integer>();
		this.visitCounter = 0;
	}
	
	public void addScore(int score)
	{
		scores.add(score);
	}
	
	public void incrementCounter()
	{
		visitCounter++;
	}
	
	public String toString()
	{
		String a = "";
		a += "\n" + "Shots fired from: " + "\n";
		for (int i = 0; i < shots.size(); i++)
			a += ("Shot number " + i + ": " + shots.get(i).toString() + "\n");
		a += "Scores: " + "\n";
		for (int i = 0; i < scores.size(); i++)
			a += ("Score number " + i + ": " + scores.get(i) + "\n");
		return a;
	}

	@Override
	public ArrayList getPossibleChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTerminal() {
		
		return true;
	}

	@Override
	public void addValue(int v) {
		addScore(v);
		
	}

	@Override
	public double getAverage() {
		
		int elements = scores.size();
		if (elements == 0)
			return 0;
		else {
			int sum = 0;
			for (int i = 0; i < elements; i++)
				sum += scores.get(i);
			return (double)sum / elements;
		}
	}

	@Override
	public int getValue() {
		return scores.get(0);
	}
	
	public ArrayList<Shot> getShots()
	{
		return shots;
	}
	
	/*
	public String toString()
	{
		String returnString ="";
		for (int i = 0; i < points.size(); i++)
			returnString += "Point "+ i + ": " + points.get(i) + "\n";
		returnString+= "Level completed: " + levelCompleted + "\n";
		if (levelCompleted == true)
			returnString+= "Score: " + score + "\n";
		return returnString;
	}
*/
}
