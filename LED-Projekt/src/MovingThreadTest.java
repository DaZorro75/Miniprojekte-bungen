package src; 

import ledControl.BoardController;

public class MovingThreadTest extends Thread{

	private Enemy[] enemies;
	private BoardController controller = BoardController.getBoardController();
	public MovingThreadTest(Enemy[] enemies) {

	this.enemies = enemies;

	}
	public void run() {
		//		controller = Main.getController();
		while (true) {
			for(int i = 0; i < enemies.length; i++) {

					enemies[i].moveEnemy(1);
				}
			controller.updateBoard();
			}
		}
	}
