package PaooGame.Collision;

import PaooGame.Game;
import PaooGame.Items.Character;
import PaooGame.Items.Hero;
import PaooGame.Items.ShadowSpider;

import javax.swing.*;
import java.awt.*;

public class ShadowSpiderCollision implements CollisionStrategy
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
        ShadowSpider shadowSpider = (ShadowSpider)character;
        Rectangle webBounds = shadowSpider.getWebBounds();

        if (webBounds != null && shadowSpider.isWebFullyOpened() == true)
        {
            Game.isPaused = true;
            hero.SetXMove(0);
            hero.SetYMove(0);
            showWebCaughtMessage();
        }
    }

    private void showWebCaughtMessage()
    {
        if (isMessageDisplayed == false) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Caught in the Shadow Spider's web!");
            dialog.setModal(true);
            dialog.setLayout(new BorderLayout());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setSize(400, 150);
            dialog.setLocationRelativeTo(null);

            JLabel messageLabel = new JLabel("<html>You've been caught by the Shadow Spider's web!<br>You lost all your lives!</html>", SwingConstants.CENTER);
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
