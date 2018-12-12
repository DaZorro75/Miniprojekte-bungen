package src;

import ledControl.BoardController; 
import ledControl.gui.KeyBuffer;

public class Projectile{

	private int x;
	private int y;
	private int[] RGB = new int[3];
	private BoardController controller = Main.getController();
	private int begin;
	KeyBuffer buffer = controller.getKeyBuffer();

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

	public void shoot() {
		if (this.y > 0) {
			draw(this.x, this.y - 1);
			controller.setColor(this.x, this.y, 100, 100, 100);
			shoot();
		}
		else {
			draw(this.x, 11);
			controller.setColor(this.x, 0, 100, 100, 100);
		}
	}
}
