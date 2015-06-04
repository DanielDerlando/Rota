package Complexity;

import State.State;

/**
 *
 * @author Daniel
 */
public class HeuristicRoute extends Heuristic {

    public static final double R = 6372.8; // In kilometers    

    public HeuristicRoute(State fState) {
        super(fState);
    }

    @Override
    public double calculate(State s) {
        double x1, x2, y1, y2;

        x1 = (double) s.getArgs().get(0);
        x2 = (double) this.getGoal().getArgs().get(0);

        y1 = (double) s.getArgs().get(1);
        y2 = (double) this.getGoal().getArgs().get(1);
        
        double dLat = Math.toRadians(x2 - x1);
        double dLon = Math.toRadians(y2 - y1);
        x1 = Math.toRadians(x1);
        x2 = Math.toRadians(x2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(x1) * Math.cos(x2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;        
    }

}
