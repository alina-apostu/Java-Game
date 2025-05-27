package PaooGame.Tiles.ForLevel1.Trepte;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class TreaptaJos extends Tile
{
    public TreaptaJos(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.treaptaJos, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
