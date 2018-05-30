/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.piscicultech.dao.ConnectionFactory;
import br.com.piscicultech.dao.EspecieDAO;
import br.com.piscicultech.dao.FornecedorDAO;
import br.com.piscicultech.dao.TanqueDAO;
import br.com.piscicultech.dao.VendaEspecieDAO;
import br.com.piscicultech.modelo.Especie;
import br.com.piscicultech.modelo.Tanque;
import br.com.piscicultech.modelo.VendaEspecie;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author samsung
 */
public class ComprarEspecie extends HttpServlet {

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
        ConnectionFactory con = new ConnectionFactory();
        if (con.conectarBanco()) {
            VendaEspecieDAO vendDAO = new VendaEspecieDAO(con.getConexao());
            FornecedorDAO fornDAO = new FornecedorDAO(con.getConexao());
            EspecieDAO espDAO = new EspecieDAO(con.getConexao());
            TanqueDAO tanqDAO = new TanqueDAO(con.getConexao());
            VendaEspecie ve = new VendaEspecie();
            ve.setTipo(request.getParameter("tipo"));
            ve.setQtd(Integer.parseInt(request.getParameter("qtd")));
            ve.setTamanhoMedio(Integer.parseInt(request.getParameter("tamanhoMedio")));
            ve.setPesoMedio(Integer.parseInt(request.getParameter("pesoMedio")));
            ve.setBiomassa(Integer.parseInt(request.getParameter("pesoMedio")) * Integer.parseInt(request.getParameter("qtd")));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try {
                ve.setDtVenda(new java.sql.Date(formato.parse(request.getParameter("dtVenda")).getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(ComprarEspecie.class.getName()).log(Level.SEVERE, null, ex);
            }
            ve.setIdEspecie(Integer.parseInt(request.getParameter("idEsp")));
            ve.setValorUni(Float.parseFloat(request.getParameter("valorUni")));
            ve.setTotal(Float.parseFloat(request.getParameter("total")));
            System.out.println("FOOORN "+request.getParameter("nomeForn"));
            System.out.println("CODIGO "+fornDAO.getFornecedor(request.getParameter("nomeForn")).getCodigo());
            ve.setCodForn(fornDAO.getFornecedor(request.getParameter("nomeForn")).getCodigo());
            System.out.println("FOOORN "+request.getParameter("nomeForn"));
            System.out.println("CODIGO "+fornDAO.getFornecedor(request.getParameter("nomeForn")).getCodigo());
            if (vendDAO.cadastrar(ve)) {
                Tanque tanque = tanqDAO.getTanque(request.getParameter("idTanq"));
                if (tanque != null) {
                    Especie esp = espDAO.getEspecie(Integer.parseInt(request.getParameter("idEsp")));
                    if (esp != null) {
                        request.setAttribute("qtd", ve.getQtd());
                        request.setAttribute("esp", esp);
                        request.setAttribute("biomassa", ve.getBiomassa());
                        request.setAttribute("tipo", ve.getTipo());
                        request.setAttribute("tam", ve.getTamanhoMedio());
                        request.setAttribute("peso", ve.getPesoMedio());
                        request.setAttribute("tanque", tanque);
                        url = "/inserir-especie.jsp";
                    } else {
                        erro = "Não foi possível acessar a espécie.";
                        url = "/erro.jsp?erro=" + erro;
                    }
                } else {
                    erro = "Não foi possível acessar o tanque.";
                    url = "/erro.jsp?erro=" + erro;
                }
            } else {
                erro = "Não foi possível confirmar a compra.";
                url = "/erro.jsp?erro=" + erro;
            }
        } else {
            erro = "Não foi possível conectar-se ao banco de dados.";
            url = "/erro.jsp?erro=" + erro;
        }
        RequestDispatcher despachante = getServletContext().getRequestDispatcher(url);
        despachante.forward(request, response);
    }

}
