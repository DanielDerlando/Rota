package ExerciseAI;

import AI.AStarSearch;
import AI.BreadthFirstSearch;
import AI.GreedySearch;
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
        State fState;
        try {
            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
                    + Double.parseDouble(args[2]) + "," + Double.parseDouble(args[3]));
            ArrayList<String> neighbours = getXMLNeighbors(myXML);
            String myXMLTemp = getContentOfURL("http://cbk0.google.com/cbk?output=xml&panoid="
                    + neighbours.get(0));
            lfinal.add(getXMLLatitude(myXMLTemp));
            lfinal.add(getXMLLongitude(myXMLTemp));
        } catch (IOException ex) {
            lfinal.clear();
            lfinal.add(Double.parseDouble(args[2]));
            lfinal.add(Double.parseDouble(args[3]));
        }
        fState = new StateRoute(lfinal);
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
        State fState;
        try {
            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
                    + Double.parseDouble(args[2]) + "," + Double.parseDouble(args[3]));
            ArrayList<String> neighbours = getXMLNeighbors(myXML);
            String myXMLTemp = getContentOfURL("http://cbk0.google.com/cbk?output=xml&panoid="
                    + neighbours.get(0));
            lfinal.add(getXMLLatitude(myXMLTemp));
            lfinal.add(getXMLLongitude(myXMLTemp));
        } catch (IOException ex) {
            lfinal.clear();
            lfinal.add(Double.parseDouble(args[2]));
            lfinal.add(Double.parseDouble(args[3]));
        }
        fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Heuristic h = new HeuristicWithRadar(fState);
        Problem p = new ProblemRoute(iState, fState);
        AStarSearch a = new AStarSearch();

        return a.search(p, h);
    }

    public static String executaGreedyRouteS(String[] args) {
        List<Object> linicial = new ArrayList<>();
        linicial.add(Double.parseDouble(args[0]));
        linicial.add(Double.parseDouble(args[1]));

        List<Object> lfinal = new ArrayList<>();
        State fState;
        try {
            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
                    + Double.parseDouble(args[2]) + "," + Double.parseDouble(args[3]));
            ArrayList<String> neighbours = getXMLNeighbors(myXML);
            String myXMLTemp = getContentOfURL("http://cbk0.google.com/cbk?output=xml&panoid="
                    + neighbours.get(0));
            lfinal.add(getXMLLatitude(myXMLTemp));
            lfinal.add(getXMLLongitude(myXMLTemp));
        } catch (IOException ex) {
            lfinal.clear();
            lfinal.add(Double.parseDouble(args[2]));
            lfinal.add(Double.parseDouble(args[3]));
        }
        fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Problem p = new ProblemRoute(iState, fState);
        Heuristic h = new HeuristicRoute(fState);
        GreedySearch s = new GreedySearch();
        return s.search(p, h);
    }

    public static String executaGreedyRadarS(String[] args) {
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
        State fState;
        try {
            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
                    + Double.parseDouble(args[2]) + "," + Double.parseDouble(args[3]));
            ArrayList<String> neighbours = getXMLNeighbors(myXML);
            String myXMLTemp = getContentOfURL("http://cbk0.google.com/cbk?output=xml&panoid="
                    + neighbours.get(0));
            lfinal.add(getXMLLatitude(myXMLTemp));
            lfinal.add(getXMLLongitude(myXMLTemp));
        } catch (IOException ex) {
            lfinal.clear();
            lfinal.add(Double.parseDouble(args[2]));
            lfinal.add(Double.parseDouble(args[3]));
        }
        fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Problem p = new ProblemRoute(iState, fState);
        Heuristic h = new HeuristicWithRadar(fState);
        GreedySearch s = new GreedySearch();
        return s.search(p, h);
    }

    public static String executaBFS(String[] args) {
        List<Object> linicial = new ArrayList<>();
        linicial.add(Double.parseDouble(args[0]));
        linicial.add(Double.parseDouble(args[1]));

        List<Object> lfinal = new ArrayList<>();
        State fState;
        try {
            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
                    + Double.parseDouble(args[2]) + "," + Double.parseDouble(args[3]));
            ArrayList<String> neighbours = getXMLNeighbors(myXML);
            String myXMLTemp = getContentOfURL("http://cbk0.google.com/cbk?output=xml&panoid="
                    + neighbours.get(0));
            lfinal.add(getXMLLatitude(myXMLTemp));
            lfinal.add(getXMLLongitude(myXMLTemp));
        } catch (IOException ex) {
            lfinal.clear();
            lfinal.add(Double.parseDouble(args[2]));
            lfinal.add(Double.parseDouble(args[3]));
        }
        fState = new StateRoute(lfinal);
        State iState = new StateRoute(linicial);
        Problem p = new ProblemRoute(iState, fState);
        BreadthFirstSearch a = new BreadthFirstSearch(p);

        return a.search();
    }

//    public static String executaSASRoute(String[] args) {
//        List<Object> linicial = new ArrayList<>();
//        linicial.add(Double.parseDouble(args[0]));
//        linicial.add(Double.parseDouble(args[1]));
//        
//        List<Object> lfinal = new ArrayList<>();
//        lfinal.add(Double.parseDouble(args[2]));
//        lfinal.add(Double.parseDouble(args[3]));
//
//        State fState = new StateRoute(lfinal);
//        State iState = new StateRoute(linicial);
//        Problem p = new ProblemRoute(iState, fState);
//        Heuristic h = new HeuristicRoute(fState);
//        SimulatedAnnealingSearch s = new SimulatedAnnealingSearch(p, 2500, fState, h);
//
//        return s.search();
//    }
//
//    public static String executaSASRadar(String[] args) {
//        List<Object> linicial = new ArrayList<>();
//        linicial.add(Double.parseDouble(args[0]));
//        linicial.add(Double.parseDouble(args[1]));
//        try {
//            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
//                    + linicial.get(0) + "," + linicial.get(1));
//            linicial.add(getXMLRua(myXML));
//        } catch (IOException ex) {
//            Logger.getLogger(ExerciseAI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        List<Object> lfinal = new ArrayList<>();
//        lfinal.add(Double.parseDouble(args[2]));
//        lfinal.add(Double.parseDouble(args[3]));
//
//        State fState = new StateRoute(lfinal);
//        State iState = new StateRoute(linicial);
//        Problem p = new ProblemRoute(iState, fState);
//        Heuristic h = new HeuristicWithRadar(fState);
//        SimulatedAnnealingSearch s = new SimulatedAnnealingSearch(p, 2500, fState, h);
//
//        return s.search();
//    }
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

    private static ArrayList<String> getXMLNeighbors(String xml) {
        ArrayList<String> vizinhos = new ArrayList<>();
        String annotation_properties = getXMLAnnotatioProprieties(xml);

        if (annotation_properties != null) {
            // Link
            // Inicio
            String inicioLink = "<link";
            int indexInicialLink = annotation_properties.indexOf(inicioLink);

            // Fim
            String finalLink = "</link>";
            int indexFinalLink = annotation_properties.indexOf(finalLink) + 7;

            int finalAnnotation = annotation_properties
                    .indexOf("</annotation_properties>");

            String inicioPanoId = "pano_id=";
            String fimPanoId = " road_argb";

            String vizinho;
            String link;

            while ((indexInicialLink) <= finalAnnotation) {
                link = annotation_properties.substring(indexInicialLink,
                        indexFinalLink);

                vizinho = link.substring(link.indexOf(inicioPanoId) + 9 /*
                         * Length
                         * + 1
                         */,
                        link.indexOf(fimPanoId) - 1);

                vizinhos.add(vizinho);

                indexInicialLink = indexFinalLink + 1;
                indexFinalLink = annotation_properties.indexOf(finalLink,
                        indexInicialLink) + 7/* Length */;
            }
        }

        return vizinhos;
    }

    private static String getXMLAnnotatioProprieties(String xml_archive) {
        if (!xml_archive.contains("<annotation_properties/>")) {
            // Inicio
            String inicioAnnotationProperties = "<annotation_properties>";
            int indexInicialAnnotations = xml_archive
                    .indexOf(inicioAnnotationProperties);

            // Fim
            String finalAnnotationProperties = "</annotation_properties>";
            int indexFinalAnnotations = xml_archive
                    .indexOf(finalAnnotationProperties)
                    + finalAnnotationProperties.length();

            // annotation_properties
            String annotation_properties = xml_archive.substring(
                    indexInicialAnnotations, indexFinalAnnotations);

            // System.out.println(annotation_properties);
            return annotation_properties;
        } else {
            return null;
        }
    }

    private static double getXMLLatitude(String myXML) {
        String latitude = getXMLElement(myXML, "lat=", " lng");
        return Double.parseDouble(latitude);
    }

    private static double getXMLLongitude(String myXML) {
        String longitude = getXMLElement(myXML, "lng=", " original_lat=");
        return Double.parseDouble(longitude);

    }
}
