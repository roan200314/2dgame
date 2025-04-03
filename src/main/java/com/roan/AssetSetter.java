package com.roan;

import com.roan.object.OBJ_Chest;
import com.roan.object.OBJ_Door;
import com.roan.object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 40 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 6 * gp.tileSize;
        gp.obj[1].worldY = 5 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 6 * gp.tileSize;
        gp.obj[2].worldY = 2 * gp.tileSize;
    }
}
