/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Editora;
import edu.br.bebelozin.Bean.Usuario;
import edu.br.bebelozin.Factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author 1946810
 */
public class EditoraDAO {
    
    private Connection connection;
    private Usuario livro;
    private Editora editora;
    
    public EditoraDAO() 
            throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        this.livro = new Usuario();
        this.editora = new Editora();
    }
    
    
    public boolean excluirEditora(Editora editora){
        String sql = "DELETE FROM editora WHERE ed_nome = ?";
                    
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, editora.getNome());
                
                    int retorno = ps.executeUpdate();
                        if(retorno == 1){
                            return true;
                        }
            } catch (SQLException ex) {
                        Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
    public boolean cadastrarEditora(Editora editora){
        String sqls = "INSERT INTO editora (ed_nome, ed_endereco)"
            + "VALUES (?, ?)";
         
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            pst.setString(1, editora.getNome());
            pst.setString(2, editora.getEndereco());
            
            int retorno = pst.executeUpdate();
                if(retorno == 1){
                    return true;
                } 
        }catch (SQLException ex) {
                    Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
    public Editora consultarEditora(Editora editora){
        String sql = "SELECT * FROM editora WHERE ed_nome = ?";
         
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, editora.getNome());
            
            ResultSet result = ps.executeQuery();
                if(result.next()){
                    editora.setCodigo(result.getInt("ed_id"));
                    editora.setNome(editora.getNome());
                    editora.setEndereco(result.getString("ed_endereco"));
                    
                    return editora;
                }
        } catch (SQLException ex) {
            Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
     public boolean atualizarEditora(Editora editora){
        String sql = "UPDATE editora SET ed_nome = ?, ed_endereco = ? "
                + "WHERE ed_id = ?";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, editora.getNome());
                    ps.setString(2, editora.getEndereco());
                    ps.setInt(3, editora.getCodigo());

                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                        Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(EditoraDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
}
