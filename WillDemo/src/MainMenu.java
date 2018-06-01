import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;

public class MainMenu extends State {

	Image mainMenu;
	
	public MainMenu() {
		try {
			mainMenu = ImageIO.read(new File("MainMenu.png"));
		} catch (Exception e) {
		}
		
		state = "MainMenu";
	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.drawImage(mainMenu, 0, 0, null);
	}

	public void keyPressed(KeyEvent e) {
		option = 1;
	}
}
