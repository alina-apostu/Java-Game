package PaooGame.Collision;

import PaooGame.Items.Hero;

import java.awt.*;

public class Floor1 implements CollisionStrategy
{

    public Floor1()
    {
        System.out.println("floor1 tile collision created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        Rectangle heroBounds = hero.getBounds();

        float heroBottom = hero.GetY() + hero.getBounds().height;
        float heroTop = hero.GetY();
        float tileTop = tileBounds.y;
        float tileBottom = tileBounds.y + tileBounds.height;



        System.out.println("hero.getJumpSpeed() = " + hero.getJumpSpeed());
        System.out.println("heroTop = " + heroTop + " "+ "heroBottom = " + heroBottom+ "tiletop = " +"tileTop= " + tileBottom);


        // cadere pe podea
        /*if (hero.getJumpSpeed() >= 0 && heroBottom > tileTop && heroTop < tileTop) {
            hero.SetY(tileTop - heroBounds.height); // poziționează eroul exact deasupra tile-ului
            hero.setJumpSpeed(0);
            hero.setFalling(false);
            hero.setisJumping(false);
            hero.setGroundLevelY(hero.GetY()); // actualizează nivelul solului
        }*/

       if (hero.getJumpSpeed() >= 0 && heroBottom <= tileTop &&
                heroBottom + hero.getJumpSpeed() >= tileTop &&
                heroBounds.intersects(tileBounds)) {

            float oldY=hero.GetY();
            float newY = tileTop - heroBounds.height-14; // dacă GetY e vârful colliderului
            hero.SetY(newY);
            hero.setJumpSpeed(0);
            hero.setFalling(false);
            hero.setisJumping(false);
            //hero.setGroundLevelY(newY);
            hero.setGroundLevelY(oldY);


        }


        //tavan

        if (hero.getJumpSpeed() < 0 && heroTop < tileBottom && heroBottom > tileBottom && heroTop + hero.getJumpSpeed() <= tileBottom) {
            float newY = tileBottom -hero.getBoundsYOffset();

            System.out.println("Tavan: hero y before = " + hero.GetY() + ", setting to = " + newY);

            hero.SetY(newY);
            hero.setJumpSpeed(0.5f);  // dă un impuls mic în jos să înceapă să cadă
            hero.setisJumping(false);
            hero.setFalling(true);
        }






    }



}
