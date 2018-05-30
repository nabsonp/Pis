/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Comprador;
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
public class CompradorDAO {
    
    private String sql;
    private Connection con;
    
    public CompradorDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<Comprador> getListaCompradores() {
        ArrayList<Comprador> comps = new ArrayList<Comprador>();
        sql = "select * from Comprador";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Comprador c = new Comprador();
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setNumEnd(rs.getInt("numEnd"));
                c.setRua(rs.getString("rua"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                c.setUf(rs.getString("uf"));
                comps.add(c);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CompradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return comps;
    }
    
}
