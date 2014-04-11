package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CardsDisplay extends JPanel {
	ArrayList<JTextField> fields;
	String name;
	
	public CardsDisplay(ArrayList<String> cards, String title){
		name = title;
		fields = new ArrayList<JTextField>();
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
		
		setBorder(new TitledBorder(new EtchedBorder(), name));
	}
}
