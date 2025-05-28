package PaooGame.States;

import PaooGame.DataBase.DataBaseManager;
import PaooGame.Items.Hero;
import PaooGame.PublicGameData;
import PaooGame.RefLinks;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PauseState extends State {
    private Rectangle resumeButton;
    private Rectangle menuButton;
    private Rectangle exitButton;
    private Rectangle saveButton;

    private State previousState; // pentru a reveni înapoi
    private Hero hero;

    public PauseState(RefLinks refLink, State previousState, Hero hero) {
        super(refLink);
        this.previousState = previousState;
        this.hero = hero;

        int width = 200;
        int height = 50;
        int x = refLink.GetWidth() / 2 - width / 2;
        int startY = 130;
        int spacing = 60;

        resumeButton = new Rectangle(x, startY, width, height);
        saveButton = new Rectangle(x, startY + spacing, width, height);
        menuButton = new Rectangle(x,startY + 2 * spacing, width, height);
        exitButton = new Rectangle(x, startY + 3 * spacing, width, height);
    }

    @Override
    public void Update() {
        // nu actualizăm nimic activ din joc cât timp e în pauză
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(new Color(28, 28, 45)); // dark purple
        g.fillRect(0, 0, refLink.GetWidth(), refLink.GetHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia", Font.BOLD, 36));
        String title = "Game Paused";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, refLink.GetWidth()/2 - titleWidth/2, 80);

        drawButton(g, resumeButton, "RESUME");
        drawButton(g, saveButton, "SAVE");
        drawButton(g, menuButton, "MAIN MENU");
        drawButton(g, exitButton, "EXIT");
    }

    private void drawButton(Graphics g, Rectangle r, String text) {
        g.setColor(new Color(92, 84, 112));
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia", Font.BOLD, 20));
        int textWidth = g.getFontMetrics().stringWidth(text);
        int textHeight = g.getFontMetrics().getAscent();
        g.drawString(text, r.x + (r.width - textWidth) / 2, r.y + (r.height + textHeight) / 2 - 5);
    }

    public void MouseClick(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (resumeButton.contains(mx, my))
        {
            State.SetState(previousState); // revine în joc
        }
        else if (menuButton.contains(mx, my))
        {
            State.SetState(new MenuState(refLink));
        }
        else if (exitButton.contains(mx, my))
        {
            System.exit(0);
        }
        else if(saveButton.contains(mx,my))
        {
            DataBaseManager.getInstance().savePlayer(PublicGameData.playerName, PublicGameData.score, PublicGameData.currentLevel, PublicGameData.characterType, (int) hero.GetX(), (int) hero.GetY(), hero.getPower("minge de foc"), hero.getPower("zbor"), hero.getPower("invizibilitate"), hero.getLives());
            System.out.println("Joc salvat manual!");
        }
    }
}
