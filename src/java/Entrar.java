/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.AdministracaoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EmpresaDAO;
import br.com.piscicultech.dao.FuncionarioDAO;
import br.com.piscicultech.dao.PagamentoFuncDAO;
import br.com.piscicultech.modelo.Funcionario;
import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
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
public class Entrar extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            HttpSession sessao = request.getSession();
            Funcionario usu = new Funcionario();
            usu.setEmail(request.getParameter("email"));
            usu.setSenha(request.getParameter("senha"));
            FuncionarioDAO usuDAO = new FuncionarioDAO(con.getConexao());
            EmpresaDAO empDAO = new EmpresaDAO(con.getConexao());
            PagamentoFuncDAO pagDAO = new PagamentoFuncDAO(con.getConexao());
            AdministracaoDAO admDAO = new AdministracaoDAO(con.getConexao());
            usu = usuDAO.validarLogin(usu);
            if (usu != null) {
                Date data = new Date(Calendar.getInstance().getTimeInMillis());
                sessao.setAttribute("data", data);       
                sessao.setAttribute("empresa", empDAO.getEmpresa(pagDAO.getCnpjEmp(usu.getCpf())));
                sessao.setAttribute("usuario", usu);
                sessao.setAttribute("usuario-logado", "true");
                if (admDAO.getCnpjEmp(usu.getCpf()) == null) {
                    sessao.setAttribute("ADM", false);
                } else {
                    sessao.setAttribute("ADM", true);
                }
                url = "/Home.jsp";
            } else {
                sessao.setAttribute("usuario", null);
                sessao.setAttribute("usuario-logado", "erro");
                url = "/Login.jsp";
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
