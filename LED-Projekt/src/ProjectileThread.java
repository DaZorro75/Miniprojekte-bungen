package src;

import ledControl.BoardController;
import ledControl.gui.KeyBuffer;
import java.awt.event.KeyEvent;


public class ProjectileThread extends Thread {

	private BoardController controller = BoardController.getBoardController();
	KeyBuffer buffer = controller.getKeyBuffer();
	
	public void run(Projectile p) {
		while (true) {
			KeyEvent event = buffer.pop();
			if (event != null) {
				if (event.getID() == java.awt.event.KeyEvent.KEY_PRESSED) 
					switch (event.getKeyCode()){
					case java.awt.event.KeyEvent.VK_SPACE:
					p.shoot();
					break;
					case java.awt.event.KeyEvent.VK_LEFT:
					if (p.getPosition()[0] != 0) {
						controller.setColor(p.getPosition()[0], p.getPosition()[1], 100, 100, 100);
						p.draw(p.getPosition()[0] - 1, p.getPosition()[1]);
						controller.updateBoard();
					}
					break;
					case java.awt.event.KeyEvent.VK_RIGHT:
						if (p.getPosition()[0] != 11) {
							controller.setColor(p.getPosition()[0], p.getPosition()[1], 100, 100, 100);
							p.draw(p.getPosition()[0] + 1, p.getPosition()[1]);
							controller.updateBoard();
						}
						break;
						default:
				}
			}
		}
	}	
}
