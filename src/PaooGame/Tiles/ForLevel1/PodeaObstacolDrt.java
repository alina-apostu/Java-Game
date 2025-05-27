package PaooGame.Tiles.ForLevel1;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class PodeaObstacolDrt extends Tile
{

        public PodeaObstacolDrt(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.podeadrt, id);
        }


      @Override
        public boolean IsSolid()
    {
        return true;
    }

}
