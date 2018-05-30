/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ArracoamentoDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.RacaoDAO;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.modelo.Arracoamento;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.Racao;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class Arracoar extends HttpServlet {

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
        int idTanque = Integer.parseInt(request.getParameter("idTanque"));
        String url, erro;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            Arracoamento arra = new Arracoamento();
            RacaoDAO racDAO = new RacaoDAO(con.getConexao());
            float arracoado = Float.parseFloat(request.getParameter("peso"));
            Racao rac = racDAO.getRacao(request.getParameter("nomeRacao"));
            if (arracoado <= rac.getPesoTotal()) {
                arra.setIdTanque(idTanque);
                arra.setCodRacao(rac.getCodigo());
                HttpSession sessao = request.getSession();
                java.util.Date data = java.util.Date.class.cast(sessao.getAttribute("data"));
                DateFormat dia = new SimpleDateFormat("dd");
                DateFormat mes = new SimpleDateFormat("MM");
                DateFormat ano = new SimpleDateFormat("yy");
                arra.setDia(Integer.parseInt(dia.format(data)));
                arra.setMes(Integer.parseInt(mes.format(data)));
                arra.setAno(Integer.parseInt(ano.format(data)));
                arra.setPeso(arracoado);
                arra.setHora(Time.valueOf(request.getParameter("hora")));
                EspecieDAO espDAO = new EspecieDAO(con.getConexao());
                ArracoamentoDAO arraDAO = new ArracoamentoDAO(con.getConexao());
                /*int vzs = arraDAO.getArracoamentos(idTanque, Integer.parseInt(dia.format(data)), Integer.parseInt(mes.format(data)), Integer.parseInt(ano.format(data))).size();
                if (vzs != 0) {
                    TanqEspDAO teDAO = new TanqEspDAO(con.getConexao());
                    TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
                    Especie esp = espDAO.getEspecie(teDAO.getTanqEsp(idTanque).getIdEspecie());
                    if (esp.getFreqAlimMin() > vzs || esp.getFreqAlimMax() < vzs) {
                    tanqDAO.setSituacao(false, idTanque);
                } else {
                    tanqDAO.setSituacao(true, idTanque);
                }
                }*/
                if (arraDAO.cadastrar(arra)) {
                    rac.setPesoTotal(rac.getPesoTotal()-arracoado);
                    racDAO.arracoar(rac);
                    url = "/AbrirTanque?tanque=" + idTanque;
                } else {
                    erro = "Não foi possível realizar o arraçoamento.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                    erro = "Não foi possível realizar o arraçoamento, pois a quantidade de ração informada é maior que a em estoque.";
                    url = "/erro.jsp?erro=" + erro;
                }
            con.fecharConexao();
        } else {
            erro = "Não foi possível estabelecer conexão com o banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
