/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.FornDesp;
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
public class FornDespDAO {
    
    private String sql;
    private Connection con;
    
    public FornDespDAO(Connection conBanco) {
        con = conBanco;
    }   
    
    public ArrayList<FornDesp> getListaFornDesp() {
        ArrayList<FornDesp> fornDesps = new ArrayList<FornDesp>();
        sql = "select * from FornDesp";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FornDesp fd = new FornDesp();
                fd.setCodDesp(rs.getInt("codDesp"));
                fd.setCodForn(rs.getInt("codForn"));
                fd.setPrecoUni(rs.getDouble("precoUni"));
                fd.setTotal(rs.getDouble("total"));
                fd.setQtd(rs.getInt("qtd"));
                fd.setDia(rs.getInt("dia"));
                fd.setMes(rs.getInt("mes"));
                fd.setAno(rs.getInt("ano"));
                fornDesps.add(fd);
            } 
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FornDespDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return fornDesps;
    }
    
}
