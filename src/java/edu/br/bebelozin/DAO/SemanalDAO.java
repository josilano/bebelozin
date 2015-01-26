/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Semanal;
import edu.br.bebelozin.Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alcivan
 */
public class SemanalDAO {
    private Connection conexao;
    private Semanal semanal;
public SemanalDAO()throws ClassNotFoundException{
    this.conexao = new ConnectionFactory().getConnection();
    System.out.println("Conectado!");
    semanal = new Semanal();
}
public boolean cadastraSemanal(Semanal semanal){
    String sql = "INSERT INTO semanal (usu_id,sem_tipos,sem_pagmto,sem_data)"
            + "VALUES (?,?,?,?)";
                    
                try (PreparedStatement ps = conexao.prepareStatement(sql)){
                    ps.setInt(1,semanal.getUsuario_id());
                    ps.setString(2,semanal.getId_sessao());
                    ps.setBoolean(3,semanal.isPagamento());
                    ps.setDate(4,new Date (semanal.getData().getTime()));
                                           
                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                    Logger.getLogger(SemanalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
            if(conexao != null){
                try {
                    conexao.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(SemanalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return false;
}

    
}
