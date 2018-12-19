package src;


import ledControl.BoardController;
import java.util.*;
public class Enemy {

	private  int x_Size;
	private static int y_Size = 1;
	private boolean direction = false;//false rechts rum
	private BoardController controller = BoardController.getBoardController();
	private int x;
	private int y;
	private int lastDot;
	private int[] RGB = new int[3];

	public Enemy(int x_Size, String color) {
		this.x_Size = x_Size;
		if (color.equals("Blue")) {
			RGB[0] = 0; 
			RGB[1] = 0;
			RGB[2] = 100;
		}
		else if (color.equals("Red")) {
			RGB[0] = 100; 
			RGB[1] = 0;
			RGB[2] = 0;
		}
		else if (color.equals("Green")) {
			RGB[0] = 0; 
			RGB[1] = 100;
			RGB[2] = 0;
		}
		else if (color.equals("Yellow")) {
			RGB[0] = 100;
			RGB[1] = 100;
			RGB[2] = 0;
					
		}
		else if (color.equals("Cyan")) {
			RGB[0] = 0;
			RGB[1] = 100;
			RGB[2] = 92;
		}
		else {
			System.err.println("Die übergebene Farbe für den Gegner ist ungültig");
			RGB[0] = 100; 
			RGB[1] = 100;
			RGB[2] = 100;
		}
	}
	
	// Gibt den gewünschten Enemy zurück
	public Enemy getEnemy() {
		return this;
	}
	
	//Zeichnet den Übergebenen Gegner auf das Spielfeld
	public void draw(int x, int y) {
//		controller = BoardController.getBoardController();
		 for (int i = x; i < x + this.x_Size; i++) {
			 controller.setColor(i, y, RGB[0], RGB[1], RGB[2]);

			 this.x = x;
			 this.y = y;
		 }
		 controller.updateBoard();
	}
	//pos[0] = Position X
	//pos[1] = Position Y
	//pos[3] = Position des letzten Punkts
	public int[] getCurrentPosition() {
		int[] pos = new int[3];
		pos[0] = this.x;
		pos[1] = this.y;
		pos[2] = this.x + this.x_Size;
//		System.out.println("Der Anfang ist an der X-Koordinate:" + pos[0]);
//		System.out.println("Die Y- Koordinate des Objektes lautet:" + pos[1]);
//		System.out.println("Das Ende des Gegners ist an der X-Koordinate " + pos[2]);
		return pos;

	}
	
	
	public int getLength() {
		return this.x_Size;
	}

	//Bewegt den übergebenen Gegner von links nach rechts
	public void moveEnemy(int step) {

		boolean oldDirection = direction;
		if (y == 5)
		System.out.println("" + direction + " " + x + " " + y + " " + x_Size);
		//Linksbewegung
		if (direction) {

			if (x == 0) {

				direction = !direction;
				x+= step;
			}
			else {

				x-=step;
			}
		}
		else {
			//Rechtsbewegung
			if (x + x_Size == 12 ) {

				direction = !direction;
				x-= step;
			}
			else {

				x+=step;
			}

		}
		draw(direction);
	}

	public void draw(boolean fill) {

		 for (int i = x; i < x + this.x_Size; i++) {
			 
			 controller.setColor(i, y, RGB[0], RGB[1], RGB[2]);
		 }
		 if (fill == true) {
			 
			 controller.setColor(x + x_Size, y, 100, 100, 100);
		 }
		 else {
			 
			 controller.setColor(x -1, y, 100, 100, 100);
		 }
	}
	
	/*public void removeSpotAt(int x) {
		Enemy temp = this;
		int t = x - temp.getCurrentPosition()[0];
		while ()
		}
	}*/
}
