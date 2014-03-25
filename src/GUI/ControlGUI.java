package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControlGUI extends JFrame {
	public ControlGUI(){
		setSize(800, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,3));
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		turnPanel whoseTurn = new turnPanel();
		add(whoseTurn);
		add(makeAccusation);
		add(nextPlayer);
		
	}

	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}

}
