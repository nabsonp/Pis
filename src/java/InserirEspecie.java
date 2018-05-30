/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.TanqEsp;
import br.com.piscicultech.modelo.Tanque;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class InserirEspecie extends HttpServlet {

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
        String nomeEsp = request.getParameter("especie");
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            TanqEspDAO tanqEspDAO = new TanqEspDAO(con.getConexao());
            Especie esp = espDAO.getEspecie(nomeEsp);
            TanqEsp tanqEsp = new TanqEsp();
            tanqEsp.setIdTanque(Integer.parseInt(request.getParameter("tanque")));
            tanqEsp.setIdEspecie(esp.getId());
            Date hoje = new Date(Calendar.getInstance().getTimeInMillis());
            tanqEsp.setDtPeixam(hoje);
            tanqEsp.setQtd(Integer.parseInt(request.getParameter("qtd")));
            tanqEsp.setBiomassa(Float.parseFloat(request.getParameter("bio")));
            tanqEsp.setSituacao(request.getParameter("situacao"));
            tanqEsp.setTipo(request.getParameter("tipo"));
            if (tanqEspDAO.cadastrar(tanqEsp)) {
                TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
                Tanque tanq = tanqDAO.getTanque(request.getParameter("tanque"));
                if (tanq != null) {
                    tanq.setPiscicultura(tanqEsp.getTipo());
                    tanq.setSituacao(false);
                    tanqDAO.editar(tanq);
                    if (tanqDAO.editar(tanq)) {
                        url = "/AbrirTanque?tanque=" + tanq.getId();
                    } else {
                        erro = "Não foi possível atualizar a situação do tanque.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível localizar o tanque.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível inserir a espécie no tanque.";
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
