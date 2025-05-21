package PaooGame.Tiles.ForLevel1.Turnuri;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Turn3 extends Tile
{
    public Turn3(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.turn3, id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }
}
