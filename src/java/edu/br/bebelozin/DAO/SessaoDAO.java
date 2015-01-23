/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Sessao;
import edu.br.bebelozin.Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablopc
 */
public class SessaoDAO {
    private Sessao sessao;
    private Connection connection;
    
    
    public SessaoDAO()
        throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        sessao = new Sessao();
    }
    

    public boolean cadastraSessao(Sessao sessao) {
        String sql = "INSERT INTO sessoes (ses_tipo)"
            + "VALUES (?)";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, sessao.getSessao_tipo());
                                           
                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                    Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return false;
    }
    public List<Sessao> listaDeSessoes(){
        String sqls = "SELECT * FROM sessoes";
         
        List<Sessao> lista = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Sessao sessaonew = new Sessao();
                    sessaonew.setSessao_id(result.getInt("ses_id"));
                    sessaonew.setSessao_tipo(result.getString("ses_tipo"));
                    
                    lista.add(sessaonew);
                    System.out.println(lista.get(0).getSessao_tipo());
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(SessaoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    }
    

