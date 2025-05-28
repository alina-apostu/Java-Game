package PaooGame.States;


import PaooGame.DataBase.DataBaseManager;
import PaooGame.PublicGameData;
import PaooGame.RefLinks;
import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane; // pentru mesaje
import javax.swing.ImageIcon; // pentru gif uri

// clasa MenuState implementeaza notiunea de meniu pentru joc
public class MenuState extends State
{
    private Rectangle startButton;
    private Rectangle loadButton;
    private Rectangle exitButton;

    private Rectangle lunaBox;
    private Rectangle freyaBox;
    private Rectangle emberBox;

    private String selectedCharacter = null;

    private Image lunaGif;
    private Image freyaGif;
    private Image emberGif;

    private int levelIndex = 1;//nivelul de inceput

    private String playerName = null;//numele jucatorului



    public MenuState(RefLinks refLink)
    {
        //Apel al constructorului clasei de baza.
        super(refLink);

        // pozitiile pentru butoane
        int buttonWidth = 140;
        int buttonHeight = 40;
        int spacing = 20; // spatiu intre butoane
        int totalWidth = buttonWidth*3 + spacing*2;
        int startX = refLink.GetWidth()/2 - totalWidth/2;
        int buttonY = 320;
        startButton = new Rectangle(startX, buttonY, buttonWidth, buttonHeight);
        loadButton = new Rectangle(startX + buttonWidth + spacing, buttonY, buttonWidth, buttonHeight);
        exitButton = new Rectangle(startX + 2 * (buttonWidth + spacing), buttonY, buttonWidth, buttonHeight);

        // pozitiile pentru cele trei personaje
        int characterWidth = 150;
        int characterHeight = 150;
        int characterSpacing = 50; // spațiu între personaje
        int totalCharactersWidth = 3 * characterWidth + 2 * characterSpacing;
        int charactersStartX = refLink.GetWidth() / 2 - totalCharactersWidth / 2;
        int charactersY = 90;
        lunaBox = new Rectangle(charactersStartX, charactersY, characterWidth, characterHeight);
        freyaBox = new Rectangle(charactersStartX + characterWidth + characterSpacing, charactersY, characterWidth, characterHeight);
        emberBox = new Rectangle(charactersStartX + 2 * (characterWidth + characterSpacing), charactersY, characterWidth, characterHeight);

        // gif-urile personajelor
        lunaGif = new ImageIcon("res/characters_gif/Luna.gif").getImage();
        freyaGif = new ImageIcon("res/characters_gif/Freya.gif").getImage();
        emberGif = new ImageIcon("res/characters_gif/Ember.gif").getImage();

    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        // fundalul ferestrei
        g.setColor(new Color(28, 28, 45)); // dark purple
        g.fillRect(0, 0, refLink.GetWidth(), refLink.GetHeight());

        // titlul jocului
        String title = "The Enchanted Library: Shadow Hunt";
        g.setFont(new Font("Papyrus",Font.BOLD,28));
        int titleWidth = g.getFontMetrics().stringWidth(title);
        int titleX = refLink.GetWidth()/2 - titleWidth/2;
        g.setColor(Color.WHITE);
        g.drawString(title, titleX, 40);

        // Desen gif-uri personaje
        g.drawImage(lunaGif, lunaBox.x, lunaBox.y, lunaBox.width, lunaBox.height, null);
        g.drawImage(freyaGif, freyaBox.x, freyaBox.y, freyaBox.width, freyaBox.height, null);
        g.drawImage(emberGif, emberBox.x, emberBox.y, emberBox.width, emberBox.height, null);

        // Numele personajelor
        g.setColor(Color.WHITE);
        g.setFont(new Font("Georgia",Font.PLAIN,20));
        String name;
        int nameWidth;
        int nameX;
        //Luna
        name="Luna";
        nameWidth = g.getFontMetrics().stringWidth(name);
        nameX = lunaBox.x + (lunaBox.width - nameWidth)/2;
        g.drawString(name, nameX, lunaBox.y + lunaBox.height + 35);
        //Freya
        name="Freya";
        nameWidth = g.getFontMetrics().stringWidth(name);
        nameX = freyaBox.x + (freyaBox.width - nameWidth)/2;
        g.drawString(name, nameX, freyaBox.y + freyaBox.height + 35);
        //Ember
        name="Ember";
        nameWidth = g.getFontMetrics().stringWidth(name);
        nameX = emberBox.x + (emberBox.width - nameWidth)/2;
        g.drawString(name, nameX, emberBox.y + emberBox.height + 35);

        // Evidențiere selecție
        g.setColor(new Color(203, 160, 255));
        if ("Luna".equals(selectedCharacter))
            g.drawRect(lunaBox.x - 5, lunaBox.y - 5, lunaBox.width + 10, lunaBox.height + 10);
        if ("Freya".equals(selectedCharacter))
            g.drawRect(freyaBox.x - 5, freyaBox.y - 5, freyaBox.width + 10, freyaBox.height + 10);
        if ("Ember".equals(selectedCharacter))
            g.drawRect(emberBox.x - 5, emberBox.y - 5, emberBox.width + 10, emberBox.height + 10);

        // butoane
        drawButton(g, startButton, "START");
        drawButton(g, loadButton, "LOAD");
        drawButton(g, exitButton, "EXIT");
    }

    private void drawButton(Graphics g, Rectangle r, String s)
    {
        // culoare de fundal pentru buton
        g.setColor(new Color(92, 84, 112));
        // se deseneaza un dreptunghi plin
        g.fillRect(r.x,r.y,r.width,r.height);
        // culoarea pentru text
        g.setColor(Color.WHITE);
        // fontul pentru text
        g.setFont(new Font("Serif", Font.BOLD,20));
        // punem textul pe buton
        int textWidth = g.getFontMetrics().stringWidth(s);
        int textHeight = g.getFontMetrics().getAscent();
        int textX = r.x + (r.width - textWidth)/2;
        int textY = r.y + (r.height + textHeight)/2;
        g.drawString(s,textX, textY);
    }

    // trebuie sa verificam daca utilizatorul a dat click intr-un anumit Rectangle
    // obtinem coordonatele MouseEvent ului si verificam daca acel pentru se afla intr-un Rectangle desenat si daca da facem actiunea pentru butonul respectiv
    public void MouseClick(MouseEvent e)
    {
        // obtinem coordonatele MouseEvent-ului
        int mx = e.getX();
        int my = e.getY();
        // verificam ce personaj a fost ales
        if(lunaBox.contains(mx,my)) selectedCharacter = "Luna";
        else if(freyaBox.contains(mx,my)) selectedCharacter = "Freya";
        else if(emberBox.contains(mx,my)) selectedCharacter = "Ember";
            // daca s-a dat click pe start mai intai verificam daca jucatorul a ales un personaj apoi trecem in PlayState
        else if(startButton.contains(mx,my))
        {

            if(selectedCharacter == null)
                JOptionPane.showMessageDialog(null, "You must choose a character before starting the game!", "Warning", JOptionPane.WARNING_MESSAGE);
            else
            {
                // jucatorul trebuie sa introduca numele sau
                playerName = JOptionPane.showInputDialog(null, "Enter your player name:", "Player Name", JOptionPane.QUESTION_MESSAGE);

                if(playerName == null || playerName.trim().isEmpty()) {
                    // dacă nu a introdus nimic, afișăm mesaj de eroare și nu continuăm
                    JOptionPane.showMessageDialog(null, "Player name cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return; //
                }

                PublicGameData.playerName = playerName;
                PublicGameData.characterType = selectedCharacter;

                JOptionPane.showMessageDialog(null,"You chose: " + selectedCharacter + "\nPlayer name: " + playerName + "\nLevel 1 is loading...", "Selected character",JOptionPane.INFORMATION_MESSAGE);

                // Trece în PlayState și transmite atât personajul, cât și numele jucătorului
                State.SetState(new PlayState(refLink, selectedCharacter, levelIndex, playerName));// schimbam starea jocului

            }
        }
        else if(loadButton.contains(mx,my))
        {
            //JOptionPane.showMessageDialog(null,"Game is loading...", "Load game",JOptionPane.INFORMATION_MESSAGE);
            String nameToLoad = JOptionPane.showInputDialog(null, "Enter player name to load:", "Load Game", JOptionPane.QUESTION_MESSAGE);

            if(nameToLoad == null || nameToLoad.trim().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Player name cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // incercam sa incarcam datele din baza de date
            DataBaseManager db = DataBaseManager.getInstance();
            boolean found = db.loadPlayerByName(nameToLoad);

            if(found)
            {
                JOptionPane.showMessageDialog(null, "Loaded game for " + PublicGameData.playerName + "\nCharacter: " + PublicGameData.characterType + "\nLevel: " + PublicGameData.currentLevel + "\nScore: " + PublicGameData.score, "Game Loaded", JOptionPane.INFORMATION_MESSAGE);

                // schimbam starea jocului in PlayState folosind datele incarcarea
                State.SetState(new PlayState(refLink, PublicGameData.characterType,PublicGameData.currentLevel, PublicGameData.playerName));

            }
            else
            {
                JOptionPane.showMessageDialog(null, "No save found for player: " + nameToLoad, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(exitButton.contains(mx,my))
        {
            System.exit(0);
        }
    }

}
