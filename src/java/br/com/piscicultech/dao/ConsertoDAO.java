/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Conserto;
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
public class ConsertoDAO {
    
    private String sql;
    private Connection con;
    
    public ConsertoDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<Conserto> getListaConsertos() {
        ArrayList<Conserto> cons = new ArrayList<Conserto>();
        sql = "select * from Conserto";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Conserto c = new Conserto();
                c.setCodForn(rs.getInt("codForn"));
                c.setCodEquip(rs.getInt("codEquip"));
                c.setInicio(rs.getDate("inicio"));
                c.setFim(rs.getDate("fim"));
                c.setHora(rs.getTime("hora"));
                c.setDia(rs.getInt("dia"));
                c.setMes(rs.getInt("mes"));
                c.setAno(rs.getInt("ano"));
                c.setPrevisaoFim(rs.getDate("previsaoFim"));
                c.setTipo(rs.getString("tipo"));
                c.setDescricao(rs.getString("descricao"));
                c.setValor(rs.getDouble("valor"));
                cons.add(c);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConsertoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cons;
    }
    
}
