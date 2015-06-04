/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import State.State;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class ActionRoute implements Action{
    private final List<State> l;

    public ActionRoute(List<State> l) {
        this.l = l;
    }
            
    @Override
    public State execute(State s) {  
        return l.remove(0);
    }

    public List<State> getStates() {
        return l;
    }
    
     
    
}
