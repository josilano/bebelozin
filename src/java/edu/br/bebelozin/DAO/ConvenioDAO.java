/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Pacientes;
import edu.br.bebelozin.Bean.Usuario;
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
 * @author 1946810
 */
public class ConvenioDAO {
    
    private Connection connection;
    private Convenio convenio;
    
    public ConvenioDAO() 
            throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        this.convenio = new Convenio();
    }
    
    //insere um convenio no bd
    public boolean cadastraConvenio(Convenio convenio){             
        String sql = "INSERT INTO convenios (conv_tipo)"
            + "VALUES (?)";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, convenio.getTipoDeConvenio());
                                           
                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                    Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return false;
    }
    
    public Convenio selecionaConvenio(Convenio convenio){
        String sqls = "SELECT * FROM convenios WHERE conv_tipo = ?";
         
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            pst.setString(1, convenio.getTipoDeConvenio());
            
            ResultSet result = pst.executeQuery();
                if(result.next()){
                    convenio.setIdConvenio(result.getInt("conv_id"));
                    convenio.setTipoDeConvenio(convenio.getTipoDeConvenio());
                    
                    
                    return convenio;
                }
        } catch (SQLException ex) {
            Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    public List<Convenio> listaDeConvenio(){
        String sqls = "SELECT * FROM convenios";
         
        List<Convenio> lista = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Convenio convenionew = new Convenio();
                    convenionew.setIdConvenio(result.getInt("conv_id"));
                    convenionew.setTipoDeConvenio(result.getString("conv_tipo"));
                    
                    lista.add(convenionew);
                    
                }
                System.out.println("recuperou da tab convenio " + lista.get(0).getTipoDeConvenio());
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    public Convenio pesquisaConvenioPorNome(String convenioNome){
        String sqls = "SELECT * FROM convenios WHERE conv_tipo = ?";
         
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            pst.setString(1, convenioNome);
            
            ResultSet result = pst.executeQuery();
                if(result.next()){
                    Convenio convenionew = new Convenio();
                    convenionew.setIdConvenio(result.getInt("conv_id"));
                    convenionew.setTipoDeConvenio(result.getString("conv_tipo"));
                    
                    return convenionew;
                    //System.out.println(lista.get(0).getNomePaciente());
                }
        } catch (SQLException ex) {
            Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    public boolean atualizaConvenio(Convenio convenio){
        String sql = "UPDATE convenios SET conv_tipo = ? WHERE conv_id = ?";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, convenio.getTipoDeConvenio());
                    ps.setInt(2, convenio.getIdConvenio());

                        int retornos = ps.executeUpdate();
                         System.out.println(retornos);
                            if(retornos == 1){
                                System.out.println("atualizado com o int valor " + retornos);
                                return true;
                            }else{
                                 System.out.println("ERRO ao atualizar int valor " + retornos);
                            }
                } catch (SQLException ex) {
                        Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
    public boolean excluiConvenio(Convenio convenio){
        String sql = "DELETE FROM convenios WHERE conv_id = ?";
                    
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, convenio.getIdConvenio());
                
                    int retorno = ps.executeUpdate();
                        if(retorno == 1){
                            return true;
                        }
            } catch (SQLException ex) {
                        Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(ConvenioDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
}
