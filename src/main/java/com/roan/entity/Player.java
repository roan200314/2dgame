package com.roan.entity;

import com.roan.GamePanel;
import com.roan.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
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
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            System.out.println("Image loading started");
            // Gebruik getResourceAsStream voor resources in de classpath
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));

            System.out.println("Image loading ended");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            String objectName = gp.obj[index].name;

            switch (objectName) {
                case "Key":
                    hasKey++;
                   gp.obj[index] = null;
                   System.out.println("Keys: " + hasKey + " remaining");
                   break;
                case "Chest":
                    if (hasKey > 0) {
                        gp.obj[index] = null;
                        hasKey--;
                        System.out.println("Keys: " + hasKey + " remaining");
                    }
                   break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[index] = null;
                        hasKey--;
                        System.out.println("Keys: " + hasKey + " remaining");
                    }
                    break;
                case "Sword":
                    gp.obj[index] = null;
                        System.out.println("You picked up the sword Enma");
                    break;

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
        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

