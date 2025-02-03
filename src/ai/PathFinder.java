package ai;

import main.*;

import java.util.*;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node>openList = new ArrayList<>();
    public ArrayList<Node>pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step =  0;


    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
        
    }

    public void instantiateNodes(){
        
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row] =  new Node(col, row);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes(){
        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            //reset all the nodes data
            node[col][row].open = false ;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
        // reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNode(int startCol, int startRow, int goalCol, int goalRow){

        resetNodes();

        // set start and goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];

        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

            //set solid node
            //check tiles
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if(gp.tileM.tile[tileNum].collision){
                node[col][row].solid = true;
            }
            // check interactive tiles too
            for(int i = 0; i < gp.inTile[1].length; i++){
                if(gp.inTile[gp.currentMap][i]!= null && gp.inTile[gp.currentMap][i].destructible){
                    int itCol = gp.inTile[gp.currentMap][i].worldX/gp.tileSize;
                    int itRow = gp.inTile[gp.currentMap][i].worldY/gp.tileSize;
                    node[itCol][itRow].solid = true;

                }
            }

            //set costs
            getCost(node[col][row]);

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

   public void getCost(Node node){
       //G cost
       int xDistance = Math.abs(node.col - startNode.col);
       int yDistance = Math.abs(node.row - startNode.row);
       node.gCost = xDistance + yDistance;
   }


}
