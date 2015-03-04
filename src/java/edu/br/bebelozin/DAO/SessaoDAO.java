/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Sessao;
import edu.br.bebelozin.Factory.ConnectionFactory;
import java.io.Serializable;
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
 * @author 1946810
 */
public class SessaoDAO {
    
    private Connection connection;
    private Sessao sessao;
    
    public SessaoDAO() 
            throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        this.sessao = new Sessao();
    }
    
    //insere uma sessão no bd
    public boolean cadastraSessao(Sessao sessao){             
        String sql = "INSERT INTO sessoes (sess_tipo)"
            + "VALUES (?)";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, sessao.getTipoDeSessao());
                                           
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
    
    public Sessao selecionaSessao(Sessao sessao){
        String sqls = "SELECT * FROM sessoes WHERE sess_tipo = ?";
         
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            pst.setString(1, sessao.getTipoDeSessao());
            
            ResultSet result = pst.executeQuery();
                if(result.next()){
                    sessao.setIdSessao(result.getInt("sess_id"));
                    sessao.setTipoDeSessao(sessao.getTipoDeSessao());
                    
                    
                    return sessao;
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
       
        return null;
    }
    
    public List<Sessao> listaDeSessao(){
        String sqls = "SELECT * FROM sessoes";
         
        List<Sessao> lista = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Sessao sessaonew = new Sessao();
                    sessaonew.setIdSessao(result.getInt("sess_id"));
                    sessaonew.setTipoDeSessao(result.getString("sess_tipo"));
                    
                    lista.add(sessaonew);
                    
                }
                System.out.println("recuperou da tab convenio " + lista.get(0).getTipoDeSessao());
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
    
    public Sessao pesquisaSessaoPorNome(String sessaoNome){
        String sqls = "SELECT * FROM sessoes WHERE sess_tipo = ?";
         
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            pst.setString(1, sessaoNome);
            
            ResultSet result = pst.executeQuery();
                if(result.next()){
                    Sessao sessaonew = new Sessao();
                    sessaonew.setIdSessao(result.getInt("sess_id"));
                    sessaonew.setTipoDeSessao(result.getString("sess_tipo"));
                    
                    return sessaonew;
                    //System.out.println(lista.get(0).getNomePaciente());
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
        return null;
    }
    
    public boolean atualizaSessao(Sessao sessao){
        String sql = "UPDATE sessoes SET sess_tipo = ? WHERE sess_id = ?";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, sessao.getTipoDeSessao());
                    ps.setInt(2, sessao.getIdSessao());
                        
                        int retornos = ps.executeUpdate();
                       
                            if(retornos == 1){
                                System.out.println("atualizado com o int valor " + retornos );
                                return true;
                            }else{
                                System.out.println("Erro ao atualizar int valor " + retornos);
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
    
    public boolean excluiSessao(Sessao sessao){
        String sql = "DELETE FROM sessoes WHERE sess_id = ?";
                    
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, sessao.getIdSessao());
                
                    int retorno = ps.executeUpdate();
                        if(retorno == 1){
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
    
}
