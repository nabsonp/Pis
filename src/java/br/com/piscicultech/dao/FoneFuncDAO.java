/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.FoneFunc;
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
public class FoneFuncDAO {
    
    private String sql;
    private Connection con;
    
    public FoneFuncDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<FoneFunc> getFones(String cpfFunc) {
        ArrayList<FoneFunc> fones = new ArrayList<FoneFunc>();
        sql = "select * from fonefunc where cpfFunc = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpfFunc);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FoneFunc f = new FoneFunc();
                f.setCpfFunc(rs.getString("cpfFunc"));
                f.setTipo(rs.getString("tipo"));
                f.setNumero(rs.getString("numero"));
                fones.add(f);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FoneFuncDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return fones;
    }
    
}
