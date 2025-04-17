package com.roan.tile;

import com.roan.GamePanel;
import com.roan.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[30];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }
    public void getTileImage() {
            setUp(0, "grass", false);
            setUp(1, "stone", true);
            setUp(19, "tree", true);
            setUp(2, "water", true);
            setUp(3, "grass2", false);
            setUp(4, "sand", false);
            setUp(5, "devilGround", false);
            setUp(6, "devilGroundSkull", false);
            setUp(7, "stoneGround", false);
            setUp(8, "dirt", false);
            setUp(9, "tombStone", true);
            setUp(10, "tombStone2", true);
            setUp(11, "water", true);
            setUp(12, "bottomWater", true);
            setUp(13, "leftWater", true);
            setUp(14, "topWater", true);
            setUp(15, "rightWater", true);
            setUp(16, "rightTopWater", true);
            setUp(17, "rightBottomWater", true);
            setUp(18, "leftBottomWater", true);
            setUp(20, "leftTopWater", true);
            setUp(21, "hole", false);

    }
    public void setUp(int index, String imagePath, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                // Check if the line is null (end of file reached)
                if (line == null) {
                    break;  // Exit the loop if no more lines are available
                }

                String[] numbers = line.split(" ");  // Split the line into numbers

                while (col < gp.maxWorldCol && col < numbers.length) {  // Ensure we don't exceed array bounds
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics g2d) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2d.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
