/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.dao.VerificacaoDAO;
import br.com.piscicultech.modelo.Funcionario;
import br.com.piscicultech.modelo.Tanque;
import br.com.piscicultech.modelo.Verificacao;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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
public class InserirDado extends HttpServlet {

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
            HttpSession sessao = request.getSession();
            TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
            Tanque tanque = tanqDAO.getTanque(request.getParameter("tanque"));
            if (tanque != null) {
                VerificacaoDAO verDAO = new VerificacaoDAO(con.getConexao());
                Verificacao ver = new Verificacao();
                ver.setIdTanque(tanque.getId());
                ver.setCpfFunc(Funcionario.class.cast(sessao.getAttribute("usuario")).getCpf());
                ver.setHora(Time.valueOf(request.getParameter("hora")));
                Date hoje = new Date(Calendar.getInstance().getTimeInMillis());
                ver.setDtVerif(new java.sql.Date(hoje.getTime()));
                ver.setNome(request.getParameter("nome"));
                ver.setValor(Float.parseFloat(request.getParameter("valor")));
                ver.setSituacao(true);
                if (verDAO.cadastrar(ver)) {
                    url = "/AbrirTanque?tanque=" + tanque.getId();
                } else {
                    erro = "Não foi possível realizar a verificação.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível localizar tanque.";
                url = "/erro.jsp?erro=" + erro;
            }
        } else {
            erro = "Não foi possível conectar-se ao banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
