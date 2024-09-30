package objects;

import entity.*;
import main.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {

        super(gp);
        name = "heart";

        image = setup("/objects/heart_full");
        image2 = setup("/objects/heart_half");
        image3 = setup("/objects/heart_blank");

    }


}
