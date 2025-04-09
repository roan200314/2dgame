package com.roan;

import com.roan.object.OBJ_Key;
import com.roan.object.OBJ_MasterKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2d;
    Font arial_40, arial_80;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String msg) {
        message = msg;
        messageOn = true;

    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;

        g2d.setFont(arial_40);
        g2d.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            // do playstate stuff
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

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
