package PaooGame;

import PaooGame.Items.Hero;

import java.awt.*;

public class ObstacolStgDrtUp implements CollisionStrategy
{
    public ObstacolStgDrtUp() {
        System.out.println("WallCollision strategy created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds)
    {
        Rectangle heroBounds = hero.getBounds();
        //boolean isssOnTile = false;

        float heroLeft = hero.GetX();
        float heroRight = hero.GetX() + heroBounds.width;
        float heroBottom = hero.GetY() + heroBounds.height;
        float heroTop = hero.GetY();

        float tileLeft = tileBounds.x;
        float tileRight = tileBounds.x + tileBounds.width;
        float tileTop = tileBounds.y;
        float tileBottom = tileBounds.y + tileBounds.height;

        System.out.println("hero.getJumpSpeed() = " + hero.getJumpSpeed());
        System.out.println("heroLeft = " + heroLeft + " "+ "heroRight = " + heroRight+ "tilLeft = "+tileLeft +"tileRight= " + tileRight);



//(SUS = să stea pe tile)
        // ===================== COLIZIUNE PE VERTICALĂ (deasupra tile-ului) =====================
        /*if (hero.getJumpSpeed() >= 0 &&
                heroBottom <= tileTop &&
                heroBottom + hero.getJumpSpeed() >= tileTop &&
                heroBounds.intersects(tileBounds)) {

            // Poziționează exact pe tile
            float newY = tileTop - heroBounds.height;
            hero.SetY(newY);
            hero.setJumpSpeed(0);
            hero.setFalling(false);  // eroul NU mai cade!
            hero.setisJumping(false);

            hero.setGroundLevelY(newY);  // Salvează poziția "podelei" pe care stă

            System.out.println(" Eroul este pe tile.");
        }*/

  //var 2
        /*
        if (hero.getJumpSpeed() >= 0 && heroBottom <= tileTop &&
                heroBottom + hero.getJumpSpeed() >= tileTop &&
                heroBounds.intersects(tileBounds))
        {
            System.out.println(" Eroul este pe tile.");

            float oldY=hero.GetY();
            float newY = tileTop - heroBounds.height-14;
            hero.SetY(newY);
            hero.setJumpSpeed(0);
            hero.setFalling(false);
            hero.setisJumping(false);
            //hero.setGroundLevelY(newY);
            //hero.setGroundLevelY(oldY);
            /*if(heroLeft>tileRight && heroRight>tileRight)
            {
                hero.setisJumping(false);
                hero.setFalling(true);
                hero.SetY( hero.GetY()+ hero.GetSpeed()); // cade

            }*/

        //}
//var buna


        if (hero.getJumpSpeed() >= 0 &&
                heroBottom <= tileTop &&
                heroBottom + hero.getJumpSpeed() >= tileTop )
        {

            // cât de mult se suprapun pe orizontală
            float overlapWidth = Math.min(heroRight, tileRight) - Math.max(heroLeft, tileLeft);
            float minRequiredOverlap = heroBounds.width * 0.3f;

            if (overlapWidth >= minRequiredOverlap) {
                System.out.println("Eroul este complet pe tile (sprijinit corespunzător)");

                float newY = tileTop - heroBounds.height-hero.getBoundsXOffset();;
                hero.SetY(newY);
                hero.setJumpSpeed(0);
                hero.setFalling(false);
                hero.setisJumping(false);
                //isssOnTile = true;
                hero.setOnTile(true);
                //hero.setGroundLevelY(newY);
            } else if(hero.GetY()==hero.GetGroundLevelY())
            {

                System.out.println("cadere");
                // Dacă nu este suficient sprijinit, continuă să cadă
                hero.setFalling(true);
                hero.setisJumping(false);
                hero.setOnTile(false);
                float newY = tileTop - heroBounds.height-hero.getBoundsXOffset();
                hero.SetY(newY);

            }
        }



        //
        // coliziune cu tavanul
       else if (
                heroTop <= tileBottom  &&
                heroBottom > tileBottom &&
                heroBounds.intersects(tileBounds)) {

            System.out.println(" Coliziune cu TAVAN detectată corect.");
            float oldY = hero.GetY();
            float newY = tileBottom - hero.getBoundsYOffset()+1;
            hero.SetY(newY);
            hero.setJumpSpeed(0.5f); // începe să cadă
            hero.setisJumping(false);
            hero.setFalling(true);
            hero.SetY( hero.GetY()+ hero.GetSpeed()); // cade
            //hero.SetSpeed(hero.GetSpeed() +hero.GetGravity()); //actioneaza gravitatia
        }


        // Verificare coliziune laterală
       else if (!hero.isOnTile() && heroBounds.intersects(tileBounds))
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



       /* if (hero.isOnTile()==false) {
            // Dacă eroul NU sare, atunci începe să cadă
            if (hero.getisJumping()==false && hero.getisFalling()==false) {
                hero.setFalling(true);
            }
        } else {
            // Dacă este pe tile, clar nu cade
            hero.setFalling(false);
        }*/

        /*if (!heroBounds.intersects(tileBounds)) {
            hero.setOnTile(false);
            hero.setFalling(true);// dacă niciun tile nu se atinge, marchează că nu e pe nimic
        }*/



    }
}
