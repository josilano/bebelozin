/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Sessao;
import edu.br.bebelozin.DAO.ConvenioDAO;
import edu.br.bebelozin.DAO.SessaoDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author pablopc
 */
@ManagedBean
public class SessaoBean {
    private Sessao sessao;
    private SessaoDAO sessaodao;
    private List<Sessao> listaSessao;

    public List<Sessao> getListaSessao() {
        return listaSessao;
    }

    public void setListaSessao(List<Sessao> listaSessao) {
        this.listaSessao = listaSessao;
    }

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
    
    public SessaoBean(){
        this.sessao = new Sessao();
    }
    
    public void cadastrarSessao(){
        
        try {
            this.sessaodao = new SessaoDAO();
            boolean insereSessao = this.sessaodao.cadastraSessao(this.sessao);
            if(insereSessao){
                FacesMessage mensagem = new FacesMessage("Tipo de sessão adicionada"); 
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
    public void listarConvenio(){        
        try {
            this.sessaodao = new SessaoDAO();
            this.listaSessao = this.sessaodao.listaDeSessoes();           
            if(listaSessao != null){                
                    System.out.println(listaSessao.get(0).getSessao_tipo());
                FacesMessage mensagem = new FacesMessage("Sessão Identificada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            else{
                FacesMessage mensagem = new FacesMessage("Sessão não Identificada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SessaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
