package clueGame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlGUI extends JFrame {
	
	
	
	
	

	public static void main(String[] args) {
		ControlGUI controlGUI = new ControlGUI();
		controlGUI.setVisible(true);
		controlGUI.setSize(900, 240);
		controlGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel controlPanel = new JPanel();
		controlGUI.add(controlPanel);
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		controlPanel.add(nextPlayer);
		controlPanel.add(makeAccusation);
		
		
	}

}
