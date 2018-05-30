/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Despesa;
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
public class DespesaDAO {

    private String sql;
    private Connection con;

    public DespesaDAO(Connection conBanco) {
        con = conBanco;
    }

    public ArrayList<Despesa> getListaDespesas() {
        ArrayList<Despesa> desps = new ArrayList<Despesa>();
        sql = "select * from Despesa";
        try {
            PreparedStatement pstmt = con.prepareCall(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Despesa d = new Despesa();
                d.setCodigo(rs.getInt("codigo"));
                d.setTipo(rs.getString("tipo"));
                d.setDescricao(rs.getString("descricao"));
                desps.add(d);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return desps;
    }

    public Despesa getDespesa(int codigo) {
        Despesa d = null;
        sql = "select * from Despesa where codigo = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                d = new Despesa();
                d.setCodigo(rs.getInt("codigo"));
                d.setTipo(rs.getString("tipo"));
                d.setDescricao(rs.getString("descricao"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

}
