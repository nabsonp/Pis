/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Quimico;
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
public class QuimicoDAO {
    
    private Connection con;
    private String sql;
    
    public QuimicoDAO(Connection conBanco) {
        con= conBanco;
    }
    
    public ArrayList<Quimico> getListaQuimico(){
        ArrayList<Quimico> quis = new ArrayList<Quimico>();
        sql = "select * from Quimicos";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Quimico q = new Quimico();
                q.setCodigo(rs.getInt("codigo"));
                q.setNome(rs.getString("nome"));
                q.setPesoTotal(rs.getFloat("pesoTotal"));
                q.setDescricao(rs.getString("descricao"));
                quis.add(q);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            quis = null;
        }
        return quis;
    }
    
    public Quimico getQuimico(String nome) {
        Quimico q = null;
        sql = "select * from quimicos where nome = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                q = new Quimico();
                q.setCodigo(rs.getInt("codigo"));
                q.setNome(rs.getString("nome"));
                q.setPesoTotal(rs.getFloat("pesoTotal"));
                q.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
    
    public boolean inserir(Quimico q) {
        sql = "update quimicos set pesoTotal = ? where codigo = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setFloat(1, q.getPesoTotal());
            pstmt.setInt(2, q.getCodigo());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuimicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
}
