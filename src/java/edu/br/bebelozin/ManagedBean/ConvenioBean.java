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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author Lano_2
 */
@ManagedBean
@ViewScoped
public class ConvenioBean {
    
    private Convenio convenio;
    private ConvenioDAO conveniodao;
    //private boolean mostraConvenio = true;
    //private String valorConvenio;
    //private final static String COMPARATIVO = "convenio";
    private List<Convenio> listaConvenio;
    
    public ConvenioBean(){
        this.convenio = new Convenio();
        validaConvenio();
    }

    //retorna todos os convenios para seleção do cadastro de um paciente
    public final void validaConvenio(){
        try {
            this.conveniodao = new ConvenioDAO();
            this.listaConvenio = this.conveniodao.listaDeConvenio();
                if(listaConvenio != null){
                    System.out.println(listaConvenio.get(0).getTipoDeConvenio());
                }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConvenioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public String getValorConvenio() {
//        return valorConvenio;
//    }
//
//    public void setValorConvenio(String valorConvenio) {
//        this.valorConvenio = valorConvenio;
//    }

    //gets e sets
    public List<Convenio> getListaConvenio() {
        return listaConvenio;
    }

    public void setListaConvenio(List<Convenio> listaConvenio) {
        this.listaConvenio = listaConvenio;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public ConvenioDAO getConveniodao() {
        return conveniodao;
    }

    public void setConveniodao(ConvenioDAO conveniodao) {
        this.conveniodao = conveniodao;
    }
    
    //cadastra um novo convenio no bd
    public void cadastrarConvenio(){
        try {
            this.conveniodao = new ConvenioDAO();
            boolean insereConvenio = this.conveniodao.cadastraConvenio(this.convenio);
            if(insereConvenio){
                FacesMessage mensagem = new FacesMessage("Convênio adicionado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha no cadastro"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConvenioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.convenio = new Convenio();  
    }
    
    public void clicarBotaoCadastroConvenio() {
        addMessage("Sucesso!", "Convênio cadastrado com sucesso.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //faz o autocomplete
    public List<Convenio> completaPesquisaConvenio(String query){
        try {
            this.conveniodao = new ConvenioDAO();
            this.listaConvenio = this.conveniodao.listaDeConvenio();
            if(listaConvenio != null){
                List<Convenio> convnew = new ArrayList<>();
                for(Convenio teste: this.listaConvenio){
                    if(teste.getTipoDeConvenio().startsWith(query)){
                        convnew.add(teste);
                    }
                }
                return convnew;
            }
            else{
                FacesMessage mensagem = new FacesMessage("Convenio não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConvenioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //seleciona o convenio encontrado na busca do autocomplete
    public void selecionarConvenio(){
        try {
            this.conveniodao = new ConvenioDAO();
            this.convenio = this.conveniodao.selecionaConvenio(this.convenio);
            if(convenio != null){
                this.convenio.setMostraPesquisa(true);
                FacesMessage mensagem = new FacesMessage("Usuario encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            else{
                FacesMessage mensagem = new FacesMessage("Usuario não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConvenioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clicarBotaoPesquisa() {
        addMessage("Sucesso!", "Paciente encontrado.");
    }
//    public void cadastrarLivro(){
//        
//        try {
//            this.livrodao = new UsuarioDAO();
//            boolean insereLivro = this.livrodao.cadastraLivro(this.livro);
//            if(insereLivro){
//                FacesMessage mensagem = new FacesMessage("Usuario adicionado"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }else{
//                FacesMessage mensagem = new FacesMessage("Falha no cadastro"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.livro = new Usuario();  
//    }
//    
    public void alteraConvenio(){
        
        try {
            this.conveniodao = new ConvenioDAO();
            boolean alteraConvenio = this.conveniodao.atualizaConvenio(this.convenio);
            if(alteraConvenio){
                this.convenio.setMostraPesquisa(false);
                FacesMessage mensagem = new FacesMessage("Convênio alterado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na alteração"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.convenio = new Convenio();
    }
    
    public void clicarBotaoAltera() {
        addMessage("Sucesso!", "Convenio alterado com sucesso.");
    }
//    
//    public void consultaLivro(){
//        
//        try {
//            this.livrodao = new UsuarioDAO();
//            Usuario livronew = this.livrodao.consultaLivro(this.livro);
//            if(livronew != null){
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
//    
    public void excluiConvenio(){
        try {
            this.conveniodao = new ConvenioDAO();
            boolean excluirConvenio = this.conveniodao.excluiConvenio(this.convenio);
            if(excluirConvenio){
                this.convenio.setMostraPesquisa(false);
                FacesMessage mensagem = new FacesMessage("Usuario excluído"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na exclusão"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConvenioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.convenio = new Convenio();
    }
    
    public void clicarBotaoExclui() {
        addMessage("Sucesso!", "Convenio excluído com sucesso.");
    }
    
    public void limpar(){
        this.convenio = new Convenio();
    }
}
