package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JFrame {
	public ControlGUI(){
		setSize(900, 240);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*
		setLayout(new GridLayout(2,3));
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		turnPanel whoseTurn = new turnPanel();
		add(whoseTurn);
		add(makeAccusation);
		add(nextPlayer);
		
*/
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2, 1));
		add(controlPanel);
		JPanel firstPanel = new JPanel();
		controlPanel.add(firstPanel);
		firstPanel.setLayout(new GridLayout(1,3));
		
		turnPanel turn = new turnPanel();
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		
		firstPanel.add(turn);
		firstPanel.add(nextPlayer);
		firstPanel.add(makeAccusation);
		
		JPanel secondPanel = new JPanel();
		controlPanel.add(secondPanel);
		
		dieRollPanel diePanel = new dieRollPanel();
		secondPanel.add(diePanel);
		
		JPanel guessPanel = new JPanel();
		JLabel guessLabel = new JLabel("Guess");
		JTextField guessField = new JTextField(30);
		guessPanel.add(guessLabel);
		guessPanel.add(guessField);
		guessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		secondPanel.add(guessPanel);
		
		JPanel resultPanel = new JPanel();
		JLabel resultLabel = new JLabel("Response");
		JTextField resultField = new JTextField(10);
		resultPanel.add(resultLabel);
		resultPanel.add(resultField);
		resultPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		secondPanel.add(resultPanel);
	}

	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}

}