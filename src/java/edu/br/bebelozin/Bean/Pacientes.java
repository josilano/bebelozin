/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.Bean;

import java.util.Date;

/**
 *
 * @author Lano_2
 */
public class Pacientes {
    
    private int idPaciente;
    private String nomePaciente;
    private String telefonePaciente;
    private String doencaPaciente;
    private String convenioPaciente;
    private boolean mostrapesquisa;
    private Date diaConsulta;
    private boolean pagamento;

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getTelefonePaciente() {
        return telefonePaciente;
    }

    public void setTelefonePaciente(String telefonePaciente) {
        this.telefonePaciente = telefonePaciente;
    }

    public String getDoencaPaciente() {
        return doencaPaciente;
    }

    public void setDoencaPaciente(String doencaPaciente) {
        this.doencaPaciente = doencaPaciente;
    }

    public String getConvenioPaciente() {
        return convenioPaciente;
    }

    public void setConvenioPaciente(String convenioPaciente) {
        this.convenioPaciente = convenioPaciente;
    }

    public boolean isMostrapesquisa() {
        return mostrapesquisa;
    }

    public void setMostrapesquisa(boolean mostrapesquisa) {
        this.mostrapesquisa = mostrapesquisa;
    }

    public Date getDiaConsulta() {
        return diaConsulta;
    }

    public void setDiaConsulta(Date diaConsulta) {
        this.diaConsulta = diaConsulta;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }
    
}