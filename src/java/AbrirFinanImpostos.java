/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.ImpTaxAluDAO;
import br.com.piscicultech.dao.PagamentoImpTaxAluDAO;
import br.com.piscicultech.modelo.ImpTaxAlu;
import br.com.piscicultech.modelo.PagamentoImpTaxAlu;
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
public class AbrirFinanImpostos extends HttpServlet {

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
            ImpTaxAluDAO itaDAO = new ImpTaxAluDAO(con.getConexao());
            PagamentoImpTaxAluDAO pitaDAO = new PagamentoImpTaxAluDAO(con.getConexao());
            ArrayList<PagamentoImpTaxAlu> pitas = pitaDAO.getListaPagamentos();
            ArrayList<ImpTaxAlu> itas = itaDAO.getListaImpTaxAlus();
            if (itas != null) {
                if (pitas != null) {
                    request.setAttribute("itas", itas);
                    request.setAttribute("pitas", pitas);
                    url = "/financeiro-impostos.jsp";
                } else {
                erro = "Não foi possível localizar os pagamanetos dos impostos, taxas e aluguéis.";
                url = "/erro.jsp?erro=" + erro;
            }
            } else {
                erro = "Não foi possível localizar os impostos, taxas e aluguéis.";
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
