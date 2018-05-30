/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.modelo.Especie;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class EditarEspecie extends HttpServlet {

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
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            Especie esp = espDAO.getEspecie(Integer.parseInt(request.getParameter("id")));
            if (esp != null) {
                if (request.getParameter("nome") != null) {
                    esp.setNome(request.getParameter("nome"));
                }
                if (request.getParameter("nomeCient") != null) {
                    esp.setNomeCient(request.getParameter("nomeCient"));
                }
                if (request.getParameter("tamMin") != null) {
                    if (!request.getParameter("tamMin").equals("")) {
                        esp.setTamMin(Float.parseFloat(request.getParameter("tamMin")));
                    }
                }
                if (request.getParameter("tamMax") != null) {
                    if (!request.getParameter("tamMax").equals("")) {
                        esp.setTamMax(Float.parseFloat(request.getParameter("tamMax")));
                    }
                }
                if (request.getParameter("pesoMin") != null) {
                    if (!request.getParameter("pesoMin").equals("")) {
                        esp.setPesoMin(Float.parseFloat(request.getParameter("pesoMin")));
                    }
                }
                if (request.getParameter("pesoMax") != null) {
                    if (!request.getParameter("pesoMax").equals("")) {
                        esp.setPesoMax(Float.parseFloat(request.getParameter("pesoMax")));
                    }
                }
                if (request.getParameter("racaoDia") != null) {
                    if (!request.getParameter("racaoDia").equals("")) {
                        esp.setRacaoDia(Float.parseFloat(request.getParameter("racaoDia")));
                    }
                }
                if (request.getParameter("freqAlimMin") != null) {
                    if (!request.getParameter("freqAlimMin").equals("")) {
                        esp.setFreqAlimMin(Integer.parseInt(request.getParameter("freqAlimMin")));
                    }
                }
                if (request.getParameter("freqAlimMax") != null) {
                    if (!request.getParameter("freqAlimMax").equals("")) {
                        esp.setFreqAlimMax(Integer.parseInt(request.getParameter("freqAlimMax")));
                    }
                }
                if (request.getParameter("tempMin") != null) {
                    if (!request.getParameter("tempMin").equals("")) {
                        esp.setTemperaturaMin(Float.parseFloat(request.getParameter("tempMin")));
                    }
                }
                if (request.getParameter("tempMax") != null) {
                    if (!request.getParameter("tempMax").equals("")) {
                        esp.setTemperaturaMax(Float.parseFloat(request.getParameter("tempMax")));
                    }
                }
                if (request.getParameter("oxiMin") != null) {
                    if (!request.getParameter("oxiMin").equals("")) {
                        esp.setOxigenioMin(Float.parseFloat(request.getParameter("oxiMin")));
                    }
                }
                if (request.getParameter("oxiMax") != null) {
                    if (!request.getParameter("oxiMax").equals("")) {
                        esp.setOxigenioMax(Float.parseFloat(request.getParameter("oxiMax")));
                    }
                }
                if (request.getParameter("phMin") != null) {
                    if (!request.getParameter("phMin").equals("")) {
                        esp.setPhMin(Float.parseFloat(request.getParameter("phMin")));
                    }
                }
                if (request.getParameter("phMax") != null) {
                    if (!request.getParameter("phMax").equals("")) {
                        esp.setPhMax(Float.parseFloat(request.getParameter("phMax")));
                    }
                }
                if (request.getParameter("amoniaMin") != null) {
                    if (!request.getParameter("amoniaMin").equals("")) {
                        esp.setAmoniaMin(Float.parseFloat(request.getParameter("amoniaMin")));
                    }
                }
                if (request.getParameter("amoniaMax") != null) {
                    if (!request.getParameter("amoniaMax").equals("")) {
                        esp.setAmoniaMax(Float.parseFloat(request.getParameter("amoniaMax")));
                    }
                }
                if (request.getParameter("gasCarbMin") != null) {
                    if (!request.getParameter("gasCarbMin").equals("")) {
                        esp.setGasCarbonicoMin(Float.parseFloat(request.getParameter("gasCarbMin")));
                    }
                }
                if (request.getParameter("gasCarbMax") != null) {
                    if (!request.getParameter("gasCarbMax").equals("")) {
                        esp.setGasCarbonicoMax(Float.parseFloat(request.getParameter("gasCarbMax")));
                    }
                }
                if (request.getParameter("nitritoMin") != null) {
                    if (!request.getParameter("nitritoMin").equals("")) {
                        esp.setNitritoMin(Float.parseFloat(request.getParameter("nitritoMin")));
                    }
                }
                if (request.getParameter("nitritoMax") != null) {
                    if (!request.getParameter("nitritoMax").equals("")) {
                        esp.setNitritoMax(Float.parseFloat(request.getParameter("nitritoMax")));
                    }
                }
                if (request.getParameter("nitratoMin") != null) {
                    if (!request.getParameter("nitratoMin").equals("")) {
                        esp.setNitratoMin(Float.parseFloat(request.getParameter("nitratoMin")));
                    }
                }
                if (request.getParameter("nitratoMax") != null) {
                    if (!request.getParameter("nitratoMax").equals("")) {
                        esp.setNitratoMax(Float.parseFloat(request.getParameter("nitratoMax")));
                    }
                }
                if (request.getParameter("alcalMin") != null) {
                    if (!request.getParameter("alcalMin").equals("")) {
                        esp.setAlcalinidadeMin(Float.parseFloat(request.getParameter("alcalMin")));
                    }
                }
                if (request.getParameter("alcalMax") != null) {
                    if (!request.getParameter("alcalMax").equals("")) {
                        esp.setAlcalinidadeMax(Float.parseFloat(request.getParameter("alcalMax")));
                    }
                }
                if (espDAO.editar(esp)) {
                    url = "/AbrirEspecie";
                } else {
                    erro = "Não foi possível realizar alterações.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível localizar a espécie para realizar as alterações.";
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
