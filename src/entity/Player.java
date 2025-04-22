package entity;

import main.*;
import objects.*;

import java.awt.*;
import java.awt.image.*;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    //standing sprite
    public int standingCounter = 0;

    // Attack
    public boolean attackCancel = false;

    //light
    public boolean lightUpdated = false;

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
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 27;
        solidArea.height = 30;

        //Attack area
//        attackArea.width = 36;
//        attackArea.height = 36;


        // Methods
        setDefaultValues();
    }


    public void setDefaultValues() {

        //for map 1
//        worldY = gp.tileSize * 10;
//        worldX = gp.tileSize * 11;
        //test dungeon 1
//        worldX = gp.tileSize * 9;
//        worldY = gp.tileSize * 39;

        //PLAYER STATUS
        maxLife = 10;
        life = maxLife;
        //projectile
        projectile = new OBJ_Fireball(gp);
//        projectile = new OBJ_Rock(gp);
        // Player stats
        level = 1;
        strength = 6; // the more strength more damage
        dexterity = 1; // more dexterity, more chances to block attacks
        exp = 1;
        nextLevelExp = 5;
        coin = 1000;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_shield_Wood(gp);
        currentLight = null;
        defaultSpeed = 5;
        speed = defaultSpeed;
        attack = getAttack();
        defense = getDefense();

        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
        setDefaultPosition();

    }

    public void setDefaultPosition() {

        //World Map
//        worldX = gp.tileSize * 23;
//        worldY = gp.tileSize * 21;
//        direction = "down";

        //for map 1
//        worldY = gp.tileSize * 10;
//        worldX = gp.tileSize * 12;
//        direction = "up";

        //for map 2
        gp.currentMap = 2;
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * 40;
        
        direction = "up";
    }

    public void restorePlayerStats() {

        life = maxLife;
        mana = maxMana;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
        speed = defaultSpeed;
    }

    public void setItems() {

        inventory.clear();

        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Lantern(gp));

    }

    public int getAttack() {

        //set the attack area according to the current weapon
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {

        return defense = dexterity * currentShield.defenseValue;

    }

    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i; 
            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot(){

        int currentShieldSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield){
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }

    public void getImage() {
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

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }


    public void getAttackImage() {

        if (currentWeapon.type == typeSword) {
            attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == typeAxe) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }

        if (currentWeapon.type == typePickAxe) {
            attackUp1 = setup("/player/boy_pick_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_pick_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_pick_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_pick_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_pick_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_pick_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_pick_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_pick_right_2", gp.tileSize * 2, gp.tileSize);
        }

    }

    public void getGuardImage() {
        guardUp = setup("/player/boy_guard_up", gp.tileSize, gp.tileSize);
        guardDown = setup("/player/boy_guard_down", gp.tileSize, gp.tileSize);
        guardLeft = setup("/player/boy_guard_left", gp.tileSize, gp.tileSize);
        guardRight = setup("/player/boy_guard_right", gp.tileSize, gp.tileSize);
    }


    public void update() {

        boolean moving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
        
        if(knockBack){
            
            gp.cCheck.checkTile(this);
            //Object collision
            gp.cCheck.checkObject(this, true);
            //Npc collision
            gp.cCheck.checkEntity(this, gp.npc);
            //Monster collision
            gp.cCheck.checkEntity(this, gp.monster);
            //Check interactive tile collision
            gp.cCheck.checkEntity(this, gp.inTile);
            
            if(collisionOn){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else {
                switch(knockBackDirection){
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

       else if (attacking) {
            attack();
        }

        // guard/parry
       else if (keyH.spacePressed) {
            guarding = true;
            guardCounter++;
        }

       else if (moving || keyH.enterPressed) {

            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;

            //GOD mode if disabled
            gp.cCheck.checkTile(this);

            //Object collision
            int objIndex = gp.cCheck.checkObject(this, true);
            pickUpObject(objIndex);

            //Npc collision
            int npcIndex = gp.cCheck.checkEntity(this, gp.npc);
            interactNpc(npcIndex);

            //Monster collision
            int monsterIndex = gp.cCheck.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);

            //Check interactive tile collision
            int inTileIndex = gp.cCheck.checkEntity(this, gp.inTile);

            //Check event
            gp.eventHandler.checkEvent();

            //If collision is false, player can move
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            if (keyH.enterPressed && !attackCancel) {
                gp.playSoundEffect(9);
                attacking = true;
                spriteCounter = 0;
            }

            attackCancel = false;
            gp.keyH.enterPressed = false;

            guarding = false;
            guardCounter = 0;

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

            //TODO check this bug when walking continuously
            //display the correct sprite when standing still (every 20 frames)
//            else {
//                standingCounter++;
//                if (standingCounter == 60) {
//                    spriteNum = 1;
//                    standingCounter = 0;
//                }
//            }
        }

        //shoot projectile when key pressed
        if (keyH.shootPressed && !projectile.isAlive && shotAvailableCounter == 30 && projectile.haveResources(this)) {

            //set default coordinates for projectile
            projectile.set(worldX, worldY, direction, true, this);

            // subtract the mana cost of the attack
            projectile.substrackResource(this);

            //adds projectile to the projectile list
//            gp.projectileList.add(projectile);
            //CHECK VACANCY
            for (int i = 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;

            gp.playSoundEffect(15);

        }

        // speed up
        if (speedBoosted) {
            speedBoostTimer++;
            speedBoostDuration = 500;
            System.out.println(speedBoostTimer);
            if (speedBoostTimer == speedBoostDuration) {
                resetSpeed();
                System.out.println("ended");
            }
        }

        //Make player invincible when receive damage
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                transparent = false;
                invincibleCounter = 0;
            }

        }
        // Timer for the next shoot
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        // check if life or mana is at full when healing if so, the value is the max value
        if (life > maxLife) {
            life = maxLife;
        }

        if (mana > maxMana) {
            mana = maxMana;
        }

        // Game Over
        if(!keyH.godModeOn){
            if (life <= 0) {
                gp.gameState = gp.gameOverState;
                gp.ui.commandNum = -1;
                gp.stopMusic();
                gp.playSoundEffect(17);
            }
        }





    }



    public void resetSpeed() {

        speedBoosted = false;
        speed = defaultSpeed;
        speedBoostTimer = 0;
    }

    //pick object
    public void pickUpObject(int i) {

        if (i != 999) {
            // Pickup only items
            if (gp.obj[gp.currentMap][i].type == typePickupOnly) {

                gp.obj[gp.currentMap][i].useItem(this);
                gp.obj[gp.currentMap][i] = null;
            }
            // Obstacles
            else if (gp.obj[gp.currentMap][i].type == typeObstacle) {
                if (keyH.enterPressed) {
                    attackCancel = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            } else {

                //Inventory items
                String displayText;

                //Check if the player's inventory is not full
                if (canReceiveItem(gp.obj[gp.currentMap][i])) {
//                    inventory.add(gp.obj[gp.currentMap][i]);

                    gp.playSoundEffect(1);
                    displayText = "Pick a " + gp.obj[gp.currentMap][i].name + " !!";
                } else {
                    displayText = "Can't carry anything more!";
                }

                gp.ui.addMessage(displayText, Color.WHITE);
                gp.obj[gp.currentMap][i] = null;     //THis dont show error but [gp.currentMap] most be in it
            }
        }


    }

    public void interactNpc(int i) {

        if (i != 999) {
            if (keyH.enterPressed) { 
                attackCancel = true;
                gp.npc[gp.currentMap][i].speak();
            }

            gp.npc[gp.currentMap][i].move(direction);
        }
    }

    public void interactMonster(int i) {

        if (i != 999) {

            if (!invincible && !gp.monster[gp.currentMap][i].dying) {
                gp.playSoundEffect(10);

                int damage = gp.monster[gp.currentMap][i].attack - defense;

                if (damage < 1) {
                    damage = 1;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }

        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {

        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible) {

                int damage = attack - gp.monster[gp.currentMap][i].defense;

                gp.playSoundEffect(11);

                if (knockBackPower > 0) {

                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }

                if(gp.monster[gp.currentMap][i].offBalance){

                    attack += 5;
                    gp.ui.addMessage("Critical Hit!! " + "+ " + attack + " Damage!", Color.RED);

                }
                
                //change to 0
                if (damage < 1) {
                    damage = 1;
                }

                gp.monster[gp.currentMap][i].life -= damage;

                gp.ui.addMessage("+" + damage + " Damage!", Color.WHITE);

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    //add message
                    gp.ui.addMessage(gp.monster[gp.currentMap][i].name + " defeated", Color.WHITE);

                    //leveling up
                    gp.ui.addMessage("+" + exp + " Experience!!", Color.DARK_GRAY);
                    exp += gp.monster[gp.currentMap][i].exp;

                    //check next level
                    checkLevelUp();
                }
            }
        }
    }

    public void damageInteractiveTile(int index) {

        if (index != 999 && gp.inTile[gp.currentMap][index].destructible &&
                gp.inTile[gp.currentMap][index].correctItem(this) && !gp.inTile[gp.currentMap][index].invincible) {

            gp.inTile[gp.currentMap][index].playSoundEffect();
            gp.inTile[gp.currentMap][index].life--;
            gp.inTile[gp.currentMap][index].invincible = true;

            //Generate particles when hitting an interactive tile
            generateParticle(gp.inTile[gp.currentMap][index], gp.inTile[gp.currentMap][index]);


            if (gp.inTile[gp.currentMap][index].life == 0) {

                //make so the wall gives an object when destroyed
//                gp.inTile[gp.currentMap][index].checkItemDrop();
                gp.inTile[gp.currentMap][index] = gp.inTile[gp.currentMap][index].getDestroyedForm();
            }
        }
    }

    public void damageProjectile(int i) {
        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.isAlive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {

        if (exp >= nextLevelExp) {

            level++;
            nextLevelExp *= 3;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSoundEffect(13);

            gp.gameState = gp.dialogueState;

            setDialogue(); 
            startDialogue(this, 0);
        }

    }

    public void setDialogue(){
        dialogues[0][0] = "Level " + level + "!/n"
                + "need " + nextLevelExp + " exp for the next Level!";
    }

    public void selectItem() {

        int itemIndex = gp.ui.getItemIndex(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type == typeSword || selectedItem.type == typeAxe || selectedItem.type == typePickAxe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }

            if (selectedItem.type == typeShield) {

                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == typeLight) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }

            if (selectedItem.type == typeUsable) {

                if (selectedItem.useItem(this)) {
                    if (selectedItem.ammount > 1) {
                        selectedItem.ammount--;
                    } else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }


    }

    //this method can be used for detecting specific items
    public int searchItemOnInventory(String itemName) {
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canReceiveItem(Entity item) {
        boolean canObtain = false;

        Entity newItem = gp.entityGenerator.getObject(item.name);

        //CHEck if item is stackable
        if (newItem.stackable) {
            int index = searchItemOnInventory(newItem.name);

            if (index != 999) {
                inventory.get(index).ammount++;
                canObtain = true;
            }
            // new item has to be checked for vacancy
            else {
                if (inventory.size() != inventorySize) {
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        }
        // NOT STACKABLE check vacancy
        else {
            if (inventory.size() != inventorySize) {
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;
    }


    public void draw(Graphics2D g2) {

        BufferedImage image = null;
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
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                if(guarding){
                    image = guardUp;
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
                if(guarding){
                    image = guardDown;
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
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }

                if(guarding){
                    image = guardLeft;
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

                if(guarding){
                    image = guardRight;
                }

            }
        }

        // change the player's opacity when invicible
        if (transparent) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
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