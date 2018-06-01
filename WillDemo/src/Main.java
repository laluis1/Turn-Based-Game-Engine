import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

// this class is a jframe, so no dealing with any bs
public class Main extends JFrame implements KeyListener{
	
	//double buffer graphics and image
	Graphics g;
	Image dbImage;
	
	//boolean for if game is running
	boolean running = true;
	
	//current state, basically between Menu, Game (maps are loaded with loader function here)
	State currentState = new MainMenu();
	
	//background image to plaster if aspect ratio of window isnt that of what I want (I thought it would be adorable)
	BufferedImage background;
	
	//aspect ratio
	double ratio = 750.0/530.0;
	
	//where all the window magic happens, p. standard
	public Main() {
		//window size
		setSize(750, 530);
		//making sure the program will close on jframe close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setting visible (needed on some os)
		setVisible(true);
		//centering the window
		setLocationRelativeTo(null);
		//setting the window title
		setTitle("Why is Ted Kirby always angry :/");
		//loading background image:
		try {
			//background = (BufferedImage) ImageIO.read(new File("background.png"));
		}catch(Exception e) {}
		//teh game loop
		while(running) {
			try {
				//updates to keep a solid 30 fps, idk, I like it like this :/
				// NOTE TO SELF: fix fps to take into account update and repaint time to have it always be 30 fps
				Thread.sleep(33);
			}catch(Exception e) {}
			//making sure the key listener is still here :/
			this.addKeyListener(this);
			//call update function
			update();
			//call JFrame paint function
			repaint();
		}
	}
	
	public static void main(String args[]) {
		new Main();
	}
	
	//where update stuff goes, updated every frame
	public void update() {
		currentState.update();
		if(currentState.state == "MainMenu") {
			if(currentState.option == 1) {
				currentState = new MainGame("Fuck me");
			}
		}
	}
	
	// basic java jframe paint
	public void paint(Graphics g2) {
		//double buffer setting up next image
		dbImage = createImage(750, 530);
		g = dbImage.getGraphics();
		
		//actual stuff to be displayed:
		g.clearRect(0, 0, 750, 530);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 750, 530);
		currentState.draw(g);
		
		//drawing the temp image: NOTE TO SELF: make this run faster like I know you can <3 fix background image mechanism autoscale backgtround iamge
		if((this.getWidth()+0.0)/(this.getHeight()+0.0) > ratio) {
			//g2.drawImage(background.getSubimage(0, 0, (this.getWidth()-(int)(ratio*this.getHeight()))/2, getHeight()), 0, 0, (this.getWidth()-(int)(ratio*this.getHeight()))/2, getHeight(), null);
			//g2.drawImage(background.getSubimage((this.getWidth()-(int)(ratio*this.getHeight()))/2+((int)(ratio*this.getHeight())), 0, (this.getWidth()-(int)(ratio*this.getHeight()))/2, getHeight()), (this.getWidth()-(int)(ratio*this.getHeight()))/2+((int)(ratio*this.getHeight())), 0, (this.getWidth()-(int)(ratio*this.getHeight()))/2, getHeight(), null);
			g2.clearRect(0, 0, (this.getWidth()-(int)(ratio*this.getHeight()))/2, getHeight());
			g2.clearRect((this.getWidth()-(int)(ratio*this.getHeight()))/2+((int)(ratio*this.getHeight())), 0, (this.getWidth()-(int)(ratio*this.getHeight()))/2, getHeight());
			g2.drawImage(dbImage, (this.getWidth()-(int)(ratio*this.getHeight()))/2, 0, (int)(ratio*this.getHeight()), this.getHeight(), null);
		}else if((this.getWidth()+0.0)/(this.getHeight()+0.0) < ratio){
			//g2.drawImage(background.getSubimage(0, 0, getWidth(), (this.getHeight()-(int)(this.getWidth()/ratio))/2), 0, 0, getWidth(), (this.getHeight()-(int)(this.getWidth()/ratio))/2, null);
			//g2.drawImage(background.getSubimage(0, 0, getWidth(), (this.getHeight()-(int)(this.getWidth()/ratio))/2), 0, (this.getHeight()-(int)(this.getWidth()/ratio))/2+(int)(this.getWidth()/ratio), getWidth(), (this.getHeight()-(int)(this.getWidth()/ratio))/2, null);
			g2.clearRect(0, 0, getWidth(), (this.getHeight()-(int)(this.getWidth()/ratio))/2);
			g2.clearRect(0, (this.getHeight()-(int)(this.getWidth()/ratio))/2+(int)(this.getWidth()/ratio), getWidth(), (this.getHeight()-(int)(this.getWidth()/ratio))/2);
			g2.drawImage(dbImage, 0, (this.getHeight()-(int)(this.getWidth()/ratio))/2, this.getWidth(), (int)(this.getWidth()/ratio), null);
		}else {
			g2.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
		}
		
	}
	
	//key press function
	public void keyPressed(KeyEvent e) {
		currentState.keyPressed(e);
	}
	
	//key release function
	public void keyReleased(KeyEvent e) {
		currentState.keyReleased(e);
	}
	
	//key typed function
	public void keyTyped(KeyEvent e) {
		currentState.keyTyped(e);
	}
}
