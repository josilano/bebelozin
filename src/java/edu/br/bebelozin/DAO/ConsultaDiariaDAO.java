/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Pacientes;
import edu.br.bebelozin.Bean.Semanal;
import edu.br.bebelozin.Bean.Sessao;
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
public class ConsultaDiariaDAO {
    
    private Connection connection;
    private Pacientes paciente;
    
    private Sessao sessao;
    
    
    public ConsultaDiariaDAO() 
            throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        this.paciente = new Pacientes();
        this.sessao = new Sessao();
    }
    
    public boolean montandoConsultaDoPaciente(Pacientes paciente, List<String> sessaolista  ){             
        String sql = "INSERT INTO sess_sem ( sem_data, pac_id, sess_tipo, conv_tipo, sem_pagamento, pac_nome, pac_diagnostico)"
            + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
            System.out.println(sessaolista.size());       
               boolean retorno = false;
            try{
                for(int i = 0; i < sessaolista.size(); i++){
                    
                    try (PreparedStatement ps = connection.prepareStatement(sql)){
                    
                      ps.setDate(1, new Date(paciente.getDiaConsulta().getTime()));
                      ps.setInt(2, paciente.getIdPaciente());
                      ps.setString(3, sessaolista.get(i));
                      ps.setString(4, paciente.getConvenioPaciente());
                      ps.setBoolean(5, paciente.isPagamento());
                      ps.setString(6, paciente.getNomePaciente());
                      ps.setString(7, paciente.getDoencaPaciente());
                    
                                           
                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                retorno = true;
                            }else{
                                retorno = false;
                            }
                     } catch (SQLException ex) {
                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } return retorno; 
            }finally{
                if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
       
    }
//    public boolean montandoCadastroSemanal(Pacientes paciente, Convenio convenio){             
//        String sql = "INSERT INTO semanal ( sem_data, pac_id, conv_tipo)"
//            + "VALUES ( ?, ?, ?)";
//                 
//               boolean retorno = false;
//            
//            
//                    System.out.println(convenio.getTipoDeConvenio());
//                    try (PreparedStatement ps = connection.prepareStatement(sql)){
//                    
//                      ps.setDate(1, new Date(paciente.getDiaConsulta().getTime()));
//                      ps.setInt(2, paciente.getIdPaciente());
//                      ps.setString(3, paciente.getConvenioPaciente());
//                    
//                                           
//                        int retornos = ps.executeUpdate();
//                            if(retornos == 1){
//                                retorno = true;
//                            }else{
//                                retorno = false;
//                            }
//                     } catch (SQLException ex) {
//                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    }finally{
//                if(connection != null){
//                try {
//                    connection.close();
//                    System.out.println("Desconectado com banco!");
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return false;
//       
//    }
    public List<Sessao> returnConsultaDiaria(){
        String sql = "SELECT * FROM sess_sem ORDER BY sess_sem_id DESC";
        List<Sessao> lista = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(sql)){
           
            ResultSet result = ps.executeQuery();
           while(result.next()){
               Sessao sesao = new Sessao();
               sesao.setTipoDeSessao(result.getString("sess_tipo"));
               sesao.setDiaConsulta(result.getDate("sem_data"));
               sesao.setIdPaciente(result.getInt("pac_id"));
               sesao.setConvenioPaciente(result.getString("conv_tipo"));
               sesao.setPagamento(result.getBoolean("sem_pagamento"));
               sesao.setNomePaciente(result.getString("pac_nome"));
               sesao.setDoencaPaciente(result.getString("pac_diagnostico"));
               
               
               lista.add(sesao);
           }
               return lista;
       }catch(SQLException ex){
           Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
           if(connection != null){
               try{
                   connection.close();
                   System.out.println("Desconectado com o banco!");
               }catch(SQLException ex){
                   Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
               }
           } 
        }
        return null;
      
    }
   
}
    
    

