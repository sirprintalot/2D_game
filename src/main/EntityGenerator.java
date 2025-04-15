package main;

import entity.*;
import objects.*;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }
    public Entity getObject(String itemName){

        return switch (itemName) {
            case OBJ_Axe.objName -> new OBJ_Axe(gp);
            case OBJ_Boots.objName -> new OBJ_Boots(gp);
            case OBJ_BronzeCoin.objName -> new OBJ_BronzeCoin(gp);
            case OBJ_Chest.objName -> new OBJ_Chest(gp);
            case OBJ_Door.objName -> new OBJ_Door(gp);
            case OBJ_Fireball.objName -> new OBJ_Fireball(gp);
            case OBJ_Heart.objName -> new OBJ_Heart(gp);
            case OBJ_Key.objName -> new OBJ_Key(gp);
            case OBJ_Lantern.objName -> new OBJ_Lantern(gp);
            case OBJ_manaCrystal.objName -> new OBJ_manaCrystal(gp);
            case OBJ_Potion_Red.objName -> new OBJ_Potion_Red(gp);
            case OBJ_Rock.objName -> new OBJ_Rock(gp);
            case OBJ_Shield_blue.objName -> new OBJ_Shield_blue(gp);
            case OBJ_shield_Wood.objName -> new OBJ_shield_Wood(gp);
            case OBJ_Sword_Normal.objName -> new OBJ_Sword_Normal(gp);
            case OBJ_Tent.objName -> new OBJ_Tent(gp);
            case OBJ_PickAxe.objName -> new OBJ_PickAxe(gp);
            case OBJ_IronDoor.objName -> new OBJ_IronDoor(gp);
            default -> {
                System.out.println("Warning: Unknown item name '" + itemName + "'");
                yield null;
            }
        };
    }
}
