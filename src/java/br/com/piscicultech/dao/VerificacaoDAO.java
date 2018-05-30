/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Verificacao;
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
public class VerificacaoDAO {

    private String sql;
    private Connection con;

    public VerificacaoDAO(Connection conBanco) {
        con = conBanco;
    }

    public boolean cadastrar(Verificacao ver) {
        sql = "insert into Verificacao values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ver.getIdTanque());
            pstmt.setString(2, ver.getCpfFunc());
            pstmt.setTime(3, ver.getHora());
            pstmt.setDate(4, ver.getDtVerif());
            pstmt.setString(5, ver.getNome());
            pstmt.setFloat(6, ver.getValor());
            pstmt.setBoolean(7, ver.isSituacao());
            pstmt.setString(8, ver.getObs());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public ArrayList<Verificacao> getVerificacoes(int idTanque, Date dtVerif) {
        ArrayList<Verificacao> vers = new ArrayList<Verificacao>();
        sql = "select * from Verificacao where idTanque = ? and dtVerif = ? order by nome desc, hora desc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, dtVerif);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Verificacao ver = new Verificacao();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setNome(rs.getString("nome"));
                ver.setValor(rs.getFloat("valor"));
                ver.setSituacao(rs.getBoolean("situacao"));
                ver.setObs(rs.getString("obs"));
                vers.add(ver);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            vers = null;
        }
        return vers;
    }

    public ArrayList<Verificacao> getUltimaVerificacao(int idTanque, Date dtVerif) {
        ArrayList<Verificacao> vers = new ArrayList<Verificacao>();
        sql = "select * from Verificacao where idTanque = ? and dtVerif = ? order by hora desc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, dtVerif);
            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next() && i < 8) {
                Verificacao ver = new Verificacao();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setNome(rs.getString("nome"));
                ver.setValor(rs.getFloat("valor"));
                ver.setSituacao(rs.getBoolean("situacao"));
                ver.setObs(rs.getString("obs"));
                vers.add(ver);
                i++;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            vers = null;
        }
        return vers;
    }

    public Verificacao getPrimeiraVerificacao() {
        Verificacao ver = new Verificacao();
        sql = "select * from Verificacao order by dtVerif asc, hora asc";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ver = null;
        }
        return ver;
    }

    public boolean excluir(int idTanque) {
        sql = "delete from Verificacao where idTanque = ?";
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

    public ArrayList<Verificacao> getLista(Date inicio, Date fim) {
        ArrayList<Verificacao> ves = new ArrayList<Verificacao>();
        sql = "select * from Verificacao  where (dtVerif) between ? and ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, inicio);
            pstmt.setDate(2, fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Verificacao ver = new Verificacao();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setNome(rs.getString("nome"));
                ver.setValor(rs.getFloat("valor"));
                ver.setSituacao(rs.getBoolean("situacao"));
                ver.setObs(rs.getString("obs"));
                ves.add(ver);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ves;
    }
    
    public ArrayList<Verificacao> getLista(int idTanque, Date inicio, Date fim) {
        ArrayList<Verificacao> ves = new ArrayList<Verificacao>();
        sql = "select * from Verificacao  where idTanque = ? and (dtVerif) between ? and ? order by nome, dtVerif";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, idTanque);
            pstmt.setDate(2, inicio);
            pstmt.setDate(3, fim);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Verificacao ver = new Verificacao();
                ver.setIdTanque(rs.getInt("idTanque"));
                ver.setCpfFunc(rs.getString("cpfFunc"));
                ver.setHora(rs.getTime("hora"));
                ver.setDtVerif(rs.getDate("dtVerif"));
                ver.setNome(rs.getString("nome"));
                ver.setValor(rs.getFloat("valor"));
                ver.setSituacao(rs.getBoolean("situacao"));
                ver.setObs(rs.getString("obs"));
                ves.add(ver);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ves;
    }
    
}
