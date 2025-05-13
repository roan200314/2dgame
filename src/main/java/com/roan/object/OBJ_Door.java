package com.roan.object;

import com.roan.GamePanel;
import com.roan.entity.Entity;

public class OBJ_Door extends Entity {


    public OBJ_Door(GamePanel gp){

        super(gp);

        name = "Door";
        down1 = setUp("/objects/door");
        collision = true;
    }
}
