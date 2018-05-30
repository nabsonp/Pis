/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.modelo.Especie;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author samsung
 */
public class EscolherEspecieRel extends HttpServlet {

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
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("empresa") != null) {
                Date inicio = Date.class.cast(sessao.getAttribute("inicio"));
                Date fim = Date.class.cast(sessao.getAttribute("fim"));
                EspecieDAO espDAO = new EspecieDAO(con.getConexao());
                ArrayList<Especie> esps = espDAO.getListaEspecie();
                if (esps != null) {
                    request.setAttribute("esps", esps);
                    sessao.setAttribute("inicio", inicio);
                    sessao.setAttribute("fim", fim);
                    url = "/rel-especies.jsp";
                } else {
                    erro = "Não foi possível acessar as espécies.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                url = "/sessao-expirada.html";
            }
        } else {
            erro = "Não foi possível conectar-se ao banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }
    
}
