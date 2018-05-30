/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Fornecedor;
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
public class FornecedorDAO {

    private String sql;
    private Connection con;

    public FornecedorDAO(Connection conBanco) {
        con = conBanco;
    }

    public Fornecedor getFornecedor(int codigo) {
        Fornecedor f = null;
        sql = "select * from Fornecedor where codigo = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                f = new Fornecedor();
                f.setCodigo(rs.getInt("codigo"));
                f.setNome(rs.getString("nome"));
                f.setEmail(rs.getString("email"));
                f.setNumEnd(rs.getInt("numEnd"));
                f.setRua(rs.getString("rua"));
                f.setBairro(rs.getString("bairroEnd"));
                f.setCidade(rs.getString("cidade"));
                f.setUf(rs.getString("uf"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }
    
    public Fornecedor getFornecedor(String nome) {
        Fornecedor f = null;
        sql = "select * from Fornecedor where nome = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                f = new Fornecedor();
                f.setCodigo(rs.getInt("codigo"));
                f.setNome(rs.getString("nome"));
                f.setEmail(rs.getString("email"));
                f.setNumEnd(rs.getInt("numEnd"));
                f.setRua(rs.getString("rua"));
                f.setBairro(rs.getString("bairroEnd"));
                f.setCidade(rs.getString("cidade"));
                f.setUf(rs.getString("uf"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public ArrayList<Fornecedor> getListaFornecedores() {
        ArrayList<Fornecedor> fors = new ArrayList<Fornecedor>();
        sql = "select * from Fornecedor";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setCodigo(rs.getInt("codigo"));
                f.setNome(rs.getString("nome"));
                f.setEmail(rs.getString("email"));
                f.setNumEnd(rs.getInt("numEnd"));
                f.setRua(rs.getString("rua"));
                f.setBairro(rs.getString("bairroEnd"));
                f.setCidade(rs.getString("cidade"));
                f.setUf(rs.getString("uf"));
                fors.add(f);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return fors;
    }

}
