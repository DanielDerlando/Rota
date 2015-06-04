/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Action.Action;
import Complexity.Heuristic;
import Complexity.HeuristicRoute;
import Node.Node;
import Problem.Problem;
import State.State;
import String.StringTool;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Daniel
 */
public class SimulatedAnnealingSearch {

    private final Problem p;
    private final Heuristic h;
    private final int kMax;
    private Node current;
    private double temp = 1;
    private int numberOfNodes = 0;

    public SimulatedAnnealingSearch(Problem p, int kMax, State sFinal, Heuristic h) {
        this.p = p;
        this.h = h;
        this.kMax = kMax;
        this.current = new Node(p.getIState(), null, null, 0.0);
    }

    public String search() {
        Random aleatorio = new Random();
        Node no;
        double deltaE, probExp, dist;
        for (int i = 1; i < kMax; i++) {
            //System.out.println("latlong:" + current.getS().getArgs().get(0) + "," + current.getS().getArgs().get(1));
            dist = Math.abs(h.calculate(current.getS()));
            temp = dist * 1000 / i;//Multiplica por 1000 para ficar em metros
            if (dist < 0.001) {
                System.out.println("Quantidade de Iterações: " + numberOfNodes);
                return (new StringTool()).nodeParseToWebString(current);
            }

            no = randomNext(p, current);
            //Se o movimento for melhor, resulta numero positivo
            //deltaE=Math.abs(h.calculate(current.getS()))-Math.abs(h.calculate(no.getS()));
            deltaE = Math.abs(h.calculate(current.getS())) - Math.abs(h.calculate(no.getS()));
            // System.out.println("delta e debug:"+deltaE);
            if (deltaE > 0) {
                current = no;

            } else {
                probExp = Math.exp(deltaE * 1000 / temp);
                //System.out.println("temp debug:"+temp);
//                System.out.println("arg: " + deltaE);
                System.out.println("ProbExp: " + probExp);
                if (aleatorio.nextDouble() < probExp) {
                    current = no;

                }
            }
            numberOfNodes++;
        }
        System.out.println("Quantidade de Nós percorridos: " + numberOfNodes);
        return (new StringTool()).nodeParseToWebString(current);
    }

    public Node randomNext(Problem p, Node atual) {
        Random rd = new Random();
        ArrayList<Action> actions = p.actions(atual.getS());
        int pos = rd.nextInt(actions.size());

        for (int i = 0; i < pos; i++) {
            p.result(atual.getS(), actions.get(0));
        }
        return new Node(p.result(atual.getS(), actions.get(0)), atual, actions.get(0), 0.0);
    }

}
