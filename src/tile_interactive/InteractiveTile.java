package tile_interactive;

import entity.*;
import main.*;

import java.awt.*;
import java.awt.image.*;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;
    
    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean correctItem(Entity entity){

        boolean correctItem = false;
        return correctItem;
    }

    public void update(){

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    // Override the draw method so the interactive tiles don't become half transparent when hit
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(down1, screenX, screenY, null);

        }
    }

    public void playSoundEffect(){}
    public InteractiveTile getDestroyedForm(){

        InteractiveTile inTile = null;
        return inTile;
    }



}
