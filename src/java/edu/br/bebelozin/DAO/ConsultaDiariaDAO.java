/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.DAO;

import edu.br.bebelozin.Bean.Pacientes;
import edu.br.bebelozin.Bean.Sessao;
import edu.br.bebelozin.Bean.Usuario;
import edu.br.bebelozin.Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alcivan
 */
public class ConsultaDiariaDAO {
    private Connection connection;
    private Pacientes paciente;
    private Sessao sessao;
    
    
    public ConsultaDiariaDAO()
        throws ClassNotFoundException{
        this.connection = new ConnectionFactory().getConnection();
        System.out.println("Conectado!");
        paciente = new Pacientes();
        sessao = new Sessao();
        }
        public boolean cadastarConsultaDiaria(Pacientes pac , Sessao sesao){
            String sql = "INSERT INTO consulta_diaria (paciente, diagnostico, sessoes )"//,pagamento)"
                    + "VALUES (?, ?, ?)";//, ?)";
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, pac.getNomePaciente());
                ps.setString(2, pac.getDoencaPaciente());
                ps.setString(3, sesao.getTipoDeSessao());
                    
                    int retornos = ps.executeUpdate();
                    System.out.println(retornos);
                        if(retornos == 1){
                            return true;
                        }
        }catch (SQLException ex){
            Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                if(connection != null){
                try{
                    connection.close();
                    System.out.println("Desconectado com o banco!");
                }catch (SQLException ex){
            Logger.getLogger(ConsultaDiariaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
         }
          return false;
    }
      

}