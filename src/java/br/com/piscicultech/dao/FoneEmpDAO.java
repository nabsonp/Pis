/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.FoneEmp;
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
public class FoneEmpDAO {
    
    private String sql;
    private Connection con;
    
    public FoneEmpDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<FoneEmp> getFones(String cnpjEmp) {
        ArrayList<FoneEmp> fones = new ArrayList<FoneEmp>();
        sql = "select * from foneemp where cnpjEmp = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cnpjEmp);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                FoneEmp f = new FoneEmp();
                f.setCnpjEmp(rs.getString("cpfFunc"));
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
