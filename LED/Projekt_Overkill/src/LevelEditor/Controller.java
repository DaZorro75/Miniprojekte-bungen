package LevelEditor;

//Import
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

//Verwaltet die grundlegende Logik des Programms
public class Controller {
	//Deklarierung von benötigten Objekten
	private Model model;
	private CheckboxGroup cbg;
	private View view;
	
	//Konstruktor für den Controller
	//Intitialisierung des Models
	public Controller(Model model) {
		this.model = model;
	}
	
	//Initialisierung des Benutzen Fensters
	public void setView(View v) {
		view = v;
	}
	
	//Setzen eines ActionListeners für die Buttons in ButtonsArray(s. View Konstruktor)
	public void registerButton(JButton b, int x, int y) {
		b.addActionListener(event ->{
			model.toggleField(x,y,cbg);
			view.toggleButton(x, y,cbg);
		});
	}
	
	//ActionListener für den ResetAll Button
	public void addResetButton(JButton b) {
		b.addActionListener(event -> onReset());
	}
	
	//ActionListener für den Save Button
	public void addSaveButton(JButton b) {
		b.addActionListener(event -> onSave());
	}
	
	//ActionListener für den Open Button
	public void addOpenButton(JButton b) {
		b.addActionListener(event -> onLoad());
	}
	
	//Initialisierung der benutzten CheckBoxArray
	public void addCBG(CheckboxGroup cbg) {
		this.cbg = cbg;
	}
	
	//Methode für ein komplettes Zurücksetzen des Spielfeldes
	public void onReset() {
		int verification = JOptionPane.showConfirmDialog(null,"Willst du alles Löschen ?");
		if(verification == 0) {
			cbg.setSelectedCheckbox(new Checkbox("Reset",cbg,false));
			int sizex = model.getArray().length;
			int sizey = model.getArray()[0].length;
			for(int i = 0; i < sizex; i++) {
				for(int k = 0; k < sizey; k++) {
					model.toggleField(i, k, cbg);
					view.toggleButton(i, k, cbg);
				}
			}
		}
	}
	
	//Methode zur Speicherung des produzierten Levels als .map Datei
	public void onSave() {
		String levelName = JOptionPane.showInputDialog("Wähle Level Name:");
		if(levelName == null ||levelName.equals(null)) {
			return;
		}
		
		try {
			FileWriter writer = new FileWriter(new File(levelName + ".map"));
			int[][] array = model.getArray();
			
			for(int i = 0; i < array.length; i++){
				String line = "";
				for(int k = 0; k < array[i].length; k++) {
					line += array[i][k];
					if(k < array[i].length - 1) {
						line += ",";
					}
				}
				writer.write(line);
				if(i < array.length -1) {
					writer.write("\r\n");
				}
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Methode zum Öffnen von .map Dateien
	public void onLoad() {
		try {
			File workingDirectory = new File(System.getProperty("user.dir"));
			
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Map File","map");
			fileChooser.setFileFilter(filter);
			fileChooser.setCurrentDirectory(workingDirectory);
			fileChooser.showOpenDialog(null);
			
			File level = fileChooser.getSelectedFile();
			
			if(level == null) {
				return;
			}
			
			BufferedReader reader = new BufferedReader(new FileReader(level));
			
			String str;
			for(int i = 0; (str = reader.readLine()) != null; i++) {
				String[] array = str.split(",");
				for(int k = 0; k < array.length; k++) {
					String mode = "";
					
					switch(array[k]) {
						case "0":
							mode = "Reset";
							break;
						case "1":
							mode = "Set Start";
							break;
						case "2":
							mode = "Set End";
							break;
						case "3":
							mode = "Set Wall";
							break;
						default:
							break;
					}
					
					cbg.setSelectedCheckbox(new Checkbox(mode,cbg,false));
					model.toggleField(i, k, cbg);
					view.toggleButton(i, k, cbg);
					
				}
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
