/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Racao;
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
public class RacaoDAO {
    
    private String sql;
    private Connection con;
    
    public RacaoDAO(Connection conBanco) {
        con =  conBanco;
    }
    
    public Racao getRacao(int codigo){
        Racao racao = null;
        sql = "select * from Racao where codigo = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                racao = new Racao();
                racao.setCodigo(rs.getInt("codigo"));
                racao.setNome(rs.getString("nome"));
                racao.setMarca(rs.getString("marca"));
                racao.setPesoTotal(rs.getFloat("pesoTotal"));
                racao.setTamPaleteMin(rs.getFloat("tamPaleteMin"));
                racao.setTamPaleteMax(rs.getFloat("tamPaleteMax"));
                racao.setUmidadeMax(rs.getFloat("umidadeMax"));
                racao.setProteinaMin(rs.getFloat("proteinaMin"));
                racao.setExtratoEtereoMin(rs.getFloat("extratoEtereoMin"));
                racao.setMateriaFibrosaMax(rs.getFloat("materiaFibrosaMax"));
                racao.setMateriaMineralMin(rs.getFloat("materiaMineralMin"));
                racao.setCalcioMin(rs.getFloat("calcioMin"));
                racao.setCalcioMax(rs.getFloat("calcioMax"));
                racao.setFosforoMin(rs.getFloat("fosforoMin"));
                racao.setVitaminaCMin(rs.getFloat("vitaminaCMin"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(RacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return racao;
    }
    
    public Racao getRacao(String nome){
        Racao racao = null;
        sql = "select * from Racao where nome = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                racao = new Racao();
                racao.setCodigo(rs.getInt("codigo"));
                racao.setNome(rs.getString("nome"));
                racao.setMarca(rs.getString("marca"));
                racao.setPesoTotal(rs.getFloat("pesoTotal"));
                racao.setTamPaleteMin(rs.getFloat("tamPaleteMin"));
                racao.setTamPaleteMax(rs.getFloat("tamPaleteMax"));
                racao.setUmidadeMax(rs.getFloat("umidadeMax"));
                racao.setProteinaMin(rs.getFloat("proteinaMin"));
                racao.setExtratoEtereoMin(rs.getFloat("extratoEtereoMin"));
                racao.setMateriaFibrosaMax(rs.getFloat("materiaFibrosaMax"));
                racao.setMateriaMineralMin(rs.getFloat("materiaMineralMin"));
                racao.setCalcioMin(rs.getFloat("calcioMin"));
                racao.setCalcioMax(rs.getFloat("calcioMax"));
                racao.setFosforoMin(rs.getFloat("fosforoMin"));
                racao.setVitaminaCMin(rs.getFloat("vitaminaCMin"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(RacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return racao;
    }
    
    public boolean arracoar(Racao rac) {
        sql = "update Racao set pesoTotal = ? where codigo = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setFloat(1, rac.getPesoTotal());
            pstmt.setInt(2, rac.getCodigo());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(RacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public ArrayList<Racao> getListaRacao(){
        ArrayList<Racao> racs = new ArrayList<Racao>();
        sql = "select * from Racao";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Racao racao = new Racao();
                racao.setCodigo(rs.getInt("codigo"));
                racao.setNome(rs.getString("nome"));
                racao.setMarca(rs.getString("marca"));
                racao.setPesoTotal((float) rs.getDouble("pesoTotal"));
                racao.setTamPaleteMin(rs.getFloat("tamPaleteMin"));
                racao.setTamPaleteMax(rs.getFloat("tamPaleteMax"));
                racao.setUmidadeMax(rs.getFloat("umidadeMax"));
                racao.setProteinaMin(rs.getFloat("proteinaMin"));
                racao.setExtratoEtereoMin(rs.getFloat("extratoEtereoMin"));
                racao.setMateriaFibrosaMax(rs.getFloat("materiaFibrosaMax"));
                racao.setMateriaMineralMin(rs.getFloat("materiaMineralMin"));
                racao.setCalcioMin(rs.getFloat("calcioMin"));
                racao.setCalcioMax(rs.getFloat("calcioMax"));
                racao.setFosforoMin(rs.getFloat("fosforoMin"));
                racao.setVitaminaCMin(rs.getFloat("vitaminaCMin"));
                racs.add(racao);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(RacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return racs;
    }
    
}
