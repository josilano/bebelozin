/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.Bean;

import edu.br.bebelozin.DAO.SessaoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Lano_2
 */
@FacesConverter(value="sessaoConverter")
public class SessaoConverter implements Converter{

@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            SessaoDAO sessaoDAO = new SessaoDAO();
            Sessao sessao = sessaoDAO.pesquisaSessaoPorNome(string);
            return sessao;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConvenioConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

@Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Sessao sessao = new Sessao();
        sessao = (Sessao) o;
        return sessao.getTipoDeSessao();
    }
}
