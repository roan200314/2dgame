package com.roan.object;

import com.roan.GamePanel;
import com.roan.entity.Entity;


public class OBJ_BossGate extends Entity {


    public OBJ_BossGate(GamePanel gp){

        super(gp);

        name = "BossGate";
        down1 = setUp("/objects/door");
        collision = true;
    }
}
