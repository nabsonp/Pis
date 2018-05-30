/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.VendaEspecie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung
 */
public class VendaEspecieDAO {
    
    private String sql;
    private Connection con;
    
    public VendaEspecieDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public boolean cadastrar(VendaEspecie v) {
        sql = "insert into VendaEspecie values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, v.getIdEspecie());
            pstmt.setInt(2, v.getCodForn());
            pstmt.setString(3, v.getTipo());
            pstmt.setDate(4, v.getDtVenda());
            pstmt.setInt(5, v.getQtd());
            pstmt.setFloat(6, v.getValorUni());
            pstmt.setFloat(7, v.getTotal());
            pstmt.setFloat(8, v.getBiomassa());
            pstmt.setFloat(9, v.getPesoMedio());
            pstmt.setFloat(10, v.getTamanhoMedio());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaEspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public ArrayList<VendaEspecie> getListaVendas(String tipo) {
        ArrayList<VendaEspecie> ves = new ArrayList<VendaEspecie>();
        sql = "select * from VendaEspecie where tipo = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tipo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VendaEspecie ve = new VendaEspecie();
                ve.setIdEspecie(rs.getInt("idEspecie"));
                ve.setCodForn(rs.getInt("codForn"));
                ve.setQtd(rs.getInt("qtd"));
                ve.setValorUni(rs.getFloat("valorUni"));
                ve.setTotal(rs.getFloat("total"));
                ve.setTipo(rs.getString("tipo"));
                ve.setBiomassa(rs.getFloat("biomassa"));
                ve.setPesoMedio(rs.getFloat("pesoMedio"));
                ve.setTamanhoMedio(rs.getFloat("tamanhoMedio"));
                ve.setDtVenda(rs.getDate("dtVenda"));
                ves.add(ve);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaEspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ves;
    }
    
}
