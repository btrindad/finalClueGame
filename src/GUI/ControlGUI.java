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
		JPanel controlPanel = new JPanel(); // controlPanel is the overarching Panel
		controlPanel.setLayout(new GridLayout(2, 1));
		add(controlPanel);
		JPanel firstPanel = new JPanel(); // firstPanel handles the first row for controlPanel
		controlPanel.add(firstPanel);
		firstPanel.setLayout(new GridLayout(1,3));
		JPanel turnPanel = new JPanel(); // Start of turnPanel
		JLabel turnLabel = new JLabel("Whose turn?");
		JTextField turnField = new JTextField(15);
		turnPanel.add(turnLabel);
		turnPanel.add(turnField); // End of turnPanel
		
		JButton nextPlayer = new JButton("Next player");
		JButton makeAccusation = new JButton("Make an accusation");
		
		firstPanel.add(turnPanel);
		firstPanel.add(nextPlayer);
		firstPanel.add(makeAccusation);
		
		JPanel secondPanel = new JPanel(); // secondPanel handles the second row of controlPanel
		controlPanel.add(secondPanel);
		JPanel diePanel = new JPanel(); // Start of diePanel
		JLabel dieLabel = new JLabel("Roll");
		JTextField dieField = new JTextField(3);
		dieField.setEditable(false);
		diePanel.add(dieLabel);
		diePanel.add(dieField);
		diePanel.setBorder(new TitledBorder (new EtchedBorder(), "Die")); // End of diePanel
		secondPanel.add(diePanel);
		
		JPanel guessPanel = new JPanel(); // Start of guessPanel
		JLabel guessLabel = new JLabel("Guess");
		JTextField guessField = new JTextField(30);
		guessField.setEditable(false);
		guessPanel.add(guessLabel);
		guessPanel.add(guessField);
		guessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess")); // End of guessPanel
		secondPanel.add(guessPanel);
		
		JPanel resultPanel = new JPanel(); // start of resultPanel
		JLabel resultLabel = new JLabel("Response");
		JTextField resultField = new JTextField(10);
		resultField.setEditable(false);
		resultPanel.add(resultLabel);
		resultPanel.add(resultField);
		resultPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result")); // End of resultPanel
		secondPanel.add(resultPanel);
	}

	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);
	}

}

/*

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
		JPanel turnPanel = 
		JTextField whoseTurn = new JTextField
		JButton nextPlayer = new JButton("Next player");
		controlPanel.add(nextPlayer);
		JButton makeAccusation = new JButton("Make an accusation");
		controlPanel.add(makeAccusation);
		
		
	}

}

*/