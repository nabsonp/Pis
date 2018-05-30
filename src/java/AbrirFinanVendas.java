/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.CompradorDAO;
import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EmpresaDAO;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.NegocioDAO;
import br.com.piscicultech.modelo.Comprador;
import br.com.piscicultech.modelo.Empresa;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.Negocio;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class AbrirFinanVendas extends HttpServlet {

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
            EmpresaDAO empDAO = new EmpresaDAO(con.getConexao());
            NegocioDAO negDAO = new NegocioDAO(con.getConexao());
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            CompradorDAO compDAO = new CompradorDAO(con.getConexao());
            ArrayList<Negocio> negs = negDAO.getListaNegocios();
            ArrayList<Comprador> comps = compDAO.getListaCompradores();
            ArrayList<Especie> esps = espDAO.getListaEspecie();
            Empresa e = empDAO.getEmpresa(String.valueOf(request.getAttribute("empresa")));
            if (negs != null) {
                if (comps != null) {
                    if (esps != null) {
                        if (e != null) {
                            request.setAttribute("negs", negs);
                            request.setAttribute("comps", comps);
                            request.setAttribute("esps", esps);
                            request.setAttribute("emp", e);
                            url = "/financeiro-vendas.jsp";
                        } else {
                            erro = "Não foi possível acessar os dados da empresa.";
                            url = "/erro.jsp?erro=" + erro;
                        }
                    } else {
                        erro = "Não foi possível localizar as espécies negociadas.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível localizar os compradores.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível localizar as negocioações.";
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
