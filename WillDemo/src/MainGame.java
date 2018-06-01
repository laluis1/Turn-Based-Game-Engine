import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//this is the main game state, where all teh magic happens :3
public class MainGame extends State {

	// entity list
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Tile> tiles = new ArrayList<Tile>();

	int map[][];

	int walkPoints[][] = new int[1][2];

	int cursorX = 0;
	int cursorY = 0;

	public static int tileWidth = 32;
	public static int tileHeight = 32;

	int offsetX = 0;
	int offsetY = 0;

	int cursorMovementCounter = 0;

	boolean down = false, up = false, left = false, right = false, x = false, z = false;

	public MainGame(String map) {
		walkPoints[0][0] = -7355608;
		walkPoints[0][1] = -7355608;
		loadMap(map);
		for (int i = 0; i < 4; i++) {
			tiles.add(new Tile(2 * tileWidth, 160 + i * tileHeight, tileWidth, tileHeight));
		}

		players.add(new Player(1, 1));
		try {
			players.get(0).sprites = new Image[1];
			players.get(0).sprites[0] = ((BufferedImage) ImageIO.read(new File("memeSprites.png"))).getSubimage(0, 0,
					32, 32);
		} catch (Exception e) {
		}
		players.get(0).team = 1;
		players.add(new Player(4, 1));
		try {
			players.get(1).sprites = new Image[1];
			players.get(1).sprites[0] = ((BufferedImage) ImageIO.read(new File("memeSprites.png"))).getSubimage(0, 0,
					32, 32);
		} catch (Exception e) {
		}
		players.get(1).team = 1;

		players.add(new Player(1, 11));
		try {
			players.get(2).sprites = new Image[1];
			players.get(2).sprites[0] = ((BufferedImage) ImageIO.read(new File("memeSprites.png"))).getSubimage(0, 32,
					32, 32);
		} catch (Exception e) {
		}
		players.get(2).team = 2;
		players.add(new Player(4, 11));
		try {
			players.get(3).sprites = new Image[1];
			players.get(3).sprites[0] = ((BufferedImage) ImageIO.read(new File("memeSprites.png"))).getSubimage(0, 32,
					32, 32);
		} catch (Exception e) {
		}
		players.get(3).team = 2;
	}

	// update called every frame
	public void update() {
		if (turn == 1) {
			if (selectedPlayer == -1 || (selectedPlayer !=-1 && players.get(selectedPlayer).walked)) {
				if (down) {
					cursorY++;
				}
				if (up) {
					cursorY--;
				}
				if (left) {
					cursorX--;
				}
				if (right) {
					cursorX++;
				}
			} else {
				if (down) {
					cursorY++;
					for (int i = 0; i < tiles.size(); i++) {
						if (tiles.get(i).x == cursorX*tileWidth && tiles.get(i).y == cursorY*tileWidth) {
							cursorY--;
							break;
						}
					}
				}
				if (up) {
					cursorY--;
					for (int i = 0; i < tiles.size(); i++) {
						if (tiles.get(i).x == cursorX*tileWidth && tiles.get(i).y == cursorY*tileWidth) {
							cursorY++;
							break;
						}
					}
				}
				if (left) {
					cursorX--;
					for (int i = 0; i < tiles.size(); i++) {
						if (tiles.get(i).x == cursorX*tileWidth && tiles.get(i).y == cursorY*tileWidth) {
							cursorX++;
							break;
						}
					}
				}
				if (right) {
					cursorX++;
					for (int i = 0; i < tiles.size(); i++) {
						if (tiles.get(i).x == cursorX*tileWidth && tiles.get(i).y == cursorY*tileWidth) {
							cursorX--;
							break;
						}
					}
				}
			}
			if (selectedPlayer != -1) {
				boolean placeNew = true;
				if (players.get(selectedPlayer).walked)
					placeNew = false;
				for (int i = 0; i < walkPoints.length; i++) {
					if (walkPoints[i][0] == cursorX && walkPoints[i][1] == cursorY) {
						placeNew = false;
					}
				}
				if (placeNew) {
					int[][] temp = walkPoints;
					walkPoints = new int[walkPoints.length + 1][2];
					for (int j = 0; j < temp.length; j++) {
						walkPoints[j] = temp[j];
					}
					walkPoints[walkPoints.length - 1][0] = cursorX;
					walkPoints[walkPoints.length - 1][1] = cursorY;
				}
			}

			offsetX += (375 - ((offsetX + cursorX * tileWidth) + tileWidth / 2)) / 10;
			offsetY += (265 - ((offsetY + cursorY * tileHeight) + tileHeight / 2)) / 10;

			if (walking) {
				if (walkPoints[walkingCounter][0] != -7355608 && walkPoints[walkingCounter][1] != -7355608) {
					players.get(selectedPlayer).x = walkPoints[walkingCounter][0];
					players.get(selectedPlayer).y = walkPoints[walkingCounter][1];
				}

				if (walkingCounter < walkPoints.length-1) {
					walkingCounter ++;
				}else{
					players.get(selectedPlayer).walked = true;
					selectedPlayer = -1;
					walking = false;
					walkingCounter = 0;
					walkPoints = new int[1][2];
					walkPoints[0][0] = -7355608;
					walkPoints[0][1] = -7355608;
					walkingCounter = 0;
				}
			}
			if (x && players.get(selectedPlayer).walked == false) {
				walking = true;
			}
			turn = 2;
			for(int i = 0; i < players.size(); i++) {
				if(!players.get(i).walked && players.get(i).team == 1) {
					turn = 1;
					System.out.println("nope");
				}
			}
		} else if (turn == 2) {
			System.out.println("turn2");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			turn = 1;
			System.out.println("turn2 done");
			for(int i = 0; i < players.size(); i++) {
				players.get(i).walked = false;
			}
		} else {

		}
	}

	int turn = 1;

	int walkingCounter = 0;
	boolean walking = false;

	// draw called every frame
	public void draw(Graphics g) {
		for (int i = 0; i < tiles.size(); i++) {
			tiles.get(i).draw(g, offsetX, offsetY);
		}
		for (int i = 0; i < walkPoints.length; i++) {
			g.setColor(Color.BLUE);
			g.fillRect(walkPoints[i][0] * tileWidth + offsetX, walkPoints[i][1] * tileHeight + offsetY, tileWidth,
					tileHeight);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).draw(g, offsetX, offsetY);
			if (players.get(i).x == cursorX && players.get(i).y == cursorY) {
				g.setColor(Color.WHITE);
				g.drawRect(offsetX + cursorX * tileWidth + 1, offsetY + cursorY * tileHeight + 1, tileWidth - 2,
						tileHeight - 2);
				g.drawRect(offsetX + cursorX * tileWidth + 2, offsetY + cursorY * tileHeight + 2, tileWidth - 4,
						tileHeight - 4);
				if (z == true && players.get(i).team == 1) {
					selectedPlayer = i;
					walkPoints = new int[1][2];
					walkPoints[0][0] = -7355608;
					walkPoints[0][1] = -7355608;
				} else if (z == true) {
				}
			}
			if (selectedPlayer != -1) {
				if (i == selectedPlayer) {
					g.setColor(Color.ORANGE);
					g.drawRect(offsetX + players.get(i).x * tileWidth, offsetY + players.get(i).y * tileHeight,
							tileWidth, tileHeight);
					g.drawRect(offsetX + players.get(i).x * tileWidth + 1, offsetY + players.get(i).y * tileHeight + 1,
							tileWidth - 2, tileHeight - 2);
					g.drawRect(offsetX + players.get(i).x * tileWidth + 2, offsetY + players.get(i).y * tileHeight + 2,
							tileWidth - 4, tileHeight - 4);
				}
			}
		}
		g.setColor(Color.WHITE);
		g.drawRect(offsetX + cursorX * tileWidth, offsetY + cursorY * tileHeight, tileWidth, tileHeight);
	}

	int selectedPlayer = -1;

	public void calculatePossible(Player p) {

	}

	// this parses files from tiled2 and returns an ArrayList of new stuff
	public ArrayList loadMap(String filename) {
		return null;
	}

	// key press function
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			z = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			x = true;
		}
	}

	// key release function
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			z = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			x = false;
		}
	}

	// key typed function
	public void keyTyped(KeyEvent e) {
	}

}
