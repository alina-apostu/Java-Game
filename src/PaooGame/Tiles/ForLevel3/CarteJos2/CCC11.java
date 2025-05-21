package PaooGame.Tiles.ForLevel3.CarteJos2;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class CCC11 extends Tile
{

        public CCC11(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.ccc11, id);
        }
    public boolean IsSolid()
    {
        return true;
    }

}
