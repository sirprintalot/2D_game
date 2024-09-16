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

    //powerup
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

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 6;
        direction = "down";

    }

    public void getPlayerImage() {
        //enhanced method
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");

        //original method
//        try {
//            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
//            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
//            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
//            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
//            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
//            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
//            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed) {

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


            // if collision is false player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            //display the correct sprite when standing still (every 20 frames)
            else {
                standingCounter++;
                if (standingCounter == 10) {
                    spriteNum = 1;
                    standingCounter = 0;
                }
            }

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

    public void interactNpc(int i){

        if(i != 999){ 
            // if the player collisions the npc, we change the game state
            //BUG dialogue happens only when an enter and arrow key are pressed
            //at the same time. detect collision while still
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                // speak with the npc
                gp.npc[i].speak();
            }
            gp.keyH.enterPressed = false;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;

                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, null);

        //DEBUG
        //display the player's collision area
//        g2.setColor(Color.red);
//        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
}
