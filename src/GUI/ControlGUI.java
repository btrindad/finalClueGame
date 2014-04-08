package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlGUI extends JPanel {
	private final int height = 220;
	
	public ControlGUI(){
		//setSize(900, 240);

		JPanel controlPanel = new JPanel(); // controlPanel is the overarching Panel

		controlPanel.setLayout(new GridLayout(2, 1));
		add(controlPanel);
		JPanel firstPanel = new JPanel(); // firstPanel handles the first row for controlPanel
		controlPanel.add(firstPanel);
		firstPanel.setLayout(new GridLayout(1,3));

		turnPanel turn = new turnPanel();
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		
		firstPanel.add(turn);
		firstPanel.add(nextPlayer);
		firstPanel.add(makeAccusation);
		
		JPanel secondPanel = new JPanel(); // secondPanel handles the second row of controlPanel
		controlPanel.add(secondPanel);
		
		secondPanel.add(new dieRollPanel());
		secondPanel.add(new guessPanel());
		secondPanel.add(new resultsPanel());
	}
	
	public void setWidth(int new_width){
		setSize(new_width, height);	
	}
		
	@Override
	public int getHeight() { return height; }

}
