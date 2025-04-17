package com.roan;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {

        //event happens
        if (hit(2, 1, "right")) {damagePit(gp.dialogueState);}
        if (hit(39,5,"left")) {healingPool(gp.dialogueState);}
        if (hit(3, 42, "left")) {teleport(gp.dialogueState); }

    }

    public void damagePit(int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "The bones are poisonous!";
        gp.player.life -=1;

    }

    public void healingPool(int gameState) {
        if (gp.keyH.enter) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drank from the pool of life \n Your HP has been recovered!";
            gp.player.life = gp.player.maxLife;
        }
    }

    public void teleport(int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Oh no! you're getting teleported!";
        gp.player.worldX = gp.tileSize * 20;
        gp.player.worldY = gp.tileSize * 20;
    }



    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x  = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;


        return hit;
    }



}
