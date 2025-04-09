package com.roan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    //debug
    public boolean checkDrawTime = false;

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = true;
        }
        if (code == KeyEvent.VK_A) {
            left = true;
        }
        if (code == KeyEvent.VK_S) {
            down = true;
        }
        if (code == KeyEvent.VK_D) {
            right = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            }
            else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_T) {
            if (!checkDrawTime) {
                checkDrawTime = true;
            } else {
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_D) {
            right = false;
        }
    }
}
