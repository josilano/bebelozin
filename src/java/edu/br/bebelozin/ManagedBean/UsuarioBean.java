/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Usuario;
import edu.br.bebelozin.DAO.UsuarioDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Lano_2
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {
    
    private Usuario usuario;
    private UsuarioDAO usuariodao;
    private boolean mostraConvenio = false;
    private String valorConvenio;
    private final static String COMPARATIVO = "convenio";
    private List<Convenio> listaConvenio;
    
    public UsuarioBean(){
        this.usuario = new Usuario();
    }

    public void validaConvenio(){
        System.out.println("rodou");
            if(valorConvenio.equals(COMPARATIVO)){
                this.mostraConvenio = true;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuariodao() {
        return usuariodao;
    }

    public void setUsuariodao(UsuarioDAO usuariodao) {
        this.usuariodao = usuariodao;
    }
    
    
//    public void cadastrarUsuario(){
//        
//        try {
//            this.usuariodao = new UsuarioDAO();
//            boolean insereLivro = this.usuariodao.cadastraLivro(this.usuario);
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
//        this.usuario = new Usuario();  
//    }
    
    public void save() {
        addMessage("Success", "Data saved");
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "index";
    }
    
    public String logar(){
        try {
            this.usuariodao = new UsuarioDAO();
            this.usuario = this.usuariodao.getUsuario(this.usuario);
            if(this.usuario != null){
                return "paginaInicial";
            }else{
                FacesMessage mensagem = new FacesMessage("Usuario/Senha Inválido!"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "index";
    }
    
    public void cadastrarValorConsulta(){
        
        try {
            this.usuariodao = new UsuarioDAO();
            boolean insereValorConsulta = this.usuariodao.cadastraValorConsulta(this.usuario);
            if(insereValorConsulta){
                FacesMessage mensagem = new FacesMessage("Valor adicionado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha no cadastro"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.usuario = new Usuario();  
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
