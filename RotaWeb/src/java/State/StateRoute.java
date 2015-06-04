/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import java.util.List;

/**
 *
 * @author Daniel
 */
public class StateRoute extends State {

    public StateRoute(List<Object> args) {
        super(args);
    }

    @Override
    public boolean equals(Object obj) {
        State s = (State) obj;
        if (this.getArgs().get(0).equals(s.getArgs().get(0)) && this.getArgs().get(1).equals(s.getArgs().get(1))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
}
