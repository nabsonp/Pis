/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ArracoamentoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.QuimicoDAO;
import br.com.piscicultech.dao.RacaoDAO;
import br.com.piscicultech.dao.TanQuimDAO;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.dao.VerificacaoDAO;
import br.com.piscicultech.dao.VerificacaoEspDAO;
import br.com.piscicultech.modelo.Arracoamento;
import br.com.piscicultech.modelo.Empresa;
import br.com.piscicultech.modelo.Quimico;
import br.com.piscicultech.modelo.Racao;
import br.com.piscicultech.modelo.TanQuim;
import br.com.piscicultech.modelo.TanqEsp;
import br.com.piscicultech.modelo.Tanque;
import br.com.piscicultech.modelo.Verificacao;
import br.com.piscicultech.modelo.VerificacaoEsp;
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
public class AbrirTanque extends HttpServlet {

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
        String url = "", erro;
        String idTanque = request.getParameter("tanque");
        HttpSession sessao = request.getSession();
        if (request.getParameter("data") != null) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                sessao.setAttribute("data", new Date(df.parse(request.getParameter("data")).getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(AbrirTanques.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Date data = new Date(java.util.Date.class.cast(sessao.getAttribute("data")).getTime());
        DateFormat dia = new SimpleDateFormat("dd");
        DateFormat mes = new SimpleDateFormat("MM");
        DateFormat ano = new SimpleDateFormat("yy");
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            TanqEspDAO tanqEspDAO = new TanqEspDAO(con.getConexao());
            TanQuimDAO tqDAO = new TanQuimDAO(con.getConexao());
            ArracoamentoDAO arrDAO = new ArracoamentoDAO(con.getConexao());
            VerificacaoDAO verDAO = new VerificacaoDAO(con.getConexao());
            VerificacaoEspDAO verEspDAO = new VerificacaoEspDAO(con.getConexao());
            QuimicoDAO quimDAO = new QuimicoDAO(con.getConexao());
            Tanque tanque = tanqDAO.getTanque(idTanque);
            if (tanque != null) {
                ArrayList<Tanque> tanques = tanqDAO.getListaTanque();
                ArrayList<Verificacao> ves = verDAO.getVerificacoes(Integer.parseInt(idTanque), data);
                if (ves != null) {
                    RacaoDAO racDAO = new RacaoDAO(con.getConexao());
                    ArrayList<Arracoamento> arrs = arrDAO.getArracoamentos(Integer.parseInt(idTanque), Integer.parseInt(dia.format(data)), Integer.parseInt(mes.format(data)), Integer.parseInt(ano.format(data)));
                    if (arrs != null) {
                        ArrayList<Racao> racoes = racDAO.getListaRacao();
                        TanqEsp tanqEsp = null;
                        ArrayList<VerificacaoEsp> veEsps = verEspDAO.getLista(tanque.getId(), data);
                        if (veEsps != null) {
                            ArrayList<TanQuim> tqs = tqDAO.getLista(Integer.parseInt(idTanque), data);
                            if (tqs != null) {
                                ArrayList<Quimico> quis = quimDAO.getListaQuimico();
                                if (quis != null) {
                                    if (sessao.getAttribute("empresa") != null) {
                                        tanqEsp = tanqEspDAO.getTanqEspTanque(tanque.getId(), Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao(), data);
                                        if (tanqEsp != null) {
                                            System.out.println("dssdvf " + tanqEsp.getDtPeixam());
                                            request.setAttribute("esp", espDAO.getEspecie(tanqEsp.getIdEspecie()));
                                        } else {
                                            request.setAttribute("esp", null);
                                        }
                                    }
                                    request.setAttribute("ves", ves);
                                    request.setAttribute("arrs", arrs);
                                    request.setAttribute("racoes", racoes);
                                    request.setAttribute("tanque", tanque);
                                    request.setAttribute("tanques", tanques);
                                    request.setAttribute("tanqEsp", tanqEsp);
                                    request.setAttribute("verEsps", veEsps);
                                    request.setAttribute("tqs", tqs);
                                    request.setAttribute("quis", quis);
                                    url = "/dados-tanque.jsp";
                                } else {
                                    erro = "Não foi possível acessar os produtos químicos.";
                                    url = "/erro.jsp?erro=" + erro;
                                }
                            } else {
                                erro = "Não foi possível acessar as inserções de insumos no tanque.";
                                url = "/erro.jsp?erro=" + erro;
                            }
                        } else {
                            erro = "Não foi possível acessar os exames biométricos feitos neste tanque.";
                            url = "/erro.jsp?erro=" + erro;
                        }
                    } else {
                        erro = "Não foi possível acessar os arraçoamentos feitos neste tanque.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível acessar as verificações realizadas no tanque.";
                    url = "/erro.jsp?erro=" + erro;
                }
                con.fecharConexao();
            } else {
                erro = "Não foi possível localizar o tanque no banco de dados.";
                url = "/erro.jsp?erro=" + erro;
            }
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
