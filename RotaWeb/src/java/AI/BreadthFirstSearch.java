/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Action.Action;
import Node.Node;
import Problem.Problem;
import State.State;
import String.StringTool;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Daniel
 */
public class BreadthFirstSearch {

    private final Problem p;
    private final Queue<Node> frontier;
    private final Set<Node> explored;
    private Node no;
    private int numberOfNodes;

    public BreadthFirstSearch(Problem problem) {
        p = problem;
        frontier = new LinkedList<>();
        no = new Node(p.getIState(), null, null, 0.0);
        frontier.add(no);
        explored = new LinkedHashSet<>();
        this.numberOfNodes = 0;
    }

    public String search() {
        String caminho = "";
        if (p.goalTest(no.getS())) {
            caminho = (new StringTool()).nodeParseToWebString(no);
            return caminho;
        }
        boolean isResolvido = false;
        while (!isResolvido) {
            if (frontier.isEmpty()) {
                System.out.println("Falhou");
                isResolvido = true;
            } else {
                no = frontier.poll();                
                System.out.println("latlong:" + no.getS().getArgs().get(0) + "," + no.getS().getArgs().get(1));
                explored.add(no);
                for (Action a : p.actions(no.getS())) {
                    State param = p.result(no.getS(), a);
                    Node child = new Node(param, no, a, 0.0);
                    this.numberOfNodes++;
                    if (!(explored.contains(child) || frontier.contains(child))) {
                        if (p.goalTest(child.getS())) {
                            caminho = (new StringTool()).nodeParseToWebString(child);
                            System.out.println("Quantidade de Nós percorridos: " + numberOfNodes);
                            return caminho;
                        }
                        frontier.add(child);
                    }
                }
            }
        }
        System.out.println("Quantidade de Nós percorridos: " + numberOfNodes);
        return caminho;
    }

}
