import ledControl.BoardController;

public class Test {

	public static BoardController controller;
	public Enemy enemy;
	
	public static void main(String[] args) 	{	
		controller = BoardController.getBoardController();	
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 13; j++) {
				controller.setColor(i, j, 100, 100, 100);
				controller.setColor(12 - i, 12 - j, 100, 100, 100);
				controller.updateBoard();
			}
		}	
		Enemy en = new Enemy(5);
		en.draw(en, 2, 1);
		controller.updateBoard();
		controller.sleep(100);
		en.moveEnemy(en);
		controller.updateBoard();
		
		Enemy en2 = new Enemy(3);
		en2.draw(en2, 4, 5);
		controller.updateBoard();
		}
		/*controller.setBackgroundColor(100, 100, 100);
		//Füllt das Board senkrecht rot
		for (int i = 0; i < 12;  i++) {
			for (int j = 0; j < 12; j++) {
			controller.setColor(i, j, 100, 0, 0);
			controller.updateBoard();
			}
		}
		//Füllt das Board senkrecht grün
		for (int i = 0; i < 12;  i++) {
			for (int j = 0; j < 12; j++) {
			controller.setColor(i, j, 0, 100, 0);
			controller.updateBoard();
			}
		}
		//Füllt das Board senkrecht blau
		for (int i = 0; i < 12;  i++) {
			for (int j = 0; j < 12; j++) {
			controller.setColor(i, j, 0, 0, 100);
			controller.updateBoard();
			}
		}
		//Füllt das Board senkrecht ziegelrot
		for (int i = 0; i < 12;  i++) {
			for (int j = 0; j < 12; j++) {
			controller.setColor(i, j, 67, 35, 36);
			controller.updateBoard();
			}
		}
		//interessanter Füllefekt von zwei Seiten
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 13; j++) {
				controller.setColor(i, j, 100, 0, 0);
				controller.setColor(12 - i, 12 - j, 100, 0, 0);
				controller.updateBoard();
		}
	} */
	
}
