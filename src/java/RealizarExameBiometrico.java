/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.VerificacaoEspDAO;
import br.com.piscicultech.modelo.Funcionario;
import br.com.piscicultech.modelo.TanqEsp;
import br.com.piscicultech.modelo.VerificacaoEsp;
import java.io.IOException;
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
public class RealizarExameBiometrico extends HttpServlet {

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
        String erro, url;
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("usuario") != null) {
                VerificacaoEspDAO veEspDAO = new VerificacaoEspDAO(con.getConexao());
                TanqEspDAO teDAO = new TanqEspDAO(con.getConexao());
                int idTanque = Integer.parseInt(request.getParameter("idTanque"));
                TanqEsp te = teDAO.getTanqEsp(idTanque);
                VerificacaoEsp veEsp = new VerificacaoEsp();
                int mortos = Integer.parseInt(request.getParameter("mortos"));
                int nascidos = Integer.parseInt(request.getParameter("nascidos"));
                veEsp.setIdTanque(idTanque);
                veEsp.setIdEspecie(Integer.parseInt(request.getParameter("idEspecie")));
                veEsp.setCpfFunc(Funcionario.class.cast(sessao.getAttribute("usuario")).getCpf());
                veEsp.setHora(Time.valueOf(request.getParameter("hora")));
                veEsp.setDtVerif(new Date(java.util.Date.class.cast(sessao.getAttribute("data")).getTime()));
                veEsp.setTamanhoMedio(Float.parseFloat(request.getParameter("tamanhoMedio")));
                veEsp.setPeso(Float.parseFloat(request.getParameter("pesoMedio")));
                veEsp.setMortos(mortos);
                veEsp.setNascidos(nascidos);
                int qtd = te.getQtd();
                if (mortos < qtd + nascidos) {
                    qtd = qtd - mortos + nascidos;
                    veEsp.setQtd(qtd);
                    if (veEspDAO.cadastrar(veEsp)) {
                        te.setQtd(qtd);
                        teDAO.editar(te);
                        url = "/AbrirTanque?tanque=" + idTanque;
                    } else {
                        erro = "Não foi possível realizar verificação biométrica.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "O número de peixes no tanque será negativo.";
                    url = "/erro.jsp?erro=" + erro;
                }

            } else {
                url = "/sessao-expirada.html";
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
