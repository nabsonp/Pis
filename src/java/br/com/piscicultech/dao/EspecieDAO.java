/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Especie;
import java.sql.Connection;
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
public class EspecieDAO {

    private String sql;
    private Connection con;

    public EspecieDAO(Connection conBanco) {
        con = conBanco;
    }

    public boolean cadastrar(Especie esp) {
        sql = "insert into Especie (nome, nomeCient, imagem, racaoDia, tamMin, "
                + "tamMax, pesoMin, pesoMax, freqAlimMin, freqAlimMax, temperaturaMin,"
                + " temperaturaMax, oxigenioMin, oxigenioMax, phMin, phMax, "
                + "amoniaMin, amoniaMax, gasCarbonicoMin, gasCarbonicoMax, nitritoMin,"
                + " nitritoMax, nitratoMin, nitratoMax, alcalMin, alcalMax) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, esp.getNome());
            pstmt.setString(2, esp.getNomeCient());
            pstmt.setString(3, esp.getImagem());
            pstmt.setFloat(4, esp.getRacaoDia());
            pstmt.setFloat(5, esp.getTamMin());
            pstmt.setFloat(6, esp.getTamMax());
            pstmt.setFloat(7, esp.getPesoMin());
            pstmt.setFloat(8, esp.getPesoMax());
            pstmt.setInt(9, esp.getFreqAlimMin());
            pstmt.setInt(10, esp.getFreqAlimMax());
            pstmt.setFloat(11, esp.getTemperaturaMin());
            pstmt.setFloat(12, esp.getTemperaturaMax());
            pstmt.setFloat(13, esp.getOxigenioMin());
            pstmt.setFloat(14, esp.getOxigenioMax());
            pstmt.setFloat(15, esp.getPhMin());
            pstmt.setFloat(16, esp.getPhMax());
            pstmt.setFloat(17, esp.getAmoniaMin());
            pstmt.setFloat(18, esp.getAmoniaMax());
            pstmt.setFloat(19, esp.getGasCarbonicoMin());
            pstmt.setFloat(20, esp.getGasCarbonicoMax());
            pstmt.setFloat(21, esp.getNitritoMin());
            pstmt.setFloat(22, esp.getNitritoMax());
            pstmt.setFloat(23, esp.getNitratoMin());
            pstmt.setFloat(24, esp.getNitratoMax());
            pstmt.setFloat(25, esp.getAlcalinidadeMin());
            pstmt.setFloat(26, esp.getAlcalinidadeMax());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean editar(Especie esp){
        sql = "update Especie set nome = ?, nomeCient = ?, imagem = ?, racaoDia = ?,"
                + " tamMin = ?, tamMax = ?, pesoMin = ?, pesoMax = ?, freqAlimMin = ?, freqAlimMax = ?, "
                + "temperaturaMin = ?, temperaturaMax = ?, oxigenioMin = ?, oxigenioMax = ?,"
                + " phMin = ?, phMax = ?, amoniaMin = ?, amoniaMax = ?, gasCarbonicoMin = ?, gasCarbonicoMax = ?, "
                + "nitritoMin = ?, nitritoMax = ?, nitratoMin = ?, nitratoMax = ?, "
                + "alcalMin = ?, alcalMax = ? "
                + "where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, esp.getNome());
            pstmt.setString(2, esp.getNomeCient());
            pstmt.setString(3, esp.getImagem());
            pstmt.setFloat(4, esp.getRacaoDia());
            pstmt.setFloat(5, esp.getTamMin());
            pstmt.setFloat(6, esp.getTamMax());
            pstmt.setFloat(7, esp.getPesoMin());
            pstmt.setFloat(8, esp.getPesoMax());
            pstmt.setInt(9, esp.getFreqAlimMin());
            pstmt.setInt(10, esp.getFreqAlimMax());
            pstmt.setFloat(11, esp.getTemperaturaMin());
            pstmt.setFloat(12, esp.getTemperaturaMax());
            pstmt.setFloat(13, esp.getOxigenioMin());
            pstmt.setFloat(14, esp.getOxigenioMax());
            pstmt.setFloat(15, esp.getPhMin());
            pstmt.setFloat(16, esp.getPhMax());
            pstmt.setFloat(17, esp.getAmoniaMin());
            pstmt.setFloat(18, esp.getAmoniaMax());
            pstmt.setFloat(19, esp.getGasCarbonicoMin());
            pstmt.setFloat(20, esp.getGasCarbonicoMax());
            pstmt.setFloat(21, esp.getNitritoMin());
            pstmt.setFloat(22, esp.getNitritoMax());
            pstmt.setFloat(23, esp.getNitratoMin());
            pstmt.setFloat(24, esp.getNitratoMax());
            pstmt.setFloat(25, esp.getAlcalinidadeMin());
            pstmt.setFloat(26, esp.getAlcalinidadeMax());
            pstmt.setInt(27, esp.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public Especie getEspecie(int id) {
        Especie esp = null;
        sql = "select * from Especie where id = '" + id + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                esp = new Especie();
                esp.setId(rs.getInt("id"));
                esp.setNome(rs.getString("nome"));
                esp.setNomeCient(rs.getString("nomeCient"));
                esp.setImagem(rs.getString("imagem"));
                esp.setTamMin(rs.getFloat("tamMin"));
                esp.setTamMax(rs.getFloat("tamMax"));
                esp.setPesoMin(rs.getFloat("pesoMin"));
                esp.setPesoMax(rs.getFloat("pesoMax"));
                esp.setRacaoDia(rs.getFloat("racaoDia"));
                esp.setFreqAlimMin(rs.getInt("freqAlimMin"));
                esp.setFreqAlimMax(rs.getInt("freqAlimMax"));
                esp.setTemperaturaMin(rs.getFloat("temperaturaMin"));
                esp.setTemperaturaMax(rs.getFloat("temperaturaMax"));
                esp.setOxigenioMin(rs.getFloat("oxigenioMin"));
                esp.setOxigenioMax(rs.getFloat("oxigenioMax"));
                esp.setPhMin(rs.getFloat("phMin"));
                esp.setPhMax(rs.getFloat("phMax"));
                esp.setAmoniaMin(rs.getFloat("amoniaMin"));
                esp.setAmoniaMax(rs.getFloat("oxigenioMax"));
                esp.setGasCarbonicoMin(rs.getFloat("gasCarbonicoMin"));
                esp.setGasCarbonicoMax(rs.getFloat("gasCarbonicoMax"));
                esp.setNitritoMin(rs.getFloat("nitritoMin"));
                esp.setNitritoMax(rs.getFloat("nitritoMax"));
                esp.setNitratoMin(rs.getFloat("nitratoMin"));
                esp.setNitratoMax(rs.getFloat("nitratoMax"));
                esp.setAlcalinidadeMin(rs.getFloat("alcalMin"));
                esp.setAlcalinidadeMax(rs.getFloat("alcalMax"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return esp;
    }
    
    public Especie getEspecie(String nome) {
        Especie esp = null;
        sql = "select * from Especie where nome = '" + nome + "'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                esp = new Especie();
                esp.setId(rs.getInt("id"));
                esp.setNome(rs.getString("nome"));
                esp.setNomeCient(rs.getString("nomeCient"));
                esp.setImagem(rs.getString("imagem"));
                esp.setTamMin(rs.getFloat("tamMin"));
                esp.setTamMax(rs.getFloat("tamMax"));
                esp.setPesoMin(rs.getFloat("pesoMin"));
                esp.setPesoMax(rs.getFloat("pesoMax"));
                esp.setRacaoDia(rs.getFloat("racaoDia"));
                esp.setFreqAlimMin(rs.getInt("freqAlimMin"));
                esp.setFreqAlimMax(rs.getInt("freqAlimMax"));
                esp.setTemperaturaMin(rs.getFloat("temperaturaMin"));
                esp.setTemperaturaMax(rs.getFloat("temperaturaMax"));
                esp.setOxigenioMin(rs.getFloat("oxigenioMin"));
                esp.setOxigenioMax(rs.getFloat("oxigenioMax"));
                esp.setPhMin(rs.getFloat("phMin"));
                esp.setPhMax(rs.getFloat("phMax"));
                esp.setAmoniaMin(rs.getFloat("amoniaMin"));
                esp.setAmoniaMax(rs.getFloat("oxigenioMax"));
                esp.setGasCarbonicoMin(rs.getFloat("gasCarbonicoMin"));
                esp.setGasCarbonicoMax(rs.getFloat("gasCarbonicoMax"));
                esp.setNitritoMin(rs.getFloat("nitritoMin"));
                esp.setNitritoMax(rs.getFloat("nitritoMax"));
                esp.setNitratoMin(rs.getFloat("nitratoMin"));
                esp.setNitratoMax(rs.getFloat("nitratoMax"));
                esp.setAlcalinidadeMin(rs.getFloat("alcalMin"));
                esp.setAlcalinidadeMax(rs.getFloat("alcalMax"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return esp;
    }

    public ArrayList<Especie> getListaEspecie() {
        ArrayList<Especie> esps = new ArrayList<Especie>();
        sql = "select * from Especie";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Especie esp = new Especie();
                esp.setId(rs.getInt("id"));
                esp.setNome(rs.getString("nome"));
                esp.setNomeCient(rs.getString("nomeCient"));
                esp.setImagem(rs.getString("imagem"));
                esp.setTamMin(rs.getFloat("tamMin"));
                esp.setTamMax(rs.getFloat("tamMax"));
                esp.setPesoMin(rs.getFloat("pesoMin"));
                esp.setPesoMax(rs.getFloat("pesoMax"));
                esp.setRacaoDia(rs.getFloat("racaoDia"));
                esp.setFreqAlimMin(rs.getInt("freqAlimMin"));
                esp.setFreqAlimMax(rs.getInt("freqAlimMax"));
                esp.setTemperaturaMin(rs.getFloat("temperaturaMin"));
                esp.setTemperaturaMax(rs.getFloat("temperaturaMax"));
                esp.setOxigenioMin(rs.getFloat("oxigenioMin"));
                esp.setOxigenioMax(rs.getFloat("oxigenioMax"));
                esp.setPhMin(rs.getFloat("phMin"));
                esp.setPhMax(rs.getFloat("phMax"));
                esp.setAmoniaMin(rs.getFloat("amoniaMin"));
                esp.setAmoniaMax(rs.getFloat("oxigenioMax"));
                esp.setGasCarbonicoMin(rs.getFloat("gasCarbonicoMin"));
                esp.setGasCarbonicoMax(rs.getFloat("gasCarbonicoMax"));
                esp.setNitritoMin(rs.getFloat("nitritoMin"));
                esp.setNitritoMax(rs.getFloat("nitritoMax"));
                esp.setNitratoMin(rs.getFloat("nitratoMin"));
                esp.setNitratoMax(rs.getFloat("nitratoMax"));
                esp.setAlcalinidadeMin(rs.getFloat("alcalMin"));
                esp.setAlcalinidadeMax(rs.getFloat("alcalMax"));
                esps.add(esp);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return esps;
    }

    public boolean excluir(int id){
        sql = "delete from Especie where id = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

}
