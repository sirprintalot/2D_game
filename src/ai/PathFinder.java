package ai;

import main.*;

import java.util.*;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;


    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();

    }

    public void instantiateNodes() {

        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row] = new Node(col, row);
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            //reset all the nodes data
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if (col == gp.maxWorldCol) {
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

    public void setNode(int startCol, int startRow, int goalCol, int goalRow) {

        resetNodes();

        // set start and goal node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];

        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            //set solid node
            //check tiles
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if (gp.tileM.tile[tileNum].collision) {
                node[col][row].solid = true;
            }
            // check interactive tiles too
            for (int i = 0; i < gp.inTile[1].length; i++) {
                if (gp.inTile[gp.currentMap][i] != null && gp.inTile[gp.currentMap][i].destructible) {
                    int itCol = gp.inTile[gp.currentMap][i].worldX / gp.tileSize;
                    int itRow = gp.inTile[gp.currentMap][i].worldY / gp.tileSize;
                    node[itCol][itRow].solid = true;

                }
            }

            //set costs
            getCost(node[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node) {
        //G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        //h Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //fCost
        node.fCost = node.hCost + node.hCost;
    }

    public boolean search() {

        while (!goalReached && step < 500) {

            int col = currentNode.col;
            int row = currentNode.row;

            // check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //open the node above
            if (row - 1 >= 0) {openNode(node[col][row - 1]);}
            //open node to the left
            if (col - 1 >= 0) {openNode(node[col - 1][row]);}
            //open the node below
            if (row + 1 < gp.maxWorldRow) {openNode(node[col][row + 1]);}
            // open the node to the right
            if (col + 1 < gp.maxWorldCol) {openNode(node[col + 1][row]);}

            }
        // find the best node
        int bestNodeIndex = 0;
        int bestNodeFCost = Integer.MAX_VALUE;

        for(int i = 0; i < openList.size(); i++){
            //check if node's f cost is better
            if(openList.get(i).fCost < bestNodeFCost){
                bestNodeIndex = i;
                bestNodeFCost = openList.get(i).fCost;
            }

            //if f cost is equal check g cost
            else if(openList.get(i).fCost == bestNodeFCost){
                if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                    bestNodeIndex = i;
                }
            }

            // if there's no node in the open list, end the loop
            if(openList.size() == 0){
                break;
            }

            //after the loop openList[bestNodeIndex]is the next setp =currentNode
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            
            step++;
        }
          return goalReached;
    }

    public void trackThePath(){

        Node current = goalNode;
        while(current != startNode){
            pathList.add(0, current);
            current = current.parent;
        }



    }

    public void openNode(Node node){
        if(!node.open && !node.checked && !node.solid){
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
            
        }
    }


}