/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Pacientes;
import edu.br.bebelozin.Bean.Usuario;
import edu.br.bebelozin.DAO.ConvenioDAO;
import edu.br.bebelozin.DAO.PacientesDAO;
import edu.br.bebelozin.DAO.UsuarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean

public class PacientesBean {
    
    //atributos
    private Pacientes paciente;
    
    private PacientesDAO pacientedao;
    private String valorConvenio;
    private List<Pacientes> listaPaciente;
    private List<Pacientes> listaConsultaDiaria = new ArrayList<>();
    //private boolean mostraPesquisa = false;
    
    //construtor
    public PacientesBean(){
        this.paciente = new Pacientes();
        pedidoListarPacientes();
    }
    
    //métodos gets e sets
    public String getValorConvenio() {
        return valorConvenio;
    }

    public void setValorConvenio(String valorConvenio) {
        this.valorConvenio = valorConvenio;
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

    public List<Pacientes> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(List<Pacientes> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }

    public List<Pacientes> getListaConsultaDiaria() {
        return listaConsultaDiaria;
    }

    public void setListaConsultaDiaria(List<Pacientes> listaConsultaDiaria) {
        this.listaConsultaDiaria = listaConsultaDiaria;
    }

//    public boolean isMostraPesquisa() {
//        return mostraPesquisa;
//    }
//
//    public void setMostraPesquisa(boolean mostraPesquisa) {
//        this.mostraPesquisa = mostraPesquisa;
//    }
  
    
    //lista todos os pacientes cadastrados
    public final void pedidoListarPacientes(){
        try {
            this.pacientedao = new PacientesDAO();
            this.listaPaciente = this.pacientedao.listaDePacientes();
                if(listaPaciente != null){
                  //  System.out.println(listaPaciente.get(0).getNomePaciente());
                }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //cadastra um paciente no bd
    public void cadastrarPaciente(){
        System.out.println("paciente capturado " + this.paciente.getNomePaciente());
        try {
            this.pacientedao = new PacientesDAO();
            System.out.println("paciente capturado " + this.paciente.getNomePaciente());
            boolean insereConvenio = this.pacientedao.cadastraPaciente(this.paciente);
            if(insereConvenio){
                FacesMessage mensagem = new FacesMessage("Usuario adicionado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha no cadastro"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PacientesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.paciente = new Pacientes();  
    }
    
    public void clicarBotaoCadastro() {
        addMessage("Sucesso!", "Paciente cadastrado com sucesso.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void excluiPaciente(){
        System.out.println("entrou no método para excluir no mb " + this.paciente.getNomePaciente());
        
        try {
            this.pacientedao = new PacientesDAO();
            boolean excluirPaciente = this.pacientedao.excluiPaciente(this.paciente);
            System.out.println("chamou método exclui no mb");
            if(excluirPaciente){
                this.paciente.setMostrapesquisa(false);
                System.out.println("retornou pro mb como excluido");
                FacesMessage mensagem = new FacesMessage("Usuario excluído"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na exclusão"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.paciente = new Pacientes();
    }
    
    public void clicarBotaoExclui() {
        addMessage("Sucesso!", "Paciente excluído com sucesso.");
    }
    
    public void alteraPaciente(){
        System.out.println("entrou no método alterar do mb");
        try {
            System.out.println("paciente a ser alterado " + this.paciente.getNomePaciente());
            System.out.println("id para ser alterado " + this.paciente.getIdPaciente());
            this.pacientedao = new PacientesDAO();
            boolean alteraPaciente = this.pacientedao.atualizaPaciente(this.paciente);
            if(alteraPaciente){
                System.out.println("estado do mostra pesqusia antes: " + this.paciente.isMostrapesquisa());
                this.paciente.setMostrapesquisa(false);
                System.out.println("estado do mostra pesqusia depois: " + this.paciente.isMostrapesquisa());
                FacesMessage mensagem = new FacesMessage("Usuario alterado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na alteração"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(PacientesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.paciente = new Pacientes();
    }
    
    public void clicarBotaoAltera() {
        addMessage("Sucesso!", "Paciente Alterado com sucesso.");
    }
    
    //seleciona o paciente encontrado na busca do autocomplete
    public void selecionarPaciente(){
        try {
            this.pacientedao = new PacientesDAO();
            Pacientes pacTemp = this.pacientedao.selecionaPaciente(this.paciente);
            System.out.println("paciente selecionado " + pacTemp.getNomePaciente());
            this.paciente = pacTemp;
            System.out.println("paciente selecionado pro this " + this.paciente.getNomePaciente());
            if(paciente != null){
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
    
    public void clicarBotaoPesquisa() {
        addMessage("Sucesso!", "Paciente encontrado.");
    }
    
    //faz o autocomplete
    public List<Pacientes> completaPesquisaPaciente(String query){
        try {
            this.pacientedao = new PacientesDAO();
            this.listaPaciente = this.pacientedao.listaDePacientes();
            if(listaPaciente != null){
                List<Pacientes> pacnew = new ArrayList<>();
                for(Pacientes teste: this.listaPaciente){
                    if(teste.getNomePaciente().startsWith(query)){
                        pacnew.add(teste);
                    }
                }
                return pacnew;
            }
            else{
                FacesMessage mensagem = new FacesMessage("Usuario não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PacientesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void limpar(){
        this.paciente = new Pacientes();
    }
    
    
//    public void addConsultaDiaria(){
//        try {
//            this.pacientedao = new PacientesDAO();
//            this.paciente = this.pacientedao.selecionaPaciente(this.paciente);
//                
//            this.listaConsultaDiaria.add(this.paciente);
//            System.out.println(this.listaConsultaDiaria.get(0).getNomePaciente());
//            if(paciente != null){
//                this.paciente.setMostrapesquisa(true);
//                FacesMessage mensagem = new FacesMessage("Usuario encontrado"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }
//            else{
//                FacesMessage mensagem = new FacesMessage("Usuario não encontrado"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}