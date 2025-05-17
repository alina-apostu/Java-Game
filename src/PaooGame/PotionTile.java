package PaooGame;

import PaooGame.Items.Hero;

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
}
