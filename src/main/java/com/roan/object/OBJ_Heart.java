package com.roan.object;

import com.roan.GamePanel;
import com.roan.entity.Entity;

public class OBJ_Heart  extends Entity {

    public OBJ_Heart(GamePanel gp){
        super(gp);

            name = "Heart";
            image = setUp("/hearts/fullHeart");
            image2 = setUp("/hearts/halfHeart");
            image3 = setUp("/hearts/emptyHeart");
        }
    }
