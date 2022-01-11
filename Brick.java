// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Brick extends Sprite
{
    static BufferedImage brickImg = null;
    static BufferedImage closedChest = null;
    static BufferedImage openedChest = null;
    boolean illuminate;

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
            brickImg = View.loadImage("brick.png");

        if (closedChest == null)
            closedChest = View.loadImage("chestClosed.png");

        if (openedChest == null)
            openedChest = View.loadImage("chestOpened.png");
    }


    //Marshals this object into a JSON DOM
    Json marshal()
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

    void drawSelf(Graphics g)
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
        return "Brick type: " + brickType + " located at ("+ x + ", " + y + ") with a width = " + w + " and a height = " + h;

    }   

    @Override
    boolean isCoin() {
        return false;
    }

    @Override
    boolean isMario() {
        return false;
    }

    @Override
    boolean isBrick() {
        return true;
    }


    @Override
    void update() {
        
        if (brickType == 1 && coinCount <= 0) {
            brickType = 2;
        }
        
    }


}