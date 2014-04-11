package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import clueGame.Solution;

public class ControlGUI extends JPanel {
	private final int height = 220;
	private dieRollPanel dieRoll = new dieRollPanel();
	private guessPanel guess = new guessPanel();
	private resultsPanel results = new resultsPanel();
	private turnPanel turn = new turnPanel();
	public JButton nextPlayer = new JButton("Next player");
	public JButton makeAccusation = new JButton("Make an accusation");
	
	public ControlGUI(){

		JPanel controlPanel = new JPanel(); // controlPanel is the overarching Panel

		controlPanel.setLayout(new GridLayout(2, 1));
		add(controlPanel);
		JPanel firstPanel = new JPanel(); // firstPanel handles the first row for controlPanel
		controlPanel.add(firstPanel);
		firstPanel.setLayout(new GridLayout(1,3));

		
		
		firstPanel.add(turn);
		firstPanel.add(nextPlayer);
		firstPanel.add(makeAccusation);
		
		JPanel secondPanel = new JPanel(); // secondPanel handles the second row of controlPanel
		controlPanel.add(secondPanel);
		
		secondPanel.add(dieRoll);
		secondPanel.add(guess);
		secondPanel.add(results);
	}
	
	public void setDieRoll(int i){
		dieRoll.dieField.setText(Integer.toString(i));
	}
	
	public void setResult(String i){
		results.resultField.setText(i);
	}
	
	public void setGuess(Solution i, boolean noGuess){
		if(i != null){
			guess.guessField.setText(i.person + " in the " + i.room + " with the " + i.weapon);
		}
		if (noGuess){
			guess.guessField.setText("");
		}
	}
	
	public void setTurn(String s){
		turn.turnField.setText(s);
	}
	
	public void setWidth(int new_width){
		setSize(new_width, height);	
	}
		
	@Override
	public int getHeight() { return height; }

}
