package com.roan.tile;

import com.roan.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }
    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass()
                    .getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass()
                    .getResourceAsStream("/tiles/stone.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass()
                    .getResourceAsStream("/tiles/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass()
                    .getResourceAsStream("/tiles/tree.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass()
                    .getResourceAsStream("/tiles/sand.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass()
                    .getResourceAsStream("/tiles/devilGround.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass()
                    .getResourceAsStream("/tiles/devilGroundSkull.png"));


        }catch (IOException e) {
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

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

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

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            int tileNum = mapTileNum[col][row];

            g2d.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;

            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }
}
