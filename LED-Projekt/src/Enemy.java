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
	
	public void draw(Enemy enemy, int x, int y) {
		controller = BoardController.getBoardController();
		 for (int i = x; i < x + this.x_Size; i++) {
			 controller.setColor(i, y, 100, 0, 0);
			 controller.updateBoard();
			 this.x = x;
			 this.y = y;
		 }	
	}
	private int[] getCurrentPosition() {
		int[] pos = new int[3];
		pos[0] = this.x;
		pos[1] = this.y;
		pos[2] = this.x + this.x_Size;
		System.out.println(pos[0]);
		System.out.println(pos[1]);
		return pos;
		
	}
	
	public void moveEnemy(Enemy enemy) {
		int[] pos = getCurrentPosition();
		if (lastDot != 12) {
		for (int i = 0; i < 12 - lastDot; i++) {
			controller.setColor(x, pos[1], 100, 100, 100);
			controller.updateBoard();
			draw(enemy, pos[0] + i, pos[1]);
			controller.updateBoard();
			}
		}
		else {
			;
		}
	}

}