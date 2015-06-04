/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Complexity.Heuristic;
import Node.Node;
import java.util.Comparator;

/**
 *
 * @author Daniel
 */
public class ComparatorRoute implements Comparator{
    private Heuristic h;

    public ComparatorRoute(Heuristic h) {
        this.h = h;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Node a1 = (Node) o1;
        Node a2 = (Node) o2;
        
        double d1 = (double) a1.getPathCost() + h.calculate(a1.getS());
        double d2 = (double) a2.getPathCost() + h.calculate(a2.getS());
        
        if(d1 > d2){
            return 1;
        } else if(d1 < d2){
            return -1;            
        } else {
            return 0;
        }
    }
    
}
