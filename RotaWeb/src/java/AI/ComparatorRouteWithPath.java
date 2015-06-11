package AI;

import java.util.Comparator;

import Complexity.Heuristic;
import Node.Node;

public class ComparatorRouteWithPath  implements Comparator{
    private Heuristic h;

    public ComparatorRouteWithPath(Heuristic h) {
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
