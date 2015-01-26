/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.Bean;

import java.util.Date;

/**
 *
 * @author Alcivan
 */
public class Semanal {
    private int usuario_id;
    private boolean pagamento;
    private String id_sessao;
    private Date data;

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    public String getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(String id_sessao) {
        this.id_sessao = id_sessao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
