/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.br.bebelozin.ManagedBean;

import edu.br.bebelozin.Bean.Convenio;
import edu.br.bebelozin.Bean.Pacientes;
import edu.br.bebelozin.Bean.Sessao;
import edu.br.bebelozin.Bean.Usuario;
import edu.br.bebelozin.DAO.ConsultaDiariaDAO;
import edu.br.bebelozin.DAO.ConvenioDAO;
import edu.br.bebelozin.DAO.PacientesDAO;
import edu.br.bebelozin.DAO.SessaoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author Lano_2
 */
@ManagedBean
@ViewScoped
public class SaldoBean {
    
    private Sessao sessao;
    private SessaoDAO sessaodao;
    private List<Sessao> listaSessao;
    
    private Pacientes paciente;
    private PacientesDAO pacientedao;
    private List<Pacientes> listaConsultaDiaria = new ArrayList<>();
    
    private ConsultaDiariaDAO consultaDiariadao;
    
    private BarChartModel graficoModel;
    private Usuario usuario;
    
    public SaldoBean(){
        this.sessao = new Sessao();
        this.paciente = new Pacientes();
        
        createAnimatedModels();
    }

    //gets e sets
    
    public BarChartModel getGraficoModel() {
        return graficoModel;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public SessaoDAO getSessaodao() {
        return sessaodao;
    }

    public void setSessaodao(SessaoDAO sessaodao) {
        this.sessaodao = sessaodao;
    }

    public List<Sessao> getListaSessao() {
        return listaSessao;
    }

    public void setListaSessao(List<Sessao> listaSessao) {
        this.listaSessao = listaSessao;
    }

    public Pacientes getPaciente() {
        return paciente;
    }

    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    public PacientesDAO getPacientedao() {
        return pacientedao;
    }

    public void setPacientedao(PacientesDAO pacientedao) {
        this.pacientedao = pacientedao;
    }

    public List<Pacientes> getListaConsultaDiaria() {
        return listaConsultaDiaria;
    }

    public void setListaConsultaDiaria(List<Pacientes> listaConsultaDiaria) {
        this.listaConsultaDiaria = listaConsultaDiaria;
    }

    public ConsultaDiariaDAO getConsultaDiadiadao() {
        return consultaDiariadao;
    }

    public void setConsultaDiadiadao(ConsultaDiariaDAO consultaDiadiadao) {
        this.consultaDiariadao = consultaDiadiadao;
    }

    public ConsultaDiariaDAO getConsultaDiariadao() {
        return consultaDiariadao;
    }

    public void setConsultaDiariadao(ConsultaDiariaDAO consultaDiariadao) {
        this.consultaDiariadao = consultaDiariadao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public void limpar(){
        this.sessao = new Sessao();
    }
    
    private void createAnimatedModels() {       
        graficoModel = initGraficoBarModel();
        graficoModel.setTitle("Pagamentos do Mês");
        graficoModel.setAnimate(true);
        graficoModel.setLegendPosition("ne");
        Axis yAxis = graficoModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(somarDevido() + 10);
    }
    
    private BarChartModel initGraficoBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries pagos = new ChartSeries();
        pagos.setLabel("Consultas Pagas");
        pagos.set(new Date(), somarPago());
//        pagos.set("2005", 100);
//        pagos.set("2006", 44);
//        pagos.set("2007", 150);
//        pagos.set("2008", 25);
 
        ChartSeries devido = new ChartSeries();
        devido.setLabel("Total a Receber");
        devido.set(new Date(), somarDevido());
//        devido.set("2005", 60);
//        devido.set("2006", 110);
//        devido.set("2007", 135);
//        devido.set("2008", 120);
 
        model.addSeries(pagos);
        model.addSeries(devido);
         
        return model;
    }
    
    //soma das consultas pagas
    private int somarPago(){
        try {
            this.sessaodao = new SessaoDAO();
            this.sessao.setSomaPagaConsulta(this.sessaodao.somaPago());
            if(sessao.getSomaPagaConsulta() >= 0){
                int valor = this.sessao.getSomaPagaConsulta();
                
                return valor;
            }
            else{
                FacesMessage mensagem = new FacesMessage("Sessão não encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaldoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    //soma das consultas devidas
    private int somarDevido(){
        try {
            this.sessaodao = new SessaoDAO();
            this.sessao.setSomaDevidaConsulta(this.sessaodao.somaDevido());
            if(sessao.getSomaDevidaConsulta()>= 0){
                return this.sessao.getSomaDevidaConsulta();
            }
            else{
                FacesMessage mensagem = new FacesMessage("Sessão não encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaldoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    //popula a lista das sessões com todas as cadastradas
    public void listaCompletaSessao(){
        try {
            this.sessaodao = new SessaoDAO();
            this.listaSessao = this.sessaodao.listaDeSessao();
            if(listaSessao != null){
                
            }
            else{
                FacesMessage mensagem = new FacesMessage("Sessão não encontrada"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaldoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    //add um paciente a lista de consultas diária
    public void addConsultaDiaria(){
        System.out.println("entrou no método addconsultadiaria do mb");
        try {
            this.consultaDiariadao = new ConsultaDiariaDAO();
            System.out.println(this.paciente.getNomePaciente());
            boolean montarConsulta = this.consultaDiariadao.montandoConsultaDoPaciente(this.paciente, this.sessao.getSessaolista());
            //boolean montarConsulta2 = this.sessaodao.montandoConsultaDaSessao(this.sesao);
                this.listaConsultaDiaria.add(this.paciente);
                System.out.println(this.listaConsultaDiaria.get(0).getNomePaciente());
            if(montarConsulta){
                
                
                
                
                this.paciente.setMostrapesquisa(true);
                FacesMessage mensagem = new FacesMessage("Usuario encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            else{
                FacesMessage mensagem = new FacesMessage("Usuario não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public void montaConsultaDiaria(){
        try {
            this.pacientedao = new PacientesDAO();
            System.out.println(this.paciente.getNomePaciente());
            this.paciente = this.pacientedao.selecionaPaciente(this.paciente);
                this.listaConsultaDiaria.add(this.paciente);
                System.out.println(this.listaConsultaDiaria.get(0).getNomePaciente());
            if(paciente != null){
                
                listaCompletaSessao();
                
                
                this.paciente.setMostrapesquisa(true);
                FacesMessage mensagem = new FacesMessage("Usuario encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            else{
                FacesMessage mensagem = new FacesMessage("Usuario não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   
   public void selecaoRapida(){
       this.listaConsultaDiaria.add(this.paciente);
       listaCompletaSessao();
   }
//  public void pedidoListarPacientes(){
//        try {
//            this.pacientedao = new PacientesDAO();
//            this.listaPaciente = this.pacientedao.listaDePacientes();
//                if(listaPaciente != null){
//                    //System.out.println(listaPaciente.get(0).getNomePaciente());
//                }else{
//                }
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }  
}