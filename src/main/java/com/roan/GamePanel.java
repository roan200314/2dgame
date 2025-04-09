package com.roan;

import com.roan.entity.Player;
import com.roan.object.SuperObject;
import com.roan.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //variabelen
    final int originalTileSize = 16; //16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; //768 pixels
    public final int screenHeight = maxScreenRow * tileSize; //576 pixels


    //world map parameters
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //FPS

    int FPS = 60;


    //System
    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Entity and object
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];

    // Game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        assetSetter.setObject();
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {

        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {

        }


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;


        // debug
        long drawTime = 0;
        if (keyH.checkDrawTime) {
            drawTime = System.nanoTime();
        }

        //draw tiles
        tileManager.draw(g2d);

        //draw object
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2d, this);
            }
        }

        //draw player
        player.draw(g2d);

        //ui draw
        ui.draw(g2d);

        //debug
        if (keyH.checkDrawTime) {
            long drawEndTime = System.nanoTime();
            long passed = drawEndTime - drawTime;
            g2d.setColor(Color.white);
            g2d.drawString("Draw time: " + passed + " ms", 10, 400);
            System.out.println("Draw time: " + passed + " ms");
        }

        g2d.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
            sound.setFile(i);
            sound.play();
    }
}
