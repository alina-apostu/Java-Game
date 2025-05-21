package PaooGame;

import PaooGame.Items.Hero;

import java.awt.*;

public class ObstacolStg implements CollisionStrategy
{
    public ObstacolStg() {
        System.out.println("WallCollision strategy created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds)
    {
        Rectangle heroBounds = hero.getBounds();
        //boolean isssOnTile = false;

        float heroLeft = hero.GetX();
        float heroRight = hero.GetX() + heroBounds.width;


        float tileLeft = tileBounds.x;
        float tileRight = tileBounds.x + tileBounds.width;



        // Verificare coliziune laterală
        if (!hero.isOnTile() && heroBounds.intersects(tileBounds))
       {
           if ((hero.getisJumping()==true) || (hero.getisFalling()==true)) {
               return;
           }


            // Coliziune din stânga (se lovește de partea dreaptă a tile-ului)
             if (heroLeft < tileRight && heroRight > tileRight) {
                // Repoziționează eroul imediat în dreapta tile-ului
                hero.SetX(tileRight);
                System.out.println("Coliziune pe stânga (cu dreapta tile-ului)");
            }
        }





    }
}
