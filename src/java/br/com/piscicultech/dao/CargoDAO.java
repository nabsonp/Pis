/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Cargo;
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
public class CargoDAO {
    
    private String sql;
    private Connection con;
    
    public CargoDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public Cargo getCargo(int codigo) {
        Cargo c = null;
        sql = "select * from Cargo where codigo = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                c = new Cargo();
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setFormaPag(rs.getString("formaPag"));
                c.setPagamento(rs.getDouble("pagamento"));
                c.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return c;
    }
    
    public ArrayList<Cargo> getListaCargos() {
        ArrayList<Cargo> cargos = new ArrayList<Cargo>();
        sql = "select * from Cargo";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Cargo c = new Cargo();
                c.setCodigo(rs.getInt("codigo"));
                c.setNome(rs.getString("nome"));
                c.setFormaPag(rs.getString("formaPag"));
                c.setPagamento(rs.getDouble("pagamento"));
                c.setDescricao(rs.getString("descricao"));
                cargos.add(c);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return cargos;
    }
    
}
