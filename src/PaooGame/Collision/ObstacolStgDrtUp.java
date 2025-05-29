package PaooGame.Collision;

import PaooGame.Items.Character;
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
        boolean isOnTileNow = false;

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

                float newY = tileTop - heroBounds.height-22;;
                hero.SetY(newY);
                hero.setJumpSpeed(0);
                hero.setFalling(false);
                hero.setisJumping(false);
                isOnTileNow= true;
                hero.setOnTile(true);


            } else if(hero.GetY()==hero.GetGroundLevelY())
            {

                System.out.println("cadere");
                // Dacă nu este suficient sprijinit, continuă să cadă
                hero.setFalling(true);
                hero.setisJumping(false);
                hero.setOnTile(false);
                float newY = tileTop - heroBounds.height;
                hero.SetY(newY);


            }
        }

        /*if (heroBottom <= tileTop && heroBottom + hero.getJumpSpeed() >= tileTop) {
            // Suprapunere orizontală
            if (heroRight > tileLeft && heroLeft < tileRight) {
                float overlapWidth = Math.min(heroRight, tileRight) - Math.max(heroLeft, tileLeft);
                float minRequiredOverlap = heroBounds.width * 0.3f;

                if (hero.getisJumping() && hero.getJumpSpeed() >= 0) {
                    // === CAZ 1: Eroul sare și aterizează pe tile ===
                    if (overlapWidth >= minRequiredOverlap) {
                        System.out.println("Eroul sare și se sprijină pe tile.");
                        float newY = tileTop - heroBounds.height - 22; // evită suprapunerea
                        hero.SetY(newY);
                        hero.setJumpSpeed(0);
                        hero.setFalling(false);
                        hero.setisJumping(false);
                        hero.setOnTile(true);
                        isOnTileNow = true;
                    }

                } else if (!hero.getisJumping()) {
                    // === CAZ 2: Eroul NU sare și nu e suficient sprijinit ===
                    if (overlapWidth < minRequiredOverlap) {
                        System.out.println("Eroul nu sare și nu e sprijinit – începe să cadă până la podea.");
                        hero.setFalling(true);
                        hero.setisJumping(false);
                        hero.setOnTile(false);

                        // Lasă eroul să cadă liber până la nivelul solului
                        float groundY = hero.getGroundLevelY();
                        hero.SetY(groundY);
                    } else {
                        // E sprijinit suficient, chiar dacă nu a sărit
                        float newY = tileTop - heroBounds.height - 22;
                        hero.SetY(newY);
                        hero.setJumpSpeed(0);
                        hero.setFalling(false);
                        hero.setisJumping(false);
                        hero.setOnTile(true);
                        isOnTileNow = true;
                    }
                }
            }
        }*/





        //
        // coliziune cu tavanul
       else if (
                heroTop <= tileBottom  &&
                heroBottom > tileBottom &&
                heroBounds.intersects(tileBounds)) {

            //float overlapWidth = Math.min(heroRight, tileRight) - Math.max(heroLeft, tileLeft);
            //float minRequiredOverlap = heroBounds.width * 0.3f; //cat de mult se atinge eroul de tavan

            //if (overlapWidth >= minRequiredOverlap && heroBounds.intersects(tileBounds)) {

                System.out.println(" Coliziune cu TAVAN detectată corect.");
                float oldY = hero.GetY();
                float newY = tileBottom - hero.getBoundsYOffset();
                //hero.SetY(newY);
                hero.setJumpSpeed(0.5f); // începe să cadă
                hero.setisJumping(false);
                hero.setFalling(true);
                hero.SetY(hero.GetY() + hero.GetSpeed()); // cade
                //hero.SetSpeed(hero.GetSpeed() +hero.GetGravity()); //actioneaza gravitatia


        }


        // Verificare coliziune laterală
       else if (!isOnTileNow && !hero.getisFalling() && !hero.getisJumping())
       {
           /*if ((hero.getisJumping()==true) || (hero.getisFalling()==true)) {
               return;
           }*/
            // Coliziune din dreapta (se lovește de partea stângă a tile-ului)
            if (heroRight > tileLeft && heroLeft < tileLeft) {
                // Repoziționează eroul imediat în stânga tile-ului
                hero.SetX(tileLeft - heroBounds.width-14);
                System.out.println("Coliziune pe dreapta (cu stânga tile-ului)");
            }

            // Coliziune din stânga (se lovește de partea dreaptă a tile-ului)
            else if (heroLeft < tileRight && heroRight > tileRight) {
                // Repoziționează eroul imediat în dreapta tile-ului
                hero.SetX(tileRight-14);
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

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {

    }
}
