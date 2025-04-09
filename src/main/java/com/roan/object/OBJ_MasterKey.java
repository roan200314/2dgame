package com.roan.object;

import com.roan.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_MasterKey extends SuperObject {

    GamePanel gp;

    public OBJ_MasterKey(GamePanel gp){
        name = "MasterKey";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/masterKey.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
