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
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2, guardUp, guardDown, guardLeft, guardRight;

    //DIRECTION 
    public String direction = "down";

    //SPRITE
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int animationSpeed = 12;

    //attacking speed
    public int motion1_duration;
    public int motion2_duration;

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

    //loot
    public Entity loot;
    public boolean opened = false;

    // speed up
    public boolean speedBoosted = false;
    public int speedBoostTimer = 0;
    public int speedBoostDuration;

    // PARRY
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    public boolean offBalance = false;

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
    public boolean attacking = false;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    //guarding
    public boolean guarding = false;
    public boolean transparent = false;

    public int shotAvailableCounter = 0;

    // CHECKING THE ENTITY CLASS FOR TAKING DAMAGE OR NOT also check weapon or shield type
    public int type; //0 player, 1 npc etc

    //Entities
    public final int typePlayer = 0;
    public final int typeNpc = 1;
    public final int typeMonster = 2;

    // dungeon puzzle
    public Entity linkedEntity;

    //Objects
    public final int typeSword = 3;
    public final int typeAxe = 4;
    public final int typeShield = 5;
    public final int typeUsable = 6;
    public final int typePickupOnly = 7;
    public final int typeObstacle = 8;
    public final int typeLight = 9;
    public final int typePickAxe = 10;

    //DIALOGUE
    public String[][] dialogues = new String[20][20];
    public int dialogueIndex = 0;
    public int dialogueSet = 0;

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

    public Entity attacker;
    public String knockBackDirection;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    // METHODS
    public void setAction() {
    }

    public void move(String direction) {

    }

    public String getOppositeDirection(String direction) {
        String opDirection = "";
        switch (direction) {
            case "up" -> opDirection = "down";
            case "down" -> opDirection = "up";
            case "left" -> opDirection = "right";
            case "right" -> opDirection = "left";
        }
        return opDirection;
    }

    // final boss
    public int getCenterX(){
        return worldX - left1.getWidth()/2;
    }
    public int getCenterY(){
       return worldY - up1.getHeight()/2;
    }

    public int getXdistance(Entity target) {
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }

    public int getYdistance(Entity target) {
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }

    public int getTileDistance(Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target)) / gp.tileSize;
        return tileDistance;
    }

    public int getGoalCol(Entity target) {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;
    }

    public int getGoalRow(Entity target) {
        int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
        return goalRow;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBotY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gp.tileSize;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gp.tileSize;
    }

    public void resetCounter() {
        spriteCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    public boolean useItem(Entity entity) {
        return false;
    }

    public void setLoot(Entity loot) {
    }

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
    }

    // npc always facing the player
    public void facePlayer() {
        //turn the npc facing the player when a dialogue occurs
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    public void startDialogue(Entity entity, int setNum) {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }


    //Interact with objects
    public void interact() {

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

    public void resetSpeed() {

    }

    // UPDATE
    public void update() {

        if (knockBack) {
            checkCollision();
            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else {
                switch (knockBackDirection) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else if (attacking) {
            attack();
        } else {
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

        //parry/guard
        if (offBalance) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }

    }

    public void checkStopChasing(Entity target, int distance, int rate) {
        if (getTileDistance(target) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }

    public void checkStartChasing(Entity target, int distance, int rate) {
        if (getTileDistance(target) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }

    public void checkShoot(int rate, int shotInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && !projectile.isAlive && shotAvailableCounter == shotInterval) {
            projectile.set(worldX, worldY, direction, true, this);
            //CHECK VACANCY
            for (int j = 0; j < gp.projectile.length; j++) {
                if (gp.projectile[gp.currentMap][j] == null) {
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {

        boolean targetInrange = false;
        int xDistance = getXdistance(gp.player);
        int yDistance = getYdistance(gp.player);

        switch (direction) {
            case "up":
                if (gp.player.getCenterY() < getCenterY() && yDistance < straight && xDistance < horizontal) {
                    targetInrange = true;
                }
            case "down":
                if (gp.player.getCenterY() > getCenterY() && yDistance < straight && xDistance < horizontal) {
                    targetInrange = true;
                }

            case "left":
                if (gp.player.getCenterX() < getCenterX() && xDistance < straight && yDistance < horizontal) {
                    targetInrange = true;
                }

            case "right":
                if (gp.player.getCenterX() > getCenterX() && xDistance < straight && yDistance < horizontal) {
                    targetInrange = true;
                }

        }
        if (targetInrange) {

            int i = new Random().nextInt(rate);
            if (i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void getRandomDirection(int interval) {
        actionLockCounter++;

        if (actionLockCounter == interval) {

            Random rand = new Random();
            int i = rand.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 26 && i <= 50) {
                direction = "down";
            }
            if (i > 51 && i <= 75) {
                direction = "left";
            }
            if (i > 76) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void attack() {

        // Creating the attacking animation
        spriteCounter++;

        if (spriteCounter <= motion1_duration) {
            spriteNum = 1;
        }
        if (spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            // original position of the player
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

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

            if (type == typeMonster) {
                if (gp.cCheck.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else {
                //player attack
                // check monster collision with the updated coordinates
                int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                // interactive tile
                int inTileIndex = gp.cCheck.checkEntity(this, gp.inTile);
                gp.player.damageInteractiveTile(inTileIndex);

                int projectileIndex = gp.cCheck.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }


            // Reset the player's position
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > motion2_duration) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack) {

        if (!gp.player.invincible) {
            // if the player is not invincible, we add damage
            int damage = attack - gp.player.defense;

            // Get the opposite direction of the attacker
            String canGuardDirection = getOppositeDirection(direction);

            if (gp.player.guarding && canGuardDirection.equals(gp.player.direction)) {

                //parry
                if (gp.player.guardCounter < 15) {
                    damage = 0;
                    gp.playSoundEffect(21);
                    System.out.println("parry");
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter -= 60;
                } else {
                    //guard
                    damage /= 4;
                    gp.playSoundEffect(5);
                }


            } else {
                // not guarding
                gp.playSoundEffect(10);
                if (damage < 1) {
                    damage = 1;
                }
            }

            if (damage != 0) {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }

            //receive knockback if there's no damage
            //setKnockBack(gp.player, this, knockBackPower);

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {

        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;

    }

    public boolean isOnScreen() {
        return worldX + gp.tileSize * 5 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 5 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //change for a boolean method
        if (isOnScreen()) {

            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {

                case "up" -> {
                    if (!attacking) {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    if (attacking) {
                        tempScreenY = screenY - up1.getHeight();  // for all size entities
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }
                        if (spriteNum == 2) {
                            image = attackUp2;
                        }
                    }
                }
                case "down" -> {
                    if (!attacking) {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }
                        if (spriteNum == 2) {
                            image = attackDown2;
                        }
                    }
                }
                case "left" -> {
                    if (!attacking) {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    if (attacking) {
                        tempScreenX = screenX - left1.getWidth();  // for all size entities
                        if (spriteNum == 1) {
                            image = attackLeft1;
                        }
                        if (spriteNum == 2) {
                            image = attackLeft2;
                        }
                    }
                }
                case "right" -> {
                    if (!attacking) {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }
                        if (spriteNum == 2) {
                            image = attackRight2;
                        }
                    }

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

            g2.drawImage(image, tempScreenX, tempScreenY, null);
            changeAlpha(g2, 1f);

            //draw solid area
            //g2.setColor(Color.BLUE);
            // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }


    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        int interval = 7;

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

    public void searchPath(int goalCol, int goalRow) {

        int startCol = getCol();
        int startRow = getRow();

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search()) {
            //next worldX && worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // entity solid area position
            int entLeftX = getLeftX(); //worldX + solidArea.x;
            int entRightX = getRightX(); //worldX + solidArea.x + solidArea.width;
            int entTopY = getTopY(); //worldY + solidArea.y;
            int entBotY = getBotY(); //worldY + solidArea.y + solidArea.height;

            // don't let the entity get stuck
            if (entTopY > nextY && entLeftX >= nextX && entRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (entTopY < nextY && entLeftX >= nextX && entRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (entTopY >= nextY && entBotY < nextY + gp.tileSize) {
                //left or right
                if (entLeftX > nextX) {
                    direction = "left";
                }
                if (entLeftX < nextX) {
                    direction = "right";
                }
            }
            //other cases
            else if (entTopY > nextY && entLeftX > nextX) {
                //up or left
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            } else if (entTopY > nextY && entLeftX < nextX) {
                //up or right
                direction = "up";
                checkCollision();
                if (collisionOn) {
                    direction = "right";
                }
            } else if (entTopY < nextY && entLeftX > nextX) {
                //down or left
                direction = "down";
                checkCollision();
                if (collisionOn) {
                    direction = "left";
                }
            } else if (entTopY < nextY && entLeftX < nextX) {
                //down or right
                direction = "down";
                checkCollision();
                if (collisionOn) {
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
    public int getDetected(Entity user, Entity[][] target, String targetName) {

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

        int col = nextWorldX / gp.tileSize;
        int row = nextWorldY / gp.tileSize;

        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                if (target[gp.currentMap][i].getCol() == col &&
                        target[gp.currentMap][i].getRow() == row &&
                        target[gp.currentMap][i].name.equals(targetName)) {

                    index = i;
                    break;

                }
            }
        }
        return index;
    }


}
