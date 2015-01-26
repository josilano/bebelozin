/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Semanal;
import edu.br.bebelozin.DAO.SemanalDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Alcivan
 */
@ManagedBean
public class SemanalBean {
    private Semanal semanal;
    private SemanalDAO semanalDAO;

    public Semanal getSemanal() {
        return semanal;
    }

    public void setSemanal(Semanal semanal) {
        this.semanal = semanal;
    }

    public SemanalDAO getSemanalDAO() {
        return semanalDAO;
    }

    public void setSemanalDAO(SemanalDAO semanalDAO) {
        this.semanalDAO = semanalDAO;
    }
    public SemanalBean(){
        this.semanal = new Semanal();
    }
     public void cadastro(){
        try {
            this.semanalDAO = new SemanalDAO();
            boolean chamacadas = semanalDAO.cadastraSemanal(semanal);
            if(chamacadas){
                
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SemanalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }this.semanal = new Semanal();
       } 
}
