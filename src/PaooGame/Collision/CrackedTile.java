package PaooGame.Collision;

import PaooGame.Game;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.PublicGameData;
import PaooGame.Tiles.Tile;

import javax.swing.*;
import java.awt.*;

public class CrackedTile implements CollisionStrategy {
    //private boolean activated = false;

    public CrackedTile() {
        System.out.println("CrackedTile strategy created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        Point tilePosition = new Point(tileBounds.x / Tile.TILE_WIDTH, tileBounds.y / Tile.TILE_HEIGHT);

        if (!hero.hasCollidedWithCrackedTile(tilePosition) && !hero.isCrackedTileCollisionThisFrame()) {

            PublicGameData.subbScore(50);// de fiecare data cand personajul atinge o crapatura, scorul scade
            hero.loseLife();//eroul pierde o viata de fiecare data cand cade in crapatura

            System.out.println("CrackedTile collision !!!!");
            hero.SetY(hero.GetY() + 15);
            hero.setCrackedTileCollisionThisFrame(true);
            hero.markCrackedTileCollision(tilePosition);

            Game.isPaused = true;
            //hero.SetXMove(0);
            //hero.SetYMove(0);

            new javax.swing.Timer(300, e -> {
                JOptionPane.showMessageDialog(
                        null,
                        "You fell into a crack in the floor!\nYou have lost one life.",
                        "Cracked Tile",
                        JOptionPane.INFORMATION_MESSAGE
                );
                Game.getRefLinks().GetKeyManager().resetKeys();
                Game.isPaused = false;
                ((javax.swing.Timer) e.getSource()).stop(); // oprim timerul după o singură execuție
            }).start();

        }
    }


    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {

    }
}
