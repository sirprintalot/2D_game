package tile_interactive;

import entity.*;
import main.*;

public class IT_DryTree extends InteractiveTile{

    GamePanel gp;
    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX  = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        
        down1 = setup("/interactive_tiles/drytree", gp.tileSize, gp.tileSize);
         destructible = true;
         
    }
    public boolean correctItem(Entity entity){

        boolean correctItem = false;
        if(entity.currentWeapon.type == typeAxe){
              correctItem = true;
        }

        return correctItem;
    }
}
