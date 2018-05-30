/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.DespesaDAO;
import br.com.piscicultech.dao.FornDespDAO;
import br.com.piscicultech.modelo.Despesa;
import br.com.piscicultech.modelo.FornDesp;
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
public class AbrirFinanContas extends HttpServlet {

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
            FornDespDAO fdDAO = new FornDespDAO(con.getConexao());
            DespesaDAO despDAO = new DespesaDAO(con.getConexao());
            ArrayList<FornDesp> fds = fdDAO.getListaFornDesp();
            if (fds != null) {
                ArrayList<Despesa> desps = new ArrayList<Despesa>();
                for (FornDesp fd : fds) {
                    desps.add(despDAO.getDespesa(fd.getCodDesp()));
                }
                request.setAttribute("fds", fds);
                request.setAttribute("desps", desps);
                url = "/financeiro-contas.jsp";
            } else {
                erro = "Não foi possível acessar as contas.";
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
