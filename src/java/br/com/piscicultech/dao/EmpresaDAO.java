/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samsung
 */
public class EmpresaDAO {

    private String sql;
    private Connection con;

    public EmpresaDAO(Connection conBanco) {
        con = conBanco;
    }

    public Empresa getEmpresa(String cnpj) {
        sql = "select * from Empresa where cnpj = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, cnpj);
            ResultSet rs = pstmt.executeQuery();
            Empresa e = new Empresa();
            if (rs.next()) {
                e.setCnpj(rs.getString("cnpj"));
                e.setNome(rs.getString("nome"));
                e.setEmail(rs.getString("email"));
                e.setFundoInvestimento(rs.getDouble("fundoInvestimento"));
                e.setNumEnd(rs.getInt("numEnd"));
                e.setBairro(rs.getString("bairro"));
                e.setCep(rs.getString("cep"));
                e.setCidade(rs.getString("cidade"));
                e.setUf(rs.getString("uf"));
                e.setDtCriacao(rs.getDate("dtCriacao"));
            }
            rs.close();
            pstmt.close();
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
