/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.PagamentoImpTaxAlu;
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
public class PagamentoImpTaxAluDAO {
    
    private String sql;
    private Connection con;
    
    public PagamentoImpTaxAluDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<PagamentoImpTaxAlu> getListaPagamentos() {
        ArrayList<PagamentoImpTaxAlu> pitas = new ArrayList<PagamentoImpTaxAlu>();
        sql = "select * from PagamentoImpTaxAlu";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PagamentoImpTaxAlu pita = new PagamentoImpTaxAlu();
                pita.setCodITA(rs.getInt("codITA"));
                pita.setCnpjEmp(rs.getString("cnpjEmp"));
                pita.setValor(rs.getDouble("valor"));
                pita.setDia(rs.getInt("dia"));
                pita.setMes(rs.getInt("mes"));
                pita.setAno(rs.getInt("ano"));
                pitas.add(pita);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoImpTaxAluDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return pitas;
    }
    
}
