// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mario extends Sprite{

    // Vertical Velocity  
    double vert_vel;
    // Jump Counter
    int jumpCounter;

    // Player Speed (Changed from CAMSpeed)
	int marioSpeed = 15;
    // Jump Force
    int jumpForce = 7;

    // Ground Variable
    static int GROUND = 448;

    // Image Variables
    static BufferedImage[] images = null; // Changed to array of images 
    static BufferedImage stillIMG = null;
    static BufferedImage backwardIMG = null;

    // Current Image Array
    double imageNum;

    // Mario's screen location
    static int marioScreenLocation;
    // Idel Location for still frame
    static int idleLocation;
    // Coin Reference
    static int xPos;

    
 

    public Mario()
    {
        this.x = 0;
        this.y = -300;
        this.w = 58;
        this.h = 86;
        marioScreenLocation = 450 - this.w;
        this.gravity = 2.1;
        imageNum = 0;
        vert_vel = 0;
        if (images == null)
        {
            images = new BufferedImage[3];
            images[0] = View.loadImage("idelR.png");
            images[1] = View.loadImage("rightMove1.png");
            //images[2] = View.loadImage("rightMove2.png"); <- removed due to missing file 
        }
        stillIMG = View.loadImage("mario.png");
        backwardIMG = View.loadImage("Upward.png");
    }

    
    public void update()
    {
        // Gravity
        vert_vel += gravity; // Gravity constant gravity
        y += vert_vel; // constant pressure on mario.y
        
        // Ground 
        if ( y > GROUND) { // Ground - gravity stops and mario stays on ground position and does not fall through
            vert_vel = 0;
            y = GROUND;
            jumpCounter = 0;
        } else {
            jumpCounter++;
        }

        // brick solution
        xPos = x;

    }

    void updateImageNum()
    {
        imageNum = 0.2 + imageNum;

        if(imageNum > 2)
        {
            imageNum = 0;
        }
    }

    @Override
    void drawSelf(Graphics g)
    {
        if (Controller.direction == -1) {
            g.drawImage(images[(int) imageNum], marioScreenLocation + w, y, -w, h, null);
        } else if (Controller.direction == 1) {
            g.drawImage(images[(int) imageNum], marioScreenLocation, y, w, h, null);
        } else if (Controller.direction == 2) {
            g.drawImage(backwardIMG, marioScreenLocation, y, w, h, null);
        }else {
            g.drawImage(stillIMG, marioScreenLocation, y, w, h, null);
        }
    }

    @Override // prints out the location of each brick - this will help with the borders
    public String toString()
    {
        return "Mario Object located at ("+ x + ", " + y + ")";
        
    } 

    @Override
    boolean isBrick() {
        return false;
    }

    @Override
    boolean isMario() {
        return true;
    }

    @Override 
    boolean isCoin() {
        return false;
    }
    
    @Override 
    Json marshal()
    {
        Json ob = Json.newObject();

        // Marshaling bricks into json file
        ob.add("x", x);
        ob.add("y", y);
        ob.add("h", h);
        ob.add("w", w);
        return ob;
    }

    void checkJump() {
        if (jumpCounter < 5.5) {
            this.vert_vel -= jumpForce;
        }
    }

    void moveRight() {
        //System.out.println(x);
        if (x >= 7800 - w) { 
            this.x = 7800 - w;
            //marioScreenLocation += 5; // Border has been added now
                                        // must edit it to where it will add 
                                        // mario to mvoe off screen 
                                        // and to come back to original x axis
            updateImageNum();
        } else {
            this.x += marioSpeed;
		    Controller.direction = 1;
		    updateImageNum();
        }
    }

    void moveLeft() {
        if (x <= -300 - w) {
            this.x = -300 - w;
            //marioScreenLocation -= 5;
            updateImageNum();
        } else {
            this.x -= marioSpeed; 
		    Controller.direction = -1;
		    updateImageNum();
        }
    }
    
    
}
