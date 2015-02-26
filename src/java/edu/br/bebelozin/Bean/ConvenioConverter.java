/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.Bean;

import edu.br.bebelozin.DAO.ConvenioDAO;
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
@FacesConverter(value="convenioConverter")
public class ConvenioConverter implements Converter{

@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            ConvenioDAO convenioDAO = new ConvenioDAO();
            Convenio convenio = convenioDAO.pesquisaConvenioPorNome(string);
            return convenio;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConvenioConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

@Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Convenio convenio = new Convenio();
        convenio = (Convenio) o;
        return convenio.getTipoDeConvenio();
    }
}
