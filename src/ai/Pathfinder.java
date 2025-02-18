package ai;

import main.*;

import java.util.*;

public class Pathfinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList =  new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();

    Node startNode, goalNode, currentNode;

    boolean goalreached = false;

    int step = 0;

    public Pathfinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
        
    }

    public void instantiateNodes(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            node[col][row] = new Node(col, row);
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
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;

            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
        //reset the openList
        openList.clear();
        pathList.clear();
        goalreached = false;
        step = 0;
    }

    public void setNodes(int starCol, int startRow, int goalCol, int goalRow){

        resetNodes();

        //set start and goal nodes
        startNode = node[starCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            //set solid node
            //check tiles
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if(gp.tileM.tile[tileNum].collision){
                node[col][row].solid = true;
            }
            //set cost
            getCost(node[col][row]);
            col++;

            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
        // check interactive tiles *out of this loop
        for(int i = 0; i < gp.inTile[1].length; i++){
            if(gp.inTile[gp.currentMap][i] != null &&
                    gp.inTile[gp.currentMap][i].destructible){
                int intCol = gp.inTile[gp.currentMap][i].worldX/gp.tileSize;
                int intRow = gp.inTile[gp.currentMap][i].worldY/gp.tileSize;

                node[intCol][intRow].solid = true;
            }
        }
    }

    public void getCost(Node node){
        // Gcost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // Hcost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        //F cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search(){

        while(!goalreached && step < 500){

            int col = currentNode.col;
            int row = currentNode.row;

            //check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //open the node above
            if(row - 1 >= 0){
                openNode(node[col][row - 1]);
            }
            //open the node to the left
            if(col - 1 >= 0 ){
                openNode(node[col - 1][row]);
            }
            //open the node below
            if(row + 1 < gp.maxWorldRow){
                openNode(node[col][row + 1]);
            }
            //open the node to the right
            if(col + 1 < gp.maxWorldCol){
                 openNode(node[col + 1][row]);
            }

            //find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++){
                //check if the node's fcost is better
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // if f cost is equal check the g cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                        
                    }
                }
            }
            //if there's no node in the openList we end the loop
            if(openList.size() == 0){
                break;
            }
            //after the loop openList[bestNodeIndex] is the next step (= currentNode)
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalreached = true;
                trackThePath();
            }
            
            step++;
        }
       return goalreached;

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
