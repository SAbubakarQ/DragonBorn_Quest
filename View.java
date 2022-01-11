// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


class View extends JPanel
{

	Model model; // member variable of type Model named model
	Mario mario;
	BufferedImage im;
	BufferedImage background;
	BufferedImage ground;
	BufferedImage dragonSHOUT;
	BufferedImage guard[] = null;
	BufferedImage greyBeard[] = null;
	double idleNum;
	double idleNum2;
	
	
	
	View(Controller c, Model m) // View Constructor
	{
		c.setView(this); // calling setter method from View Constructor
		model = m;
		this.background = loadImage("background.jpeg");
		this.ground = loadImage("ground.png");
		this.dragonSHOUT = loadImage("dragonSHOUT.jpeg");
		if (guard == null) {
			guard = new BufferedImage[2];
			guard[0] = loadImage("knight.png");
			guard[1] = loadImage("knight2.png");
		}
		if (greyBeard == null) {
			greyBeard = new BufferedImage[2];
			greyBeard[0] = loadImage("greybeard.png");
			greyBeard[1] = loadImage("greybeard2.png");
		}
		
	}

	static BufferedImage loadImage(String filename) // load image from anywhere
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
			//System.out.println(filename + " loaded...");
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	} 

	void updateIdleNum() {
		idleNum = 0.2 + idleNum;
		if (idleNum > 2) {
			idleNum = 0;
		}
	}

	public void paintComponent (Graphics g) // paintComponent is a method in JPanel / This method paints bricks to screen
	{
		// Draw background
		g.drawImage(this.background, -200 - model.mario.x/2, 0, 2800, 650, null); // Draws background
		// Draws Ground Texture
		g.drawImage(this.ground, -100 - model.mario.x/4, 150, null);
		// Draws Knight to guard left border of map
		g.drawImage(this.guard[(int) idleNum], -38 - model.mario.x, 458, null);
		// Draw Easter Egg DragonShout
		g.drawImage(this.dragonSHOUT, 3700 - model.mario.x/2, 0, 1200, 650, null);
		// Draw GreyBeard to guard right border of map
		g.drawImage(this.greyBeard[(int)idleNum], 8200 - model.mario.x, 458, null);
		g.drawImage(this.greyBeard[(int)idleNum], 8062 - model.mario.x, 458, null);


		// Draws Sprites
		for (int i = 0; i < model.sprites.size(); i++) {
			Sprite s = model.sprites.get(i);
			s.drawSelf(g);
		}

		updateIdleNum();

	}
}
