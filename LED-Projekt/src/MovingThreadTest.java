package src; 

import ledControl.BoardController;

public class MovingThreadTest extends Thread{
	
	public BoardController controller;

	public void run(Enemy enemy) {
		controller = Main.getController();
		int[] pos = enemy.getCurrentPosition();
		if (pos[2] != 12) {
			enemy.moveEnemy(enemy, 1);
			controller.updateBoard();
		}
	}
}
