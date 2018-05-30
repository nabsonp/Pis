/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.QuimicoDAO;
import br.com.piscicultech.dao.TanQuimDAO;
import br.com.piscicultech.modelo.Quimico;
import br.com.piscicultech.modelo.TanQuim;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
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
public class InserirInsumo extends HttpServlet {

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
        int idTanque = Integer.parseInt(request.getParameter("idTanque"));
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            HttpSession sessao = request.getSession();
            TanQuimDAO tqDAO = new TanQuimDAO(con.getConexao());
            QuimicoDAO quiDAO = new QuimicoDAO(con.getConexao());
            Float peso = Float.parseFloat(request.getParameter("peso"));
            String NomeQuim = request.getParameter("nomeQuim");
            Time hora = Time.valueOf(request.getParameter("hora"));
            java.util.Date data = java.util.Date.class.cast(sessao.getAttribute("data"));
            Quimico q = quiDAO.getQuimico(NomeQuim);
            if (q.getPesoTotal() >= peso) {
                TanQuim tq = new TanQuim();
                tq.setCodQuimico(q.getCodigo());
                tq.setIdTanque(idTanque);
                tq.setDtInsercao(new Date(data.getTime()));
                tq.setHoraInsercao(hora);
                tq.setPeso(peso);
                if (tqDAO.cadastrar(tq)) {
                    q.setPesoTotal((q.getPesoTotal() - peso));
                    if (quiDAO.inserir(q)) {
                        url = "/AbrirTanque?tanque="+idTanque;
                    } else {
                        erro = "Não foi possível descontar a quantidade inserida no estoque";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível inserir químico.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "A quantidade em gramas do produto excede a armazenada em estoque.";
                url = "/erro.jsp?erro=" + erro;
            }
        } else {
            erro = "Não foi possível estabelecer conexão com o banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
