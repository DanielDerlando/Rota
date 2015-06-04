package Action;

import State.State;


/**
 *
 * @author Daniel
 */
public interface Action {
    
    public State execute(State s);       
    
}
