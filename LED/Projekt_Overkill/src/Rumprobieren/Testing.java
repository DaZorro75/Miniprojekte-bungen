package Rumprobieren;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import ledControl.BoardController;

public class Testing {
	private static int xAkt,xpos,x_ende;
	private static int yAkt,ypos,y_ende;
	private static BoardController controller;
	private static ArrayList<int[]> queue;
	private static boolean[][] visited;
	//private static int counter = 0;
	
	public static void main(String[] args) {
		
		controller = BoardController.getBoardController();
		
		Load(controller);
		
		xAkt = xpos;
		yAkt = ypos;
		
		controller.updateLedStripe();
		
		visited = new boolean[12][12];
		
		for(int i = 0; i < visited.length; i++) {
			for(int k = 0; k < visited[i].length; k++) {
				visited[k][i] = false;
			}
		}
		
		queue = new ArrayList<int[]>();
		
		//moveAlgo();
		start();
	}
	
	public static void start() {
		try {
			controller.getKeyBuffer().popAll();
			boolean possible = moveAlgo();
			int verification = JOptionPane.showConfirmDialog(null,"Level ist Spielbar (Bitte Klicke auf OK, andernfalls wird das Programm geschlossen)");
			if(verification == 0 && possible){
				moveTo(xpos,ypos);
				controller.setColor(x_ende,y_ende,127,0,0);
				controller.updateLedStripe();
				
				while(xAkt != x_ende || yAkt != y_ende) {
					KeyEvent event = controller.getKeyBuffer().pop();
					if(event != null) {
						if(event.getID() == KeyEvent.KEY_PRESSED) {
							switch(event.getKeyCode()) {
								case KeyEvent.VK_UP:
									if(yAkt-1 >= 0 && !checkBorder(xAkt,yAkt-1)) {
										moveWithColor(xAkt,yAkt-1);
									}
									break;
								case KeyEvent.VK_DOWN:
									if(yAkt+1 < 12 && !checkBorder(xAkt,yAkt+1)) {
										moveWithColor(xAkt,yAkt+1);
									}
									break;
								case KeyEvent.VK_LEFT:
									if(xAkt-1 >= 0 && !checkBorder(xAkt-1,yAkt)) {
										moveWithColor(xAkt-1,yAkt);
									}
									break;
								case KeyEvent.VK_RIGHT:
									if(xAkt+1 < 12 && !checkBorder(xAkt+1,yAkt)) {
										moveWithColor(xAkt+1,yAkt);
									}
									break;
								case KeyEvent.VK_W:
									if(yAkt-1 >= 0 && !checkBorder(xAkt,yAkt-1)) {
										moveWithColor(xAkt,yAkt-1);
									}
									break;
								case KeyEvent.VK_S:
									if(yAkt+1 < 12 && !checkBorder(xAkt,yAkt+1)) {
										moveWithColor(xAkt,yAkt+1);
									}
									break;
								case KeyEvent.VK_A:
									if(xAkt-1 >= 0 && !checkBorder(xAkt-1,yAkt)) {
										moveWithColor(xAkt-1,yAkt);
									}
									break;
								case KeyEvent.VK_D:
									if(xAkt+1 < 12 && !checkBorder(xAkt+1,yAkt)) {
										moveWithColor(xAkt+1,yAkt);
									}
									break;
								default:
									break;
							}
						}
					}
				}
				
				verification = JOptionPane.showConfirmDialog(null,"Geschaft!");
				System.exit(verification);				
			}
			else {
				System.exit(0);
			}
		}
		catch(IndexOutOfBoundsException e) {
			int verification = JOptionPane.showConfirmDialog(null,"Level nicht möglich!");
			System.exit(verification);
		}
	}
	
	public static boolean checkBorder(int x, int y) {
		int[] colorAT = controller.getColorAt(x, y);
		if(colorAT[0] == 0 && colorAT[1] == 0 && colorAT[2] == 127) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean moveAlgo() {
		if(xAkt == x_ende && yAkt == y_ende) {
			return true;
		}
		else {
			checkFreeTiles();
			int[] move = queue.get(0);
			queue.remove(move);
			moveTo(move[0], move[1]);
			return moveAlgo();
		}
	}
	
	public static void moveTo(int x, int y) {
		xAkt = x;
		yAkt = y;
	}
	
	public static void moveWithColor(int x, int y) {
		controller.setColor(xAkt, yAkt, 0,0,0);
		controller.setColor(x, y, 0,127,0);
		xAkt = x;
		yAkt = y;
		controller.updateLedStripe();
	}
	
	public static void checkFreeTiles() {
		 	int[][] temp = new int[4][2];
		 	temp[0] = null;
		 	temp[1] = null;
		 	temp[2] = null;
		 	temp[3] = null;
		 
			if(checkOutOfBounds(xAkt-1,yAkt) && checkColor(xAkt-1,yAkt)){
				int[] input = {xAkt-1,yAkt};
				temp[0] = input;
			}
			if(checkOutOfBounds(xAkt,yAkt-1) && checkColor(xAkt,yAkt-1)){
				int[] input = {xAkt,yAkt-1};
				temp[1] = input;
			}
			if(checkOutOfBounds(xAkt+1,yAkt) && checkColor(xAkt+1, yAkt)){
				int[] input = {xAkt+1,yAkt};
				temp[2] = input;
			}
			if(checkOutOfBounds(xAkt,yAkt+1) && checkColor(xAkt, yAkt+1)){
				int[] input = {xAkt,yAkt+1};
				temp[3] = input;
			}
			for(int[] move: temp) {
				boolean testNull = move == null;
				if(!testNull && !visited[move[1]][move[0]]) {
					queue.add(move);
					visited[move[1]][move[0]] = true;
				}
			}
		 
	}
	
	public static void moveRandom(int x,int y) {
		Random rand = new Random();
		int i = 0;
		while(x != x_ende || y != y_ende) {
			System.out.println(i);
			switch(rand.nextInt(4)) {
				case 0:
					if(checkOutOfBounds(x-1,y) && checkColor(x-1,y)) { // Oben
						controller.setColor(x, y, 0,0,0);
						controller.setColor(x-1, y, 0 , 127,0);
						x-=1;
					}
					break;
				case 1:
					if(checkOutOfBounds(x,y-1) && checkColor(x,y-1)) { //Links
						controller.setColor(x, y, 0,0,0);
						controller.setColor(x, y-1, 0 , 127,0);
						y-=1;
					}
					break;
				case 2:
					if(checkOutOfBounds(x+1,y) && checkColor(x+1, y)){ //Unten
						controller.setColor(x, y, 0,0,0);
						controller.setColor(x+1, y, 0 ,127,0);
						x+=1;
					}
					break;
				case 3:
					if(checkOutOfBounds(x,y+1) && checkColor(x, y+1)) {
						controller.setColor(x, y, 0,0,0);
						controller.setColor(x, y+1, 0 ,127 , 0);
						y+=1;
					}
					break;
				default:
			}
			i+= 1;
			controller.updateLedStripe();
		}
	}
	
	private static boolean checkColor(int x, int y) {
		int[] colorAT = controller.getColorAt(x, y);
		
		if((colorAT[0] == 0 && colorAT[1] == 0 && colorAT[2] == 0) || (colorAT[0] == 127 && colorAT[1] == 0 && colorAT[2] == 0)) {
			return true;
		}
		return false;
	}
	
	private static boolean checkOutOfBounds(int x, int y) {
		if(x < 12 && x >= 0 && y >= 0 && y < 12) {
			return true;
		}
		return false;
	}
	
	private static void Load(BoardController controller) {
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
					switch(Integer.parseInt(array[k])) {
						case 0:
							controller.setColor(k,i,0,0,0);
							break;
						case 1:
							controller.setColor(k,i,0,127,0);
							xpos = k;
							ypos = i;
							break;
						case 2:
							controller.setColor(k,i,127,0,0);
							x_ende = k;
							y_ende = i;
							break;
						case 3:
							controller.setColor(k,i,0,0,127);
							break;
						default:
							break;
					}
				}
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
