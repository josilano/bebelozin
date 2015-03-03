/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Sessao;
import edu.br.bebelozin.DAO.ConvenioDAO;
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

public class SessaoBean {
    
    private Sessao sessao;
    private SessaoDAO sessaodao;
    private List<Sessao> listaSessao;
    
    public SessaoBean(){
        this.sessao = new Sessao();
    }

    //gets e sets
    public Sessao getSesao() {
        return sessao;
    }

    public void setSesao(Sessao sesao) {
        this.sessao = sesao;
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
    
    //cadastra uma nova sessão no bd
    public void cadastrarSessao(){
        try {
            this.sessaodao = new SessaoDAO();
            boolean insereSessao = this.sessaodao.cadastraSessao(this.sessao);
            if(insereSessao){
                FacesMessage mensagem = new FacesMessage("Sessão adicionada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha no cadastro"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sessao = new Sessao();  
    }
    
    public void clicarBotaoCadastraSessao() {
        addMessage("Sucesso!", "Sessão cadastrada com sucesso.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //faz o autocomplete
    public List<Sessao> completaPesquisaSessao(String query){
        try {
            this.sessaodao = new SessaoDAO();
            this.listaSessao = this.sessaodao.listaDeSessao();
            if(listaSessao != null){
                List<Sessao> sessnew = new ArrayList<>();
                for(Sessao teste: this.listaSessao){
                    if(teste.getTipoDeSessao().startsWith(query)){
                        sessnew.add(teste);
                    }
                }
                return sessnew;
            }
            else{
                FacesMessage mensagem = new FacesMessage("Sessão não encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //seleciona a sessão encontrada na busca do autocomplete
    public void selecionarSessao(){
        try {
            this.sessaodao = new SessaoDAO();
            this.sessao = this.sessaodao.selecionaSessao(this.sessao);
            if(this.sessao != null){
                this.sessao.setMostraPesquisa(true);
                FacesMessage mensagem = new FacesMessage("Sessão encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            else{
                FacesMessage mensagem = new FacesMessage("Sessão não encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clicarBotaoPesquisa() {
        addMessage("Sucesso!", "Sessão encontrada.");
    }

    public void alteraSessao(){
        try {
            this.sessaodao = new SessaoDAO();
            boolean alteraSessao = this.sessaodao.atualizaSessao(this.sessao);
            if(alteraSessao){
                this.sessao.setMostraPesquisa(false);
                FacesMessage mensagem = new FacesMessage("Sessão alterada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na alteração"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sessao = new Sessao();
    }
    
    public void clicarBotaoAltera() {
        addMessage("Sucesso!", "Sessão alterada com sucesso.");
    }
    
    public void excluiSessao(){
        try {
            this.sessaodao = new SessaoDAO();
            boolean excluirSessao = this.sessaodao.excluiSessao(this.sessao);
            if(excluirSessao){
                this.sessao.setMostraPesquisa(false);
                FacesMessage mensagem = new FacesMessage("Sessão excluída"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na exclusão"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.sessao = new Sessao();
    }
    
    public void clicarBotaoExclui() {
        addMessage("Sucesso!", "Sessão excluída com sucesso.");
    }
    
    public void limpar(){
        this.sessao = new Sessao();
    }
    
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
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}