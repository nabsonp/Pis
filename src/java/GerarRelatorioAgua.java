/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.dao.VerificacaoDAO;
import br.com.piscicultech.modelo.Empresa;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.TanqEsp;
import br.com.piscicultech.modelo.Tanque;
import br.com.piscicultech.modelo.Verificacao;
import java.io.IOException;
import java.io.PrintWriter;
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
public class GerarRelatorioAgua extends HttpServlet {

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
        int idTanque = Integer.parseInt(request.getParameter("tanque"));
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("usuario") != null) {
                Date inicio = Date.class.cast(sessao.getAttribute("inicio"));
                Date fim = Date.class.cast(sessao.getAttribute("fim"));
                if (inicio != null || fim != null) {
                    VerificacaoDAO veDAO = new VerificacaoDAO(con.getConexao());
                    TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
                    TanqEspDAO teDAO = new TanqEspDAO(con.getConexao());
                    EspecieDAO espDAO = new EspecieDAO(con.getConexao());
                    ArrayList<Verificacao> ves = veDAO.getLista(idTanque, inicio, fim);
                    if (ves != null) {
                        ArrayList<Tanque> tanques = tanqDAO.getListaTanque(Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao(), fim);
                        if (tanques != null) {
                            TanqEsp te = teDAO.getTanqEspTanque(idTanque, Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao(), fim);
                            if (te != null) {
                                request.setAttribute("esp", espDAO.getEspecie(te.getIdEspecie()));
                            }
                            request.setAttribute("tanques", tanques);
                            request.setAttribute("ves", ves);
                            request.setAttribute("idTanque", idTanque);
                            url = "/rel-tanque-agua.jsp";
                        } else {
                            erro = "Não foi possível acessar os tanques.";
                            url = "/erro.jsp?erro=" + erro;
                        }
                    } else {
                        erro = "Não foi possível gerar relatório.";
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
