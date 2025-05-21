package PaooGame.Collision;

import PaooGame.Items.Hero;

import java.awt.*;

public class TavanTile implements CollisionStrategy
{
    public TavanTile() {
        System.out.println("TavanPodea collision strategy created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds)
    {
        Rectangle heroBounds = hero.getBounds();

        float heroLeft = hero.GetX();
        float heroRight = hero.GetX() + heroBounds.width;
        float heroBottom = hero.GetY() + heroBounds.height;
        float heroTop = hero.GetY();

        float tileLeft = tileBounds.x;
        float tileRight = tileBounds.x + tileBounds.width;
        float tileTop = tileBounds.y;
        float tileBottom = tileBounds.y + tileBounds.height;

        //System.out.println("hero.getJumpSpeed() = " + hero.getJumpSpeed());
        //System.out.println("heroTop = " + heroTop + " "+ "heroBottom = " + heroBottom+ "tiletop = " +"tileBottom= " + tileBottom);


        // COLIZIUNE PE VERTICALĂ (SUS = să stea pe tile)

        /*if (hero.getJumpSpeed() >= 0 && heroBottom <= tileTop &&
                heroBottom + hero.getJumpSpeed() >= tileTop &&
                heroBounds.intersects(tileBounds))
        {

            float oldY=hero.GetY();
            float newY = tileTop - heroBounds.height-20; //
            hero.SetY(newY);
            hero.setJumpSpeed(0);
            hero.setFalling(true);
            hero.setisJumping(false);
            hero.setGroundLevelY(newY);
            //hero.setGroundLevelY(oldY);


        }*/

        // COLIZIUNE PE VERTICALĂ (JOS = tavan)
        // verifică dacă eroul urcă și atinge partea de jos a tile-ului
         if (heroTop <= tileBottom  &&
                        heroBottom > tileBottom &&
                        heroBounds.intersects(tileBounds))
        {

            System.out.println(" Coliziune cu TAVAN detectată corect.");
            float oldY = hero.GetY();
            //float newY = tileBottom - hero.getBoundsYOffset();
            float newY = tileBottom ;
            hero.SetY(newY);
            hero.setJumpSpeed(0.5f); // începe să cadă
            hero.setisJumping(false);
            hero.setFalling(true);
            hero.SetY( hero.GetY()+ hero.GetSpeed()); // cade
            //hero.SetSpeed(hero.GetSpeed() +hero.GetGravity());//actioneaza gravitatea
        }



    }
}
