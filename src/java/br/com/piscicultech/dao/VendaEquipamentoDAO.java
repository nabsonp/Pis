/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.VendaEquipamento;
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
public class VendaEquipamentoDAO {
    
    private String sql;
    private Connection con;
    
    public VendaEquipamentoDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<VendaEquipamento> getListaVendas() {
        ArrayList<VendaEquipamento> ves = new ArrayList<VendaEquipamento>();
        sql = "select * from VendaEquipamento";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VendaEquipamento ve = new VendaEquipamento();
                ve.setCodEquip(rs.getInt("codEquip"));
                ve.setCodForn(rs.getInt("codForn"));
                ve.setQtd(rs.getInt("qtd"));
                ve.setValorUni(rs.getFloat("valorUni"));
                ve.setTotal(rs.getFloat("total"));
                ve.setDtVenda(rs.getDate("dtVenda"));
                ves.add(ve);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaEquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ves;
    }
    public ArrayList<VendaEquipamento> getListaVendas(Date dt, Date atual) {
        ArrayList<VendaEquipamento> ves = new ArrayList<VendaEquipamento>();
        sql = "select * from VendaEquipamento where (dtVenda) between ? and ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, dt);
            pstmt.setDate(2, atual);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VendaEquipamento ve = new VendaEquipamento();
                ve.setCodEquip(rs.getInt("codEquip"));
                ve.setCodForn(rs.getInt("codForn"));
                ve.setQtd(rs.getInt("qtd"));
                ve.setValorUni(rs.getFloat("valorUni"));
                ve.setTotal(rs.getFloat("total"));
                ve.setDtVenda(rs.getDate("dtVenda"));
                ves.add(ve);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaEquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ves;
    }
    
}
