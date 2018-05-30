/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Exercao;
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
public class ExercaoDAO {
    
    private String sql;
    private Connection con;
    
    public ExercaoDAO(Connection conBanco) {
        con = conBanco;
    }

    public Exercao getExercao(String cpfFunc) {
        Exercao e = null;
        sql = "select * from Exercao where cpfFunc = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpfFunc);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                e = new Exercao();
                e.setCpfFunc(rs.getString("cpfFunc"));
                e.setCodCargo(rs.getInt("codCargo"));
                e.setInicio(rs.getDate("inicio"));
                e.setFim(rs.getDate("fim"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExercaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return e;
    }
    
    public ArrayList<Exercao> getListExercoes() {
        ArrayList<Exercao> exs = new ArrayList<Exercao>();
        sql = "select * from Exercao";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Exercao e = new Exercao();
                e.setCpfFunc(rs.getString("cpfFunc"));
                e.setCodCargo(rs.getInt("codCargo"));
                e.setInicio(rs.getDate("inicio"));
                e.setFim(rs.getDate("fim"));
                exs.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExercaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return exs;
    }
    
}
