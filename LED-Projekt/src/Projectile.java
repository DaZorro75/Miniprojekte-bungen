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
		if (this.y >= 0) {
			draw(this.x, this.y - 1);
			controller.setColor(this.x, this.y, 100, 100, 100);
			getCollision();
			shoot();
		}
		else {
			draw(this.x, 11);
			controller.setColor(this.x, 0, 100, 100, 100);
		}
	}
	
	public void reverse() {
		if (this.y + 1 <= 11) {
			draw (this.x, this.y + 1);
			controller.setColor(this.x, this.y, 100, 100, 100);
			reverse();
		}
		else {
				draw(this.x, 12);
				controller.setColor(this.x, 11, 100, 100, 100);
		}
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
	
	public boolean getCollision() {
		int nextColor = 0;
		if (this.y - 1 >= 0) {
			nextColor = Main.checkColor(this.x, this.y -1);
			
		}
		if (nextColor != 0) {
			if (Main.checkColor(this.x, this.y - 1) == 0) {
				return false;
			}
			else if (Main.checkColor(this.x, this.y - 1) == 1) {
				System.err.println("Kollision mit einer Wand.");
				this.reverse();
				return true;
			}
			else {
				System.err.println("Kollision mit einem Enemy");
				return true;
			}
		}
		else {
			return false;
		}
	}
}
