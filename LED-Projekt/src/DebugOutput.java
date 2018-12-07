package src;

import javax.swing.*;

public class DebugOutput{

	/* Folgende Integer Werte repräsentieren die folgenden Objekte:
	 * 0 - Void
	 * 1 - Enemy
	 * 2 - Wall
	 * 3 - Projectile
	 */
	public int[][] objPos = new int[12][12];
	
	public DebugOutput() {
		
	}
	
	public void initialize() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12;j++) {
				objPos[i][j] = 0;
			}
		}
	}
	
	
	public void writeEnemyPos(int x, int y, Enemy enemy) {
		for (int i = x; i < x + enemy.getLength(); i++) {
			objPos[i][y] = 1;
		}
	}
	
	public void writeWallPos(int x, int y, Wall wall) {
		for (int i = x; i < x + wall.getSize(); i++) {
			objPos[i][y] = 2;
		}
	}
	
	public void writeProjectilePos(int x, int y, Projectile projectile) {
		objPos[x][y] = 3;
	}
	public void showData() {
	JTextField jTextField1 = new JTextField();
		String txt = "";
		for (int i = 0; i < objPos.length; i++) {
			for (int j = 0; j < objPos[0].length; j++) {
				txt += objPos[i][j] + " ,";
			}
			txt += "\n";
		}
		System.out.println(txt);
	}
}
