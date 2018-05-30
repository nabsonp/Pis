/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.VendaRacao;
import java.sql.Connection;
import java.sql.Date;
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
public class VendaRacaoDAO {

    private Connection con;
    private String sql;

    public VendaRacaoDAO(Connection conBanco) {
        con = conBanco;
    }

    public ArrayList<VendaRacao> getLista() {
        ArrayList<VendaRacao> vendas = new ArrayList<VendaRacao>();
        sql = "select * from VendaRacao";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VendaRacao v = new VendaRacao();
                v.setCodRacao(rs.getInt("codRacao"));
                v.setCodForn(rs.getInt("codForn"));
                v.setData(rs.getDate("data"));
                v.setQtd(rs.getInt("qtd"));
                v.setValorUni(rs.getFloat("valorUni"));
                v.setTotal(rs.getFloat("total"));
                v.setPesoUni(rs.getFloat("pesoUni"));
                vendas.add(v);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaRacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return vendas;
    }
    
    public ArrayList<VendaRacao> getLista(Date dt, Date atual) {
        ArrayList<VendaRacao> vendas = new ArrayList<VendaRacao>();
        sql = "select * from VendaRacao where (dt) between ? and ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, dt);
            pstmt.setDate(2, atual);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VendaRacao v = new VendaRacao();
                v.setCodRacao(rs.getInt("codRacao"));
                v.setCodForn(rs.getInt("codForn"));
                v.setData(rs.getDate("dt"));
                v.setQtd(rs.getInt("qtd"));
                v.setValorUni(rs.getFloat("valorUni"));
                v.setTotal(rs.getFloat("total"));
                v.setPesoUni(rs.getFloat("pesoUni"));
                vendas.add(v);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaRacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return vendas;
    }

    public VendaRacao getUltimaVenda(int codRacao) {
        VendaRacao v = null;
        sql = "select * from VendaRacao where codRacao = ? order by data desc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, codRacao);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                v = new VendaRacao();
                v.setCodRacao(rs.getInt("codRacao"));
                v.setCodForn(rs.getInt("codForn"));
                v.setData(rs.getDate("data"));
                v.setQtd(rs.getInt("qtd"));
                v.setValorUni(rs.getFloat("valorUni"));
                v.setTotal(rs.getFloat("total"));
                v.setPesoUni(rs.getFloat("pesoUni"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaRacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return v;
    }

}
