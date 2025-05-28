package PaooGame.States;

import PaooGame.RefLinks;

import java.awt.*;

// clasa State implementeaza notiunea abstracta de stare a jocului/programului.

public abstract class State
{
    // Urmatoarele atribute sunt statice pentru a evita dealocarea spatiului de memorie la trecerea dintr-o stare in alta.
    private static State previousState  = null; // Referinta catre starea anterioara a jocului.*/
    private static State currentState   = null; // Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    protected RefLinks refLink;
    public State(RefLinks refLink)
    {
        this.refLink = refLink;
    }

    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
    }

    public static State GetState()
    {
        return currentState;
    }

    public abstract void Update();

    public abstract void Draw(Graphics g);
}
