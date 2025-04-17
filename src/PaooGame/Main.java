package PaooGame;

import PaooGame.Maps.Map;
import PaooGame.GameWindow.GameWindow;

public class Main
{
    public static void main(String[] args)
    {
        Game paooGame = new Game("PaooGame", 800, 2*6*32);
        //Game paooGame = new Game("PaooGame", 800, 700);
        paooGame.StartGame();
    }
}
