package GUI;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class cardPanel extends JPanel {
	JLabel title;
	
	cardPanel(){
		this.setLayout(new GridLayout(3,1));
	}
}
