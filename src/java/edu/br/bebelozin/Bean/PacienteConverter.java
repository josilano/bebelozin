/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.Bean;

import edu.br.bebelozin.DAO.PacientesDAO;
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
@FacesConverter(value="pacienteConverter")
public class PacienteConverter implements Converter {

@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            PacientesDAO pacienteDAO = new PacientesDAO();
            Pacientes paciente = pacienteDAO.pesquisaPacientesPorNome(string);
            return paciente;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PacienteConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

@Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Pacientes paciente = new Pacientes();
        paciente = (Pacientes) o;
        return paciente.getNomePaciente();
    }

}
