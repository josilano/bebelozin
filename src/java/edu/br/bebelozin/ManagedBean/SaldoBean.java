/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.br.bebelozin.ManagedBean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Alcivan
 */
@ManagedBean
public class SaldoBean implements Serializable{
      private BarChartModel grafico;

    
        @PostConstruct
      public void init() {
        createAnimatedModels();
    }
      
      
      public BarChartModel getGrafico() {
        return grafico;
    }
      
      private void createAnimatedModels() {
        
         
        grafico = initBarModel();
        grafico.setTitle("Estatistica!");
        grafico.setAnimate(true);
        grafico.setLegendPosition("ne");
        Axis yAxis = grafico.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
       private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries consultas = new ChartSeries();
        consultas.setLabel("Consultas");
        consultas.set("2004", 120);
        consultas.set("2005", 100);
        consultas.set("2006", 44);
        consultas.set("2007", 150);
        consultas.set("2008", 25);
 
        ChartSeries testes = new ChartSeries();
        testes.setLabel("Testes");
        testes.set("2004", 52);
        testes.set("2005", 60);
        testes.set("2006", 110);
        testes.set("2007", 135);
        testes.set("2008", 120);
 
        model.addSeries(consultas);
        model.addSeries(testes);
         
        return model;
    }
    
      
}
