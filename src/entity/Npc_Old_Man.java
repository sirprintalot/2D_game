package entity;

import main.*;

public class Npc_Old_Man extends Entity{

    public Npc_Old_Man(GamePanel gp) {
        
            super(gp);
            direction = "down";
            speed = 1;
            getImage();

    }

    public void getImage() {
        //enhanced method
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");


    }
}
