/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.Factory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 1946810
 */
public class ConnectionFactory implements Serializable{
 
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost/bebelozin", usuario = "postgres", senha = "";
    
    public Connection getConnection(){
        try{
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
