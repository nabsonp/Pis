/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.CargoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.ExercaoDAO;
import br.com.piscicultech.dao.FuncionarioDAO;
import br.com.piscicultech.modelo.Cargo;
import br.com.piscicultech.modelo.Exercao;
import br.com.piscicultech.modelo.Funcionario;
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
public class AbrirFinanMaoDeObra extends HttpServlet {

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
            FuncionarioDAO funcDAO = new FuncionarioDAO(con.getConexao());
            CargoDAO carDAO = new CargoDAO(con.getConexao());
            ExercaoDAO exDAO = new ExercaoDAO(con.getConexao());
            ArrayList<Funcionario> funcs = funcDAO.getListaFuncionarios();
            if (funcs != null) {
                request.setAttribute("funcs", funcs);
                ArrayList<Exercao> exs = exDAO.getListExercoes();
                if (exs != null) {
                    request.setAttribute("exs", exs);
                    ArrayList<Cargo> cars = carDAO.getListaCargos();
                    if (cars != null) {
                        request.setAttribute("cars", cars);
                        url = "/financeiro-mao-de-obra.jsp";
                    } else {
                        erro = "Não foi possível acessar as exerções dos cargos.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível acessar as exerções dos cargos.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível acessar os funcionários.";
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