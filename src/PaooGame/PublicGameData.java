package PaooGame;

import PaooGame.States.PlayState;
import PaooGame.States.State;

import javax.swing.*;

public class PublicGameData
{
    public static int score = 0;
    public static String playerName;
    public static String characterType;
    public static int currentLevel = 1;
    public static float playerPosX = 50;
    public static float playerPosY = 108;
    public static int fireballPower = 0;
    public static int flyPower = 0;
    public static int invisibilityPower = 0;
    public static int lives = 5;
    public static boolean loadedFromSave = false;
    public static RefLinks refLinks;


    public static void addScore(int points)
    {
        PublicGameData.score+= points;
    }

    public static void subbScore(int amount)
    {
        score -= amount;
        if (score < 0) //daca scorul devine <0, atunci jucul se va seta la inceputul nivelului actual
        {
            score = 0;
            JOptionPane.showMessageDialog(null, "You lost!\nYour score dropped to 0!\nRestarting current level...");

            //
            State.SetState(new PlayState(refLinks, characterType,currentLevel, playerName));
        }
    }
}
