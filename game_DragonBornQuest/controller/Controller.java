package game_DragonBornQuest.controller;// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import game_DragonBornQuest.sprites.Brick;
import game_DragonBornQuest.sprites.Mario;
import game_DragonBornQuest.sprites.Sprite;
import game_DragonBornQuest.view.View;
import game_DragonBornQuest.model.*;
import game_DragonBornQuest.util.Json;

import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.event.*;

public class Controller implements ActionListener, KeyListener, MouseInputListener// class able to handle ActionEvents (someone pushes a button)
{
	View view;
	Model model;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;

	boolean key_A;
	boolean key_D;
	static boolean key_Spacebar;

	boolean edit = false;
	public static int direction = 0;

	int Xi, Yi;
	int Xf, Yf;
	int xPos, yPos;
	int width;
	int height;
	int x, y, w, h;
	int t;

    String CLEAN_MAP = "game_DragonBornQuest/resources/json/cleanMap.json";
    String MAP = "game_DragonBornQuest/resources/json/map.json";
	
	public void setView(View v)
	{
		view = v;
	}

	public Controller(Model m)
	{
		model = m;
	}
	
	public void actionPerformed(ActionEvent e) // In order to have ActionEvents, we must have ActionPerformed. 
	{
	}
	
	// mouse listener method
	public void mousePressed(MouseEvent e)
	{
		//System.out.println(model.mario.x + " " + game_DragonBornQuest.sprites.Mario.marioScreenLocation);
		//System.out.println("("+ e.getX() + "," + e.getY() + ")");
		if (edit == true) {
				Xi = e.getX() + model.mario.x - Mario.marioScreenLocation;
				Yi = e.getY();
				model.sprites.add(new Brick(model, t));
		}

	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
	// Mouse Motion Listener Methods
	public void mouseDragged(MouseEvent e) {

		if (edit == true) {
			Sprite current = model.sprites.get(model.sprites.size() - 1);

			Xf = e.getX() + model.mario.x - Mario.marioScreenLocation;
			Yf = e.getY();
		
			xPos = Xi;
			yPos = Yi;

			width = Xf - xPos;
			height = Yf - yPos;

			if (width < 0) {
				xPos = Xf;
				width = Xi - Xf;
			}
		
			if (height < 0) {
				yPos = Yf;
				height = Yi - Yf;
			}

		current.setAll(xPos, yPos, width, height);
		}


	}

	public void mouseMoved(MouseEvent e) {}

	// keyboard listener method
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
					direction = 1;
					keyRight = true;
					break;
			case KeyEvent.VK_LEFT:
					direction = -1;
					keyLeft = true;
					break;
			case KeyEvent.VK_UP:
					direction = 2;
					keyUp = true; 
					break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_ESCAPE: System.out.println("-Exiting game_DragonBornQuest.service.Game-"); System.exit(0); break;

			case KeyEvent.VK_A: key_A = true; break;
			case KeyEvent.VK_D: key_D = true; break;

			case KeyEvent.VK_SPACE:
					if (model.mario.jumpCounter <= 5) {
					key_Spacebar = true;
					}
					break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
					direction = 1;
					keyRight = false;
					break;
			case KeyEvent.VK_LEFT:
					direction = -1;
					keyLeft = false;
					break;
			case KeyEvent.VK_UP: 
					direction = 2;
					keyUp = false;
					break;
			case KeyEvent.VK_DOWN:
					direction = 0;
					keyDown = false;
					break;

			case KeyEvent.VK_A: key_A = false; break;
			case KeyEvent.VK_D: key_D = false; break;

			case KeyEvent.VK_SPACE:
					key_Spacebar = false;
					break;

			case KeyEvent.VK_T:
					t = (++t > 1) ? 0 : t;
                	System.out.println("Tile changed to " + t);
                	break;
			
		}

		char c = e.getKeyChar();

		if (c == 'e' || c == 'E'){
			edit = !edit;
			System.out.println("Edit mode: " + edit);
		}

		// Easter Egg
		if (c == '$'){
			System.out.println("++EASTER EGG!++");
		}

		if (edit == true) {

			// Save Map
			if (c == 's' || c == 'S') {
				model.marshal().save(MAP);
				System.out.println("--Saved--");
			}

			// Load Map
			if (c == 'l' || c == 'L'){
				Json j = Json.load(MAP);
				model.unmarshal(j);
				System.out.println("--Loaded Map--");
			}

			// Fresh Map
			if (c == 'f' || c == 'F'){
				Json j = Json.load(CLEAN_MAP);
				model.unmarshal(j);
				System.out.println("--Fresh Slate | Removing Bricks--");
			}

			// Undo Last game_DragonBornQuest.sprites.Brick
			if (c == 'z' || c == 'Z') {
				if (model.sprites.size() > 1) {
					model.sprites.remove(model.sprites.size() - 1);
				}
				System.out.println("--Undo--");
			}
		}

	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void update()
	{
		if (keyRight || key_D)
		{
			model.mario.moveRight();
		}
		if (keyLeft || key_A)
		{
			model.mario.moveLeft();
		}
		if (key_Spacebar) 
		{
			model.mario.checkJump();
		}
	}

}
