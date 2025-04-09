package com.roan.entity;

import com.roan.GamePanel;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
    }
    public void getPlayerImage() {
        up1 = setUp("/npc/oldMan_up_1");
        up2 = setUp("/npc/oldMan_up_2");
        left1 = setUp("/npc/oldMan_left_1");
        left2 = setUp("/npc/oldMan_left_2");
        right1 = setUp("/npc/oldMan_right_1");
        right2 = setUp("/npc/oldMan_right_2");
        down1 = setUp("/npc/oldMan_down_1");
        down2 = setUp("/npc/oldMan_down_2");

    }

}
