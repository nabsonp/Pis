/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.modelo.Tanque;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class CadastrarTanque extends HttpServlet {

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
            Tanque tanque = new Tanque();
            tanque.setTipo(request.getParameter("tipo"));
            tanque.setPiscicultura("-"); // pegar do radio
            tanque.setRevestimento(request.getParameter("revest"));
            tanque.setMaterial(request.getParameter("mat"));
            tanque.setCapacidade(Double.parseDouble(request.getParameter("cap")));
            tanque.setComp(Double.parseDouble(request.getParameter("comp")));
            tanque.setLargura(Double.parseDouble(request.getParameter("larg")));
            tanque.setProfund(Double.parseDouble(request.getParameter("prof")));
            tanque.setVazao(Double.parseDouble(request.getParameter("vazao")));
            TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
            if (tanqDAO.cadastrar(tanque)) {
                url = "/AbrirTanques";
            } else {
                erro = "Não foi possível cadastrar o tanque.";
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
