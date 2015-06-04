package Node;

import Action.Action;
import State.State;
import java.util.Objects;

/**
 *
 * @author Daniel
 */
public class Node {
    private State s;
    private Node parent;
    private Action a;
    private double pathCost;

    public Node(State s, Node parent, Action a, double pathCost) {
        this.s = s;
        this.parent = parent;
        this.a = a;
        if(parent != null){
            this.pathCost = pathCost + this.parent.pathCost;
        } else {
            this.pathCost = pathCost;
        }
        
    }    

    public State getS() {
        return s;
    }

    public void setS(State s) {
        this.s = s;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Action getA() {
        return a;
    }

    public void setA(Action a) {
        this.a = a;
    }

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(double pathCost) {
        this.pathCost = pathCost;
    }

    

    @Override
    public boolean equals(Object obj) {
        final Node other = (Node) obj;
        return this.s.equals(other.s);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.s);
        return hash;
    }
    
}
