// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import java.awt.Graphics;

abstract class Sprite {
    
    int x, y;
    int w, h;
    int coinCount = 5;
    int brickType;

    //Gravity Variable
    double gravity;
    
    abstract void update();
    abstract void drawSelf(Graphics g);
    abstract Json marshal();
    abstract boolean isBrick();
    abstract boolean isMario();
    abstract boolean isCoin();

    public void setAll(int xPos, int yPos, int width, int height) {
        x = xPos;
        y = yPos;
        w = width;
        h = height;
    }

}
