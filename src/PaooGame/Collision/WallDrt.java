package PaooGame.Collision;

import PaooGame.Items.Character;
import PaooGame.Items.Hero;

import java.awt.*;

public class WallDrt implements CollisionStrategy
{
    public WallDrt() {
        System.out.println("WallCollision DRT strategy created");
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


            // Coliziune din stânga (se lovește de partea dreaptă a tile-ului)
           if (heroLeft < tileRight && heroRight > tileRight) {
                // Repoziționează eroul imediat în dreapta tile-ului
                hero.SetX(tileRight);
                System.out.println("Coliziune pe stânga (cu dreapta tile-ului)");
            }
        }

    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {

    }
}
