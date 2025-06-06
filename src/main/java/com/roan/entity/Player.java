package com.roan.entity;

import com.roan.GamePanel;
import com.roan.KeyHandler;
import com.roan.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 26;
        speed = 8;
        direction = "down";

        //Player stats
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setUp("/player/boy_up_1");
        up2 = setUp("/player/boy_up_2");
        left1 = setUp("/player/boy_left_1");
        left2 = setUp("/player/boy_left_2");
        right1 = setUp("/player/boy_right_1");
        right2 = setUp("/player/boy_right_2");
        down1 = setUp("/player/boy_down_2");
        down2 = setUp("/player/boy_down_1");
        titleScreen = setUp("/player/titleScreen");

    }

    public BufferedImage setUp(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(imageName +".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    public void update() {

        if (keyH.down || keyH.up || keyH.left || keyH.right) {
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else if (keyH.right) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
          int objIndex = gp.cChecker.checkObject(this, true);
          pickUpObject(objIndex);

          //check NPC collision
            int npcIndex = gp.cChecker.checkEntityCollision(this, gp.npc);
            interactNPC(npcIndex);

            //Check event
            gp.eHandler.checkEvent();
            gp.keyH.enter = false;

            //If collision is false player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                       break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }



            spriteCounter++;

            if (spriteCounter > 10) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                }
                else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int index) {

        if (index != 999) {

        }
    }

    public void interactNPC(int index) {


        if (index != 999) {

            if (keyH.enter) {
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
        }

    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                image = up2;}
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, null);
    }
}

