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
		
		Enemy a = new Enemy(5);
		MovingThreadTest a1 = new MovingThreadTest();
		Enemy b = new Enemy(2);
		MovingThreadTest b1 = new MovingThreadTest();
		a.draw(a, 2, 2);
		b.draw(b, 7, 5);
		while(a.getCurrentPosition()[2] != 12 || b.getCurrentPosition()[2] != 12) {
		a1.run(a);
		controller.updateBoard();
		a1.run(b);
		controller.updateBoard();
		}
		
		
		
		clearPlayField();
	}
}
