package PaooGame;

import PaooGame.Items.Hero;

import java.awt.*;

public interface CollisionStrategy
{
    void handleCollisionTile(Hero hero, Rectangle tileBounds);
}
