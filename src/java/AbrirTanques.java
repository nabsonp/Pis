/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ArracoamentoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.dao.VerificacaoDAO;
import br.com.piscicultech.dao.VerificacaoEspDAO;
import br.com.piscicultech.modelo.Empresa;
import br.com.piscicultech.modelo.Especie;
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
public class AbrirTanques extends HttpServlet {

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
        String url = "", erro;
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
            TanqueDAO tanqueDAO = new TanqueDAO(con.getConexao());
            TanqEspDAO tanqEspDAO = new TanqEspDAO(con.getConexao());
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            ArracoamentoDAO arrDAO = new ArracoamentoDAO(con.getConexao());
            VerificacaoDAO verDAO = new VerificacaoDAO(con.getConexao());
            VerificacaoEspDAO verEspDAO = new VerificacaoEspDAO(con.getConexao());
            ArrayList<Tanque> tanques = tanqueDAO.getListaTanque(tanqueDAO.getDataCriacaoTanques(), new java.sql.Date(data.getTime()));
            ArrayList<TanqEsp> tanqEsps = new ArrayList<TanqEsp>();
            boolean aux = false;
            for (Tanque t : tanques) {
                ArrayList<Verificacao> ves = verDAO.getUltimaVerificacao(t.getId(), data);
                ArrayList<VerificacaoEsp> vesEsp = verEspDAO.getLista(t.getId(), data);
                if (ves != null) {
                    if (ves.isEmpty()) {
                        t.setSituacao(false);
                    } else {
                        t.setSituacao(true);
                        for (Verificacao v : ves) {
                            if (v.isSituacao() && t.isSituacao()) {
                                t.setSituacao(true);
                                if (vesEsp.isEmpty()) {
                                    t.setSituacao(false);
                                }
                            } else {
                                t.setSituacao(false);
                            }
                        }
                    }
                }
                TanqEsp tanqEsp = null;
                if (sessao.getAttribute("empresa") != null) {
                    tanqEsp = tanqEspDAO.getTanqEspTanque(t.getId(), Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao(), data);
                    if (tanqEsp != null) {
                        Especie esp = espDAO.getEspecie(tanqEsp.getIdEspecie());
                        request.setAttribute(t.getId() + "", esp);
                        if (t.isSituacao()) {
                            int vzs = arrDAO.getQtdArrac(t.getId(), Integer.parseInt(dia.format(data)), Integer.parseInt(mes.format(data)), Integer.parseInt(ano.format(data)));
                            if (vzs < esp.getFreqAlimMin() || vzs > esp.getFreqAlimMax()) {
                                t.setSituacao(false);
                            }
                        }
                    } else {
                        request.setAttribute(t.getId() + "", null);
                    }
                } else {
                    aux = true;
                }
            }
            if (aux) {
                url = "/sessao-expirada.jsp";
            } else {
                tanqEsps = tanqEspDAO.getListaTanqEsp(Empresa.class.cast(sessao.getAttribute("empresa")).getDtCriacao(), data);
                request.setAttribute("tanqEsps", tanqEsps);
                request.setAttribute("primeiro", tanqueDAO.getDataCriacaoTanques());
                request.setAttribute("tanques", tanques);
                url = "/tanques.jsp";
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
