/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.modelo.Especie;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class CadastrarEspecie extends HttpServlet {

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
            Especie esp = new Especie();
            esp.setNome(request.getParameter("nome"));
            esp.setNomeCient(request.getParameter("nomeCient"));
            esp.setImagem("especies/imagem-placeholder.png");
            esp.setTamMin(Float.parseFloat(request.getParameter("tamMin")));
            esp.setTamMax(Float.parseFloat(request.getParameter("tamMax")));
            esp.setPesoMin(Float.parseFloat(request.getParameter("pesoMin")));
            esp.setPesoMax(Float.parseFloat(request.getParameter("pesoMax")));
            esp.setRacaoDia(Float.parseFloat(request.getParameter("racaoDia")));
            esp.setFreqAlimMin(Integer.parseInt(request.getParameter("freqAlimMin")));
            esp.setFreqAlimMax(Integer.parseInt(request.getParameter("freqAlimMax")));
            esp.setTemperaturaMin(Float.parseFloat(request.getParameter("temperaturaMin")));
            esp.setTemperaturaMax(Float.parseFloat(request.getParameter("temperaturaMax")));
            esp.setOxigenioMin(Float.parseFloat(request.getParameter("oxigenioMin")));
            esp.setOxigenioMax(Float.parseFloat(request.getParameter("oxigenioMax")));
            esp.setPhMin(Float.parseFloat(request.getParameter("phMin")));
            esp.setPhMax(Float.parseFloat(request.getParameter("phMax")));
            esp.setAmoniaMin(Float.parseFloat(request.getParameter("amoniaMin")));
            esp.setAmoniaMax(Float.parseFloat(request.getParameter("amoniaMax")));
            esp.setGasCarbonicoMin(Float.parseFloat(request.getParameter("gasCarbonicoMin")));
            esp.setGasCarbonicoMax(Float.parseFloat(request.getParameter("gasCarbonicoMax")));
            esp.setNitritoMin(Float.parseFloat(request.getParameter("nitritoMin")));
            esp.setNitritoMax(Float.parseFloat(request.getParameter("nitritoMax")));
            esp.setNitratoMin(Float.parseFloat(request.getParameter("nitratoMin")));
            esp.setNitratoMax(Float.parseFloat(request.getParameter("nitratoMax")));
            esp.setAlcalinidadeMin(Float.parseFloat(request.getParameter("alcalMin")));
            esp.setAlcalinidadeMax(Float.parseFloat(request.getParameter("alcalMax")));
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            if (espDAO.cadastrar(esp)) {
                url = "/AbrirEspecies";
            } else {
                erro = "Não foi possível cadastrar a espécie.";
                url = "/erro.jsp?erro=" + erro;
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
