package PaooGame.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import PaooGame.States.MenuState;

import PaooGame.States.PlayState;

import PaooGame.States.PauseState;

import PaooGame.States.State;

/*
    Clasa responsabilă pentru gestionarea click-urilor mouse-ului.
*/

public class MouseInput implements MouseListener
{
    @Override
    public void mousePressed(MouseEvent e)
    {
        // starea curenta a jocului
        State currentState = State.GetState();
        // daca starea curenta este MenuState trimitem evenimentul acolo
        if(currentState instanceof MenuState) // instanceof verifica tipul obiectului
        {
            ((MenuState) currentState).MouseClick(e); // transform obiectul state in MenuState si trimitem coordonatele clickului
        }

        // daca starea curenta este PauseState trimitem evenimentul acolo
        else if(currentState instanceof PauseState)
        {
            ((PauseState) currentState).MouseClick(e);
        }
    }
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
