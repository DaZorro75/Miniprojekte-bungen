package LevelEditor;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Hauptanwendung zum starten des LevelEditor
public class Main {
	public static void main(String[] args) {
		int size[] = {12,12};
	    setSize(size);																			//Wahl der Größe des Spielfeldes
	    
		//int size = Integer.parseInt(JOptionPane.showInputDialog("Wähle Größe des Feldes"));
		Model m = new Model(size);																//Dek. und Init. des benutzten Models
		Controller c = new Controller(m);														//Dek. und Init. des Controllers
		View v = new View(c,size);																//Dek. und Init. des Fensters
		c.setView(v);																			//Zuweisung des Fensters an den Controller
	}
	
	//Eingabe für die höhe und breite des Spielfeldes
	public static void setSize(int[] size) {
		JTextField xField = new JTextField(5);
	    JTextField yField = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Länge (Standard ist 12):"));
	    myPanel.add(xField);
	    myPanel.add(Box.createHorizontalStrut(15));
	    myPanel.add(new JLabel("Höhe (Standard ist 12):"));
	    myPanel.add(yField);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel,
		        "Bitte Type Höhe und Länge", JOptionPane.OK_CANCEL_OPTION);
		    if (result == JOptionPane.OK_OPTION) {
		    	if(yField.getText().equals(null) || xField.getText().equals(null)) {
		    		size[1] = Integer.parseInt(xField.getText());
			    	size[0] = Integer.parseInt(yField.getText());
		    	}
		    }
	}
}
