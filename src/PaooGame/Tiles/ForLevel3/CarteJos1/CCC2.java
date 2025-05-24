package PaooGame.Tiles.ForLevel3.CarteJos1;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class CCC2 extends Tile
{

        public CCC2(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.ccc2, id);
        }
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
