import ledControl.BoardController;
import java.util.*;
public class Enemy {
	
	private  int x_Size;
	private static int y_Size = 1;
	private BoardController controller;
	
	public Enemy(int x) {
		this.x_Size = x;
	}
		public void draw(Enemy enemy, int x, int y) {
		controller = BoardController.getBoardController();
		 for (int i = x; i < x + this.x_Size; i++) {
			 controller.setColor(i, y, 100, 0, 0);
			 controller.updateBoard();
		 }
		
	}
	
	

}