package com.roan.object;

import com.roan.GamePanel;
import com.roan.entity.Entity;


public class OBJ_Chest extends Entity {


    public OBJ_Chest(GamePanel gp){


        super(gp);

        name = "Chest";
        down1 = setUp("/objects/chest");
    }
}
