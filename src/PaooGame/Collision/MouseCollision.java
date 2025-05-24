package PaooGame.Collision;

import PaooGame.Game;
import PaooGame.Items.*;
import PaooGame.Items.Character;
import PaooGame.Items.NPC.Mouse;

import javax.swing.*;
import java.awt.*;

public class MouseCollision implements CollisionStrategy {
    private String powerMessage = "";
    private boolean isMessageDisplayed = false;

    @Override
    public void handleCollisionTile(Hero hero, Rectangle tileBounds) {
        // nu avem nevoie de ea aici
    }

    @Override
    public void handleCollisionCharacter(Hero hero, Character character)
    {
        Mouse mouse = (Mouse) character;
        if (mouse.isConsumed() == false)
        {
            String color = mouse.getCurrentColor();

            switch (color)
            {
                case "purple":
                    hero.setPower("minge de foc");
                    powerMessage = "You have gained the Fireball power!";
                    break;
                case "green":
                    hero.setPower("zbor");
                    powerMessage = "You can now fly over obstacles!";
                    break;
                case "blue":
                    hero.setPower("invizibilitate");
                    powerMessage = "You are invisible now. Use it wisely!";
                    break;
            }
            mouse.setConsumed(true);

            Game.isPaused = true;
            hero.SetYMove(0);
            hero.SetXMove(0);

            showPowerMessage(hero, mouse);
        }
    }


    public void showPowerMessage(Hero hero, Mouse mouse)
    {
        if(isMessageDisplayed == false)
        {
            Game.isPaused = true;
            // cream un dialog nou
            JDialog dialog = new JDialog();
            dialog.setTitle("Power Gained");
            dialog.setModal(true); // adica utilizatorul trebuie sa inchida aceasta fereastra inainte de a reveni la joc
            dialog.setLayout(new BorderLayout());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // la inchidere dialocul sa fie inchis nu toata aplicatia
            dialog.setSize(300,150); // dimensiunea ferestrei
            dialog.setLocationRelativeTo(null);

            JLabel messageLabel = new JLabel(powerMessage, SwingConstants.CENTER); // cream o eticheta care contine mesajul si il centram orizontal
            dialog.add(messageLabel, BorderLayout.CENTER); // adaugam eticheta cu mesajul in mijlocul ferestrei

            JButton continueButton = new JButton("Continue"); // cream un buton pe care scrie continue
            continueButton.addActionListener(e ->
            {
                dialog.dispose();
                isMessageDisplayed = false;
                Game.isPaused = false;
                Game.getRefLinks().GetKeyManager().resetKeys();

                mouse.showSparkle(); // afisam sclipiciul
            });

            // panou pentru buton
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(continueButton);
            dialog.add(buttonPanel,BorderLayout.SOUTH); // si il adaugam in sudul ferestrei

            isMessageDisplayed = true;
            dialog.setVisible(true);
        }
    }
}
