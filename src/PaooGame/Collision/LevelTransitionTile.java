package PaooGame.Collision;


import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Maps.Map;
import PaooGame.PublicGameData;
import PaooGame.States.PlayState;
import PaooGame.States.State;

import java.awt.Rectangle;

public class LevelTransitionTile implements CollisionStrategy {
    private boolean levelChanged = false;

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        if (!levelChanged && hero.getBounds().intersects(tileBounds)) {
            levelChanged = true;

            int CurrentLevel = hero.GetReflink().GetMap().getLevelIndex();
            int nextLevel = CurrentLevel + 1;

            if (nextLevel <= 3) {
                PublicGameData.currentLevel=nextLevel;
                if(hero.getLives()==5)
                {
                    PublicGameData.addScore(200);
                   //mesaj de inchere nivel fara a pierde vieti
                    javax.swing.JOptionPane.showMessageDialog(null,
                            "Congratulations! You completed level " + CurrentLevel +
                                    " without losing a single life!\nYou earned 200 bonus points!",
                            "Perfect Run!",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    hero.GetReflink().GetKeyManager().resetKeys(); //resetam pentru a nu ramane blocata tasta pentru nivelul urmator
                }
                else{
                    //mesaj simplu de incheiere nivel

                    javax.swing.JOptionPane.showMessageDialog(null,
                            "Congratulations! You completed level " + CurrentLevel +"!",
                            "Level Completed!",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    hero.GetReflink().GetKeyManager().resetKeys(); //resetam pentru a nu ramane blocata tasta pentru nivelul urmator


                }
                // se seteaza noua hartă
                System.out.println("nextLevel="+nextLevel);
                hero.GetReflink().SetMap(new Map(hero.GetReflink(), nextLevel));


                PublicGameData.loadedFromSave = false;
                PlayState nextState = new PlayState(hero.GetReflink(), hero.getCharacterType(), nextLevel ,hero.getPlayerName());
                // Schimbăm starea curentă
                State.SetState(nextState);
            } else {
                System.out.println("Ai terminat toate nivelurile!");


            }
        }
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {

    }
}
