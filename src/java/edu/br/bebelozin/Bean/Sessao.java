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
public class Sessao {
    
    private int idSessao;
    private String tipoDeSessao;
    private boolean mostraPesquisa;

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public String getTipoDeSessao() {
        return tipoDeSessao;
    }

    public void setTipoDeSessao(String tipoDeSessao) {
        this.tipoDeSessao = tipoDeSessao;
    }

    public boolean isMostraPesquisa() {
        return mostraPesquisa;
    }

    public void setMostraPesquisa(boolean mostraPesquisa) {
        this.mostraPesquisa = mostraPesquisa;
    }
}
