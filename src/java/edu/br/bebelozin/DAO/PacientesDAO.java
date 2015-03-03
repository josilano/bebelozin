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
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class PacientesDAO {
    
    private Connection connection;
    private Pacientes paciente;
    
    public PacientesDAO() 
            throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        this.paciente = new Pacientes();
    }
    

    public boolean cadastraPaciente(Pacientes paciente){             
        String sql = "INSERT INTO pacientes (pac_nome, pac_tel, pac_doenca, conv_tipo)"
            + "VALUES (?, ?, ?, ?)";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, paciente.getNomePaciente());
                    ps.setString(2, paciente.getTelefonePaciente());
                    ps.setString(3, paciente.getDoencaPaciente());
                    ps.setString(4, paciente.getConvenioPaciente());
                                           
                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                    Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return false;
    }
    
    public Pacientes selecionaPaciente(Pacientes paciente){
        Pacientes p = new Pacientes();
        String sqls = "SELECT * FROM pacientes WHERE pac_id = ?";
        System.out.println("ID Pac:" + paciente.getIdPaciente()); 
        System.out.println("Nome Pac:" + paciente.getNomePaciente()); 
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            pst.setInt(1, paciente.getIdPaciente());
            
            ResultSet result = pst.executeQuery();
                if(result.next()){
                    p.setIdPaciente(result.getInt("pac_id"));
                    p.setNomePaciente(result.getString("pac_nome"));
                    p.setTelefonePaciente(result.getString("pac_tel"));
                    p.setDoencaPaciente(result.getString("pac_doenca"));
                    p.setConvenioPaciente(result.getString("conv_tipo"));
                }
                return p;
        } catch (SQLException ex) {
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
    public List<Pacientes> listaDePacientes(){
        String sqls = "SELECT * FROM pacientes";
         
        List<Pacientes> lista = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Pacientes pacientenew = new Pacientes();
                    pacientenew.setIdPaciente(result.getInt("pac_id"));
                    pacientenew.setNomePaciente(result.getString("pac_nome"));
                    pacientenew.setTelefonePaciente(result.getString("pac_tel"));
                    pacientenew.setDoencaPaciente(result.getString("pac_doenca"));
                    pacientenew.setConvenioPaciente(result.getString("conv_tipo"));
                    
                    lista.add(pacientenew);
                    //System.out.println("era p sair o nome" + lista.get(0).getNomePaciente() + " !");
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    public boolean atualizaPaciente(Pacientes paciente){
        String sql = "UPDATE pacientes SET pac_nome = ?, pac_tel = ?, pac_doenca = ?, conv_tipo = ? "
                + "WHERE pac_id = ?";
                    
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setString(1, paciente.getNomePaciente());
                    ps.setString(2, paciente.getTelefonePaciente());
                    ps.setString(3, paciente.getDoencaPaciente());
                    ps.setString(4, paciente.getConvenioPaciente());
                    ps.setInt(5, paciente.getIdPaciente());

                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                System.out.println("atualizado com o int valor " + retornos);
                                return true;
                            }else {
                                System.out.println("ERRO ao atualizar int valor " + retornos);
                            }
                } catch (SQLException ex) {
                        Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
    public boolean excluiPaciente(Pacientes paciente){
        String sql = "DELETE FROM pacientes WHERE pac_id = ?";
                    System.out.println("entrou no método pra excluir no dao");
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, paciente.getIdPaciente());
                System.out.println("entrou no ps do excluir no dao");
                System.out.println("valor do id_paciente capturado: " + paciente.getIdPaciente());
                    int retorno = ps.executeUpdate();
                    System.out.println(retorno);
                        if(retorno == 1){
                            System.out.println("excluiu no dao");
                            return true;
                        }
            } catch (SQLException ex) {
                        Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                    if(connection != null){
                        try {
                            connection.close();
                            System.out.println("Desconectado com banco!");
                        } catch (SQLException ex) {
                            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
    public Pacientes pesquisaPacientesPorNome(String pacienteNome){
        String sqls = "SELECT * FROM pacientes WHERE pac_nome = ?";
         
        try (PreparedStatement pst = connection.prepareStatement(sqls)){
            pst.setString(1, pacienteNome);
            
            ResultSet result = pst.executeQuery();
                if(result.next()){
                    Pacientes pacientenew = new Pacientes();
                    pacientenew.setIdPaciente(result.getInt("pac_id"));
                    pacientenew.setNomePaciente(result.getString("pac_nome"));
                    pacientenew.setTelefonePaciente(result.getString("pac_tel"));
                    pacientenew.setDoencaPaciente(result.getString("pac_doenca"));
                    pacientenew.setConvenioPaciente(result.getString("conv_tipo"));
                    
                    return pacientenew;
                    //System.out.println(lista.get(0).getNomePaciente());
                }
        } catch (SQLException ex) {
            Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(PacientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
}
