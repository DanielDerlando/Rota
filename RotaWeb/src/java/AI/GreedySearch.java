package AI;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import Action.Action;
import Complexity.Heuristic;
import Node.Node;
import Problem.Problem;
import State.State;
import String.StringTool;

/**
 *
 * @author Daniel
 */
public class GreedySearch {

    private Queue frontier;
    private Set<Node> exploredSet;
    private int numberOfNodes=0;

    public String search(Problem p, Heuristic h) {
        Node n = new Node(p.getIState(), null, null, 0);
        ComparatorRouteWithOnlyHeuristics cR = new ComparatorRouteWithOnlyHeuristics(h);
        frontier = new PriorityQueue(cR);
        frontier.add(n);
        exploredSet = new LinkedHashSet<>();
        boolean executando = true;
        while (executando) {
            if (frontier.isEmpty()) {
                System.out.println("Falha");
                executando = false;
            } else {
                n = (Node) frontier.poll();
                double var = h.calculate(n.getS());
                System.out.println("custo:" + var + " latlong:" + n.getS().getArgs().get(0) + "," + n.getS().getArgs().get(1));
                if (p.goalTest(n.getS())) {
                    System.out.println("Caminho Encontrado");
                    executando = false;
                    StringTool st = new StringTool();
                    System.out.println("Quantidade de Nós percorridos: " + numberOfNodes);
                    return st.nodeParseToWebString(n);
                } else {
                    exploredSet.add(n);
                    numberOfNodes++;
                    for (Action a : p.actions(n.getS())) {
                        State param = p.result(n.getS(), a);
                        Node child = new Node(param, n, a, custo(param, n));
                        if (!(exploredSet.contains(child) || frontier.contains(child))) {
                            frontier.add(child);
                        } else {
                            if (frontier.contains(child)) {
                                Iterator<Node> i = frontier.iterator();
                                Node n2;
                                boolean b = false;
                                double d, d1;
                                while (i.hasNext() && !b) {
                                    n2 = (Node) i.next();
                                    b = n2.getS().equals(child.getS());
                                    if (b) {
                                        d = h.calculate(n2.getS());
                                        d1 = h.calculate(child.getS());
                                        if (d > d1) {
                                            this.replace(n, child);
                                            System.out.println("Caminho menor passando pelo mesmo estado");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

    public void replace(Node n, Node child) {
        frontier.remove(n);
        frontier.add(child);
    }

    public double custo(State filho, Node atual) {
        double x1, x2, y1, y2, raioDaTerra;

        raioDaTerra = 6372.8;

        x1 = (double) atual.getS().getArgs().get(0);
        x2 = (double) filho.getArgs().get(0);

        y1 = (double) atual.getS().getArgs().get(1);
        y2 = (double) filho.getArgs().get(1);

        double dLat = Math.toRadians(x2 - x1);
        double dLon = Math.toRadians(y2 - y1);
        x1 = Math.toRadians(x1);
        x2 = Math.toRadians(x2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(x1) * Math.cos(x2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return raioDaTerra * c;
    }
}
