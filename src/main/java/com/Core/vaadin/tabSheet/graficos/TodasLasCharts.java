package com.Core.vaadin.tabSheet.graficos;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class TodasLasCharts extends VerticalLayout {
	
	public TodasLasCharts() {
		setSizeFull();
		Component charts = getAllCharts();
		
		addComponent(charts);
		setComponentAlignment(charts, Alignment.BOTTOM_CENTER);
	}
	
	
	private Component getAllCharts() {
		VerticalLayout centrar = (VerticalLayout) getLayout();
		centrar.setSizeUndefined();
		centrar.setSpacing(true);
		centrar.addComponent(getChartArea());
		centrar.addComponent(getChartDispercion());
		centrar.addComponent(getChartLinea());
		centrar.addComponent(getChartLogaritmica());
		centrar.addComponent(getChartPaso());
		centrar.addComponent(getChartPolar());
		centrar.addComponent(getChartSerieTiempo());
		centrar.addComponent(getChartPasoArea());
		
		return centrar;
	}
	private Component getChartLogaritmica() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart logaritmica = new Chart(Chart.LOGARITMICA,"ChartLogaritmica");
		layout.addComponent(logaritmica);
		
		return layout;
	}
	
	private Component getChartSerieTiempo() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart serieTiempo = new Chart(Chart.SERIETIEMPO, "Chart Serie Tiempo");
		layout.addComponent(serieTiempo);
		
		return layout;
	}
	
	private Component getChartPolar() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart polar = new Chart(Chart.POLAR, "Chart Polar");
		layout.addComponent(polar);
		
		return layout;
	}
	
	private Component getChartLinea() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart linea = new Chart(Chart.LINEA, "Chart Linea");
		layout.addComponent(linea);
		
		return layout;
	}
	
	private Component getChartPasoArea() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart pasoArea = new Chart(Chart.PASOAREA, "Chart Paso Area");
		layout.addComponent(pasoArea);
		
		return layout;
	}
	
	private Component getChartPaso() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart paso = new Chart(Chart.PASO, "Chart Paso");
		layout.addComponent(paso);
		
		return layout;
	}
	
	private Component getChartArea() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart area = new Chart(Chart.AREA, "Chart Area");
		layout.addComponent(area);
		
		return layout;
	}
	
	private Component getChartDispercion() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		Chart dispercion = new Chart(Chart.DISPERSION, "Chart Dispercion");
		layout.addComponent(dispercion);
		
		return layout;
	}
	
	private Component getLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		
		return layout;
	}

}
