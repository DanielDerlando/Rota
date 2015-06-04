package ExerciseAI;

import AI.AStarSearch;
import AI.BreadthFirstSearch;
import AI.SimulatedAnnealingSearch;
import Complexity.Heuristic;
import Complexity.HeuristicRoute;
import Complexity.HeuristicWithRadar;
import Problem.Problem;
import Problem.ProblemRoute;
import State.State;
import State.StateRoute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class ExerciseAI {

    /**
     * @param args the command line arguments
     */
    public static String executaAStarRoute(String[] args) {
        List<Object> linicial = new ArrayList<>();
        linicial.add(Double.parseDouble(args[0]));
        linicial.add(Double.parseDouble(args[1]));

        List<Object> lfinal = new ArrayList<>();
        lfinal.add(Double.parseDouble(args[2]));
        lfinal.add(Double.parseDouble(args[3]));

        State fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Heuristic h = new HeuristicRoute(fState);
        Problem p = new ProblemRoute(iState, fState);
        AStarSearch a = new AStarSearch();

        return a.search(p, h);
    }

    public static String executaAStarRadar(String[] args) {
        List<Object> linicial = new ArrayList<>();
        linicial.add(Double.parseDouble(args[0]));
        linicial.add(Double.parseDouble(args[1]));
        try {
            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
                    + linicial.get(0) + "," + linicial.get(1));
            linicial.add(getXMLRua(myXML));
        } catch (IOException ex) {
            Logger.getLogger(ExerciseAI.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Object> lfinal = new ArrayList<>();
        lfinal.add(Double.parseDouble(args[2]));
        lfinal.add(Double.parseDouble(args[3]));

        State fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Heuristic h = new HeuristicWithRadar(fState);
        Problem p = new ProblemRoute(iState, fState);
        AStarSearch a = new AStarSearch();

        return a.search(p, h);
    }

    public static String executaBFS(String[] args) {
        List<Object> linicial = new ArrayList<>();
        linicial.add(Double.parseDouble(args[0]));
        linicial.add(Double.parseDouble(args[1]));

        List<Object> lfinal = new ArrayList<>();
        lfinal.add(Double.parseDouble(args[2]));
        lfinal.add(Double.parseDouble(args[3]));

        State fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Problem p = new ProblemRoute(iState, fState);
        BreadthFirstSearch a = new BreadthFirstSearch(p);

        return a.search();
    }

    public static String executaSASRoute(String[] args) {
        List<Object> linicial = new ArrayList<>();
        linicial.add(Double.parseDouble(args[0]));
        linicial.add(Double.parseDouble(args[1]));
        
        List<Object> lfinal = new ArrayList<>();
        lfinal.add(Double.parseDouble(args[2]));
        lfinal.add(Double.parseDouble(args[3]));

        State fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Problem p = new ProblemRoute(iState, fState);
        Heuristic h = new HeuristicRoute(fState);
        SimulatedAnnealingSearch s = new SimulatedAnnealingSearch(p, 2500, fState, h);

        return s.search();
    }

    public static String executaSASRadar(String[] args) {
        List<Object> linicial = new ArrayList<>();
        linicial.add(Double.parseDouble(args[0]));
        linicial.add(Double.parseDouble(args[1]));
        try {
            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
                    + linicial.get(0) + "," + linicial.get(1));
            linicial.add(getXMLRua(myXML));
        } catch (IOException ex) {
            Logger.getLogger(ExerciseAI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Object> lfinal = new ArrayList<>();
        lfinal.add(Double.parseDouble(args[2]));
        lfinal.add(Double.parseDouble(args[3]));

        State fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Problem p = new ProblemRoute(iState, fState);
        Heuristic h = new HeuristicWithRadar(fState);
        SimulatedAnnealingSearch s = new SimulatedAnnealingSearch(p, 2500, fState, h);

        return s.search();
    }

    private static String getContentOfURL(String path) throws IOException {
        String input;
        URL url = new URL(path);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(url
                .openConnection().getInputStream()))) {
            input = br.readLine();
        }

        return input;
    }

    private static String getXMLElement(String myXML, String condicaoInicial,
            String condicaoFinal) {
        int indexCondicaoInicial = myXML.indexOf(condicaoInicial)
                + condicaoInicial.length() + 1;
        int indexCondicaoFinal = myXML.indexOf(condicaoFinal) - 1;

        try {
            return new String(myXML.substring(indexCondicaoInicial, indexCondicaoFinal).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProblemRoute.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    private static String getXMLRua(String myXMLTemp) {
        String rua = getXMLElement(myXMLTemp, "<text", "/text>");
        return rua;
    }
}
