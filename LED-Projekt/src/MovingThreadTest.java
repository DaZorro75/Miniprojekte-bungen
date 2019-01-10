package src; 

import ledControl.BoardController;

public class MovingThreadTest extends Thread{

	private Enemy[] enemies;
	private BoardController controller = BoardController.getBoardController();
	public MovingThreadTest(Enemy[] enemies) {

	this.enemies = enemies;

	}
	
	public void updateEnemies(Enemy[] enemies) {
		if (this.isAlive() == true) {
			this.interrupt();
			this.enemies = enemies;
			this.resume();
		}
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
