/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Equipamento;
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
public class EquipamentoDAO {
    
    private Connection con;
    private String sql;
    
    public EquipamentoDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<Equipamento> getListaEquipamento() {
        ArrayList<Equipamento> equis = new ArrayList<Equipamento>();
        sql = "select * from Equipamento";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Equipamento equi = new Equipamento();
                equi.setCodigo(rs.getInt("codigo"));
                equi.setNome(rs.getString("nome"));
                equi.setTipo(rs.getString("tipo"));
                equi.setQtd(rs.getInt("qtd"));
                equis.add(equi);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equis;
    }
    
}
