package tile_interactive;

import entity.*;
import main.*;

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
    public void playSoundEffect(){}
    public InteractiveTile getDestroyedForm(){

        InteractiveTile inTile = null;
        return inTile;
    }
}
