/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.VendaQuimico;
import java.sql.Connection;
import java.sql.Date;
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
public class VendaQuimicoDAO {
    
    private Connection con;
    private String sql;

    public VendaQuimicoDAO(Connection conBanco) {
        con = conBanco;
    }

    public ArrayList<VendaQuimico> getLista(Date dia, Date atual) {
        ArrayList<VendaQuimico> vendas = new ArrayList<VendaQuimico>();
        sql = "select * from VendaQuimico where (dia) between ? and ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, dia);
            pstmt.setDate(2, atual);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VendaQuimico v = new VendaQuimico();
                v.setCodQuim(rs.getInt("codQuim"));
                v.setCodForn(rs.getInt("codForn"));
                v.setDia(rs.getDate("dia"));
                v.setQtd(rs.getInt("qtd"));
                v.setValorUni(rs.getFloat("valorUni"));
                v.setTotal(rs.getFloat("total"));
                v.setPesoUni(rs.getFloat("pesoUni"));
                vendas.add(v);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaRacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return vendas;
    }
    
}
