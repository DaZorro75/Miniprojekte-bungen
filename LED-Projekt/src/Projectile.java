package src;

import ledControl.BoardController;
import java.awt.event.*;

public class Projectile implements KeyListener{

	private int x;
	private int y;
	private int[] RGB = new int[3];
	private BoardController controller = Main.getController();
	private int begin;

	public Projectile(String color) {
		if (color == "Blue") {
			RGB[0] = 0; 
			RGB[1] = 0;
			RGB[2] = 100;
		}
		else if (color == "Red") {
			RGB[0] = 100; 
			RGB[1] = 0;
			RGB[2] = 0;
		}
		else if (color == "Green") {
			RGB[0] = 0; 
			RGB[1] = 100;
			RGB[2] = 0;
		}
		else {
			System.err.println("Die übergebene Farbe für das Projektil ist ungültig");
			RGB[0] = 100; 
			RGB[1] = 100;
			RGB[2] = 100;
		}
	}

	public int[] getPosition() {
		int[] pos = new int[2];
		pos[0] = this.x;
		pos[1] = this.y;
		return pos;
	}

	public void draw(int x, int y) {
		controller.setColor(x, y, RGB[0], RGB[1], RGB[2]);
		controller.updateBoard();
		this.x = x;
		this.y = y;
	}

	public void shoot() {
		int i = 0;
		while (i < 12) {
			draw(this.x, 12 - i);
			controller.setColor(this.x, 12 - i, 100, 100, 100);
			if(i == 0) {
			controller.sleep(999);
			}
			controller.updateBoard();
			i++;
			}
		}
	
	//Key Events
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
			int[] pos = this.getPosition();
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			draw(pos[0] - 1, pos[1]);
			controller.setColor(pos[0], pos[1], 100, 100, 100);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			draw(pos[0] + 1, pos[1]);
			controller.setColor(pos[0], pos[1], 100, 100, 100);
		}
		controller.updateBoard();
	}
}
