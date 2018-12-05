package src;

import ledControl.BoardController;

public class Main {
	
	private static BoardController controller;
	
	public static void initializePlayField() {
		controller = BoardController.getBoardController();
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 13; j++) {
					controller.setColor(i, j, 100, 100, 100);
					controller.setColor(12 - i, 12 - j, 100, 100, 100);
					controller.updateBoard();
				}
			}	
		}
	
	public static void clearPlayField() {
		controller = BoardController.getBoardController();
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 13; j++) {
					controller.setColor(i, j, 0, 0, 0);
					controller.setColor(12 - i, 12 - j, 0, 0, 0);
					controller.updateBoard();
				}
			}	
		}
	
	public static BoardController getController() {
		return controller;
	}
	
	public static void main(String[] args) {
		//Test der Methoden
		initializePlayField();
		
		Enemy a = new Enemy(5, "Red");
		MovingThreadTest a1 = new MovingThreadTest();
		Enemy b = new Enemy(2, "Blue");
		Enemy c = new Enemy(3, "Green");
		Enemy d = new Enemy(4, "Black");
		Wall wall = new Wall(3);
		Wall wall2 = new Wall(3);
		a.draw(a, 2, 2);
		b.draw(b, 7, 5);
		c.draw(c, 5, 7);
		d.draw(d, 4, 10);
		wall.draw(6, 0);
		wall2.draw(6, 5);
		int[] col = new int[3];
		while(a.getCurrentPosition()[2] != 12 || b.getCurrentPosition()[2] != 12) {
		a1.run(a);
		a1.run(b);
		a1.run(c);
		a1.run(d);
		controller.updateBoard();
		}
		
		
		
		clearPlayField();
	}
}
