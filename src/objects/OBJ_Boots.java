package objects;

import main.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class OBJ_Boots extends SuperObject{

    GamePanel gp;

    public OBJ_Boots(GamePanel gp){

        name = "boots";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
            utilityTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
