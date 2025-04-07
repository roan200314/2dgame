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
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 40 * gp.tileSize;
        gp.obj[0].worldY = 8 * gp.tileSize;

        gp.obj[3] = new OBJ_Key(gp);
        gp.obj[3].worldX = 3 * gp.tileSize;
        gp.obj[3].worldY = 48 * gp.tileSize;

        gp.obj[1] = new OBJ_Door(gp);
        gp.obj[1].worldX = 25 * gp.tileSize;
        gp.obj[1].worldY = 15 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest(gp);
        gp.obj[2].worldX = 25 * gp.tileSize;
        gp.obj[2].worldY = 11 * gp.tileSize;

        gp.obj[4] = new OBJ_Sword(gp);
        gp.obj[4].worldX = 48 * gp.tileSize;
        gp.obj[4].worldY = 47 * gp.tileSize;
    }
}
