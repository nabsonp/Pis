/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.FornecedorDAO;
import br.com.piscicultech.dao.VendaEspecieDAO;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.Fornecedor;
import br.com.piscicultech.modelo.VendaEspecie;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AbrirFinanPeixes extends HttpServlet {

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
        boolean ok = false;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            VendaEspecieDAO veDAO = new VendaEspecieDAO(con.getConexao());
            FornecedorDAO forDAO = new FornecedorDAO(con.getConexao());
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            ArrayList<VendaEspecie> ves = veDAO.getListaVendas("pos-larvas");
            if (ves != null) {
                request.setAttribute("pos-larvas", ves);
                ves = veDAO.getListaVendas("alevinos");
                if (ves != null) {
                    request.setAttribute("alevinos", ves);
                    ves = veDAO.getListaVendas("juvenis");
                    if (ves != null) {
                        request.setAttribute("juvenis", ves);
                        ves = veDAO.getListaVendas("engorda");
                        if (ves != null) {
                            request.setAttribute("engorda", ves);
                            request.setAttribute("esps", espDAO.getListaEspecie());
                            request.setAttribute("fors", forDAO.getListaFornecedores());
                            url = "/financeiro-peixes.jsp";
                        } else {
                            erro = "Não foi possível localizar compras.";
                            url = "/erro.jsp?erro=" + erro;
                        }
                    } else {
                        erro = "Não foi possível localizar compras.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível localizar compras.";
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

}