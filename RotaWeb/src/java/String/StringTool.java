/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package String;

import Node.Node;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class StringTool {

    FileWriter writeFile = null;
    File file = null;
    private final String path = "C:\\Users\\Daniel\\Documents\\NetBeansProjects\\RotaWeb\\web\\";

    public StringTool() {
    }

    public void criaJson(String name, Node end) {
        String dados = "{\"coordenadas\":";
        String json = "]";
        while (end.getParent() != null) {
            json = ",[[" + end.getS().getArgs().get(0) + "],[" + end.getS().getArgs().get(1) + "]]" + json;
            end = end.getParent();
        }
        json = "[[[" + end.getS().getArgs().get(0) + "],[" + end.getS().getArgs().get(1) + "]]" + json;
        dados = dados + json + "}";
        try {
            file = new File(path + name + ".json");
            if (file.exists()) {
                System.out.println(file.getPath());
                file.delete();
            }
            writeFile = new FileWriter(file);
            //Escreve no arquivo conteudo do Objeto JSON
            writeFile.write(dados);
            writeFile.flush();
            System.out.println("Arquivo criado " + file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writeFile.close();
            } catch (IOException ex) {
                Logger.getLogger(StringTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String nodeParseToWebString(Node end) {
        int numberOfNodes = 0;
        String dados = "";

        while (end.getParent() != null) {
            dados = end.getS().getArgs().get(0).toString() + " " + end.getS().getArgs().get(1).toString() + " " + dados;
            numberOfNodes++;
            end = end.getParent();
        }
        dados = end.getS().getArgs().get(0).toString() + " " + end.getS().getArgs().get(1).toString() + " " + dados;
        System.out.println("Comprimento do Caminho: " + numberOfNodes);
        return dados;
    }
}
