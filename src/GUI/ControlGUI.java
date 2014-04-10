package GUI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlGUI extends JPanel {
	private final int height = 220;
	private dieRollPanel dieRoll = new dieRollPanel();
	private guessPanel guess = new guessPanel();
	private resultsPanel results = new resultsPanel();
	private turnPanel turn = new turnPanel();

	
	public ControlGUI(){

		JPanel controlPanel = new JPanel(); // controlPanel is the overarching Panel

		controlPanel.setLayout(new GridLayout(2, 1));
		add(controlPanel);
		JPanel firstPanel = new JPanel(); // firstPanel handles the first row for controlPanel
		controlPanel.add(firstPanel);
		firstPanel.setLayout(new GridLayout(1,3));

		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		
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
	
	public void setTurn(String s){
		turn.turnField.setText(s);
	}
	
	public void setWidth(int new_width){
		setSize(new_width, height);	
	}
		
	@Override
	public int getHeight() { return height; }

}
