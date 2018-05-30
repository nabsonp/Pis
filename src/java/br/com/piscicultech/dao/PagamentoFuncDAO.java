/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.PagamentoFunc;
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
public class PagamentoFuncDAO {

    private String sql;
    private Connection con;

    public PagamentoFuncDAO(Connection conBanco) {
        con = conBanco;
    }

    public ArrayList<PagamentoFunc> getPags(String cpfFunc) {
        ArrayList<PagamentoFunc> pgs = new ArrayList<PagamentoFunc>();
        sql = "select * from PagamentoFunc where cpfFunc = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpfFunc);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                PagamentoFunc pg = new PagamentoFunc();
                pg.setCpfFunc(rs.getString("cpfFunc"));
                pg.setCnpjEmps(rs.getString("cnpjEmp"));
                pg.setVlrPago(rs.getDouble("vlrPago"));
                pg.setDtPag(rs.getDate("dtPag"));
                pgs.add(pg);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoFuncDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return pgs;
    }

    public String getCnpjEmp(String cpfFunc) {
        sql = "select cnpjEmp from PagamentoFunc where cpfFunc = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpfFunc);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("cnpjEmp");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PagamentoFuncDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
