/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Arracoamento;
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
public class ArracoamentoDAO {

    public String sql;
    public Connection con;

    public ArracoamentoDAO(Connection conBanco) {
        this.con = conBanco;
    }

    public boolean cadastrar(Arracoamento a){
        sql = "insert into Arracoamento values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, a.getIdTanque());
            pstmt.setInt(2, a.getCodRacao());
            pstmt.setInt(3, a.getDia());
            pstmt.setInt(4, a.getMes());
            pstmt.setInt(5, a.getAno());
            pstmt.setTime(6, a.hora);
            pstmt.setFloat(7, a.getPeso());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex + ": " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    public ArrayList<Arracoamento> getArracoamentos(int idTanque, int dia, int mes, int ano) {
        ArrayList<Arracoamento> arrs = new ArrayList<Arracoamento>();
        sql = "select * from Arracoamento where idTanque = ? and dia = ? and mes = ? and ano = ? order by hora desc";
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setInt(2, dia);
            pstmt.setInt(3, mes);
            pstmt.setInt(4, ano);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Arracoamento arr = new Arracoamento();
                arr.setIdTanque(rs.getInt("idTanque"));
                arr.setCodRacao(rs.getInt("codRacao"));
                arr.setDia(rs.getInt("dia"));
                arr.setMes(rs.getInt("mes"));
                arr.setAno(rs.getInt("ano"));
                arr.setHora(rs.getTime("hora"));
                arr.setPeso(rs.getFloat("peso"));
                arrs.add(arr);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArracoamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrs;
    }

    public boolean excluir(int idTanque) {
        sql = "delete from Arracoamento where idTanque = ?";
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

    public int getQtdArrac(int idTanque, int dia, int mes, int ano) {
        int vzs = -1;
        sql = "select count(*) from Arracoamento where idTanque = ? and dia = ? and mes = ? and ano = ? order by hora desc";
        PreparedStatement pstmt;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setInt(2, dia);
            pstmt.setInt(3, mes);
            pstmt.setInt(4, ano);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                vzs = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ArracoamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vzs;
    }
    
}
