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
	
	public static void main(String[] args) {
		//Test der Methoden
		initializePlayField();
		
		Enemy a = new Enemy(5);
		Enemy b = new Enemy(2);
		a.draw(a, 2, 2);
		b.draw(b, 7, 5);
		controller.sleep(2000);
		a.moveEnemy(a, 2);
		controller.sleep(2000);
		b.moveEnemy(b, 3);
		
		
		
		clearPlayField();
	}
}
