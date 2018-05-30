/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.ConsertoDAO;
import br.com.piscicultech.dao.EquipamentoDAO;
import br.com.piscicultech.dao.VendaEquipamentoDAO;
import br.com.piscicultech.modelo.Conserto;
import br.com.piscicultech.modelo.Equipamento;
import br.com.piscicultech.modelo.VendaEquipamento;
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
public class AbrirFinanMaqEquip extends HttpServlet {

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
            EquipamentoDAO equiDAO = new EquipamentoDAO(con.getConexao());
            VendaEquipamentoDAO venDAO = new VendaEquipamentoDAO(con.getConexao());
            ConsertoDAO conDAO = new ConsertoDAO(con.getConexao());
            ArrayList<Equipamento> es = equiDAO.getListaEquipamento();
            ArrayList<Conserto> cons = conDAO.getListaConsertos();
            ArrayList<VendaEquipamento> vens = venDAO.getListaVendas();
            if (es != null) {
                request.setAttribute("equis", es);
                if (cons != null) {
                    request.setAttribute("cons", cons);
                    if (vens != null) {
                        request.setAttribute("vens", vens);
                        url = "/financeiro-maq-equip.jsp";
                    } else {
                        erro = "Não foi possível localizar as vendas realizadas.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível localizar as manutenções realizadas.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível localizar as máquinas e equipamentos.";
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
