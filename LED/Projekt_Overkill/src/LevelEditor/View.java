package LevelEditor;

//Import von benötigten Klassen
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Klasse die das sichtbare Fenster, also den Level-Editor erzeugen kann.
public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	//Deklarierung von Buttons und einer CheckboxGroup
	private JButton[][] buttons;											//Button Array, welche alle Buttons beinhaltet die für das Spielfeld relevant sind
	private JButton resetAll = new JButton("Reset All");
	private JButton save = new JButton("Save Map");
	private JButton open = new JButton("Open Map");
	private CheckboxGroup cbg = new CheckboxGroup();
	
	//Konstruktor des Fensters, kennt den Controller und die Größe des Spielfeldes
	public View(Controller c, int[] size) {
		
		//Dek. und Init. eines Panels mit einem Gridlayout für die Buttons mit der übergebenem Größe
		JPanel panel = new JPanel(new GridLayout(size[0],size[1]));
		//Dek. und Init. eines ButtonArrays mit der übergebenen Größe
		buttons = new JButton[size[0]][size[1]];

		//Füllen des ButtonArrays mit Buttons und einfügen in das Panel mit dem Gridlayout sowie Aufruf der Methode registriereButton
		for (int i = 0; i < buttons.length; i++) {
			for (int k = 0; k < buttons[i].length; k++) {
				buttons[i][k] = new JButton();
				buttons[i][k].setBackground(Color.BLACK);
				buttons[i][k].setPreferredSize(new Dimension(50, 50));
				panel.add(buttons[i][k]);

				c.registerButton(buttons[i][k], i, k);

			}
		}
		
		//regitrieren der Buttons save und open sowie der CheckboxGroup cbg
		c.addResetButton(resetAll);
		c.addSaveButton(save);
		c.addCBG(cbg);
		c.addOpenButton(open);
		
		//Dek. und Init. eines zweiten Panels south
		JPanel south = new JPanel();
		
		//Dek. und Init. eines dritten Panels box und einfügen von Checkboxen, welche in der cbg gruppiert werden
		JPanel box = new JPanel();
		box.setLayout(new GridLayout(1,3));
		box.add(new Checkbox("Set Start",cbg,false));
		box.add(new Checkbox("Set End",cbg,false));
		box.add(new Checkbox("Set Wall",cbg,false));
		box.add(new Checkbox("Reset",cbg,false));		
		
		//Einfügen von box, save, open in das south pannel
		south.add(box);
		south.add(resetAll);
		south.add(save);
		south.add(open);
		
		//Setzen des Fenster Titels
		//Einfügen der Panels panel und south in das JFrame
		//Setzen der Standard Schließ Operation
		//pack setzt die Einzelnen Elemente auf ihre bevorzugte Standardgröße
		//Sichtbarkeit auf true setzen
		setTitle("Level Editor");
		add(panel, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	//Methode toggleField die beim Klick auf das Feld (s. Controller registriereButton) aufgerufen wird.
	//Bekommt Position(x,y) des Buttons übergeben und den aktuellen Zustand eine Checkbox,
	//damit man entscheiden kann welche Farbe der button bekommen soll.
	//Start = Grün
	//Ende = Rot
	//Wand = Cyan
	//Standard = Schwarz
	public void toggleButton(int x, int y,CheckboxGroup cbg){
		if(cbg.getSelectedCheckbox() == null) {
			return;
		}
		
		switch(cbg.getSelectedCheckbox().getLabel()) {
			case "Set Start":
				checkArray(Color.GREEN);
				buttons[x][y].setBackground(Color.GREEN);
				break;
			case "Set End":
				checkArray(Color.RED);
				buttons[x][y].setBackground(Color.RED);
				break;
			case "Set Wall":
				buttons[x][y].setBackground(Color.CYAN);
				break;
			case "Reset":
				buttons[x][y].setBackground(Color.BLACK);
				break;
			default:
				break;
		}
	}
	
	//Guckt ob es schon einen Button gibt der einen bestimmten Farbe hat
	//Relevant für Start und Ziel, da diese nur einmal vorkommen dürfen
	public void checkArray(Color color) {
		for(int i = 0; i < buttons.length; i++) {
			for(int k = 0; k < buttons[i].length; k++) {
				if(color.equals(buttons[i][k].getBackground())) {
					buttons[i][k].setBackground(Color.BLACK);
				}
			}
		}
	}
}
