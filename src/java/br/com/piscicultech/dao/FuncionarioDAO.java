/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Funcionario;
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
public class FuncionarioDAO {

    private String sql;
    private Connection con;

    public FuncionarioDAO(Connection conBanco) {
        con = conBanco;
    }

    public Funcionario getFuncionario(String cpf) {
        sql = "select * from Funcionario where cpf = ?";
        Funcionario func = new Funcionario();
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                func.setCpf(rs.getString("cpf"));
                func.setRg(rs.getString("rg"));
                func.setNome(rs.getString("nome"));
                func.setImagem(rs.getString("imagem"));
                func.setSexo(rs.getString("sexo"));
                func.setDtNascimento(rs.getDate("dtNascimento"));
                func.setEmail(rs.getString("email"));
                func.setInicioTrab(rs.getDate("inicioTrab"));
                func.setFimTrab(rs.getDate("fimTrab"));
                func.setUltimaSessao(rs.getString("ultimaSessao"));
                func.setNumEnd(rs.getInt("numEnd"));
                func.setRua(rs.getString("rua"));
                func.setBairro(rs.getString("bairro"));
                func.setCidade(rs.getString("cidade"));
                func.setUf(rs.getString("uf"));
                func.setCep(rs.getString("cep"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return func;
    }
    
    public Funcionario validarLogin(Funcionario usu) {
        sql = "select * from Funcionario where email = ? and senha = md5(?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, usu.getEmail());
            pstmt.setString(2, usu.getSenha());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usu.setCpf(rs.getString("cpf"));
                usu.setRg(rs.getString("rg"));
                usu.setNome(rs.getString("nome"));
                usu.setImagem(rs.getString("imagem"));
                usu.setSexo(rs.getString("sexo"));
                usu.setDtNascimento(rs.getDate("dtNascimento"));
                usu.setEmail(rs.getString("email"));
                usu.setSenha(null);
                usu.setInicioTrab(rs.getDate("inicioTrab"));
                usu.setFimTrab(rs.getDate("fimTrab"));
                usu.setUltimaSessao(rs.getString("ultimaSessao"));
                usu.setNumEnd(rs.getInt("numEnd"));
                usu.setRua(rs.getString("rua"));
                usu.setBairro(rs.getString("bairro"));
                usu.setCidade(rs.getString("cidade"));
                usu.setUf(rs.getString("uf"));
                usu.setCep(rs.getString("cep"));
            } else {
                usu = null;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }

    public boolean cadastrar(Funcionario func) {
        sql = "insert into Funcionario values (?, ?, ?, ?, ?, ?, ?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, func.getCpf());
            pstmt.setString(2, func.getRg());
            pstmt.setString(3, func.getNome());
            pstmt.setString(4, func.getImagem());
            pstmt.setString(5, func.getSexo());
            pstmt.setDate(6, (Date) func.getDtNascimento());
            pstmt.setString(7, func.getEmail());
            pstmt.setString(8, func.getSenha());
            pstmt.setDate(9, (Date) func.getInicioTrab());
            pstmt.setDate(10, (Date) func.getFimTrab());
            pstmt.setString(11, func.getUltimaSessao());
            pstmt.setInt(12, func.getNumEnd());
            pstmt.setString(13, func.getRua());
            pstmt.setString(14, func.getBairro());
            pstmt.setString(15, func.getCidade());
            pstmt.setString(16, func.getUf());
            pstmt.setString(17, func.getCep());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean alterar(Funcionario func){
        sql = "update Funcionario "
                + "set rg = ?, nome = ?, imagem = ?, sexo = ?, dtNascimento = ?, email = ?, senha = ?, inicioTrab = ?, fimTrab = ?, ultimaSessao = ?, numEnd = ?, rua = ?, bairro = ?, cidade = ?, uf = ?, cep = ? "
                + "where cpf = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, func.getRg());
            pstmt.setString(2, func.getNome());
            pstmt.setString(3, func.getImagem());
            pstmt.setString(4, func.getSexo());
            pstmt.setDate(5, (Date) func.getDtNascimento());
            pstmt.setString(6, func.getEmail());
            pstmt.setString(7, func.getSenha());
            pstmt.setDate(8, (Date) func.getInicioTrab());
            pstmt.setDate(9, (Date) func.getFimTrab());
            pstmt.setString(10, func.getUltimaSessao());
            pstmt.setInt(11, func.getNumEnd());
            pstmt.setString(12, func.getRua());
            pstmt.setString(13, func.getBairro());
            pstmt.setString(14, func.getBairro());
            pstmt.setString(15, func.getCidade());
            pstmt.setString(16, func.getUf());
            pstmt.setString(17, func.getCep());
            pstmt.setString(18, func.getCpf());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean excluir(String cpf) {
        sql = "delete from Funcionario where cpf = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public ArrayList<Funcionario> getListaFuncionarios() {
        ArrayList<Funcionario> funcs = new ArrayList<Funcionario>();
        sql = "select * from Funcionario";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setRg(rs.getString("rg"));
                func.setNome(rs.getString("nome"));
                func.setImagem(rs.getString("imagem"));
                func.setSexo(rs.getString("sexo"));
                func.setDtNascimento(rs.getDate("dtNascimento"));
                func.setEmail(rs.getString("email"));
                func.setSenha(null);
                func.setInicioTrab(rs.getDate("inicioTrab"));
                func.setFimTrab(rs.getDate("fimTrab"));
                func.setUltimaSessao(rs.getString("ultimaSessao"));
                func.setNumEnd(rs.getInt("numEnd"));
                func.setRua(rs.getString("rua"));
                func.setBairro(rs.getString("bairro"));
                func.setCidade(rs.getString("cidade"));
                func.setUf(rs.getString("uf"));
                func.setCep(rs.getString("cep"));
                funcs.add(func);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return funcs;
    }
    
}
