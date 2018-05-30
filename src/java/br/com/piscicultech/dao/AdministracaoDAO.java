/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung
 */
public class AdministracaoDAO {
    
    private String sql;
    private Connection con;
    
    public AdministracaoDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public String getCpfAdm(String cnpjEmp) {
        String cpfFunc = "";
        sql = "select cpfFunc from administracao where cnpjEmp = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cnpjEmp);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cpfFunc = rs.getString("cpfFunc");
            }
           rs.close();
           pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdministracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cpfFunc;
    }
    
    public String getCnpjEmp(String cpfProprietario) {
        String cnpjEmp;
        sql = "select cnpjEmp from Administracao where cpfProprietario = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpfProprietario);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cnpjEmp = rs.getString("cnpjEmp");
            } else {
                cnpjEmp = null;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdministracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cnpjEmp;
    }
    
}
