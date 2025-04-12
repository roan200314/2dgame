package com.roan.entity;

import com.roan.GamePanel;

import java.util.Random;

public class NPC_ChineseMan extends Entity {


    public NPC_ChineseMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        down1 = setUp("/npc_Shop/chineseManDown1");
        down2 = setUp("/npc_Shop/chineseManDown2");
        up1 = setUp("/npc_Shop/chineseManUp1");
        up2 = setUp("/npc_Shop/chineseManUp2");
        left1 = setUp("/npc_Shop/chineseManLeft");
        left2 = setUp("/npc_Shop/chineseManLeft");
        right1 = setUp("/npc_Shop/chineseManRight");
        right2 = setUp("/npc_Shop/chineseManRight");

    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();

            int i = random.nextInt(50) + 1;

            if (i < 25) {
                direction = "up";
            }
            if (i > 25 && i < 50) {
                direction = "down";
            }
            actionLockCounter = 0;
        }
    }

    public void setDialogue() {
        dialogue[0] = "Gello";
        dialogue[1] = "You wanna buy?";
        dialogue[2] = "Have you seen the pile of dead bodies, \n the King doesn't care about his people. What a shame..";
    }
    public void speak() {
        super.speak();
    }
}
