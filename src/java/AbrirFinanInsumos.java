/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.QuimicoDAO;
import br.com.piscicultech.dao.RacaoDAO;
import br.com.piscicultech.dao.VendaQuimicoDAO;
import br.com.piscicultech.dao.VendaRacaoDAO;
import br.com.piscicultech.modelo.Empresa;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class AbrirFinanInsumos extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            RacaoDAO racDAO = new RacaoDAO(con.getConexao());
            VendaRacaoDAO vendRacao = new VendaRacaoDAO(con.getConexao());
            VendaQuimicoDAO vendQuim = new VendaQuimicoDAO(con.getConexao());
            request.setAttribute("racs", racDAO.getListaRacao());
            QuimicoDAO quiDAO = new QuimicoDAO(con.getConexao());
            HttpSession sessao = request.getSession();
            if (request.getParameter("data") != null) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    sessao.setAttribute("data", new Date(df.parse(request.getParameter("data")).getTime()));
                } catch (ParseException ex) {
                    Logger.getLogger(AbrirTanques.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Date data = new Date(java.util.Date.class.cast(sessao.getAttribute("data")).getTime());
            Date emp = Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao();
            request.setAttribute("quis", quiDAO.getListaQuimico());
            request.setAttribute("racs", racDAO.getListaRacao());
            request.setAttribute("vendaRacao", vendRacao.getLista(emp, data));
            request.setAttribute("vendaQuim", vendQuim.getLista(emp, data));
            url = "/financeiro-insumos.jsp";
            con.fecharConexao();
        } else {
            erro = "Não foi possível conectar-se ao banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
