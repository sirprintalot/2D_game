package entity;

import main.*;
import objects.*;

public class NPC_Merchant extends Entity{

    //Constructor
    public NPC_Merchant(GamePanel gp) {

        super(gp);
//        direction = "up";
//        speed = 2;
        getImage();
        setDialogue();
        setItems();

    }

    //Get the sprite image depending on the position
    public void getImage() {
        //enhanced method
        up1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){

        dialogues[0] = "HI! I have interesting things /nWould you /nlike to trade??";
        dialogues[1] = "asklfnasflkn?";
        dialogues[2] = "sdad jasdfkanfn asdasdfa dafafasf /n qsaasasdasdfaf";
        dialogues[3] = "asdfasf aksfbajffopnfasdfafaf /n asfafsasdasdad /n asdasdfasf";
    }

    
    public void speak(){

        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.merchant = this;

    }

    public void setItems(){
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Shield_blue(gp));
    }
}
