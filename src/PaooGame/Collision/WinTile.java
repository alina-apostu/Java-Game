package PaooGame.Collision;

import PaooGame.DataBase.DataBaseManager;
import PaooGame.Game;
import PaooGame.Items.Hero;
import PaooGame.Items.Character;
import PaooGame.PublicGameData;

import javax.swing.*;
import java.awt.*;



public class WinTile implements CollisionStrategy {

    private boolean triggered = false; // ne asigurăm că se execută o singură dată

    public WinTile() {
        System.out.println("Finish tile collision created");
    }

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        if (triggered) return;

        if (hero.getBounds().intersects(tileBounds)) {
            PublicGameData.score+=1000;
            triggered = true;

            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);

            JOptionPane.showMessageDialog(null,
                    "Congratulations!\nYou finished the game!!\nYou earned 1000 points!",
                    "Victory",
                    JOptionPane.INFORMATION_MESSAGE);


            //salvare nume jucator si scor in baza de date
            DataBaseManager db = DataBaseManager.getInstance();
            db.connect();
            //db.createTable(); //

            String playerName = PublicGameData.playerName;
            int score = PublicGameData.score;
            String character = PublicGameData.characterType;
            int level = PublicGameData.currentLevel;


            if (playerName != null && character != null) {
                db.savePlayer(playerName, score, level, character,(int) hero.GetX(),(int) hero.GetY(), hero.getPower("minge de foc"), hero.getPower("zbor"), hero.getPower("invizibilitate"), hero.getLives());
            }

            if(level == 3)
                db.markLevel3Finished(playerName);

            boolean done = false;
            while (!done) {
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Congratulations!\nYou finished the game!!\nYour score is: "+PublicGameData.score+"\nWhat do you want to do next?",
                        "Victory",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new Object[]{"Exit", "Show Top 3"},
                        "Exit"
                );

                if (choice == 0) { // Exit
                    db.close();
                    System.exit(0);
                } else if (choice == 1) { // Show Top 3
                    java.util.List<String> topPlayers = db.getTopPlayers(3);
                    StringBuilder topMsg = new StringBuilder("Top 3 Players:\n");
                    for (String player : topPlayers) {
                        topMsg.append(player).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, topMsg.toString(), "Leaderboard", JOptionPane.PLAIN_MESSAGE);
                } else {
                    done = true; // în caz că se închide fereastra
                }
            }

            db.close();
        }
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character) {
        //nu se implementeaza nimic aici
    }
}
