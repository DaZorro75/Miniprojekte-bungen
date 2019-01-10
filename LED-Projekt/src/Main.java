package src;


import ledControl.BoardController; 
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

import java.util.Arrays;
import java.util.LinkedList;
import ledControl.gui.KeyBuffer;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Main {

	private static BoardController controller = BoardController.getBoardController();
	static KeyBuffer buffer = controller.getKeyBuffer();
	public LinkedList<Enemy> enemyList;
	private static Enemy[] eN;
	private static MovingThreadTest thread;
	public static boolean debug_Mode;
	public static Projectile pro;
	
	public static void setEnemyByID(Enemy e, int id) {
		for (int i = 0; i < eN.length; i++) {
			if (eN[i].id == id) {
				eN[i] = e;
			}
		}
	}
	
	
	
	public static void updateThread() {
		try 
		{thread.interrupt();
		MovingThreadTest t = new MovingThreadTest(eN);
		thread = t;
		thread.start();
		}
		catch (Exception e) {
			
		}
	}
	
	public static void start() {
		thread.start();
	}
	
	public static boolean getDebug() {
		return debug_Mode;
	}
	
	public static Enemy[] getEnemies() {
		return eN;
	}
	
	public static void setEnemies(Enemy[] e) {
		eN = e;
	}
	
	public static MovingThreadTest getThread() {
		return thread;
	}

	
	public static Projectile getProjectile() {
		return pro;
	}
	
	public static void setProjectile(Projectile p) {
		pro = p;
	}
	

	public static void initializePlayField(boolean d) {
//		controller = BoardController.getBoardController();
		if (d == true) {
		for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
					controller.setColor(i, j, 100, 100, 100);
					controller.setColor(12 - i -1, 12 - j -1, 100, 100, 100);
					controller.updateBoard();
				}
			}
		}
		else {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
					controller.setColor(i, j, 0, 0, 0);
					controller.setColor(12 - i -1, 12 - j -1, 0, 0, 0);
					controller.updateBoard();
				}
			}
		}
	}

	public static void clearPlayField() {
//		controller = BoardController.getBoardController();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
					controller.setColor(i, j, 0, 0, 0);
					controller.setColor(12 - i -1, 12 - j -1, 0, 0, 0);
					controller.updateBoard();
				}
			}
		}

	private static int getRandomNumber() {
		Random r = new Random();
		return r.nextInt(5);
	}
	public static BoardController getController() {
		return controller;
	}
	
	public static int checkColor(int x, int y, boolean debug) {
		// 0 - Void; 1 - Wall; 2 - Enemy; 3 - Projectile
		int[] i = controller.getColorAt(x, y);
		if (i[0] == 100 && i[1] == 100 && i[2] == 100 && debug == true) {
			return 0;
		}
		else if (i[0] == 0 && i[1] == 0 && i[2] == 0 && debug == false) {
			return 0;
		}
		else if (i[0] == 100 && i[1] == 90 && i[2] == 43) {
			return 1;
		}
		else if (i[0] == pro.getRGB()[0] && i[1] == pro.getRGB()[1] && i[2] == pro.getRGB()[2]) {
			return 3;
		}
		else {
			return 2;
		}
	}

	public static void main(String[] args) {
		
		int debug = JOptionPane.showConfirmDialog(null, 
															"Debug-Modus aktivieren?",	
															"Debug-Modus",
															JOptionPane.YES_NO_OPTION);
		if (debug == 0) {
			//JPasswordField passwordField = new JPasswordField(10);
		    //passwordField.setEchoChar('*');
		    //JOptionPane.showMessageDialog ( null, passwordField, "Passwort?", JOptionPane.OK_OPTION );
		    //char[] chars = passwordField.getPassword();
		    //String BenutzerPassEingabe = new String("Kekse");
		    //if (Arrays.equals(chars, BenutzerPassEingabe.toCharArray())) {
		    	debug_Mode = true;
		    	initializePlayField(true);
		    //}
		    //else {
		    	//JOptionPane.showMessageDialog(null, "Du besitzt nicht die notwendigen Rechte den Debug - Modus aufzurufen");
		    	//System.exit(0);
		    //}
			
		}
		else {
			initializePlayField(false);
			debug_Mode = false;
		}
			//Test der Methoden
		//initializePlayField();

		//Info: Der schwarze Gegner dient dem Testen, ob die übergebene Farbe gültig ist
		Enemy a = new Enemy(5, "Red" , 0);
		Enemy b = new Enemy(2, "Blue", 1);
		Enemy c = new Enemy(3, "Green", 2);
		Enemy d = new Enemy(4, "Black", 3);
		Enemy e = new Enemy(6, "Cyan", 4);
		Enemy f = new Enemy(4, "Yellow", 5);
		eN = new Enemy[] {a,b,c,d,e,f};
		

		a.draw(2, 2, debug_Mode);
		b.draw(7, 5, debug_Mode);
		c.draw(5, 7, debug_Mode);
		d.draw(4, 10, debug_Mode);
		e.draw(3, 0, debug_Mode);
		f.draw(11, 4, debug_Mode);
		MovingThreadTest move = new MovingThreadTest(eN);
		thread = move;
		move.start();


		Wall wall = new Wall(3);
		Wall wall2 = new Wall(3);
		wall.draw(6, 0);
		wall2.draw(6, 5);
		
	

		Projectile p = new Projectile(getRandomNumber());
		p.draw(5, 11);
		pro = p;
		
		
		controller.updateBoard();
		
		
		while(a.getCurrentPosition()[2] != 12 || b.getCurrentPosition()[2] != 12) {
		controller.updateBoard();
		
		//Key Events
		
		while (true){
			KeyEvent event = buffer.pop();
			if (event != null){
				if (event.getID() == java.awt.event.KeyEvent.KEY_PRESSED)
					switch (event.getKeyCode()){
					case java.awt.event.KeyEvent.VK_SPACE:
					p.shoot();
					p.changeColor(getRandomNumber());
					break;
					case java.awt.event.KeyEvent.VK_LEFT:
					if (p.getPosition()[0] != 0) {
						if (debug_Mode == true) {
						controller.setColor(p.getPosition()[0], p.getPosition()[1], 100, 100, 100);
						p.draw(p.getPosition()[0] - 1, p.getPosition()[1]);
						controller.updateBoard();
						}
						else {
							controller.setColor(p.getPosition()[0], p.getPosition()[1], 0, 0, 0);
							p.draw(p.getPosition()[0] - 1, p.getPosition()[1]);
							controller.updateBoard();
						}
					}
					break;
					case java.awt.event.KeyEvent.VK_RIGHT:
						if (p.getPosition()[0] != 11) {
							if (debug_Mode == true) {
							controller.setColor(p.getPosition()[0], p.getPosition()[1], 100, 100, 100);
							p.draw(p.getPosition()[0] + 1, p.getPosition()[1]);
							controller.updateBoard();
						}
							else {
								controller.setColor(p.getPosition()[0], p.getPosition()[1], 0, 0, 0);
								p.draw(p.getPosition()[0] + 1, p.getPosition()[1]);
								controller.updateBoard();
							}
						}
						break;
						default:
					}
				}
			} 
		}  
	} 
}
