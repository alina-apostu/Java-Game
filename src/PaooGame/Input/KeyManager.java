package PaooGame.Input;

import PaooGame.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \class public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */
public class KeyManager implements KeyListener
{
    private boolean[] keys; /*!< Vector de flaguri pentru toate tastele. Tastele vor fi regasite dupa cod [0 - 255]*/
    public boolean up;      /*!< Flag pentru tasta "sus" apasata.*/
    public boolean down;    /*!< Flag pentru tasta "jos" apasata.*/
    public boolean left;    /*!< Flag pentru tasta "stanga" apasata.*/
    public boolean right;   /*!< Flag pentru tasta "dreapta" apasata.*/
    public boolean foc;
    public boolean zbor;
    public boolean invizibil;

    /*! \fn public KeyManager()
        \brief Constructorul clasei.
     */
    public KeyManager()
    {
        ///Constructie vector de flaguri aferente tastelor.
        keys = new boolean[256];
    }


    public void Update()
    {
        up    = keys[KeyEvent.VK_UP]; //modificate sageata sus
        down  = keys[KeyEvent.VK_DOWN]; //sageata jos
        left  = keys[KeyEvent.VK_LEFT]; //sageata stg
        right = keys[KeyEvent.VK_RIGHT]; //sageata drt

        zbor    = keys[KeyEvent.VK_W]; //modificate sageata sus
        foc  = keys[KeyEvent.VK_A]; //sageata jos
        invizibil  = keys[KeyEvent.VK_S]; //sageata stg

    }

    /*! \fn public void keyPressed(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta apasata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        ///se retine in vectorul de flaguri ca o tasta a fost apasata.
        keys[e.getKeyCode()] = true;
    }

    /*! \fn public void keyReleased(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand un un eveniment de tasta eliberata este generat.

         \param e obiectul eveniment de tastatura.
     */
    @Override
    public void keyReleased(KeyEvent e)
    {
        ///se retine in vectorul de flaguri ca o tasta a fost eliberata.
        keys[e.getKeyCode()] = false;
    }

    /*! \fn public void keyTyped(KeyEvent e)
        \brief Functie ce va fi apelata atunci cand o tasta a fost apasata si eliberata.
        Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void resetKeys()
    {
        for (int i = 0; i < keys.length; i++)
        {
            keys[i] = false;
        }
        up = false;
        down = false;
        left = false;
        right = false;
    }
}
