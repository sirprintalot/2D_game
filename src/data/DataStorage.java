package data;

import java.io.*;
import java.util.*;

public class DataStorage implements Serializable {

    // player stats
    int level;
    int maxLife;
    int life;
    int mana;
    int maxMana;
    int strength;
    int dexterity;
    int exp;
    int nextLevelExp;
    int coin;

    int dayTime ;


    //player inventory
    ArrayList<String>itemNames = new ArrayList<>();
    ArrayList<Integer>itemAmount = new ArrayList<>();

    int currentWeaponSlot;
    int currentShieldSlot;

    //Objects on the map
    String[][] mapObjectNames;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    String[][] mapObjectLootName;
    boolean[][] mapObjectOpened;






}
