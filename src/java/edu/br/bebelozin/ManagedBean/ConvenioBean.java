/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Usuario;
import edu.br.bebelozin.DAO.ConvenioDAO;
import edu.br.bebelozin.DAO.UsuarioDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


/**
 *
 * @author Lano_2
 */
@ManagedBean

public class ConvenioBean {
    
    private Convenio convenio;
    private ConvenioDAO conveniodao;
    private boolean mostraConvenio = false;
    private String valorConvenio;
    private final static String COMPARATIVO = "convenio";
    private List<Convenio> listaConvenio;
    
    public ConvenioBean(){
        this.convenio = new Convenio();
    }

    public void validaConvenio(){
        if(valorConvenio.equals(COMPARATIVO)){
            this.mostraConvenio = true;
                try {
                    this.conveniodao = new ConvenioDAO();
                    this.listaConvenio = this.conveniodao.listaDeConvenio();
                    if(listaConvenio != null){
                        System.out.println(listaConvenio.get(0).getTipoDeConvenio());
                    }else{
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
            this.mostraConvenio = false;
        }
        
    }
    
    public boolean isMostraConvenio() {
        return mostraConvenio;
    }

    public void setMostraConvenio(boolean mostraConvenio) {
        this.mostraConvenio = mostraConvenio;
    }

    public String getValorConvenio() {
        return valorConvenio;
    }

    public void setValorConvenio(String valorConvenio) {
        this.valorConvenio = valorConvenio;
    }

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

    public void setUsuariodao(ConvenioDAO conveniodao) {
        this.conveniodao = conveniodao;
    }
    
    
    public void cadastrarConvenio(){
        
        try {
            this.conveniodao = new ConvenioDAO();
            boolean insereConvenio = this.conveniodao.cadastraConvenio(this.convenio);
            if(insereConvenio){
                FacesMessage mensagem = new FacesMessage("Usuario adicionado"); 
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
//    public void alteraLivro(){
//        
//        try {
//            this.livrodao = new UsuarioDAO();
//            boolean alteraLivro = this.livrodao.atualizaLivro(this.livro);
//            if(alteraLivro){
//                FacesMessage mensagem = new FacesMessage("Usuario alterado"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }else{
//                FacesMessage mensagem = new FacesMessage("Falha na alteração"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.livro = new Usuario();
//    }
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
//    public void excluiLivro(){
//        
//        try {
//            this.livrodao = new UsuarioDAO();
//            boolean excluirLivro = this.livrodao.excluiLivro(this.livro);
//            if(excluirLivro){
//                FacesMessage mensagem = new FacesMessage("Usuario excluído"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }else{
//                FacesMessage mensagem = new FacesMessage("Falha na exclusão"); 
//                FacesContext.getCurrentInstance().addMessage(null, mensagem);
//            }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.livro = new Usuario();
//    }
    

}
