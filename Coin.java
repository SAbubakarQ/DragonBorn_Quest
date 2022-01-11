// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class Coin extends Sprite{

    // Coin Image
    static BufferedImage coinImg = null;

    // Velocities
    double velVert = 0;
    double velHorz = 0;

    // Distance
    int right = 20;
    int left = -20;
    int range = right - left;

    // Random
    double random = Math.random();

    Coin(int xCoin, int yCoin, int wCoin, int hCoin) {

        loadImage();

        this.x = xCoin;
        this.y = yCoin;
        this.w = wCoin;
        this.h = hCoin;

        this.gravity = 1.0;
        velHorz = (int)(Math.random() * 20) - 5;
        velVert = -20;
    }

    void loadImage() {
        if (coinImg == null)
            coinImg = View.loadImage("coin.png");
    }

    @Override
    void update() {
        x += velHorz;
        velVert += gravity;
        y += velVert;
    }

    @Override
    void drawSelf(Graphics g) {
        g.drawImage(coinImg, x - Mario.xPos + Mario.marioScreenLocation, y, w, h, null);
    }

    @Override
    Json marshal() {
        return null;
    }

    @Override
    boolean isBrick() {
        return false;
    }

    @Override
    boolean isMario() {
        return false;
    }

    @Override
    boolean isCoin() {
        return true;
    }
    
}
