/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Daniel
 */
public abstract class State {

    private final List<Object> args;

    public State(List<Object> args) {
        this.args = args;
    }

    public List<Object> getArgs() {
        return args;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.args);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.args, other.args)) {
            return false;
        }
        return true;
    }
    
    
}
