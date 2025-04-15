package tile_interactive;

import main.*;

public class IT_MetalPLate extends InteractiveTile {

    GamePanel gp;
    public static final String itName = "Metal Plate";

    public IT_MetalPLate(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/interactive_tiles/metalplate", gp.tileSize, gp.tileSize);
        //destructible = true; not destructible

        name = itName;
        // update the solid area so the player can pass through
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
