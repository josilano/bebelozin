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
        String sql = "INSERT INTO sess_sem (sem_data, pac_id, sess_tipo)"
            + "VALUES (?, ?, ?)";
                for(int i = 0; i <= this.sessao.getSessaolista().size(); i++)
                try (PreparedStatement ps = connection.prepareStatement(sql)){
                    ps.setDate(1, new Date(paciente.getDiaConsulta().getTime()));
                    ps.setInt(2, paciente.getIdPaciente());
                    ps.setString(3, sessaolista.get(i));
                    
                                           
                        int retornos = ps.executeUpdate();
                            if(retornos == 1){
                                return true;
                            }
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
       
        return false;
    }
}
