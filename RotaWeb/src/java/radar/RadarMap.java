package radar;

import java.util.ArrayList;
import java.util.List;

public class RadarMap {
	private List<String> radars;

	public RadarMap() {
		radars = new ArrayList<String>();
		radars.add("Av. Dr. Acúrcio Tôrres");
		radars.add("Av. Alm. Tamandaré");
		radars.add("RJ-104");
		radars.add("Av. Alm. Ary Parreiras");
		radars.add("Estr. Caetano Monteiro");
		radars.add("R. Cel. Tamarindo");
		radars.add("R. Des. Lima Castro");
		radars.add("Estr. Velha de Maricá");
		radars.add("Av. Ewerton Xavier");
		radars.add("Av. Felíciano Sodré");
		radars.add("Estr. Francisco da Cruz Nunes");
		radars.add("R. Gen. Castrioto");
		radars.add("Av. Irene Lopes Sodré");
		radars.add("Av. Prof. João Brasil");
		radars.add("R. Pres. João Pessoa");
		radars.add("Av. Jansen de Melo");
		radars.add("R. Manoel Pacheco de Carvalho");
		radars.add("R. Marquês de Paraná");
		radars.add("Rua Dr. Mário Viana");
		radars.add("R. Noronha Torrezão");
		radars.add("Av. Pres. Roselvelt");
		radars.add("Av. Prof. Plínio Leite");
		radars.add("Av. Rui Barbosa");
		radars.add("RJ-106");
		radars.add("Av. Roberto Silveira");
		radars.add("Av. Quintino Bocaiúva");
	}

	public List<String> getRadars() {
		return radars;
	}

}
