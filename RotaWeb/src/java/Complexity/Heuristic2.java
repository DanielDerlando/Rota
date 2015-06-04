/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complexity;

import Action.ActionRoute;
import State.State;
import State.StateRoute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class Heuristic2 extends Heuristic {

    public Heuristic2(State goal) {
        super(goal);
    }

    @Override
    public double calculate(State s) {
//        String
//        try {
//            String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll=" + s.getArgs().get(0) + "," + s.getArgs().get(1));
//            ArrayList<String> neighbours = getXMLNeighbors(myXML);
//
//            String myXMLTemp;
//            ArrayList<State> states = new ArrayList<>();
//            List<Object> args;
//
//            for (String neighbour : neighbours) {
//                args = new ArrayList<>();
//                myXMLTemp = getContentOfURL("http://cbk0.google.com/cbk?output=xml&panoid=" + neighbour);               
//                states.add(new StateRoute(args));
//            }            
//        } catch(Exception e){
//            e.printStackTrace();
//        }
        return 1.0;
    }
    
//    private static String getContentOfURL(String path) throws IOException {
//        String input;
//        URL url = new URL(path);
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
//            input = br.readLine();
//        }
//
//        return input;
//    }
//    
//    private static ArrayList<String> getXMLNeighbors(String xml) {
//        ArrayList<String> vizinhos = new ArrayList<>();
//
//        if (annotation_properties != null) {
//            //Link
//            //Inicio
//            String inicioLink = "<link";
//            int indexInicialLink = annotation_properties.indexOf(inicioLink);
//
//            //Fim
//            String finalLink = "</link>";
//            int indexFinalLink = annotation_properties.indexOf(finalLink) + 7;
//
//            int finalAnnotation = annotation_properties.indexOf("</annotation_properties>");
//
//            String inicioPanoId = "pano_id=";
//            String fimPanoId = " road_argb";
//
//            String vizinho;
//            String link;
//
//            while ((indexInicialLink) <= finalAnnotation) {
//                link = annotation_properties.substring(indexInicialLink, indexFinalLink);
//
//                vizinho = link.substring(link.indexOf(inicioPanoId) + 9 /*Length + 1*/, link.indexOf(fimPanoId) - 1);
//
//                vizinhos.add(vizinho);
//
//                indexInicialLink = indexFinalLink + 1;
//                indexFinalLink = annotation_properties.indexOf(finalLink, indexInicialLink) + 7/*Length*/;
//            }
//        }
//
//        return vizinhos;
//    }

}
