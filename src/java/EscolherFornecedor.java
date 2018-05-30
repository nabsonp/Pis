/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.FornecedorDAO;
import br.com.piscicultech.modelo.Fornecedor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class EscolherFornecedor extends HttpServlet {

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
        String tipo = request.getParameter("tipo");
        String prod = request.getParameter("prod");
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            FornecedorDAO fornDAO = new FornecedorDAO(con.getConexao());
            ArrayList<Fornecedor> forns = fornDAO.getListaFornecedores();
            if (forns != null) {
                request.setAttribute("forns", forns);
                request.setAttribute("tipo", tipo);
                request.setAttribute("prod", prod);
                url = "/escolher-fornecedor.jsp";
            } else {
                erro = "Não foi possível acessar os fornecedores.";
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
