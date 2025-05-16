package com.roan;
import com.roan.entity.Entity;
import com.roan.object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2d;
    Font arial_40, arial_80;
    BufferedImage heart_full, heart_empty, heart_half;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);

        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_empty = heart.image3;
        heart_half = heart.image2;
    }

    public void showMessage(String msg) {
        message = msg;
        messageOn = true;

    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;

        g2d.setFont(arial_40);
        g2d.setColor(Color.white);


        //Title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //play State
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        //Pause State
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
        }
        //Dialogue state
        if(gp.gameState == gp.dialogueState) {
            drawDialoguesScreen();
            drawPlayerLife();
        }

    }

    private void drawPlayerLife() {


        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;


        //draw blank hearts
        while (i < gp.player.maxLife / 2) {
            g2d.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //Reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.life) {
            g2d.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2d.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    private void drawTitleScreen() {


        //title name
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 46F));
        String text = "The undestined destined";
        int x = getXforText(text);
        int y = gp.tileSize * 3;

        //Shadow
        g2d.setColor(Color.black);
        g2d.drawString(text, x + 3, y + 4);


        g2d.setColor(Color.white);
        g2d.drawString(text, x, y);

        //Image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2d.drawImage(gp.player.titleScreen, x, y, gp.tileSize * 2, gp.tileSize * 2, null);


        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        x = getXforText(text);
        y += gp.tileSize * 4;
        g2d.drawString(text, x, y);
        if (commandNum == 0) {
            g2d.drawString(">", x-gp.tileSize, y);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "LOAD GAME";
        x = getXforText(text);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if (commandNum == 1) {
            g2d.drawString(">", x-gp.tileSize, y);
        }

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
        text = "QUIT";
        x = getXforText(text);
        y += gp.tileSize;
        g2d.drawString(text, x, y);
        if (commandNum == 2) {
            g2d.drawString(">", x-gp.tileSize, y);
        }


    }


    private void drawDialoguesScreen() {
        //Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2d.drawString(line, x, y);
            y += 40;
        }
    }


    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0,210);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforText(text);

        int y = gp.screenHeight / 2;

        g2d.drawString(text, x, y);
    }

    public int getXforText(String text) {
        int x;
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
