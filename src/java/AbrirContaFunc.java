/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.CargoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.ExercaoDAO;
import br.com.piscicultech.dao.FoneFuncDAO;
import br.com.piscicultech.dao.PagamentoFuncDAO;
import br.com.piscicultech.modelo.Cargo;
import br.com.piscicultech.modelo.Exercao;
import br.com.piscicultech.modelo.FoneFunc;
import br.com.piscicultech.modelo.Funcionario;
import br.com.piscicultech.modelo.PagamentoFunc;
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
public class AbrirContaFunc extends HttpServlet {

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
            if (sessao.getAttribute("usuario") != null) {
                Funcionario func = Funcionario.class.cast(sessao.getAttribute("usuario"));
                FoneFuncDAO fnDAO = new FoneFuncDAO(con.getConexao());
                ExercaoDAO exeDAO = new ExercaoDAO(con.getConexao());
                CargoDAO carDAO = new CargoDAO(con.getConexao());
                PagamentoFuncDAO pgDAO = new PagamentoFuncDAO(con.getConexao());
                ArrayList<FoneFunc> fones = fnDAO.getFones(func.getCpf());
                if (fones != null) {
                    Exercao exe = exeDAO.getExercao(func.getCpf());
                    if (exe != null) {
                        Cargo car = carDAO.getCargo(exe.getCodCargo());
                        if (car != null) {
                            ArrayList<PagamentoFunc> pgs = pgDAO.getPags(func.getCpf());
                            if (pgs != null) {
                                request.setAttribute("fones", fones);
                                request.setAttribute("exercao", exe);
                                request.setAttribute("cargo", car);
                                request.setAttribute("pgs", pgs);
                                url = "/conta-func.jsp";
                            } else {
                                erro = "Não foi possível acessar os pagamentos do funcionario.";
                                url = "/erro.jsp?erro=" + erro;
                            }
                        } else {
                            erro = "Não foi possível verificar o cargo do funcionário.";
                            url = "/erro.jsp?erro=" + erro;
                        }
                    } else {
                        erro = "Não foi possível verificar o tempo de exerção de cargo.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível acessar os telefones do funcionário.";
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
