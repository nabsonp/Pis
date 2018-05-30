/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.VerificacaoEspDAO;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.VerificacaoEsp;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
public class GerarRelatorioEspecie extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
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
            int idEspecie = Integer.parseInt(request.getParameter("esp"));
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("usuario") != null) {
                Date inicio = Date.class.cast(sessao.getAttribute("inicio"));
                Date fim = Date.class.cast(sessao.getAttribute("fim"));
                if (inicio != null || fim != null) {
                    VerificacaoEspDAO veEspDAO = new VerificacaoEspDAO(con.getConexao());
                    EspecieDAO espDAO = new EspecieDAO(con.getConexao());
                    ArrayList<VerificacaoEsp> ves = veEspDAO.getListaEspAsc(idEspecie, inicio, fim);
                    if (ves != null) {
                        ArrayList<Especie> esps = espDAO.getListaEspecie();
                        request.setAttribute("esps", esps);
                        request.setAttribute("ves", ves);
                        request.setAttribute("esp", espDAO.getEspecie(idEspecie));
                        url = "/rel-especie.jsp";
                    } else {
                        erro = "Por favor, insira o período novamente clicando <a href=\"escolher-periodo.jsp\">aqui</a>.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Por favor, insira o período novamente clicando <a href=\"escolher-periodo.jsp\">aqui</a>.";
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
