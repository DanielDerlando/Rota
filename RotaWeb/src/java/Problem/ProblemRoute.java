/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problem;

import Action.Action;
import Action.ActionRoute;
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
public class ProblemRoute implements Problem {

	private State iState;
	private State fState;
	public static double longestDistance;

	public ProblemRoute(State iState, State fState) {
		this.iState = iState;
		this.fState = fState;
		double x1, x2, y1, y2;

		x1 = (double) iState.getArgs().get(0);
		x2 = (double) fState.getArgs().get(0);

		y1 = (double) iState.getArgs().get(1);
		y2 = (double) fState.getArgs().get(1);

		double dLat = Math.toRadians(x2 - x1);
		double dLon = Math.toRadians(y2 - y1);
		x1 = Math.toRadians(x1);
		x2 = Math.toRadians(x2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
				* Math.sin(dLon / 2) * Math.cos(x1) * Math.cos(x2);
		double c = 2 * Math.asin(Math.sqrt(a));

		this.longestDistance = c * 6372.8;
	}

	@Override
	public ArrayList<Action> actions(State s) {
		ArrayList<Action> actions = new ArrayList<>();
		try {
			String myXML = getContentOfURL("http://cbk0.google.com/cbk?output=xml&ll="
					+ s.getArgs().get(0) + "," + s.getArgs().get(1));
			ArrayList<String> neighbours = getXMLNeighbors(myXML);

			String myXMLTemp;
			ArrayList<State> states = new ArrayList<>();
			List<Object> args;

			for (String neighbour : neighbours) {
				args = new ArrayList<>();
				myXMLTemp = getContentOfURL("http://cbk0.google.com/cbk?output=xml&panoid="
						+ neighbour);
				args.add(getXMLLatitude(myXMLTemp));
				args.add(getXMLLongitude(myXMLTemp));
                                String rua = getXMLRua(myXMLTemp);
				//System.out.println(rua);
                                args.add(rua);
                                
				states.add(new StateRoute(args));
			}
			for (State state : states) {
				actions.add(new ActionRoute(states));
			}

		} catch (IOException ex) {
			Logger.getLogger(ProblemRoute.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		return actions;
	}

	private static String getXMLRua(String myXMLTemp) {
		String rua = getXMLElement(myXMLTemp, "<text", "/text>");
		return rua;
	}

	/*
	 * Método que compara se o Estado Atual é igual ao Estado Objetivo usando
	 * as Latitudes e Longitudes.
	 */
	@Override
	public boolean goalTest(State s) {
		double x1, x2, y1, y2, dif1, dif2;
		x1 = (double) s.getArgs().get(0);
		x2 = (double) fState.getArgs().get(0);
		y1 = (double) s.getArgs().get(1);
		y2 = (double) fState.getArgs().get(1);
		dif1 = x1 - x2;
		dif2 = y1 - y2;
		if (Math.abs(dif1) < 0.0001 && Math.abs(dif2) < 0.0001) {
			return true;
		}
		return false;
	}

	@Override
	public State getIState() {
		return iState;
	}

	@Override
	public State result(State s, Action a) {
		return a.execute(s);
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

	private static String getXMLElement(String myXML, String condicaoInicial,
			String condicaoFinal) {
		int indexCondicaoInicial = myXML.indexOf(condicaoInicial)
				+ condicaoInicial.length() + 1;
		int indexCondicaoFinal = myXML.indexOf(condicaoFinal) - 1;

            try {
                return new String(myXML.substring(indexCondicaoInicial, indexCondicaoFinal).getBytes(),"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ProblemRoute.class.getName()).log(Level.SEVERE, null, ex);
                return "";
            }
	}

}
