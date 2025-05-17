package PaooGame;

import PaooGame.Items.Hero;
import PaooGame.Items.Character;

import javax.swing.*;
import java.awt.*;

public class PotionTile implements CollisionStrategy {

    public PotionTile() {
        System.out.println("book tile collision created");
    }

    private boolean messageShown = false; // se afișează o singură dată

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        if (!messageShown) {
            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);

            JOptionPane.showMessageDialog(null,
                    "You have discovered a deadly potion!\\nYou have lost one life.",
                    "Magic Evil Book",
                    JOptionPane.INFORMATION_MESSAGE);

            Game.isPaused = false;
            Game.getRefLinks().GetKeyManager().resetKeys();

            messageShown = true;
        }
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {

    }
}
