/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Pacientes;
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
    
    public boolean montandoConsultaDoPaciente(Pacientes paciente, List<String> sessaolista){             
        String sqls = "INSERT INTO semanal (sem_data, pac_id, sem_pagmto, conv_tipo, pac_nome, pac_doenca)"
            + "VALUES (?, ?, ?, ?, ?, ?)";
        boolean verificacao = false;
        System.out.println("entrou no montarConsulta do dao");
            try (PreparedStatement pst = connection.prepareStatement(sqls)){
                pst.setDate(1, new Date(paciente.getDiaConsulta().getTime()));
                pst.setInt(2, paciente.getIdPaciente());
                pst.setBoolean(3, paciente.isPagamento());
                pst.setString(4, paciente.getConvenioPaciente());
                pst.setString(5, paciente.getNomePaciente());
                pst.setString(6, paciente.getDoencaPaciente());
                
                    int retorno = pst.executeUpdate();
                        if(retorno == 1){
                            
                            String sql = "INSERT INTO sess_sem (sem_data, pac_id, sess_tipo, "
                                    + "sem_pagmto, conv_tipo, pac_nome, pac_doenca)"
                                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
                            try (PreparedStatement ps = connection.prepareStatement(sql)){
                                for(int i = 0; i < sessaolista.size(); i++){  
                                    ps.setDate(1, new Date(paciente.getDiaConsulta().getTime()));
                                    ps.setInt(2, paciente.getIdPaciente());
                                    ps.setString(3, sessaolista.get(i));
                                    ps.setBoolean(4, paciente.isPagamento());
                                    ps.setString(5, paciente.getConvenioPaciente());
                                    ps.setString(6, paciente.getNomePaciente());
                                    ps.setString(7, paciente.getDoencaPaciente());
                                        System.out.println("volta nº " + i);
                                        int retornos = ps.executeUpdate();
                                            if(retornos == 1){
                                                verificacao = true;
                                            }
                                }
                                return verificacao;
                            } catch (SQLException ex) {
                                Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
            } catch (SQLException ex) {
                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
//        String sql = "INSERT INTO sess_sem (sem_data, pac_id, sess_tipo, sem_pagmto, conv_tipo, pac_nome)"
//            + "VALUES (?, ?, ?, ?, ?, ?)";
        //boolean verificacao = false;
        //System.out.println("entrou no montarConsulta do dao");
//                try (PreparedStatement ps = connection.prepareStatement(sql)){
//                    for(int i = 0; i < sessaolista.size(); i++){  
//                        ps.setDate(1, new Date(paciente.getDiaConsulta().getTime()));
//                        ps.setInt(2, paciente.getIdPaciente());
//                        ps.setString(3, sessaolista.get(i));
//                        ps.setBoolean(4, paciente.isPagamento());
//                        ps.setString(5, paciente.getConvenioPaciente());
//                        ps.setString(6, paciente.getNomePaciente());
//                                           System.out.println("volta nº " + i);
//                            int retornos = ps.executeUpdate();
//                                if(retornos == 1){
//                                    verificacao = true;
//                                }
//                    }
//                    return verificacao;
//                } catch (SQLException ex) {
//                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
            finally{
                if(connection != null){
                try {
                    connection.close();
                    System.out.println("Desconectado com banco!");
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return verificacao;
    }
}
