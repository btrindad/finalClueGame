package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControlGUI extends JFrame {
	public ControlGUI(){
		setSize(800, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		add(nextPlayer, BorderLayout.NORTH);
		add(makeAccusation, BorderLayout.EAST);
	}

	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}

}
