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

         // set the tree life, so it takes x amount of hits to disappear
         life = 3;
         
    }
    public boolean correctItem(Entity entity){

        boolean correctItem = false;
        if(entity.currentWeapon.type == typeAxe){
              correctItem = true;
        }

        return correctItem;
    }

    public void playSoundEffect(){
        gp.playSoundEffect(16);
    }
    
    public InteractiveTile getDestroyedForm(){

        InteractiveTile inTile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return inTile;
    }



}
