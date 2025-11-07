package game_DragonBornQuest.view;// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import game_DragonBornQuest.controller.Controller;
import game_DragonBornQuest.model.*;
import game_DragonBornQuest.sprites.Mario;
import game_DragonBornQuest.sprites.Sprite;


public class View extends JPanel
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

    // Images
    String BACKGROUND = "game_DragonBornQuest/img/background.jpeg";
    String GROUND = "game_DragonBornQuest/img/ground.png";
    String DRAGONSHOUT = "game_DragonBornQuest/img/dragonSHOUT.jpeg";
    String GUARD1 = "game_DragonBornQuest/img/knight.png";
    String GUARD2 = "game_DragonBornQuest/img/knight2.png";
    String GREYBEARD1 = "game_DragonBornQuest/img/greybeard.png";
    String GREYBEARD2 = "game_DragonBornQuest/img/greybeard2.png";
	
	
	
	public View(Controller c, Model m)
	{
		c.setView(this);
		model = m;
		this.background = loadImage(BACKGROUND);
		this.ground = loadImage(GROUND);
		this.dragonSHOUT = loadImage(DRAGONSHOUT);
		if (guard == null) {
			guard = new BufferedImage[2];
			guard[0] = loadImage(GUARD1);
			guard[1] = loadImage(GUARD2);
		}
		if (greyBeard == null) {
			greyBeard = new BufferedImage[2];
			greyBeard[0] = loadImage(GREYBEARD1);
			greyBeard[1] = loadImage(GREYBEARD2);
		}
		
	}

	public static BufferedImage loadImage(String filename) // load image from anywhere
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
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
		g.drawImage(this.background, -200 - model.mario.x/2, 0, 3700, 650, null);

        // Draws Ground Texture
        g.drawImage(this.ground, -100 - model.mario.x/6, 150, null);

        // Draw Easter Egg DragonShout
        g.drawImage(this.dragonSHOUT, 3500 - model.mario.x/2, 0, 1400, 650, null);

        // Draws Knight to guard left border of map
        g.drawImage(this.guard[(int) idleNum], -38 - model.mario.x, 458, null);

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
