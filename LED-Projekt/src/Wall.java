package src;

import ledControl.BoardController;;

public class Wall {

	//RGB f�r die Wand: 127 - 90 - 43

	private BoardController controller = Main.getController();
	private int size;
	private int x;
	private int y;
	private int head = this.x + this.size;

	public Wall(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}

	//Zeichnet die Wand auf das Spielfeld
	public void draw(int y, int x) {
		for (int i = x; i < this.size + x ; i++) {
		controller.setColor(i, y, 100, 90, 43);
		controller.updateBoard();
		this.x = x;
		this.y = y;
		}
	}

	//Gibt die Position der Wand als Array wieder
	public int[] getPosition() {
		int[] pos = new int[3];
		pos[0] = this.x;
		pos[1] = this.y;
		pos[2] = this.head;
		return pos;
	}

	//Bewegt die Wand um die �bergebene Anzahl an Feldern
	public void moveWall(int fields) {
		int pos[] = getPosition();
		int[] controllerColor = new int[3];
		controllerColor = controller.getColorAt(pos[0], pos[1]);
		int[] col = new int[3];
		col[0] = 127;
		col[1] = 90;
		col[2] = 43;
		if(pos[2] < 12) {
			for (int i = pos[0]; i < pos[0] + fields; i++) {
				draw(pos[1], i);
				if (controllerColor[0] != 127) {
				controller.setColor(i, pos[1], 100, 100, 100);
				}
				else {
					controller.setColor(i, pos[1], 100, 90, 43);
				}
				controller.updateBoard();
			}
		}
	}


}
 
