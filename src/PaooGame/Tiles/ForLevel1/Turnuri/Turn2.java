package PaooGame.Tiles.ForLevel1.Turnuri;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Turn2 extends Tile
{
    public Turn2(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.turn2, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
