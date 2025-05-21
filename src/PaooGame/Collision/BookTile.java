package PaooGame.Collision;

import PaooGame.Game;
import PaooGame.Items.Hero;


import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import PaooGame.Items.Character;

public class BookTile implements CollisionStrategy {
    private Set<Point> triggeredTiles = new HashSet<>();


    public BookTile() {
        System.out.println("book tile collision created");
    }



    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        Point tilePos = new Point(tileBounds.x / 32, tileBounds.y / 32);

        if (!triggeredTiles.contains(tilePos)) {
            triggeredTiles.add(tilePos);
            System.out.println(tilePos.x+" "+tilePos.y);


            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);

            JOptionPane.showMessageDialog(null,
     "You have discovered a deadly book of the spider!\nYou have lost one life.",

                    "Magic Evil Book",
                    JOptionPane.INFORMATION_MESSAGE);

            Game.isPaused = false;
            Game.getRefLinks().GetKeyManager().resetKeys();

        }
    }
   @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {

    }
}

