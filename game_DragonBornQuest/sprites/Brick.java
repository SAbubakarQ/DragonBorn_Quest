package game_DragonBornQuest.sprites;// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game_DragonBornQuest.model.Model;
import game_DragonBornQuest.util.Json;
import game_DragonBornQuest.view.*;




public class Brick extends Sprite
{
    static BufferedImage brickImg = null;
    static BufferedImage closedChest = null;
    static BufferedImage openedChest = null;
    boolean illuminate;

    String BRICK = "game_DragonBornQuest/img/brick.png";
    String CHEST_CLOSED = "game_DragonBornQuest/img/chestClosed.png";
    String CHEST_OPENED = "game_DragonBornQuest/img/chestOpened.png";


    Model model;

    public Brick(Model m, int t)
    {
        loadImage();
        model = m;
        brickType = t;
    }

    public Brick(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        brickType = (int)ob.getLong("brickType");
        loadImage();
        model = m;
    }

    void loadImage()
    {
        if (brickImg == null)
            brickImg = View.loadImage(BRICK);

        if (closedChest == null)
            closedChest = View.loadImage(CHEST_CLOSED);

        if (openedChest == null)
            openedChest = View.loadImage(CHEST_OPENED);
    }


    //Marshals this object into a JSON DOM
    public Json marshal()
    {
        Json ob = Json.newObject();

        // Marshaling bricks into json file
        ob.add("x", x);
        ob.add("y", y);
        ob.add("h", h);
        ob.add("w", w);
        ob.add("brickType", brickType);
        return ob;
    }

    public void drawSelf(Graphics g)
    {
        if (brickType == 0)
            g.drawImage(brickImg, x -model.mario.x + Mario.marioScreenLocation, y, w, h, null);
        else if (brickType == 1) 
            g.drawImage(closedChest, x - model.mario.x + Mario.marioScreenLocation, y, w, h, null);
        else if (brickType == 2)
            g.drawImage(openedChest, x - model.mario.x + Mario.marioScreenLocation, y, w, h, null);
    }



    @Override // prints out the location of each brick - this will help with the borders
    public String toString()
    {
        return "game_DragonBornQuest.sprites.Brick type: " + brickType + " located at ("+ x + ", " + y + ") with a width = " + w + " and a height = " + h;

    }   

    @Override
    public boolean isCoin() {
        return false;
    }

    @Override
    boolean isMario() {
        return false;
    }

    @Override
    public boolean isBrick() {
        return true;
    }


    @Override
    public void update() {
        
        if (brickType == 1 && coinCount <= 0) {
            brickType = 2;
        }
        
    }


}