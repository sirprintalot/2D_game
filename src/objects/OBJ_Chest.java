package objects;

import main.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class OBJ_Chest extends SuperObject{

    GamePanel gp;

    public OBJ_Chest(GamePanel gp){

        name = "chest";
        try{
             image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
             utilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
