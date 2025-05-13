package objects;

import entity.*;
import main.*;

public class OBJ_BlueHeart extends Entity {

    GamePanel gp;
    public static final String objName = "Blue Heart";
    public OBJ_BlueHeart(GamePanel gp) {
        super(gp);

        this.gp = gp;
        this.name = objName;
        type = typePickupOnly;

        down1 = setup("/objects/blueheart", gp.tileSize, gp.tileSize);
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You got the King's emerald";
        dialogues[0][1] = "This will lead you home...";
    }

    public boolean use(Entity entity){

        gp.gameState  = gp.cutSceneState;
        gp.cutSceneManager.sceneNum = gp.cutSceneManager.ending;
        return true;
    }
}
