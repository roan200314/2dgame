package com.roan.entity;

import com.roan.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }
    public void getImage() {
        up1 = setUp("/npc/oldMan_up_1");
        up2 = setUp("/npc/oldMan_up_2");
        left1 = setUp("/npc/oldMan_left_1");
        left2 = setUp("/npc/oldMan_left_2");
        right1 = setUp("/npc/oldMan_right_1");
        right2 = setUp("/npc/oldMan_right_2");
        down1 = setUp("/npc/oldMan_down_1");
        down2 = setUp("/npc/oldMan_down_2");

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
