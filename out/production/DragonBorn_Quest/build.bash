#!/bin/bash
clear
set -u -e
javac game_DragonBornQuest.service.Game.java View.java Controller.java Model.java Brick.java Json.java Mario.java Sprite.java Coin.java
echo "Compiling completed! Running Game..."
java game_DragonBornQuest.service.Game
rm *.class
