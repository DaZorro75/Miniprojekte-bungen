package LevelEditor;

//Importierung benötigter Klassen
import java.awt.CheckboxGroup;


//Klasse Model welche den logischen Aufbau des Spielfeldes verwaltet
public class Model {
	private int[][] array;											//Deklarierung eines Arrays mit den Namen array, welche den Zustand der einzelnen Felder
																	//speichert
	
	//Konstruktor dem ein Integer size übergeben wird
	public Model(int[] size) {
		array = new int[size[0]][size[1]];								//Initialisierung des Arrays mit der übergebenen Größe also size
	}
	
	//Methode toggleField die beim Klick auf das Feld (s. Controller registriereButton) aufgerufen wird.
	//Bekommt Position(x,y) des Buttons übergeben und den aktuellen Zustand eine Checkbox,
	//damit man entscheiden kann welcher Zustand gespeichert werden soll.
	//Start = 1
	//Ende = 2
	//Wand = 3
	//Standard = 0
	public void toggleField(int x, int y, CheckboxGroup cbg) {
		if(cbg.getSelectedCheckbox() == null) {
			return;
		}
		
		switch(cbg.getSelectedCheckbox().getLabel()) {
			case "Set Start":
				checkArray(1);
				array[x][y] = 1;
				break;
			case "Set End":
				checkArray(2);
				array[x][y] = 2;
				break;
			case "Set Wall":
				array[x][y] = 3;
				break;
			case "Reset":
				array[x][y] = 0;
				break;
			default:
				break;
		}
	}
	
	//Gibt das Zustandsarray zurück
	public int[][] getArray(){
		return array;
	}
	
	
	//Guckt ob es schon einen Eintrag gibt der einen bestimmten Zustand hat
	//Relevant für Start und Ziel, da dort die Einträge nur einmal vorkommen dürfen
	public void checkArray(int number) {
		for(int i = 0; i < array.length; i++) {
			for(int k = 0; k < array[i].length; k++) {
				if(number == array[i][k]) {
					array[i][k] = 0;
				}
			}
		}
	}
	
}
