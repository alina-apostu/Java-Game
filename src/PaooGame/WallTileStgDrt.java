package PaooGame;

import PaooGame.Items.Hero;

import java.awt.*;

public class WallTileStgDrt implements CollisionStrategy
{
    public WallTileStgDrt() {
        System.out.println("WallCollision strategy created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds)
    {
        Rectangle heroBounds = hero.getBounds();

        float heroLeft = hero.GetX();
        float heroRight = hero.GetX() + heroBounds.width;
        float tileLeft = tileBounds.x;
        float tileRight = tileBounds.x + tileBounds.width;

        if (!hero.isOnTile() && heroBounds.intersects(tileBounds))
        {
            if ((hero.getisJumping()==true) || (hero.getisFalling()==true)) {
                return;
            }
            // Coliziune din dreapta (se lovește de partea stângă a tile-ului)
            if (heroRight > tileLeft && heroLeft < tileLeft) {
                // Repoziționează eroul imediat în stânga tile-ului
                hero.SetX(tileLeft - heroBounds.width);
                System.out.println("Coliziune pe dreapta (cu stânga tile-ului)");
            }

            // Coliziune din stânga (se lovește de partea dreaptă a tile-ului)
            else if (heroLeft < tileRight && heroRight > tileRight) {
                // Repoziționează eroul imediat în dreapta tile-ului
                hero.SetX(tileRight);
                System.out.println("Coliziune pe stânga (cu dreapta tile-ului)");
            }
        }

    }
}
