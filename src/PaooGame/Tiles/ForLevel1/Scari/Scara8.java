package PaooGame.Tiles.ForLevel1.Scari;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

public class Scara8 extends Tile
{
    public Scara8(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.scara8, id);
    }
    @Override
    public boolean IsSolid()
    {
        return true;
    }
}

