/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.VerificacaoEsp;
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
public class VerificacaoEspDAO {
    
    private String sql;
    private Connection con;
    
    public VerificacaoEspDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public boolean cadastrar(VerificacaoEsp veEsp) {
        sql = "insert into VerificacaoEsp values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, veEsp.getIdTanque());
            pstmt.setString(2, veEsp.getCpfFunc());
            pstmt.setInt(3, veEsp.getIdEspecie());
            pstmt.setTime(4, veEsp.getHora());
            pstmt.setDate(5, veEsp.getDtVerif());
            pstmt.setFloat(6, veEsp.getTamanhoMedio());
            pstmt.setFloat(7, veEsp.getPeso());
            pstmt.setInt(8, veEsp.getQtd());
            pstmt.setInt(9, veEsp.getMortos());
            pstmt.setInt(10, veEsp.getNascidos());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoEspDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public ArrayList<VerificacaoEsp> getLista(int idTanque, Date fim) {
        ArrayList<VerificacaoEsp> ves = new ArrayList<VerificacaoEsp>();
        sql = "select * from VerificacaoEsp  where idTanque = ? and dtVerif = ? order by dtVerif desc, hora desc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                VerificacaoEsp ver = new VerificacaoEsp();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setTamanhoMedio(rs.getFloat("tamanhoMedio"));
                ver.setPeso(rs.getFloat("peso"));
                ver.setQtd(rs.getInt("qtd"));
                ver.setMortos(rs.getInt("mortos"));
                ver.setNascidos(rs.getInt("nascidos"));
                ves.add(ver);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ves;
    }
    
    public ArrayList<VerificacaoEsp> getLista(int idTanque, Date inicio, Date fim) {
        ArrayList<VerificacaoEsp> ves = new ArrayList<VerificacaoEsp>();
        sql = "select * from VerificacaoEsp  where idTanque = ? and (dtVerif) between ? and ? order by dtVerif desc, hora desc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, inicio);
            pstmt.setDate(3, fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                VerificacaoEsp ver = new VerificacaoEsp();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setTamanhoMedio(rs.getFloat("tamanhoMedio"));
                ver.setPeso(rs.getFloat("peso"));
                ver.setQtd(rs.getInt("qtd"));
                ver.setMortos(rs.getInt("mortos"));
                ver.setNascidos(rs.getInt("nascidos"));
                ves.add(ver);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ves;
    }
    
    public ArrayList<VerificacaoEsp> getListaAsc(int idTanque, Date inicio, Date fim) {
        ArrayList<VerificacaoEsp> ves = new ArrayList<VerificacaoEsp>();
        sql = "select * from VerificacaoEsp  where idTanque = ? and (dtVerif) between ? and ? order by dtVerif asc, hora asc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, inicio);
            pstmt.setDate(3, fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                VerificacaoEsp ver = new VerificacaoEsp();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setTamanhoMedio(rs.getFloat("tamanhoMedio"));
                ver.setPeso(rs.getFloat("peso"));
                ver.setQtd(rs.getInt("qtd"));
                ver.setMortos(rs.getInt("mortos"));
                ver.setNascidos(rs.getInt("nascidos"));
                ves.add(ver);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ves;
    }
    
    public ArrayList<VerificacaoEsp> getListaEspAsc(int idEspecie, Date inicio, Date fim) {
        ArrayList<VerificacaoEsp> ves = new ArrayList<VerificacaoEsp>();
        sql = "select * from VerificacaoEsp  where idEspecie = ? and (dtVerif) between ? and ? order by dtVerif asc, hora asc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idEspecie);
            pstmt.setDate(2, inicio);
            pstmt.setDate(3, fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                VerificacaoEsp ver = new VerificacaoEsp();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setTamanhoMedio(rs.getFloat("tamanhoMedio"));
                ver.setPeso(rs.getFloat("peso"));
                ver.setQtd(rs.getInt("qtd"));
                ver.setMortos(rs.getInt("mortos"));
                ver.setNascidos(rs.getInt("nascidos"));
                ves.add(ver);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ves;
    }
 
    public boolean excluir(int idTanque) {
        sql = "delete from verificacaoesp where idTanque = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
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
