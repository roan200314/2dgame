package com.roan.object;

import com.roan.GamePanel;
import com.roan.entity.Entity;

public class OBJ_Sword extends Entity {

    public OBJ_Sword(GamePanel gp){

        super(gp);
        name = "Sword";
        down1 = setUp("/objects/sword");
    }
}
