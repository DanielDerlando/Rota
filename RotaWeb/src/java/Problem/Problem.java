package Problem;

import Action.Action;
import State.State;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public interface Problem {
    public ArrayList<Action> actions(State s);
    
    public boolean goalTest(State s);
    
    public State getIState();
    
    public State result(State s, Action a);
}
