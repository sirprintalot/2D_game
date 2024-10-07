package entity;

import main.*;

import java.awt.*;
import java.awt.image.*;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    //standing sprite
    public int standingCounter = 0;

    //power up
    public int speedIncrement = 4;
    

    //objective
//    public int hasKey = 0;
//    public int chestCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        //pass the gamePanel from entity
        super(gp);
        // key handler
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        //solid area specific for the player
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 25;
        solidArea.height = 25;

        //Attack area
        attackArea.width = 36;
        attackArea.height = 36;


        // Methods
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }


    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 6;
        direction = "down";

        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;

    }

    public void getPlayerImage() {
        //enhanced method
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);

    }

    public void update() {

        boolean moving = keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed;

        if(attacking){
            attack();
        }

        else if (moving) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gp.cCheck.checkTile(this);

            //check object collision
            int objIndex = gp.cCheck.checkObject(this, true);
            pickUpObject(objIndex);

            //check npc collision
            int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
            interactNpc(npcIndex);

            // Monster collision
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);


            // Check event
            gp.eventHandler.checkEvent();

            // if collision is false player can move
            if (!collisionOn) {
                switch (direction) {

                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            //sprite animation
            spriteCounter++;
            if (spriteCounter > animationSpeed) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        //display the correct sprite when standing still (every 20 frames)
        else {
            standingCounter++;
            if (standingCounter == 30) {
                spriteNum = 1;
                standingCounter = 0;
            }
        }

        // Interact with npc when pressing enter key
        int npcIndex = gp.cCheck.checkEntity(this, gp.npc);

        if (keyH.enterPressed) {
            if (npcIndex != 999) {
                interactNpc(npcIndex);
            }
            // Reset the enter key
            gp.keyH.enterPressed = false;
        }


        // player taking damage from monster
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attack(){

        // Creating the attacking animation
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum =  2;

            // original position of the player
            int currentWorldX =  worldX;
            int currentWorldY =  worldY;
            int solidAreaWidth =  solidArea.width;
            int solidAreaHeight =  solidArea.height;

            // player's position adjusted for the attack
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            // Attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // check monster collision with the updated coordinates
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // Reset the player's position
            worldX =  currentWorldX;
            worldY =  currentWorldY;
            solidArea.width =  solidAreaWidth;
            solidArea.height = solidAreaHeight;


        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }



    //pick object
    public void pickUpObject(int i) {
        if (i != 999) {
//            String objectName = gp.obj[i].name;
//            switch (objectName) {
//                case "key":
//                    gp.playSoundEffect(1);
//                    gp.obj[i] = null;
////                    System.out.println(hasKey + " keys found!");
//                    gp.ui.showMessage("you got a Key!!");
//                    break;
//
//                case "door":
//
//                    gp.playSoundEffect(5);
//                    gp.ui.showMessage("Door locked. You need a key");
//
////                    if (hasKey > 0) {
////                        gp.obj[i] = null;
////                        gp.playSoundEffect(3);
////                        hasKey--;
////                        gp.ui.showMessage("Door opened " + hasKey + " keys left..");
////                    }
////                    else {
//                    break;
//
//                case "chest":
//
//                    gp.obj[i] = null;
//                    gp.playSoundEffect(2);

//                    if (chestCounter >= 3) {
//
//                        gp.ui.gameFinished = true;
////                        gp.stopMusic();
//                        gp.playSoundEffect(4);
//                        gp.ui.showMessage("All " + chestCounter + " treasures found!!");
//                    }
//
//                    break;

//                case "boots":
//
//                    speed += speedIncrement; //para coca machucada
//                    gp.obj[i] = null;
//                    gp.playSoundEffect(6);
//                    gp.ui.showMessage("You have boots!!");
//
//                    break;
//            }

        }
    }

    public void interactNpc(int i) {

        if (keyH.enterPressed) {

            if (i != 999) {
                // if the player collisions the npc, we change the game state
                if (gp.keyH.enterPressed) {
                    gp.gameState = gp.dialogueState;
                    // speak with the npc
                    gp.npc[i].speak();
                    System.out.println("dialogue");
                }

            } else {
                attacking = true;
                System.out.println("attack");
            }

        }

    }

    public void interactMonster(int i) {

        if (i != 999) {

            if (!invincible) {
                life -= 1;
                invincible = true;
            }

        }
    }

    public void damageMonster(int i){

        if( i != 999){
             if(!gp.monster[i].invincible){
                 gp.monster[i].life -= 1;
                 gp.monster[i].invincible = true;

                 if(gp.monster[i].life <= 0){
                     gp.monster[i] = null;
                 }
             }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {

            case "up" -> {
                if(!attacking){
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if(attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
            }
            case "down" -> {
                if(!attacking){
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if(attacking){
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
            }
            case "left" -> {
                if(!attacking){
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if(attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
            }
            case "right" -> {
                if(!attacking){
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if(attacking){
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }

            }
        }

        // change the player's opacity when invicible
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        //Draw the character
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset the opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        //DEBUG
        //display the player's collision area
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        //check if damage with monster is ok
//        g2.setFont(new Font("Arial", Font.PLAIN, 25));
//        g2.setColor(Color.WHITE);
//        g2.drawString("invincible counter: " + invincibleCounter, 10, 380 );
    }
}
