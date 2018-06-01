import java.awt.Color;
import java.awt.Graphics;

public class Tile extends Entity{
	int tileWidth, tileHeight;
	public Tile(int x, int y, int tileWidth, int tileHeight) {
		super(x, y);
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g, int offsetX, int offsetY) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x + offsetX, y + offsetY, tileWidth, tileHeight);
	}
}
