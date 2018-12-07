package src;

import ledControl.BoardController;
import javax.swing.*;
import java.awt.event.*;

public class Main {

	private static BoardController controller = BoardController.getBoardController();
	private static int width = 12;


	public static void initializePlayField() {
//		controller = BoardController.getBoardController();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 12; j++) {
					controller.setColor(i, j, 100, 100, 100);
					controller.setColor(12 - i -1, 12 - j -1, 100, 100, 100);
					controller.updateBoard();
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

	public static BoardController getController() {
		return controller;
	}

	public static void main(String[] args) {
	    
		//Test der Methoden
		initializePlayField();

		//Info: Der schwarze Gegner dient dem Testen, ob die übergebene Farbe gültig ist
		Enemy a = new Enemy(5, "Red");
		Enemy b = new Enemy(2, "Blue");
		Enemy c = new Enemy(3, "Green");
		Enemy d = new Enemy(4, "Black");
		Enemy e = new Enemy(6, "Cyan");
		Enemy f = new Enemy(4, "Yellow");
		Enemy[] enemies = new Enemy[] {a,b,c,d,e,f};

		a.draw(2, 2);
		b.draw(7, 5);
		c.draw(5, 7);
		d.draw(4, 10);
		e.draw(3, 6);
		f.draw(11, 4);
		MovingThreadTest move = new MovingThreadTest(enemies);
		move.start();

		Wall wall = new Wall(3);
		Wall wall2 = new Wall(3);
		wall.draw(6, 0);
		wall2.draw(6, 5);
		
	

		Projectile p = new Projectile("Red");
		p.draw(5, 11);
		p.shoot();
		controller.updateBoard();
		
		
		while(a.getCurrentPosition()[2] != 12 || b.getCurrentPosition()[2] != 12) {
		controller.updateBoard();

		}
	}
}
