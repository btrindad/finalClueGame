package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardsDisplay extends JPanel {
	ArrayList<JTextField> fields;
	
	public CardsDisplay(ArrayList<String> cards){
		fields = new ArrayList<JTextField>();
		JTextField temp;
		for(String s : cards){
			fields.add(new JTextField(s, s.length()));
		}
	}
	
	public void display(){
		setLayout(new GridLayout(fields.size(), 1));
		
		for(JTextField d : fields){
			d.setEditable(false);
			add(d);
		}
		
		setBorder(new TitiledBorder(new EtchedBorder))
	}
}
