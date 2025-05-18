package PaooGame.Collision;

import PaooGame.Items.Hero;
import PaooGame.Items.Character;

import java.awt.*;

public interface CollisionStrategy
{
    void handleCollisionTile(Hero hero, Rectangle tileBounds);
    void handleCollisionCharacter(Hero hero, Character character);
}
