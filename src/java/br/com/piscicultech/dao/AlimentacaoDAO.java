/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Alimentacao;
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
public class AlimentacaoDAO {

    private String sql;
    private Connection con;

    public AlimentacaoDAO(Connection conBanco) {
        con = conBanco;
    }

    public ArrayList<Alimentacao> getAlimentacao(int codRacao) {
        ArrayList<Alimentacao> alis = new ArrayList<Alimentacao>();
        sql = "select * from Alimentacao where codRacao = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, codRacao);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Alimentacao a = new Alimentacao();
                a.setIdEsp(rs.getInt("idEsp"));
                a.setCodRacao(rs.getInt("codRacao"));
                a.setFaseDeVida(rs.getString("faseDeVida"));
                alis.add(a);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlimentacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return alis;
    }

}
