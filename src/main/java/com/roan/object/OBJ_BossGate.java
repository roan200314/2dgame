package com.roan.object;

import com.roan.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BossGate extends SuperObject{

    GamePanel gp;

    public OBJ_BossGate(GamePanel gp){
        name = "BossGate";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bossDoor.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
