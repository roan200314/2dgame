package com.roan.object;

import com.roan.GamePanel;
import com.roan.entity.Entity;

public class OBJ_MasterKey extends Entity {

    public OBJ_MasterKey(GamePanel gp){

        super(gp);
        name = "MasterKey";
        down1 = setUp("/objects/masterKey");
    }
}
