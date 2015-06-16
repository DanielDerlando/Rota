/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ExerciseAI.ExerciseAI;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class Controler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] latlon1 = request.getParameter("latlon1").split(",");
        String[] latlon2 = request.getParameter("latlon2").split(",");
        String[] args = {latlon1[0], latlon1[1], latlon2[0], latlon2[1]};
        String ia = request.getParameter("funcao").trim();
        String resposta;
        switch (ia) {
            case "Busca em Largura":
                resposta = ExerciseAI.executaBFS(args);
                break;
            case "Busca A* com heuristica de rota":
                resposta = ExerciseAI.executaAStarRoute(args);
                break;
            case "Busca A* com heuristica de radar":
                resposta = ExerciseAI.executaAStarRadar(args);
                break;
            case "Busca Gulosa com heuristica de rota":
                resposta = ExerciseAI.executaGreedyRouteS(args);
                break;
            case "Busca Gulosa com heuristica de radar":
                resposta = ExerciseAI.executaGreedyRadarS(args);
                break;
            default:
                resposta ="";
                break;

        }
        request.setAttribute("caminho", resposta);
        //jsp pegar o request.atributo

        request.getRequestDispatcher("caminho.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
