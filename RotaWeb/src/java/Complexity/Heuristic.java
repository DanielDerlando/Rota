/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complexity;

import State.State;

/**
 *
 * @author Daniel
 */
public abstract class Heuristic {
    
    private final State goal;

    public Heuristic(State goal) {
        this.goal = goal;
    }

    public abstract double calculate(State s);

    public State getGoal() {
        return goal;
    }
}
