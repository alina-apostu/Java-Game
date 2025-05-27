package PaooGame.Collision;

import PaooGame.Game;
import PaooGame.Items.Hero;

import PaooGame.Items.Character;
import PaooGame.PublicGameData;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class PotionTile implements CollisionStrategy {

    private Set<Point> triggeredTiles = new HashSet<>();

    public PotionTile()
    {
        System.out.println("potion tile collision created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        Point tilePos = new Point(tileBounds.x / 32, tileBounds.y / 32);
        System.out.println("s a apelat handle pt potion");

        if (!triggeredTiles.contains(tilePos)) {

            PublicGameData.subbScore(50);// de fiecare data cand personajul atinge o potiune, scorul scade
            hero.loseLife();//eroul pierde o viata de fiecare data cand se intalneste cu o potiune

            triggeredTiles.add(tilePos);


            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);

            JOptionPane.showMessageDialog(null,

                    "You have discovered a deadly potion!\nYou have lost one life.",
                    "Magic Potion",

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
