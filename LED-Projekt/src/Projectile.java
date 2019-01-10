package src;

import ledControl.BoardController; 
import ledControl.gui.KeyBuffer;

public class Projectile{

	private int x;
	private int y;
	private int[] RGB = new int[3];
	private BoardController controller = Main.getController();
	KeyBuffer buffer = controller.getKeyBuffer();
	private Enemy[] enemies = Main.getEnemies();
	private boolean debug = Main.getDebug();
	
	public Projectile(int i) {
		if (i == 0) {
			RGB[0] = 0; 
			RGB[1] = 0;
			RGB[2] = 100;
		}
		else if (i == 1) {
			RGB[0] = 100; 
			RGB[1] = 0;
			RGB[2] = 0;
		}
		else if (i == 2) {
			RGB[0] = 0; 
			RGB[1] = 100;
			RGB[2] = 0;
		}
		else if (i == 3) {
			RGB[0] = 100;
			RGB[1] = 100;
			RGB[2] = 0;
					
		}
		else if (i == 4) {
			RGB[0] = 0;
			RGB[1] = 100;
			RGB[2] = 92;
		}
		else {
			System.err.println("Die übergebene Farbe für das Projektil ist ungültig");
			RGB[0] = 100; 
			RGB[1] = 100;
			RGB[2] = 100;
		}
	}
	
	public void changeColor(int i) {
		if (i == 0) {
			RGB[0] = 0; 
			RGB[1] = 0;
			RGB[2] = 100;
		}
		else if (i == 1) {
			RGB[0] = 100; 
			RGB[1] = 0;
			RGB[2] = 0;
		}
		else if (i == 2) {
			RGB[0] = 0; 
			RGB[1] = 100;
			RGB[2] = 0;
		}
		else if (i == 3) {
			RGB[0] = 100;
			RGB[1] = 100;
			RGB[2] = 0;
					
		}
		else if (i == 4) {
			RGB[0] = 0;
			RGB[1] = 100;
			RGB[2] = 92;
		}
		else {
			System.err.println("Die übergebene Farbe für das Projektil ist ungültig");
			RGB[0] = 100; 
			RGB[1] = 100;
			RGB[2] = 100;
		}
		this.draw(this.x, this.y);
	}
	
	
	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = this.x;
		pos[1] = this.y;
		return pos;
	}

	public void draw(int x, int y) {
		controller.setColor(x, y, RGB[0], RGB[1], RGB[2]);
		controller.updateBoard();
		this.x = x;
		this.y = y;
	}
	
	public int getNextColor(int x, int y) {
		if (this.y - 1 > -1) {
			return Main.checkColor(x, y - 1, debug);
		}
		else {
			return -1;
		}
	}

	public void shoot() {
		int next = getNextColor(this.x, this.y);
		if (this.debug == true) {
		switch(next) {
		case -1:
			controller.setColor(this.x, 11, 100, 100, 100);
			draw(this.x, 11);
			System.err.println("Das Ende des Feldes wurde erreicht!");
			break;
		case 0:
			controller.setColor(this.x, this.y, 100, 100, 100);
			draw(this.x, this.y -1);
			shoot();
			break;
		case 1:
			break;
		case 2:
			Enemy temp = findEnemy(this.x, this.y - 1);
			if (this.x == temp.getCurrentPosition()[0]) {
				temp.removeAt(1);
				Main.updateThread();
				controller.setColor(this.x, this.y, 100, 100, 100);
				draw(this.x, 11);
			}
			else {
				temp.removeAt(this.x - temp.getCurrentPosition()[0]);
				Main.updateThread();
				controller.setColor(this.x, this.y, 100, 100, 100);
				draw(this.x, 11);
			}
			System.err.println("Mit einem Enemy wurde kollidiert, Code ausgeführt.");
			Main.setEnemyByID(temp, temp.id);
			break;
			}
		}
		else {
			switch(next) {
			case -1:
				controller.setColor(this.x, 11, 0, 0, 0);
				break;
			case 0:
				controller.setColor(this.x, this.y, 0, 0, 0);
				draw(this.x, this.y -1);
				shoot();
				break;
			case 1: 
				break;
			case 2:
				Enemy temp = findEnemy(this.x, this.y - 1);
				if (this.x == temp.getCurrentPosition()[0]) {
					temp.removeAt(0);
					controller.setColor(this.x, this.y, 0, 0, 0);
					draw(this.x, 11);
				}
				else {
					temp.removeAt(this.x - temp.getCurrentPosition()[0]);
					controller.setColor(this.x, this.y, 0, 0, 0);
					draw(this.x, 11);
				}
				System.err.println("Mit einem Enemy wurde kollidiert, Code ausgeführt.");
				Main.setEnemyByID(temp, temp.id);
				Main.updateThread();
				break;
				}
		}
	}
	
	public void collision(int x, int y) {
		int nextColor = Main.checkColor(x, y, debug);
		switch(nextColor) {
		case 1:
			if (this.x + 1 != 11) {
				draw(this.x, this.y + 1);
				if (debug == true) {
					controller.setColor(this.x, this.y, 100, 100, 100);
				}
				else {
					controller.setColor(this.x, this.y, 0, 0, 0);
				}
			}
			collision(this.x, this.y + 1);
		case 2:
			
			default:
		}
	}
	
	public int[] getRGB() {
		return this.RGB;
	}
	
	public int getColor() {
		if (
			RGB[0] == 0 && 
			RGB[1] == 0 &&
			RGB[2] == 100
			) 
			{
			return 0;
			//Blau
		}
		else if (
			RGB[0] == 100 && 
			RGB[1] == 0 &&
			RGB[2] == 0
			)
			{
			return 1;
			//Rot
		}
		else if (
			RGB[0] == 0 && 
			RGB[1] == 100 &&
			RGB[2] == 0
			)
		{
			return 2;
			//Grün
		}
		else if (
			RGB[0] == 100 &&
			RGB[1] == 100 &&
			RGB[2] == 0
			)
		{
			return 3;
			//Gelb
		}
		else {
			return 4;
			//Cyan
		}
	}
	
	public Enemy findEnemy(int x, int y) {
		Enemy result = null;
		for (int i = 0; i < enemies.length; i++) {
			if (enemies[i].getCurrentPosition()[1] == y) {
				result = enemies[i];
			}
		}
		return result;
	}
}
	
