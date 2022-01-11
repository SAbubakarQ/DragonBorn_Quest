#!/bin/bash
clear
set -u -e
javac Game.java View.java Controller.java Model.java Brick.java Json.java Mario.java Sprite.java Coin.java
echo "Compiling completed! Running Game..."
java Game
rm *.class
