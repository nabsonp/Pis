/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.TanqEsp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung
 */
public class TanqEspDAO {

    private String sql = "";
    private Connection con;

    public TanqEspDAO(Connection conBanco) {
        con = conBanco;
    }

    public boolean cadastrar(TanqEsp te) {
        sql = "insert into TanqEsp values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, te.getIdTanque());
            pstmt.setInt(2, te.getIdEspecie());
            pstmt.setDate(3, te.getDtPeixam());
            pstmt.setInt(4, te.getQtd());
            pstmt.setString(5, te.getSituacao());
            pstmt.setFloat(6, te.getBiomassa());
            pstmt.setString(7, te.getTipo());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqEspDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean editar(TanqEsp te) {
        sql = "update TanqEsp set qtd = ?, situacao = ?, biomassa = ?, tipo = ? where idTanque = ? and idEspecie = ? and dtPeixam = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, te.getQtd());
            pstmt.setString(2, te.getSituacao());
            pstmt.setFloat(3, te.getBiomassa());
            pstmt.setString(4, te.getTipo());
            pstmt.setInt(5, te.getIdTanque());
            pstmt.setInt(6, te.getIdEspecie());
            pstmt.setDate(7, te.getDtPeixam());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqEspDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public TanqEsp getTanqEspTanque(int idTanque, Date inicio, Date fim) {
        TanqEsp tanqEsp = null;
        sql = "select * from TanqEsp where idTanque = ? and (dtPeixam) between ? and ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, inicio);
            pstmt.setDate(3, fim);
            System.out.println("ID " + idTanque);
            System.out.println("INICIO " + inicio);
            System.out.println("FIM"+fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tanqEsp = new TanqEsp();
                tanqEsp.setIdTanque(rs.getInt("idTanque"));
                tanqEsp.setIdEspecie(rs.getInt("idEspecie"));
                tanqEsp.setQtd(rs.getInt("qtd"));
                tanqEsp.setTipo(rs.getString("tipo"));
                tanqEsp.setSituacao(rs.getString("situacao"));
                tanqEsp.setBiomassa(rs.getFloat("biomassa"));
                tanqEsp.setDtPeixam(rs.getDate("dtPeixam"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqEspDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanqEsp;
    }

    public ArrayList<TanqEsp> getListaTanqEsp(Date inicio, Date fim) {
        ArrayList<TanqEsp> tanqEsps = new ArrayList<TanqEsp>();
        sql = "select * from TanqEsp where (dtPeixam) between ? and ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, inicio);
            pstmt.setDate(2, fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TanqEsp tanqEsp = new TanqEsp();
                tanqEsp.setIdTanque(rs.getInt("idTanque"));
                tanqEsp.setIdEspecie(rs.getInt("idEspecie"));
                tanqEsp.setQtd(rs.getInt("qtd"));
                tanqEsp.setTipo(rs.getString("tipo"));
                tanqEsp.setSituacao(rs.getString("situacao"));
                tanqEsp.setBiomassa(rs.getFloat("biomassa"));
                tanqEsp.setDtPeixam(rs.getDate("dtPeixam"));
                tanqEsps.add(tanqEsp);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqEspDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tanqEsps;
    }

    public boolean excluir(int idTanque) {
        sql = "delete from TanqEsp where idTanque = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public TanqEsp getTanqEsp(int idTanque) {
        TanqEsp tanqEsp = new TanqEsp();
        sql = "select * from TanqEsp where idTanque = " + idTanque + "";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tanqEsp.setIdTanque(rs.getInt("idTanque"));
                tanqEsp.setIdEspecie(rs.getInt("idEspecie"));
                tanqEsp.setQtd(rs.getInt("qtd"));
                tanqEsp.setTipo(rs.getString("tipo"));
                tanqEsp.setSituacao(rs.getString("situacao"));
                tanqEsp.setBiomassa(rs.getFloat("biomassa"));
                tanqEsp.setDtPeixam(rs.getDate("dtPeixam"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqEspDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tanqEsp;
    }

}
