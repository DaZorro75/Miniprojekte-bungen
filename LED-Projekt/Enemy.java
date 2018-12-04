import ledControl.BoardController;
import java.util.*;
public class Enemy {
	
	private  int x_Size;
	private static int y_Size = 1;
	private BoardController controller;
	private int x;
	private int y;
	private int lastDot;
	
	public Enemy(int x) {
		this.x_Size = x;
	}
	//Zeichnet den Übergebenen Gegner auf das Spielfeld
	public void draw(Enemy enemy, int x, int y) {
		controller = BoardController.getBoardController();
		 for (int i = x; i < x + this.x_Size; i++) {
			 controller.setColor(i, y, 100, 0, 0);
			 controller.updateBoard();
			 this.x = x;
			 this.y = y;
		 }	
	}
	//pos[0] = Position X
	//pos[1] = Position Y
	//pos[3] = Position des letzten Punkts
	private int[] getCurrentPosition() {
		int[] pos = new int[3];
		pos[0] = this.x;
		pos[1] = this.y;
		pos[2] = this.x + this.x_Size;
		System.out.println(pos[0]);
		System.out.println(pos[1]);
		return pos;
		
	}
	
	//Bewegt den übergebenen Gegner von links nach rechts
	public void moveEnemy(Enemy enemy, int fields) {
		int[] pos = getCurrentPosition();
		if (pos[0] != 12 - this.x_Size || pos[2] != 12) {
			for (int i = pos[0]; i < pos[0] + fields; i++) {
			draw(enemy, pos[0] + fields, pos[1]);
			controller.setColor(i, pos[1], 100, 100, 100);
			}
		}
	}
	
	public void moveMultipleEnemies() {
		
	}
}