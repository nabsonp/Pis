/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.modelo.Empresa;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.TanqEsp;
import br.com.piscicultech.modelo.Tanque;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EscolherTanqueRel extends HttpServlet {

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
            DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            Date fim, inicio;
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("empresa") != null) {
                if (sessao.getAttribute("inicio") == null && sessao.getAttribute("fim") == null) {
                    try {
                        sessao.setAttribute("inicio", new Date(fmt.parse(String.valueOf(request.getParameter("inicio"))).getTime()));
                        sessao.setAttribute("fim", new Date(fmt.parse(String.valueOf(request.getParameter("fim"))).getTime()));
                    } catch (ParseException ex) {
                        Logger.getLogger(EscolherTanqueRel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                inicio = Date.valueOf(String.valueOf(sessao.getAttribute("inicio")));
                fim = Date.valueOf(String.valueOf(sessao.getAttribute("fim")));
                TanqueDAO tanqueDAO = new TanqueDAO(con.getConexao());
                ArrayList<Tanque> tanques = tanqueDAO.getListaTanque(Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao(), fim);
                EspecieDAO espDAO = new EspecieDAO(con.getConexao());
                TanqEspDAO teDAO = new TanqEspDAO(con.getConexao());
                ArrayList<TanqEsp> tes = new ArrayList<TanqEsp>();
                if (tanques != null) {
                    for (Tanque t : tanques) {
                        TanqEsp te = teDAO.getTanqEsp(t.getId());
                        tes.add(te);
                        Especie esp = espDAO.getEspecie(te.getIdEspecie());
                        request.setAttribute(t.getId() + "", esp);
                    }
                    request.setAttribute("tanques", tanques);
                    request.setAttribute("tanqEsps", tes);
                    url = "/rel-tanques.jsp";
                } else {
                    erro = "Não foi possível acessar os tanques.";
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
