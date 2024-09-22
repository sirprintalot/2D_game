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
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    //DIRECTION
    public String direction;

    //SPRITE
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int animationSpeed = 12;

    //CHARACTER STATUS
    public int maxLife;
    public int life;

    //DIALOGUE
    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    //solid area for all entities (can be overwritten for each case)
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    //this counter controls the nps's activity
    public int actionLockCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}

    public void speak(){

        //if all the dialogues are finished, loop back to the first one
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        gp.ui.currentDialog = dialogues[dialogueIndex];
        dialogueIndex++;

        //turn the npc facing the player when a dialogue occurs
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
    public void update() {

        setAction();

        collisionOn = false;
        gp.cCheck.checkTile(this);
        gp.cCheck.checkObject(this, false);
        gp.cCheck.checkPlayer(this);

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

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)

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
        {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    //    enhanced method
    public BufferedImage setup(String imagePath) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


}
