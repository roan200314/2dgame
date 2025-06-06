package com.roan;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;



    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }


    }

    public void checkEvent() {

        //Check if player is more than 1 tile away from LAST event.
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            //event happens
            if (hit(2, 1, "any")) {damagePit(2, 1, gp.dialogueState);}
            if (hit(39,5,"left")) {healingPool(39, 5, gp.dialogueState);}
            if (hit(3, 42, "left")) {teleport(3, 42, gp.dialogueState); }
        }


    }

    public void damagePit(int col, int row, int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "The bones are poisonous!";
        gp.player.life -=1;
        eventRect[col][row].eventDone = true;

        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gp.keyH.enter) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drank from the pool of life \n Your HP has been recovered!";
            gp.player.life = gp.player.maxLife;
        }
    }

    public void teleport(int col, int row, int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Oh no! you're getting teleported!";
        gp.player.worldX = gp.tileSize * 20;
        gp.player.worldY = gp.tileSize * 20;
    }



    public boolean hit(int col, int row, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;


            }
        }

        gp.player.solidArea.x  = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;


        return hit;
    }



}
