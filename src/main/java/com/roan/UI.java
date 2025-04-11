package com.roan;
import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2d;
    Font arial_40, arial_80;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

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


        //play State
        if (gp.gameState == gp.playState) {
            // do playstate stuff
        }
        //Pause State
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        //Dialogue state
        if(gp.gameState == gp.dialogueState) {
            drawDialoguesScreen();
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
