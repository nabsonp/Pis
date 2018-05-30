/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.FuncionarioDAO;
import br.com.piscicultech.modelo.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
public class CadastrarFuncionario extends HttpServlet {

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
            Funcionario func = new Funcionario();
            func.setCpf(request.getParameter("cpf"));
            func.setRg(request.getParameter("rg"));
            func.setNome(request.getParameter("nome"));
            func.setImagem("funcionarios/usuario.png");
            func.setSexo(request.getParameter("sexo"));
            func.setDtNascimento(Date.valueOf(request.getParameter("dtNasc")));
            func.setEmail(request.getParameter("email"));
            func.setSenha(request.getParameter("senha"));
            func.setInicioTrab(new Date(Calendar.getInstance().getTimeInMillis()));
            func.setNumEnd(Integer.parseInt(request.getParameter("num")));
            func.setRua(request.getParameter("rua"));
            func.setBairro(request.getParameter("bairro"));
            func.setCidade(request.getParameter("cid"));
            func.setUf(request.getParameter("uf"));
            func.setCep(request.getParameter("cep"));
            FuncionarioDAO funcDAO = new FuncionarioDAO(con.getConexao());
            if (funcDAO.cadastrar(func)) {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuario", func);
                sessao.setAttribute("usuario_logado", "true");
                url = "/Home.jsp";
            } else {
                erro = "Não foi possível cadastrar o funcionário.";
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
