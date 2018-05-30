/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.FornecedorDAO;
import br.com.piscicultech.dao.QuimicoDAO;
import br.com.piscicultech.dao.RacaoDAO;
import br.com.piscicultech.dao.VendaRacaoDAO;
import br.com.piscicultech.modelo.Fornecedor;
import br.com.piscicultech.modelo.Quimico;
import br.com.piscicultech.modelo.Racao;
import br.com.piscicultech.modelo.VendaRacao;
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
public class EscolherInsumo extends HttpServlet {

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
        String url = "", erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            if (tipo.equals("racao")) {
                RacaoDAO racDAO = new RacaoDAO(con.getConexao());
                ArrayList<Racao> racs = racDAO.getListaRacao();
                if (racs != null) {
                    VendaRacaoDAO venDAO = new VendaRacaoDAO(con.getConexao());
                    ArrayList<VendaRacao> vendas = new ArrayList<VendaRacao>();
                    for (Racao r : racs) {
                        vendas.add(venDAO.getUltimaVenda(r.getCodigo()));
                    }
                    request.setAttribute("prods", racs);
                    request.setAttribute("vendas", vendas);
                    url = "/escolher-insumo.jsp";
                } else {
                    erro = "Não foi possível acessar as rações cadastradas.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                if (tipo.equals("quimico")) {
                    QuimicoDAO quimDAO = new QuimicoDAO(con.getConexao());
                    ArrayList<Quimico> quims = quimDAO.getListaQuimico();
                    if (quims != null) {
                        request.setAttribute("prods", quims);
                        url = "/escolher-insumo.jsp";
                    } else {
                        erro = "Não foi possível acessar os produtos químicos cadastradas.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                }
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
