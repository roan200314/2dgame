package com.roan.object;

import com.roan.GamePanel;
import com.roan.entity.Entity;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1 = setUp("/objects/key");

    }
}
