/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Quimico;
import br.com.piscicultech.modelo.TanQuim;
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
public class TanQuimDAO {
    
    private Connection conn;
    private String sql;
    
    public TanQuimDAO(Connection conBanco) {
        conn = conBanco;
    }
    
    public ArrayList<TanQuim> getLista(int idTanque, Date dt) {
        ArrayList<TanQuim> tqs = new ArrayList<TanQuim>();
        sql = "select * from TanQuim where idTanque = ? and dtInsercao = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, dt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TanQuim tq = new TanQuim();
                tq.setIdTanque(rs.getInt("idTanque"));
                tq.setCodQuimico(rs.getInt("codQuimico"));
                tq.setDtInsercao(rs.getDate("dtInsercao"));
                tq.setHoraInsercao(rs.getTime("horaInsercao"));
                tq.setPeso(rs.getFloat("peso"));
                tqs.add(tq);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanQuimDAO.class.getName()).log(Level.SEVERE, null, ex);
            tqs = null;
        }
        return tqs;
    }
    
    public boolean cadastrar(TanQuim q) {
        sql = "insert into TanQuim values (?,?,?,?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, q.getIdTanque());
            pstmt.setInt(2, q.getCodQuimico());
            pstmt.setDate(3, q.getDtInsercao());
            pstmt.setTime(4, q.getHoraInsercao());
            pstmt.setFloat(5, q.getPeso());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TanQuimDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean excluir(int idTanque){
        sql = "delete from tanquim where idTanque = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanQuimDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
}
