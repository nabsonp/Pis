/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import br.com.piscicultech.modelo.Negocio;
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
public class NegocioDAO {
    
    private String sql;
    private Connection con;
    
    public NegocioDAO(Connection conBanco) {
        con = conBanco;
    }
    
    public ArrayList<Negocio> getListaNegocios() {
        ArrayList<Negocio> negs = new ArrayList<Negocio>();
        sql = "select * from Negocio";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Negocio n = new Negocio();
                n.setIdEspecie(rs.getInt("idEspecie"));
                n.setCodComp(rs.getInt("codComp"));
                n.setCnpjEmp(rs.getString("cnpjEmp"));
                n.setQtd(rs.getInt("qtd"));
                n.setValorUni(rs.getFloat("valorUni"));
                n.setTotal(rs.getFloat("total"));
                n.setBiomassa(rs.getFloat("biomassa"));
                n.setDia(rs.getInt("dia"));
                n.setMes(rs.getInt("mes"));
                n.setAno(rs.getInt("ano"));
                negs.add(n);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(NegocioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return negs;
    }
    
}
