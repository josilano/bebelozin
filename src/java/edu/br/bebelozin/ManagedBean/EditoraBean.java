/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Editora;
import edu.br.bebelozin.DAO.EditoraDAO;
import java.io.Serializable;
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

public class EditoraBean implements Serializable{
    
   private Editora editora;
   private EditoraDAO editoradao;

   public EditoraBean(){
       this.editora = new Editora();
   }
   
    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public EditoraDAO getEditoradao() {
        return editoradao;
    }

    public void setEditoradao(EditoraDAO editoradao) {
        this.editoradao = editoradao;
    }
   
   public void cadastrarEditora(){
        
       try {
           this.editoradao = new EditoraDAO();
           boolean insereEditora = editoradao.cadastrarEditora(this.editora);
           if(insereEditora){
                FacesMessage mensagem = new FacesMessage("Editora cadastrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha no cadastro"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(EditoraBean.class.getName()).log(Level.SEVERE, null, ex);
       }
       this.editora = new Editora();
    }
    
    public void alteraEditora(){
        
       try {
           this.editoradao = new EditoraDAO();
           boolean alteraEditora = editoradao.atualizarEditora(this.editora);
           if(alteraEditora){
                FacesMessage mensagem = new FacesMessage("Editora alterada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na alteração"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(EditoraBean.class.getName()).log(Level.SEVERE, null, ex);
       }
       this.editora = new Editora(); 
    }
    
    public void consultaEditora(){
        
       try {
           this.editoradao = new EditoraDAO();
           Editora editoranew = editoradao.consultarEditora(this.editora);
           if(editoranew != null){
                FacesMessage mensagem = new FacesMessage("Editora encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Editora não encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(EditoraBean.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void excluiEditora(){
        
       try {
           this.editoradao = new EditoraDAO();
           boolean excluirEditora = editoradao.excluirEditora(this.editora);
           if(excluirEditora){
                FacesMessage mensagem = new FacesMessage("Editora excluído"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na exclusão"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(EditoraBean.class.getName()).log(Level.SEVERE, null, ex);
       }
       this.editora = new Editora();
    }
}
