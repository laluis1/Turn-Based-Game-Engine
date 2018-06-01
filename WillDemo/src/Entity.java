import java.awt.Graphics;
import java.awt.Rectangle;

//basic textbook entity class, every entity extends this;
public class Entity {
	//entity position
	int x, y;
	//entity boundaries
	Rectangle bounds = new Rectangle();
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// called every frame again :/
	public void update() {
		
	}
	
	//drawing entities
	public void draw(Graphics g, int offsetX, int offsetY) {
		
	}
}
