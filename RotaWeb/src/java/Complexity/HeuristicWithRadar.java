/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complexity;

import Problem.ProblemRoute;
import State.State;

import radar.RadarMap;

/**
 *
 * @author Daniel
 */
public class HeuristicWithRadar extends Heuristic {

	public HeuristicWithRadar(State goal) {
		super(goal);
	}

	@Override
	public double calculate(State s) {
		RadarMap radar = new RadarMap();
		double distance = calculateDistance(s);
                
		if (radar.getRadars().contains(s.getArgs().get(2))) {
			return distance + ProblemRoute.longestDistance;
		}
		return distance;
	}

	private double calculateDistance(State s) {
		double x1, x2, y1, y2;

		x1 = (double) s.getArgs().get(0);
		x2 = (double) this.getGoal().getArgs().get(0);

		y1 = (double) s.getArgs().get(1);
		y2 = (double) this.getGoal().getArgs().get(1);

		double dLat = Math.toRadians(x2 - x1);
		double dLon = Math.toRadians(y2 - y1);
		x1 = Math.toRadians(x1);
		x2 = Math.toRadians(x2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
				* Math.sin(dLon / 2) * Math.cos(x1) * Math.cos(x2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double R = 6372.8;
		return R * c;
	}
}
