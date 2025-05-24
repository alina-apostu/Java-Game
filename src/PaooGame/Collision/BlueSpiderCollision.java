package PaooGame.Collision;

import PaooGame.Game;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Items.SpiderBlue;

import javax.swing.*;
import java.awt.*;

public class BlueSpiderCollision implements CollisionStrategy
{
    private boolean isMessageDisplayed = false;

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds)
    {
        // nu se foloseste aici
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {
        SpiderBlue blueSpider = (SpiderBlue)character;
        Game.isPaused = true;
        hero.SetYMove(0);
        hero.SetXMove(0);
        showMessage();
        hero.setWasStungByBlueSpider(blueSpider);

    }

    public void showMessage()
    {
        if (isMessageDisplayed == false)
        {
            JDialog dialog = new JDialog();
            dialog.setTitle("Stung by a blue spider!");
            dialog.setModal(true);
            dialog.setLayout(new BorderLayout());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(400, 150);
            dialog.setLocationRelativeTo(null);

            JLabel messageLabel = new JLabel("<html>You've been stung by a blue spider!<br>You lost one life!</html>", SwingConstants.CENTER);
            dialog.add(messageLabel, BorderLayout.CENTER);

            JButton continueButton = new JButton("Continue");
            continueButton.addActionListener(e ->
            {
                dialog.dispose();
                isMessageDisplayed = false;
                Game.isPaused = false;
                Game.getRefLinks().GetKeyManager().resetKeys();
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(continueButton);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            isMessageDisplayed = true;
            dialog.setVisible(true);
        }
    }
}
