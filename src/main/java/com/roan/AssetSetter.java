package com.roan;

import com.roan.object.OBJ_Chest;
import com.roan.object.OBJ_Door;
import com.roan.object.OBJ_Key;
import com.roan.object.OBJ_Sword;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 40 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[3] = new OBJ_Key();
        gp.obj[3].worldX = 4 * gp.tileSize;
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 6 * gp.tileSize;
        gp.obj[1].worldY = 5 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 6 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize;

        gp.obj[4] = new OBJ_Sword();
        gp.obj[4].worldX = 7 * gp.tileSize;
        gp.obj[4].worldY = 21 * gp.tileSize;
    }
}
