/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.piscicultech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    String driver = "com.mysql.jdbc.Driver";
    
    Connection conBanco;

    public boolean conectarBanco() {
        System.out.println("Conectando ao banco...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conBanco = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/piscicultech", "root", "root");
            return true;
        } catch (ClassNotFoundException | SQLException erro) {
            return false;
        }
    }

    public boolean fecharConexao() {
        try {
            conBanco.close();
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public Connection getConexao() {
        return conBanco;
    }
}

