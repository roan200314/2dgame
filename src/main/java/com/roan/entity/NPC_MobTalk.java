package com.roan.entity;

import com.roan.GamePanel;

import java.util.Random;

public class NPC_MobTalk extends Entity{

    public NPC_MobTalk(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        down1 = setUp("/npc_mobTalk/mobDown1");
        down2 = setUp("/npc_mobTalk/mobDown2");
        up1 = setUp("/npc_mobTalk/mobUp1");
        up2 = setUp("/npc_mobTalk/mobUp2");
        left1 = setUp("/npc_mobTalk/mobLeft1");
        left2 = setUp("/npc_mobTalk/mobLeft1");
        right1 = setUp("/npc_mobTalk/mobRight1");
        right2 = setUp("/npc_mobTalk/mobRight1");

    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 40) {
            Random random = new Random();

            int i = random.nextInt(25) + 1;

            if (i < 12) {
                direction = "up";
            }
            if (i > 12 && i < 25) {
                direction = "down";
            }
            actionLockCounter = 0;
        }
    }

    public void setDialogue() {
        dialogue[0] = "You.. You smell foul, are you perhapss.. \n Tarsnished?";
        dialogue[1] = "The mob king resides here. What business.. \nssss do thy have here??";
        dialogue[2] = "You want to kill him? Goodluck i guess.. \n (Is he from the prophecy?)";
    }

    public void speak() {

        super.speak();
    }
}

