package game_DragonBornQuest.model;// Bismillah-AbubakarQ
/*
Abubakar Qasim
This is the corrected version of Assignment 5 and all of its parts
*/

import java.util.ArrayList;
import java.util.Iterator;

import game_DragonBornQuest.sprites.Brick;
import game_DragonBornQuest.sprites.Coin;
import game_DragonBornQuest.sprites.Mario;
import game_DragonBornQuest.sprites.Sprite;
import game_DragonBornQuest.util.Json;

public class Model {

    public Mario mario;
    Coin coin;
    public ArrayList<Sprite> sprites;

    String MAP = "game_DragonBornQuest/resources/json/map.json";
    

    public Model()
    {
        sprites = new ArrayList<Sprite>();
        mario = new Mario();
        sprites.add(mario);


        // Load Map upon starting
        Json j = Json.load(MAP);
		unmarshal(j);
		System.out.println("Map loaded!");

    }

    public void update()
    {
        Iterator<Sprite> iter = sprites.iterator();
        while (iter.hasNext()) {
            Sprite s = iter.next();
            s.update();
        }

        
        for (int i = 1; i < sprites.size(); i++) {
            Collisions(mario, sprites.get(i));
        }
        coinRemover();
    }

    
    void Collisions(Sprite sprite1, Sprite sprite2)
    {
        for(int i = 1; i < sprites.size(); i++)
        {
            if (sprites.get(i).isBrick()) 
            {
                Sprite s = sprites.get(i);
            

                // Simplified game_DragonBornQuest.sprites.Mario Variables from Powerpoint
                int s1HEAD = sprite1.y;
                int s1TOES = sprite1.y + sprite1.h;
                int s1RIGHT = sprite1.x + sprite1.w;
                int s1LEFT = sprite1.x;

                // Simplified game_DragonBornQuest.sprites.Brick Variables from PowerPoint
                int s2HEAD = s.y;
                int s2TOES = s.y + s.h; // Lol brick's don't have toes
                int s2RIGHT = s.x + s.w;
                int s2LEFT = s.x;

                // Rounding x,y values
                float x, y;

            boolean Collision = false;

            boolean colHEAD, colTOES, colRIGHT, colLEFT; // collision Head, collision Toes, collision Right, collision Right.
            boolean colRH, colRT, colLH, colLT; // collision right head, collision right toes, collision left head, collision left toes.

            if ( s1HEAD > s2TOES || s1TOES < s2HEAD || s1RIGHT < s2LEFT || s1LEFT > s2RIGHT) {
                Collision = false;
            } else {
                Collision = true;
            }

            if (Collision) {
                

                // Head Collision
                if (colHEAD = (s1TOES >= s2HEAD && s1HEAD <= s2HEAD)) {
                    colHEAD = true;
                } else {
                    colHEAD = false;
                }

                // Toe Collision
                if (colTOES = (s1HEAD <= s2TOES && s1TOES >= s2TOES)){
                    colTOES = true;
                }
                else {
                    colTOES = false;
                }
                
                // Right Collision
                if (colRIGHT = (s1LEFT < s2RIGHT && s1RIGHT >= s2RIGHT)) {
                    colRIGHT = true;
                } else {
                    colRIGHT = false;
                }

                // Left Collision
                if (colLEFT = (s1RIGHT > s2LEFT && s1LEFT <= s2LEFT)) {
                    colLEFT = true;
                } else {
                    colLEFT = false;
                }

                // Corner Right Head
                if (colRH = colRIGHT && colHEAD){
                    colRH = true;
                } else {
                    colRT = false;
                }

                // Corner Right Toes
                if (colRT = colRIGHT && colTOES) {
                    colRT = true;
                } else {
                    colRT = false;
                }

                // Corner Left Head
                if (colLH = colLEFT && colHEAD) {
                    colLH = true;
                } else {
                    colLH = false;
                }

                // Corner Left Toes
                if (colLT = colLEFT && colTOES) {
                    colLT = true;
                } else {
                    colLT = false;
                }

                //Corner Collision with Rounded Values
                if (colRIGHT & (s1TOES < s2TOES && s1HEAD > s2HEAD)) {
                    colRIGHT = true;
                } else if (colLEFT && (s1TOES < s2TOES && s1HEAD > s2HEAD)) {
                    colLEFT = true;
                } else if (colHEAD && (s1LEFT > s2LEFT && s1RIGHT < s2RIGHT)) {
                    colHEAD = true;
                } else if (colTOES && (s1LEFT > s2LEFT && s1RIGHT < s2RIGHT)) {
                    colTOES = true;
                } 

                else if (colRT) {
                    x = s2RIGHT - s1LEFT; y = s2TOES - s1HEAD;
                    colRIGHT = (x < y);
                    colTOES = (x >= y);
                }

                else if (colLT) {
                    x = Math.abs(s2LEFT - s1RIGHT); y = s2TOES - s1HEAD;
                    colLEFT = x < y;
                    colTOES = x >= y;
                }

                else if (colRH) {
                    x = s2RIGHT - s1LEFT; y = Math.abs(s2HEAD - s1TOES);
                    colRIGHT = x < y;
                    colHEAD = x >= y;
                }

                else if (colLH) {
                    x = Math.abs(s2LEFT - s1RIGHT); y = Math.abs(s2HEAD - s1TOES);
                    colLEFT = x < y;
                    colHEAD = x >= y;
                }

                else {
                    colRIGHT = false;
                    colLEFT = false;
                    colTOES = false;
                    colHEAD = false;
                }


                //-------------Collision Math-------------//
                if(colHEAD) {
                    mario.vert_vel = 0;
                    mario.jumpCounter = 0;
                    sprite1.y = s2HEAD - sprite1.h;
                }

                if (colTOES) { // Add game_DragonBornQuest.sprites.Coin Collision
                    sprite1.y = s2TOES;
                    if (s.coinCount > 0 && s.brickType == 1) {
                        sprites.add(new Coin(s.x, s.y, s.w, s.h));
                        s.coinCount--;
                    }
                }

                if (colRIGHT) {
                    sprite1.x = s2RIGHT;
                }

                if(colLEFT) {
                    sprite1.x = s2LEFT - sprite1.w;
                }

                }
            } 

        }
    }

    public void coinRemover() {
        
        for (int i = 0; i < sprites.size(); i++) {

            Sprite s = sprites.get(i);

            if (s.isCoin() == true && s.y >= Mario.GROUND + 100) {
                sprites.remove(i);
            }
        }
    }


    // Marshals this object into a JSON DOM
    public Json marshal()
    {
        Json jsonObject = Json.newObject();
        Json tmpList = Json.newList();
        
        jsonObject.add("bricks", tmpList);
        
        for(int i = 0; i < sprites.size(); i++)
            if (sprites.get(i).isBrick())
                tmpList.add(sprites.get(i).marshal());

        return jsonObject;
    }

    public void unmarshal(Json ob)
        {
            sprites = new ArrayList<Sprite>();
            mario = new Mario();
            sprites.add(mario);
            Json tmpList = ob.get("bricks");
            for(int i = 0; i < tmpList.size(); i++)
                sprites.add(new Brick(tmpList.get(i), this));
        }
    
}
