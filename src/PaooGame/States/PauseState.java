package PaooGame.States;

import PaooGame.RefLinks;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PauseState extends State {
    private Rectangle resumeButton;
    private Rectangle menuButton;
    private Rectangle exitButton;

    private State previousState; // pentru a reveni înapoi

    public PauseState(RefLinks refLink, State previousState) {
        super(refLink);
        this.previousState = previousState;

        int width = 200;
        int height = 50;
        int x = refLink.GetWidth() / 2 - width / 2;
        int startY = 200;
        int spacing = 60;

        resumeButton = new Rectangle(x, startY, width, height);
        menuButton = new Rectangle(x, startY + spacing, width, height);
        exitButton = new Rectangle(x, startY + 2 * spacing, width, height);
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
        g.drawString(title, refLink.GetWidth()/2 - titleWidth/2, 100);

        drawButton(g, resumeButton, "Resume");
        drawButton(g, menuButton, "Main Menu");
        drawButton(g, exitButton, "Exit");
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

        if (resumeButton.contains(mx, my)) {
            State.SetState(previousState); // revine în joc
        } else if (menuButton.contains(mx, my)) {
            State.SetState(new MenuState(refLink));
        } else if (exitButton.contains(mx, my)) {
            System.exit(0);
        }
    }
}
