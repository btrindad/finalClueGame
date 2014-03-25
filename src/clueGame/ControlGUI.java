package clueGame;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ControlGUI extends JFrame {
	public ControlGUI(){
		setSize(800, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		ControlGUI gui = new ControlGUI();
		gui.setVisible(true);

	}

}
