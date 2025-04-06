package com.roan;

import com.roan.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String msg) {
        message = msg;
        messageOn = true;

    }

    public void draw(Graphics2D g2d) {

        if (gameFinished) {

            g2d.setFont(arial_40);
            g2d.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize*3);
            g2d.drawString(text, x, y);

            g2d.setFont(arial_80);
            g2d.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize*2);
            g2d.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2d.setFont(arial_40);
            g2d.setColor(Color.white);
            g2d.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2d.drawString("x: " + gp.player.hasKey, 74,65);

            //draw message
            if (messageOn) {
                g2d.setFont(g2d.getFont().deriveFont(20f));
                g2d.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }



    }
}
