/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.AdministracaoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.FoneEmpDAO;
import br.com.piscicultech.dao.FoneFuncDAO;
import br.com.piscicultech.dao.FuncionarioDAO;
import br.com.piscicultech.modelo.Empresa;
import br.com.piscicultech.modelo.FoneEmp;
import br.com.piscicultech.modelo.FoneFunc;
import br.com.piscicultech.modelo.Funcionario;
import java.io.IOException;
import java.util.ArrayList;
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
public class AbrirContaEmpresa extends HttpServlet {

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
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("empresa") != null) {
                Empresa emp = Empresa.class.cast(sessao.getAttribute("empresa"));
                AdministracaoDAO admDAO = new AdministracaoDAO(con.getConexao());
                FuncionarioDAO funcDAO = new FuncionarioDAO(con.getConexao());
                FoneEmpDAO fnDAO = new FoneEmpDAO(con.getConexao());
                FoneFuncDAO fnFuncDAO = new FoneFuncDAO(con.getConexao());
                Funcionario adm = funcDAO.getFuncionario(admDAO.getCpfAdm(emp.getCnpj()));
                if (adm != null) {
                    ArrayList<FoneEmp> fones = fnDAO.getFones(emp.getCnpj());
                    if (fones != null) {
                        ArrayList<FoneFunc> fonesFunc = fnFuncDAO.getFones(adm.getCpf());
                        if (fonesFunc != null) {
                            request.setAttribute("fones", fones);
                            request.setAttribute("fonesFunc", fonesFunc);
                            request.setAttribute("adm", adm);
                            url = "/conta-emp.jsp";
                        } else {
                            erro = "Não foi possível acessar os telefones do funcionário.";
                            url = "/erro.jsp?erro=" + erro;
                        }
                    } else {
                        erro = "Não foi possível acessar os telefones da empresa.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível acessar o administrador.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                url = "/sessao-expirada.html";
            }
        } else {
            erro = "Não foi possível conectar-se ao banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
