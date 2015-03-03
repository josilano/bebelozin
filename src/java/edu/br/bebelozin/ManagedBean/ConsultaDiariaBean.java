/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Pacientes;
import edu.br.bebelozin.Bean.Sessao;
import edu.br.bebelozin.DAO.ConsultaDiariaDAO;
import edu.br.bebelozin.DAO.ConvenioDAO;
import edu.br.bebelozin.DAO.PacientesDAO;
import edu.br.bebelozin.DAO.SessaoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author Lano_2
 */
@ManagedBean

public class ConsultaDiariaBean {
    
    private Sessao sessao;
    private SessaoDAO sessaodao;
    private List<Sessao> listaSessao;
    
    private Pacientes paciente;
    private PacientesDAO pacientedao;
    private List<Pacientes> listaConsultaDiaria = new ArrayList<>();
    
    private ConsultaDiariaDAO consultaDiariadao;
    
    private String particular = "PARTICULAR";
    
    public ConsultaDiariaBean(){
        this.sessao = new Sessao();
        this.paciente = new Pacientes();
    }

    //gets e sets
    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public SessaoDAO getSessaodao() {
        return sessaodao;
    }

    public void setSessaodao(SessaoDAO sessaodao) {
        this.sessaodao = sessaodao;
    }

    public List<Sessao> getListaSessao() {
        return listaSessao;
    }

    public void setListaSessao(List<Sessao> listaSessao) {
        this.listaSessao = listaSessao;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public PacientesDAO getPacientedao() {
        return pacientedao;
    }

    public void setPacientedao(PacientesDAO pacientedao) {
        this.pacientedao = pacientedao;
    }

    public List<Pacientes> getListaConsultaDiaria() {
        return listaConsultaDiaria;
    }

    public void setListaConsultaDiaria(List<Pacientes> listaConsultaDiaria) {
        this.listaConsultaDiaria = listaConsultaDiaria;
    }

    public ConsultaDiariaDAO getConsultaDiadiadao() {
        return consultaDiariadao;
    }

    public void setConsultaDiadiadao(ConsultaDiariaDAO consultaDiadiadao) {
        this.consultaDiariadao = consultaDiadiadao;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }
    
    
    public void limpar(){
        this.sessao = new Sessao();
    }
    
    //popula a lista das sessões com todas as cadastradas
    public void listaCompletaSessao(){
        try {
            this.sessaodao = new SessaoDAO();
            this.listaSessao = this.sessaodao.listaDeSessao();
            if(listaSessao != null){
                
            }
            else{
                FacesMessage mensagem = new FacesMessage("Sessão não encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConsultaDiariaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    //add um paciente a lista de consultas diária
    public void addConsultaDiaria(){
        System.out.println("entrou no método addconsultadiaria do mb");
        try {
            this.consultaDiariadao = new ConsultaDiariaDAO();
            System.out.println(this.paciente.getNomePaciente());
            boolean montarConsulta = this.consultaDiariadao.montandoConsultaDoPaciente(this.paciente, this.sessao.getSessaolista());
            //boolean montarConsulta2 = this.sessaodao.montandoConsultaDaSessao(this.sesao);
                this.listaConsultaDiaria.add(this.paciente);
                System.out.println(this.listaConsultaDiaria.get(0).getNomePaciente());
            if(montarConsulta){
                
                
                
                
                this.paciente.setMostrapesquisa(true);
                FacesMessage mensagem = new FacesMessage("Usuario encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            else{
                FacesMessage mensagem = new FacesMessage("Usuario não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void montaConsultaDiaria(){
        try {
            this.pacientedao = new PacientesDAO();
            System.out.println(this.paciente.getNomePaciente());
            this.paciente = this.pacientedao.selecionaPaciente(this.paciente);
                this.listaConsultaDiaria.add(this.paciente);
                System.out.println(this.listaConsultaDiaria.get(0).getNomePaciente());
            if(paciente != null){
                
                listaCompletaSessao();
                
                
                this.paciente.setMostrapesquisa(true);
                FacesMessage mensagem = new FacesMessage("Usuario encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            else{
                FacesMessage mensagem = new FacesMessage("Usuario não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   
   public void selecaoRapida(){
       this.listaConsultaDiaria.add(this.paciente);
       listaCompletaSessao();
   }
//  public void pedidoListarPacientes(){
//        try {
//            this.pacientedao = new PacientesDAO();
//            this.listaPaciente = this.pacientedao.listaDePacientes();
//                if(listaPaciente != null){
//                    //System.out.println(listaPaciente.get(0).getNomePaciente());
//                }else{
//                }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }  
}