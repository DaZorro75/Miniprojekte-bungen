package src;
import ledControl.BoardController;

public class Level {

	public int[][] level = new int[12][12];
	private static BoardController controller = Main.getController();
	
	public Level(String level) {
		for (int i = 0, j = 0; i < 12 && j < 12; i++, j++) {
			this.level[i][j] = 0;
		}
		
	}
	
	public void writeEnemy(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			this.level[i][y] = 1;
		}
	}
	
	public void writeWalls(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			this.level[i][y] = 2;
		}
	}
	
	public void drawLevelToBoard() {
		for (int i = 0, j = 0; i < 12 && j < 12; i++, j++) {
			if (this.level[i][j] == 0) {
				controller.setColor(i, j, 100, 100, 100);
			}
			else if (this.level[i][j] == 2) {
				int wallSize = 1;
				int k = i;
				int l = j;
				while (level[k][l] == 2) {
					wallSize++;
					k++;
				}
				Wall wall = new Wall(wallSize);
				wall.draw(i, j);
				i += wallSize;
			}
			else if (this.level[i][j] == 1) {
				int enemySize = 1;
				int r = i;
				int s = j;
				while (level[r][s] == 1) {
					enemySize++;
					r++;
				}
				Enemy enemy = new Enemy(enemySize, "Cyan");
				enemy.draw(i, j);
				i += enemySize;
			}
		}
	}
}
