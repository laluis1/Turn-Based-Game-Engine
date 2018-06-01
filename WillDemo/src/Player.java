import java.awt.Graphics;
import java.awt.Image;

public class Player extends Entity {
	// 1: player team, 2: enemy team, 3: other team
	int team;
	int HP;
	int ATK;
	int SPD;
	int DEF;
	int RES;
	int SP; // skill point
	int MT; // might
	int MP; // movement
	// for MP:
	// 1. Infantry can only move 2 spaces and will only be able to move one
	// space through forests.
	// 2. Armoured can only move 1 space and cannot move through
	// forests.
	// 3. Flying can move 2 spaces regardless of terrain.
	// 4. Cavalry can move 3 spaces but cannot move through forests.
	char[] inventory;

	// 0 - n is move up, n - 2n is move right, 2n - 3n is move down, 3n - 4n is move
	// left
	Image sprites[];
	
	boolean walked = false;
	boolean used = false;
	
	String type = "";

	public Player(int x, int y) {
		super(x, y);
	}

	public void update() {

	}

	public void draw(Graphics g, int offsetX, int offsetY) {
		g.drawImage(sprites[0], x*MainGame.tileWidth + offsetX, y*MainGame.tileHeight + offsetY, MainGame.tileWidth, MainGame.tileHeight, null);
	}
}
