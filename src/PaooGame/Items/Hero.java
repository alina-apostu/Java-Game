package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class Hero extends Character
{
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private BufferedImage[] characterUp; // in functie de ce personaj este ales in meniu
    private BufferedImage[] characterRight;
    private int characterIndex; // index care indica cadrul curent din animatie
    private long lastTime;

    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y, String characterName)
    {
        //Apel al constructorului clasei de baza
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        if(characterName.equals("Luna"))
        {
            characterUp = Assets.lunaUp;
            characterRight = Assets.lunaRight;
        }
        else if(characterName.equals("Freya"))
        {
            characterUp = Assets.freyaUp;
            characterRight = Assets.freyaRight;
        }
        else if(characterName.equals("Ember"))
        {
            characterUp = Assets.emberUp;
            characterRight = Assets.emberRight;
        }
        else throw new IllegalArgumentException("Invalid character name: " + characterName);

        //Seteaza imaginea de start a eroului
        image = characterRight[0];
        characterIndex = 0; // se incepe de la primul cadru
        lastTime = System.currentTimeMillis(); // pentru a controla veteza animatiei
            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 32;
        normalBounds.height = 32;

            ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
    }

    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    @Override
    public void Update()
    {
        //Verifica daca a fost apasata o tasta
        GetInput();

        //Actualizeaza pozitia
        Move();

        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTime > 150) // 150 ms intre cadre
        {
            characterIndex++; // avansam la urmatorul cadru
            lastTime = currentTime;
        }

        //Actualizeaza imaginea
        if(refLink.GetKeyManager().up == true)
        {
            if(characterIndex < characterUp.length)
                image = characterUp[characterIndex];
            else
            {
                characterIndex = 0;
                image = characterUp[characterIndex];
            }
        }
        else if(refLink.GetKeyManager().right == true)
        {
            if(characterIndex < characterRight.length)
                image = characterRight[characterIndex];
            else
            {
                characterIndex = 0;
                image=characterRight[characterIndex];
            }
        }
        else if(refLink.GetKeyManager().left == true)
        {
            if(characterIndex < characterRight.length)
                image = flipImageHorizontally(characterRight[characterIndex]);
            else
            {
                characterIndex = 0;
                image=flipImageHorizontally(characterRight[characterIndex]);
            }
        }
    }

    private BufferedImage flipImageHorizontally(BufferedImage img)
    {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage flippedImg = new BufferedImage(width,height,img.getType());
        Graphics2D g = flippedImg.createGraphics();
        g.drawImage(img,0, 0, width, height, width, 0, 0, height, null);
        g.dispose();
        return flippedImg;
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            yMove = -speed;
        }
            ///Verificare apasare tasta "jos"
        if(refLink.GetKeyManager().down)
        {
            yMove = speed;
        }
            ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left)
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right)
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x, (int)y, width, height, null);

            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
}
