package com.roan.monster;

import com.roan.GamePanel;
import com.roan.entity.Entity;

import java.util.Random;

public class MON_GreenSlime extends Entity {


    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 1;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setUp("/monster/greenSlime_down1");
        up2 = setUp("/monster/greenSlime_down2");
        down1 = setUp("/monster/greenSlime_down1");
        down2 = setUp("/monster/greenSlime_down2");
        left1 = setUp("/monster/greenSlime_down2");
        left2 = setUp("/monster/greenSlime_down1");
        right1 = setUp("/monster/greenSlime_down2");
        right2 = setUp("/monster/greenSlime_down1");
    }
    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();

            int i = random.nextInt(100) + 1;

            if (i < 25) {
                direction = "up";
            }
            if (i > 25 && i < 50) {
                direction = "down";
            }
            if (i > 50 && i < 75) {
                direction = "left";
            }
            if (i > 75 && i < 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
