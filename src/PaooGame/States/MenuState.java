package PaooGame.States;

import PaooGame.RefLinks;
import java.awt.*;
import java.awt.event.MouseEvent;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    private Rectangle startButton;
    private Rectangle loadButton;
    private Rectangle exitButton;

    private Rectangle lunaBox;
    private Rectangle freyaBox;
    private Rectangle emberBox;

    private String selectedCharacter = null;

    public MenuState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);

        int buttonWidth = 200;
        int buttonHeight = 60;
        int centerX = 400;

        // pozitiile pentru butoane
        startButton = new Rectangle(centerX, 400, buttonWidth, buttonHeight);
        loadButton = new Rectangle(centerX, 480, buttonWidth, buttonHeight);
        exitButton = new Rectangle(centerX, 560, buttonWidth, buttonHeight);
        // pozitiile pentru cele trei personaje
        lunaBox = new Rectangle(100, 200, 150, 150);
        freyaBox = new Rectangle(300, 200, 150, 150);
        emberBox = new Rectangle(500, 200, 150, 150);
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
        g.setColor(new Color(48,25,52)); // dark purple
        g.fillRect(0, 0, refLink.GetWidth(), refLink.GetHeight());

        // titlul jocului
        g.setColor(Color.WHITE);
        g.setFont(new Font("Papyrus",Font.BOLD,40));
        g.drawString("The Enchanted Library: Shadow Hunt", 100, 80);

        // personaje
        g.setColor(Color.LIGHT_GRAY);
        //Luna
        g.drawRect(lunaBox.x, lunaBox.y, lunaBox.width, lunaBox.height);
        g.drawString("Luna", lunaBox.x + 40, lunaBox.y + lunaBox.height + 20); //  adaugam 40 ca sa mutam textul spre dreapta chenarului ca sa arate centrat si 20 sub chenar pentru a lasa un spatiu
        //Freya
        g.drawRect(freyaBox.x, freyaBox.y, freyaBox.width, freyaBox.height);
        g.drawString("Freya", freyaBox.x + 40, freyaBox.y + freyaBox.height + 20);
        //Ember
        g.drawRect(emberBox.x, emberBox.y, emberBox.width, emberBox.height);
        g.drawString("Ember", emberBox.x + 40, emberBox.y + emberBox.height + 20);

        // daca un personaj este selectat desenam un chenar in jurul sau pentru a evidentia ca a fost selectat
        if ("Luna".equals(selectedCharacter)) g.drawRect(lunaBox.x - 5, lunaBox.y - 5, lunaBox.width + 10, lunaBox.height + 10);
        if ("Freya".equals(selectedCharacter)) g.drawRect(freyaBox.x - 5, freyaBox.y - 5, freyaBox.width + 10, freyaBox.height + 10);
        if ("Ember".equals(selectedCharacter)) g.drawRect(emberBox.x - 5, emberBox.y - 5, emberBox.width + 10, emberBox.height + 10);

        // butoane
        drawButton(g, startButton, "START");
        drawButton(g, loadButton, "LOAD");
        drawButton(g, exitButton, "EXIT");
    }

    private void drawButton(Graphics g, Rectangle r, String s)
    {
        // culoare de fundal pentru buton
        g.setColor(Color.DARK_GRAY);
        // se deseneaza un dreptunghi plin
        g.fillRect(r.x,r.y,r.width,r.height);
        // culoarea pentru text
        g.setColor(Color.WHITE);
        // fontul pentru text
        g.setFont(new Font("Serif", Font.BOLD,20));
        // punem textul pe buton
        g.drawString(s,r.x + 50, r.y + 35);
    }
}
