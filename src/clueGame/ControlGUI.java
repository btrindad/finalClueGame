package clueGame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ControlGUI {

	public static void main(String[] args) {
		JFrame controlGUI = new JFrame();
		controlGUI.setVisible(true);
		controlGUI.setSize(800, 250);
		controlGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		controlGUI.add(nextPlayer, BorderLayout.NORTH);
		controlGUI.add(makeAccusation, BorderLayout.EAST);
		
		
	}

}
