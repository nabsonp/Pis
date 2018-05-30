/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.VerificacaoDAO;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.Funcionario;
import br.com.piscicultech.modelo.Verificacao;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author samsung
 */
public class RealizarVerificacaoGeral extends HttpServlet {

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
        int idTanque = Integer.parseInt(request.getParameter("idTanque"));
        int idEspecie = Integer.parseInt(request.getParameter("idEspecie"));
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            VerificacaoDAO verDAO = new VerificacaoDAO(con.getConexao());
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            Especie esp = espDAO.getEspecie(idEspecie);
            HttpSession session = request.getSession();
            String cpfFunc = Funcionario.class.cast(session.getAttribute("usuario")).getCpf();
            HttpSession sessao = request.getSession();
            Date peixam = new Date(java.util.Date.class.cast(sessao.getAttribute("data")).getTime());
            DateFormat dia = new SimpleDateFormat("dd");
            DateFormat mes = new SimpleDateFormat("MM");
            DateFormat fullAno = new SimpleDateFormat("yyyy");
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
                if (i == 0) {
                    if (ve.getValor() < esp.getTemperaturaMin() || ve.getValor() > esp.getTemperaturaMax()) {
                        ve.setSituacao(false);
                    } else {
                        ve.setSituacao(true);
                    }
                } else {
                    if (i == 1) {
                        if (ve.getValor() < esp.getOxigenioMin() || ve.getValor() > esp.getOxigenioMax()) {
                            ve.setSituacao(false);
                        } else {
                            ve.setSituacao(true);
                        }
                    } else {
                        if (i == 2) {
                            if (ve.getValor() < esp.getAmoniaMin() || ve.getValor() > esp.getAmoniaMax()) {
                                ve.setSituacao(false);
                            } else {
                                ve.setSituacao(true);
                            }
                        } else {
                            if (i == 3) {
                                if (ve.getValor() < esp.getNitritoMin() || ve.getValor() > esp.getNitritoMax()) {
                                    ve.setSituacao(false);
                                } else {
                                    ve.setSituacao(true);
                                }
                            } else {
                                if (i == 4) {
                                    if (ve.getValor() < esp.getNitratoMin() || ve.getValor() > esp.getNitratoMax()) {
                                        ve.setSituacao(false);
                                    } else {
                                        ve.setSituacao(true);
                                    }
                                } else {
                                    if (i == 5) {
                                        if (ve.getValor() < esp.getGasCarbonicoMin() || ve.getValor() > esp.getGasCarbonicoMax()) {
                                            ve.setSituacao(false);
                                        } else {
                                            ve.setSituacao(true);
                                        }
                                    } else {
                                        if (i == 6) {
                                            if (ve.getValor() < esp.getAlcalinidadeMin() || ve.getValor() > esp.getAlcalinidadeMax()) {
                                                ve.setSituacao(false);
                                            } else {
                                                ve.setSituacao(true);
                                            }
                                        } else {
                                            if (i == 7) {
                                                if (ve.getValor() < esp.getPhMin() || ve.getValor() > esp.getPhMax()) {
                                                    ve.setSituacao(false);
                                                } else {
                                                    ve.setSituacao(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (verDAO.cadastrar(ve)) {
                    if (aux) {
                        aux = true;
                    }
                } else {
                    aux = false;
                }
            }
            if (aux) {
                url = "/AbrirTanque?tanque=" + idTanque + "&dia=" + Integer.parseInt(dia.format(peixam)) + "&mes=" + Integer.parseInt(mes.format(peixam)) + "&ano=" + Integer.parseInt(fullAno.format(peixam));
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
