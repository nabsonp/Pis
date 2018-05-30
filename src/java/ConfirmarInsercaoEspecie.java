/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.TanqEspDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.dao.VerificacaoDAO;
import br.com.piscicultech.dao.VerificacaoEspDAO;
import br.com.piscicultech.modelo.Funcionario;
import br.com.piscicultech.modelo.TanqEsp;
import br.com.piscicultech.modelo.Tanque;
import br.com.piscicultech.modelo.Verificacao;
import br.com.piscicultech.modelo.VerificacaoEsp;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author samsung
 */
public class ConfirmarInsercaoEspecie extends HttpServlet {

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
        int idEspecie = Integer.parseInt(request.getParameter("idEspecie"));
        int idTanque = Integer.parseInt(request.getParameter("idTanque"));
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            TanqEspDAO teDAO = new TanqEspDAO(con.getConexao());
            TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
            tanqDAO.setSituacao(true, idTanque);
            VerificacaoDAO verDAO = new VerificacaoDAO(con.getConexao());
            HttpSession session = request.getSession();
            String cpfFunc = Funcionario.class.cast(session.getAttribute("usuario")).getCpf();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date peixam = null;
            try {
                peixam = new Date(formato.parse(String.valueOf(request.getParameter("peixamento"))).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(ConfirmarInsercaoEspecie.class.getName()).log(Level.SEVERE, null, ex);
            }
            DateFormat dia = new SimpleDateFormat("dd");
            DateFormat mes = new SimpleDateFormat("MM");
            DateFormat ano = new SimpleDateFormat("yy");
            String[] dados = new String[8];
            dados[0] = "temperatura";
            dados[1] = "oxigenio";
            dados[2] = "amonia";
            dados[3] = "nitrito";
            dados[4] = "nitrato";
            dados[5] = "gasCarb";
            dados[6] = "alcal";
            dados[7] = "ph";
            boolean aux = true;
            for (int i = 0; i < 8; i++) {
                Verificacao ve = new Verificacao();
                ve.setIdTanque(idTanque);
                ve.setCpfFunc(cpfFunc);
                ve.setHora(Time.valueOf(request.getParameter("hora")));
                ve.setDtVerif(peixam);
                ve.setNome(dados[i]);
                ve.setValor(Float.parseFloat(request.getParameter(dados[i])));
                ve.setSituacao(true);
                if (verDAO.cadastrar(ve)) {
                    if (aux) {
                        aux = true;
                    }
                } else {
                    aux = false;
                }
            }
            if (aux) {
                TanqEsp te = new TanqEsp();
                te.setIdEspecie(idEspecie);
                te.setIdTanque(idTanque);
                te.setDtPeixam(peixam);
                te.setQtd(Integer.parseInt(request.getParameter("qtd")));
                te.setSituacao("VIVENDO");
                te.setBiomassa(Float.parseFloat(request.getParameter("bio")));
                te.setTipo(request.getParameter("tipo"));
                if (teDAO.cadastrar(te)) {
                    Tanque t = tanqDAO.getTanque(""+idTanque);
                    t.setPiscicultura(te.getTipo());
                    tanqDAO.editar(t);
                    VerificacaoEspDAO veEspDAO = new VerificacaoEspDAO(con.getConexao());
                    VerificacaoEsp veEsp = new VerificacaoEsp();
                    veEsp.setIdTanque(idTanque);
                    veEsp.setIdEspecie(idEspecie);
                    veEsp.setCpfFunc(cpfFunc);
                    veEsp.setHora(Time.valueOf(request.getParameter("hora")));
                    veEsp.setDtVerif(peixam);
                    veEsp.setTamanhoMedio(Float.parseFloat(request.getParameter("tam")));
                    veEsp.setPeso(Float.parseFloat(request.getParameter("peso")));
                    veEsp.setMortos(0);
                    veEsp.setNascidos(0);
                    veEsp.setQtd(Integer.parseInt(request.getParameter("qtd")));
                    if (veEspDAO.cadastrar(veEsp)) {
                        url = "/AbrirTanque?tanque=" + idTanque + "&dia=" + Integer.parseInt(dia.format(peixam)) + "&mes=" + Integer.parseInt(mes.format(peixam)) + "&ano=" + Integer.parseInt(ano.format(peixam));
                    } else {
                        erro = "Não foi possível realizar verificação biométrica.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível inserir a espécie no tanque.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível realizar verificação.";
                url = "/erro.jsp?erro=" + erro;
            }
        } else {
            erro = "Não foi possível conectar-se ao banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

}
