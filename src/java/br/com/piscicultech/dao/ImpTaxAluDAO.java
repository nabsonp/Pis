/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.ImpTaxAlu;
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
public class ImpTaxAluDAO {
    
    public String sql;
    public Connection con;
    
    public ImpTaxAluDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<ImpTaxAlu> getListaImpTaxAlus() {
        ArrayList<ImpTaxAlu> itas = new ArrayList<ImpTaxAlu>();
        sql = "select * from ImpTaxAlu";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ImpTaxAlu ita = new ImpTaxAlu();
                ita.setCodigo(rs.getInt("codigo"));
                ita.setValor(rs.getDouble("valor"));
                ita.setDescricao(rs.getString("descricao"));
                ita.setCobrador(rs.getString("cobrador"));
                ita.setVencimento(rs.getDate("vencimento"));
                ita.setSituacao(rs.getString("situacao"));
                itas.add(ita);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpTaxAluDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return itas;
    }
    
}
