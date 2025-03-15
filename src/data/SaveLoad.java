package data;

import entity.*;
import main.*;
import objects.*;

import java.io.*;

public class SaveLoad {

    GamePanel gp;

    public SaveLoad(GamePanel gp){
        this.gp = gp;
    }

    public Entity getObject(String itemName){

        if(itemName == null){
            throw new RuntimeException("Null object name");
        }

        Entity obj = switch (itemName) {
            case "Axe" -> new OBJ_Axe(gp);
            case "light boots" -> new OBJ_Boots(gp);
            case "bronze coin" -> new OBJ_BronzeCoin(gp);
            case "key" -> new OBJ_Key(gp);
            case "Lantern" -> new OBJ_Lantern(gp);
            case "mana crystal" -> new OBJ_manaCrystal(gp);
            case "Red potion" -> new OBJ_Potion_Red(gp);
            case "Blue Shield" -> new OBJ_Shield_blue(gp);
            case "Wooden Shield" -> new OBJ_shield_Wood(gp);
            case "Normal Sword" -> new OBJ_Sword_Normal(gp);
            case "Tent" -> new OBJ_Tent(gp);
            case "chest" -> new OBJ_Chest(gp);
            default -> null;
        };

        return obj;
    }

    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));

            DataStorage ds = new DataStorage();

            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.mana = gp.player.mana;
            ds.maxMana = gp.player.maxMana;
            ds.strength = gp.player.strength;
            ds.dexterity = gp.player.dexterity;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;

            //Player inventory
            for(int i = 0; i < gp.player.inventory.size(); i++){
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.itemAmount.add(gp.player.inventory.get(i).ammount);
            }

            //Player equipped a weapon and shield
            ds.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
            ds.currentShieldSlot = gp.player.getCurrentShieldSlot();

            // Objects on map
            ds.mapObjectNames = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY = new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootName = new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];

            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for(int i = 0; i < gp.obj[1].length; i++ ){
                    if(gp.obj[mapNum][i] == null){
                        ds.mapObjectNames[mapNum][i] = "NA";
                    }
                    else {
                        ds.mapObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;

                        if(gp.obj[mapNum][i].loot != null){
                            ds.mapObjectLootName[mapNum][i] = gp.obj[mapNum][i].loot.name;
                        }
                        ds.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;

                    }
                }
            }



            // write the data storage object
            oos.writeObject(ds);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));

            // read the data storage obj
            DataStorage ds = (DataStorage)ois.readObject();

            // Load player stats
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.mana = ds.mana;
            gp.player.maxMana = ds.maxMana;
            gp.player.strength = ds.strength;
            gp.player.dexterity = ds.dexterity;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;
//            gp.player.worldX = gp.tileSize * 23;
//            gp.player.worldY = gp.tileSize * 12;
//            gp.player.direction = "up";

            // Load player inventory
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++){
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                gp.player.inventory.get(i).ammount = ds.itemAmount.get(i);
            }

            //load player cuurent weapon and shield
            gp.player.currentWeapon = gp.player.inventory.get(ds.currentWeaponSlot);
            gp.player.currentShield = gp.player.inventory.get(ds.currentShieldSlot);
            gp.player.getAttack();
            gp.player.getDefense();
            gp.player.getAttackImage();

            // Load objects on map
            for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
                for(int i = 0; i < gp.obj[1].length; i++){
                    if(ds.mapObjectNames[mapNum][i].equals("NA")){
                        gp.obj[mapNum][i] = null;
                    }
                    else{
                        gp.obj[mapNum][i] = getObject(ds.mapObjectNames[mapNum][i]);
                        System.out.println(getObject(ds.mapObjectNames[mapNum][i]));
                        gp.obj[mapNum][i].worldX = ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY = ds.mapObjectWorldY[mapNum][i];

                        if(ds.mapObjectLootName != null){
                            gp.obj[mapNum][i].loot =getObject(ds.mapObjectLootName[mapNum][i]);
                        }
                        gp.obj[mapNum][i].opened = ds.mapObjectOpened[mapNum][i];
                        if(gp.obj[mapNum][i].opened) {
                            gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2;
                        }
                    }
                }
            }



        }
        catch(Exception e){
            throw new RuntimeException(e);
        }




    }


}
