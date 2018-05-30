/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ArracoamentoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.TanQuimDAO;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.dao.VerificacaoDAO;
import br.com.piscicultech.dao.VerificacaoEspDAO;
import br.com.piscicultech.modelo.TanQuim;
import br.com.piscicultech.modelo.TanqEsp;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class ExcluirTanque extends HttpServlet {

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
        int idTanque = Integer.parseInt(request.getParameter("id"));
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
            TanqEspDAO teDAO = new TanqEspDAO(con.getConexao());
            ArracoamentoDAO arrDAO = new ArracoamentoDAO(con.getConexao());
            VerificacaoDAO verDAO = new VerificacaoDAO(con.getConexao());
            VerificacaoEspDAO verEspDAO = new VerificacaoEspDAO(con.getConexao());
            TanQuimDAO tqDAO = new TanQuimDAO(con.getConexao());
            //RelTanqEsp
            TanqEsp te = teDAO.getTanqEsp(idTanque);
            boolean permissao = false;
            if (te != null) {
                if (te.getQtd() == 0) {
                    permissao = true;
                }
            } else {
                permissao = true;
            }
            if (permissao) {
                teDAO.excluir(idTanque);
                arrDAO.excluir(idTanque);
                verDAO.excluir(idTanque);
                tqDAO.excluir(idTanque);
                verEspDAO.excluir(idTanque);
                    // Escluir RelTanqEsp **
                if (tanqDAO.excluir(idTanque)) {
                    url = "/AbrirTanques";
                } else {
                    erro = "Não foi possível excluir tanque.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Para realizar a exclusão de um tanque, este precisa estar vazio.";
                url = "/erro.jsp?erro=" + erro;
            }
            con.fecharConexao();
        } else {
            erro = "Não foi possível conectar-se ao banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
