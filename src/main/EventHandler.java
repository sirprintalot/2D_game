package main;


public class EventHandler {

    GamePanel gp;
    EventRect[][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;


    public EventHandler(GamePanel gp) {

        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int row = 0;
        int col = 0;

        while (row < gp.maxWorldRow) {

            while (col < gp.maxWorldCol) {
                eventRect[col][row] = new EventRect();
                eventRect[col][row].x = 23;
                eventRect[col][row].y = 23;
                eventRect[col][row].width = 2;
                eventRect[col][row].height = 2;
                eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
                eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

                col++;
            }
            col = 0;
            row++;

        }

    }

    public void checkEvent() {

        //Check the distance between the character, and the event tile
        //is it's more than a tile the event can happen again
        int xdistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);

        int distance = Math.max(xdistance, yDistance);

        if (distance > gp.tileSize) {

            canTouchEvent = true;
        }

        if (canTouchEvent) {

            if (hit(27, 15, "right")) {
                damagePit(gp.dialogueState, 27, 15);
            }
            if (hit(23, 42, "any")) {
                teleport(gp.dialogueState);
            }
            if (hit(23, 12, "any")) {
                healingPool(gp.dialogueState);
            }
        }

    }

    public boolean hit(int col, int row, String reqDirection) {

        boolean hit = false;
        // PLayer's position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        //check if there's a collision
        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {

                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        //reset positions
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }

    public void damagePit(int gameState, int col, int row) {

        gp.gameState = gameState;
        gp.playSoundEffect(10);
        gp.ui.currentDialogue = "You fall into a pit";
        gp.player.life -= 1;

        //this flag makes the event happens just one time
//        eventRect[col][row].eventDone = true;
        //reset the tile distace to the event so will keep happening
        //as long you are at one tile of distance
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {
        
        if (gp.keyH.enterPressed) {

            gp.gameState = gameState;
            gp.player.attackCancel = true;
            gp.playSoundEffect(2);
            gp.ui.currentDialogue = "You drank the healing water. /nLife restored";
            gp.player.life = gp.player.maxLife;
            canTouchEvent = false;

            //TODO implement use of when pressing just enter key

        }
    }

    public void teleport(int gameState) {

        gp.playSoundEffect(12);
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Teleport!";

        // position to be teleported to
        gp.player.worldX = gp.tileSize * 38;
        gp.player.worldY = gp.tileSize * 7;

        //TODO after one teleportation reset the tile to the original one
        //TODO MAKE THE PLAYER flicker while teleporting
    }


}