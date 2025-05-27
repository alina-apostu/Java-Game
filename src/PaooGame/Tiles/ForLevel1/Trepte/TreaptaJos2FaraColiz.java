package PaooGame.Tiles.ForLevel1.Trepte;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class TreaptaJos2FaraColiz extends Tile
{
    public TreaptaJos2FaraColiz(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.treaptaJos2FaraColiz, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
