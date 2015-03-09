/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.Bean;

import java.io.Serializable;

/**
 *
 * @author Lano_2
 */
public class Convenio extends Pacientes{
    
    private int idConvenio;
    private String tipoDeConvenio;
    private boolean mostraPesquisa;

    public int getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getTipoDeConvenio() {
        return tipoDeConvenio;
    }

    public void setTipoDeConvenio(String tipoDeConvenio) {
        this.tipoDeConvenio = tipoDeConvenio;
    }

    public boolean isMostraPesquisa() {
        return mostraPesquisa;
    }

    public void setMostraPesquisa(boolean mostraPesquisa) {
        this.mostraPesquisa = mostraPesquisa;
    }
    
}
