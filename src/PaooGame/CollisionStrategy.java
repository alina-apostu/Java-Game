package PaooGame;

import PaooGame.Items.Hero;

import java.awt.*;

public interface CollisionStrategy
{
    void handleCollision(Hero hero, Rectangle tileBounds);
}
