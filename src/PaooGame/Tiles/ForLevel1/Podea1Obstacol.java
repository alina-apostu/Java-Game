package PaooGame.Tiles.ForLevel1;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Podea1Obstacol extends Tile
{

        public Podea1Obstacol(int id)
        {
            /// Apel al constructorului clasei de baza
            super(Assets.podea1, id);
        }


      @Override
        public boolean IsSolid()
    {
        return true;
    }

}
