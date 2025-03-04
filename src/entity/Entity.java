package entity;

import main.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;
    public int speed;

    //IMAGES
    // Walking
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    // Atacking
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;

    //DIRECTION 
    public String direction = "down";

    //SPRITE
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int animationSpeed = 12;

    //CHARACTER STATUS
    public int maxLife;
    public int life;

    public boolean isAlive = true;
    public boolean dying = false;

    // Character stats
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int maxMana;
    public int mana;

    // Character equipment
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;

    // Item attributes
    public int attackValue;
    public int defenseValue;
    public String itemDescription = "";
    public int useCost;
    public int ammo;
    public int value;
    public int price;
    public int lightRadius;

    //Inventory
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;

    //stackable items
    public boolean stackable = false;
    public int ammount = 1;

    // HP bar on monsters
    boolean hpBarOn = false;

    // CHARACTER DAMAGE MANAGE
    public boolean invincible = false;
    public int invincibleCounter = 0;

    // Attack
    boolean attacking = false;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public int shotAvailableCounter = 0;

    // CHECKING THE ENTITY CLASS FOR TAKING DAMAGE OR NOT also check weapon or shield type
    public int type; //0 player, 1 npc etc

    //Entities
    public final int typePlayer = 0;
    public final int typeNpc = 1;
    public final int typeMonster = 2;

    //Objects
    public final int typeSword = 3;
    public final int typeAxe = 4;
    public final int typeShield = 5;
    public final int typeUsable = 6;
    public final int typePickupOnly = 7;
    public final int typeObstacle = 8;
    public final int typeLight = 9;

    //DIALOGUE
    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    //solid area for all entities (can be overwritten for each case)
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    //this counter controls the nps's activity
    public int actionLockCounter = 0;
    public int dyingCounter = 0;

    int hpBarCounter = 0;

    //Super object attributes
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;

    //pathfinding
    public boolean onPath = false;

    //Knock back
    public boolean knockBack = false;
    public int defaultSpeed;
    int knockBackCounter = 0;
    public int knockBackPower = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    // METHODS
    public void setAction() {
    }

    public int getLeftX(){
        return worldX + solidArea.x;
    }
    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }
    public int getTopY(){
        return worldY + solidArea.y;
    }
    public int getBotY(){
        return worldY + solidArea.y + solidArea.height;
    }
    public int getRow(){
        return (worldY+solidArea.y)/gp.tileSize;
    }

    public int getCol(){
        return (worldX+solidArea.x)/gp.tileSize;
    }
    public boolean useItem(Entity entity) { return false;}

    public void damageReaction() {
    }

    public void checkItemDrop() {
    }

    public void dropItem(Entity itemDropped) {

        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = itemDropped;
                gp.obj[gp.currentMap][i].worldX = worldX; // dead monster's coordenates
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void speak() {

        //if all the dialogues are finished, loop back to the first one
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        //turn the npc facing the player when a dialogue occurs
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    //Interact with objects
    public void interact(){

    }

    //Particle
    public Color getParticleColor() {

        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {

        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife() {

        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        // Instance a new particle
        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);

        // add this particle to the particle array
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);

    }

    //pathfinding
    public void checkCollision() {

        collisionOn = false;
        gp.cCheck.checkTile(this);
        gp.cCheck.checkObject(this, false);
        gp.cCheck.checkEntity(this, gp.npc);
        gp.cCheck.checkEntity(this, gp.monster);
        gp.cCheck.checkEntity(this, gp.inTile);

        boolean contactPLayer = gp.cCheck.checkPlayer(this);

        if (this.type == typeMonster && contactPLayer) {
            damagePlayer(attack);
        }
    }

    // UPDATE
    public void update() {
        if(knockBack){
            checkCollision();
            if(collisionOn){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else {
                switch(gp.player.direction){
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
             knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }

        else{
            setAction();
            checkCollision();
            //for the movement we copy the player's movement
            // if collision is false player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
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

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // Timer for the next shoot
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

    }

    public void damagePlayer(int attack) {

        if (!gp.player.invincible) {

            // if the player is not invincible, we add damage
            gp.playSoundEffect(10);

            int damage = attack - gp.player.defense;

            if (damage < 0) {
                damage = 0;
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }

    }

    public boolean isOnScreen(){
        return worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //change for a boolean method
        if (isOnScreen()) {

            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                case "down" -> {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                case "left" -> {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                case "right" -> {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
            }

                // Monster's health bar
                if (type == 2 && hpBarOn) {
                    // Calculate the remaining life
                    double oneScale = (double) gp.tileSize / maxLife;
                    double hpBarValue = oneScale * life;

                    //border
                    g2.setColor(Color.BLACK);
                    g2.fillRect(screenX - 3, screenY - 6, gp.tileSize + 5, 15);
                    // fill
                    g2.setColor(new Color(255, 0, 90));
                    g2.fillRect(screenX, screenY - 4, (int) hpBarValue, 10);

                    hpBarCounter++;

                    if (hpBarCounter > 600) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }

                if (invincible) {
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2, 0.5f);
                }

                if (dying) {
                    dyingAnimation(g2);
                }

                g2.drawImage(image, screenX, screenY, null);
                changeAlpha(g2, 1f);

            //draw solid area
            g2.setColor(Color.BLUE);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
        }



    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        int interval = 5;

        if (dyingCounter <= interval) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > interval && dyingCounter <= interval * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > interval * 2 && dyingCounter <= interval * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > interval * 3 && dyingCounter <= interval * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > interval * 4 && dyingCounter <= interval * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > interval * 5 && dyingCounter <= interval * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > interval * 6 && dyingCounter <= interval * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > interval * 7 && dyingCounter <= interval * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > interval * 8) {
//            dying = false;
            isAlive = false;
        }

    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    //    enhanced method
    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaledImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public void searchPath(int goalCol, int goalRow){

           int startCol = getCol();
           int startRow = getRow();

         gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

         if(gp.pFinder.search()) {
             //next worldX && worldY
             int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
             int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

             // entity solid area position
             int entLeftX = getLeftX(); //worldX + solidArea.x;
             int entRightX = getRightX(); //worldX + solidArea.x + solidArea.width;
             int entTopY = getTopY(); //worldY + solidArea.y;
             int entBotY = getBotY(); //worldY + solidArea.y + solidArea.height;

             // don't let the entity get stuck
             if(entTopY > nextY && entLeftX >= nextX && entRightX < nextX +gp.tileSize){
                 direction = "up";
             }
             else if(entTopY < nextY && entLeftX >= nextX && entRightX < nextX +gp.tileSize){
                 direction = "down";
             }
             else if(entTopY >= nextY && entBotY < nextY + gp.tileSize){
                 //left or right
                 if(entLeftX > nextX){
                     direction = "left";
                 }
                 if(entLeftX < nextX){
                     direction = "right";
                 }
             }
             //other cases
             else if(entTopY > nextY && entLeftX > nextX){
                 //up or left
                 direction = "up";
                 checkCollision();
                 if(collisionOn){
                     direction = "left";
                 }
             }
             else if(entTopY > nextY && entLeftX < nextX){
                 //up or right
                 direction = "up";
                 checkCollision();
                 if(collisionOn){
                     direction = "right";
                 }
             }
             else if(entTopY < nextY && entLeftX > nextX){
                 //down or left
                 direction = "down";
                 checkCollision();
                 if(collisionOn){
                     direction = "left";
                 }
             }

             else if(entTopY < nextY && entLeftX < nextX){
                 //down or right
                 direction = "down";
                 checkCollision();
                 if(collisionOn){
                     direction = "right";
                 }
             }
              //stop the npc when arriving to goal
             // dissable for case 2
//             int nextCol = gp.pFinder.pathList.get(0).col;
//             int nextRow = gp.pFinder.pathList.get(0).row;
//             if(nextCol == goalCol && nextRow == goalRow){
//                 onPath = false;
//                 System.out.println("goalreached");
//             }

         }
    }

    //Object detection
    public int getDetected(Entity user, Entity[][] target, String targetName){

        int index = 999;

        //check surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction) {
            case "up" -> nextWorldY = user.getTopY() - user.speed;
            case "down" -> nextWorldY = user.getBotY() + user.speed;
            case "left" -> nextWorldX = user.getLeftX() - user.speed;
            case "right" -> nextWorldX = user.getRightX() + user.speed;
        }

        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                if(target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)){

                    index = i;
                    break;

                }
            }
        }
        return index;
    }


}
