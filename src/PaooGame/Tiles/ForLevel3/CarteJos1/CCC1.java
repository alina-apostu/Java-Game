package PaooGame.Tiles.ForLevel3.CarteJos1;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class CCC1 extends Tile
{

        public CCC1(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.ccc1, id);
        }
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
