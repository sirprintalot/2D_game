package entity;

import main.*;

public class Projectile extends Entity {

    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean isAlive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.isAlive = isAlive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        //Check which entity shoots the projectile

        if(user == gp.player){

            int mosnterIndex = gp.cCheck.checkEntity(this, gp.monster);
            if(mosnterIndex != 999){
                gp.player.damageMonster(mosnterIndex, attack);
                isAlive = false;
            }
            
        }
        if(user != gp.player){
            
              boolean contactPlayer = gp.cCheck.checkPlayer(this);
              if(contactPlayer && !gp.player.invincible){
                  damagePlayer(attack);
                  isAlive = false;
              }
        }

        switch (direction) {
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }

        life--;
        if(life <= 0){
            isAlive = false;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {

                spriteNum = 1;

            }
            spriteCounter = 0;
        }
    }


}
