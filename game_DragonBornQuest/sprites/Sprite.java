package game_DragonBornQuest.sprites;// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import game_DragonBornQuest.util.Json;

import java.awt.Graphics;

public abstract class Sprite {
    
    public int x;
    public int y;
    public int w;
    public int h;
    public int coinCount = 5;
    public int brickType;

    //Gravity Variable
    double gravity;
    
    public abstract void update();
    public abstract void drawSelf(Graphics g);
    public abstract Json marshal();
    public abstract boolean isBrick();
    abstract boolean isMario();
    public abstract boolean isCoin();

    public void setAll(int xPos, int yPos, int width, int height) {
        x = xPos;
        y = yPos;
        w = width;
        h = height;
    }

}
