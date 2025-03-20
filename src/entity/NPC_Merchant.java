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

        // DIALOGUE SET 1
        dialogues[0][0] = "Ah, a new face! /n" +
                "Or perhaps a returning customer? /n" +
                "Either way, my wares are yours to " +
                "/n browse!";
        dialogues[0][1] =  "Haggling, eh? " +
                "/nI respect the spirit, " +
                "/nbut my prices are already a steal!";
        dialogues[0][2] = "A fine choice! " +
                "/n Oh… seems you're a bit short on coin. " +
                "/n Perhaps a little side questing is " +
                "/n in order?";
        dialogues[0][3] = "Ah, you've got a keen eye! " +
                "/n This little gem? " +
                "/n It’s not just shiny—it " +
                "/n hums with power!";


        //DIALOGUE SET 2
        dialogues[1][4] = "A wise purchase! " +
                "/n May it serve you well... " +
                "/n and bring you back for more!";
        dialogues[1][5] = "Hmm… Not bad craftsmanship. " +
                "/n I’ll take it off your hands, " +
                "/n for a fair price of course!";
        dialogues[1][6] = "Hah! I run a respectable shop! " +
                "/n What would I do with " +
                "/n this... thing?";

        // DIALOGUE SET 3
        dialogues[2][7] = "Travel safe, friend! " +
                "/n And if you find something curious " +
                "/n on your journey, " +
                "/n I’ll be waiting!";
    }

    
    public void speak(){

        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;

    }

    public void setItems(){
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Shield_blue(gp));
    }
}
