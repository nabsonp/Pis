/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Tanque;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung
 */
public class TanqueDAO {

    private String sql;
    private Connection con;

    public TanqueDAO(Connection conBanco) {
        con = conBanco;
    }

    public boolean cadastrar(Tanque tanque) {
        sql = "insert into Tanque (tipo, piscicultura, situacao, revestimento, material, diaPeixam, mesPeixam, anoPeixam, capacidade, vazao, profund, largura, comp, dtCriacao)\n"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tanque.getTipo());
            pstmt.setString(2, tanque.getPiscicultura());
            pstmt.setBoolean(3, tanque.isSituacao());
            pstmt.setString(4, tanque.getRevestimento());
            pstmt.setString(5, tanque.getMaterial());
            pstmt.setInt(6, tanque.getDiaPeixam());
            pstmt.setInt(7, tanque.getMesPeixam());
            pstmt.setInt(8, tanque.getAnoPeixam());
            pstmt.setFloat(9, (float) tanque.getCapacidade());
            pstmt.setFloat(10, (float) tanque.getVazao());
            pstmt.setFloat(11, (float) tanque.getProfund());
            pstmt.setFloat(12, (float) tanque.getLargura());
            pstmt.setFloat(13, (float) tanque.getComp());
            pstmt.setDate(14, tanque.getDtCriacao());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Date getDataCriacaoTanques() {
        Date prim = null;
        sql = "select dtCriacao from Tanque order by dtCriacao";
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                prim = rs.getDate("dtCriacao");
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prim;
    }
    
    public boolean editar(Tanque tanque) {
        sql = "update Tanque set tipo = ?, piscicultura = ?, situacao = ?, revestimento = ?, material = ?, diaPeixam = ?, mesPeixam = ?, anoPeixam = ?, capacidade = ?, vazao = ?,"
                + " profund = ?, largura = ?, comp = ?, nitritoMin = ?, nitritoMax = ?, nitratoMin = ?, nitratoMax = ?"
                + "where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, tanque.getTipo());
            pstmt.setString(2, tanque.getPiscicultura());
            pstmt.setBoolean(3, tanque.isSituacao());
            pstmt.setString(4, tanque.getRevestimento());
            pstmt.setString(5, tanque.getMaterial());
            pstmt.setInt(6, tanque.getDiaPeixam());
            pstmt.setInt(7, tanque.getMesPeixam());
            pstmt.setInt(8, tanque.getAnoPeixam());
            pstmt.setFloat(9, (float) tanque.getCapacidade());
            pstmt.setFloat(10, (float) tanque.getVazao());
            pstmt.setFloat(11, (float) tanque.getProfund());
            pstmt.setFloat(12, (float) tanque.getLargura());
            pstmt.setFloat(13, (float) tanque.getComp());
            pstmt.setFloat(14, tanque.getNitritoMin());
            pstmt.setFloat(15, tanque.getNitritoMax());
            pstmt.setFloat(16, tanque.getNitratoMin());
            pstmt.setFloat(17, tanque.getNitratoMax());
            pstmt.setInt(18, tanque.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void setSituacao(boolean sit, int idTanque){
        sql = "update Tanque set situacao = ? where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setBoolean(1, sit);
            pstmt.setInt(2, idTanque);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Tanque getTanque(String id) {
        Tanque tanque = null;
        sql = "select * from Tanque where id = '" + id + "'";
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                tanque = new Tanque();
                tanque.setId(rs.getInt("id"));
                tanque.setTipo(rs.getString("tipo"));
                tanque.setPiscicultura(rs.getString("piscicultura"));
                tanque.setSituacao(rs.getBoolean("situacao"));
                tanque.setRevestimento(rs.getString("revestimento"));
                tanque.setMaterial(rs.getString("material"));
                tanque.setDiaPeixam(rs.getInt("diaPeixam"));
                tanque.setMesPeixam(rs.getInt("mesPeixam"));
                tanque.setAnoPeixam(rs.getInt("anoPeixam"));
                tanque.setCapacidade(rs.getDouble("capacidade"));
                tanque.setVazao(rs.getDouble("vazao"));
                tanque.setProfund(rs.getDouble("profund"));
                tanque.setLargura(rs.getDouble("largura"));
                tanque.setComp(rs.getDouble("comp"));
                tanque.setNitritoMin(rs.getFloat("nitritoMin"));
                tanque.setNitritoMax(rs.getFloat("nitritoMax"));
                tanque.setNitratoMin(rs.getFloat("nitritoMin"));
                tanque.setNitratoMin(rs.getFloat("nitritoMax"));
                tanque.setAlcalinidadeMin(rs.getFloat("alcalinidadeMin"));
                tanque.setAlcalinidadeMin(rs.getFloat("alcalinidadeMax"));
                tanque.setDtCriacao(rs.getDate("dtCriacao"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanque;
    }

    public ArrayList<Tanque> getListaTanque() {
        ArrayList<Tanque> tanques = new ArrayList<Tanque>();
        sql = "select * from Tanque";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Tanque tanque = new Tanque();
                tanque.setId(rs.getInt("id"));
                tanque.setTipo(rs.getString("tipo"));
                tanque.setPiscicultura(rs.getString("piscicultura"));
                tanque.setSituacao(rs.getBoolean("situacao"));
                tanque.setRevestimento(rs.getString("revestimento"));
                tanque.setMaterial(rs.getString("material"));
                tanque.setDiaPeixam(rs.getInt("diaPeixam"));
                tanque.setMesPeixam(rs.getInt("mesPeixam"));
                tanque.setAnoPeixam(rs.getInt("anoPeixam"));
                tanque.setCapacidade(rs.getDouble("capacidade"));
                tanque.setVazao(rs.getDouble("vazao"));
                tanque.setProfund(rs.getDouble("profund"));
                tanque.setLargura(rs.getDouble("largura"));
                tanque.setComp(rs.getDouble("comp"));
                tanque.setNitritoMin(rs.getFloat("nitritoMin"));
                tanque.setNitritoMax(rs.getFloat("nitritoMax"));
                tanque.setNitratoMin(rs.getFloat("nitritoMin"));
                tanque.setNitratoMin(rs.getFloat("nitritoMax"));
                tanque.setAlcalinidadeMin(rs.getFloat("alcalinidadeMin"));
                tanque.setAlcalinidadeMin(rs.getFloat("alcalinidadeMax"));
                tanque.setDtCriacao(rs.getDate("dtCriacao"));
                tanques.add(tanque);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tanques;
    }

    public ArrayList<Tanque> getListaTanque(Date dtCriacao, Date primeiro) {
        ArrayList<Tanque> tanques = new ArrayList<Tanque>();
        sql = "select * from Tanque  where (dtCriacao) between ? and ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, dtCriacao);
            pstmt.setDate(2, primeiro);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Tanque tanque = new Tanque();
                tanque.setId(rs.getInt("id"));
                tanque.setTipo(rs.getString("tipo"));
                tanque.setPiscicultura(rs.getString("piscicultura"));
                tanque.setSituacao(rs.getBoolean("situacao"));
                tanque.setRevestimento(rs.getString("revestimento"));
                tanque.setMaterial(rs.getString("material"));
                tanque.setDiaPeixam(rs.getInt("diaPeixam"));
                tanque.setMesPeixam(rs.getInt("mesPeixam"));
                tanque.setAnoPeixam(rs.getInt("anoPeixam"));
                tanque.setCapacidade(rs.getDouble("capacidade"));
                tanque.setVazao(rs.getDouble("vazao"));
                tanque.setProfund(rs.getDouble("profund"));
                tanque.setLargura(rs.getDouble("largura"));
                tanque.setComp(rs.getDouble("comp"));
                tanque.setNitritoMin(rs.getFloat("nitritoMin"));
                tanque.setNitritoMax(rs.getFloat("nitritoMax"));
                tanque.setNitratoMin(rs.getFloat("nitritoMin"));
                tanque.setNitratoMin(rs.getFloat("nitritoMax"));
                tanque.setAlcalinidadeMin(rs.getFloat("alcalinidadeMin"));
                tanque.setAlcalinidadeMin(rs.getFloat("alcalinidadeMax"));
                tanque.setDtCriacao(rs.getDate("dtCriacao"));
                tanques.add(tanque);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return tanques;
    }
    
    public boolean excluir(int id) {
        sql = "delete from Tanque where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TanqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
