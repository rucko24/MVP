package com.Core.vaadin.tabSheet.graficos;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class TodasLasCharts extends VerticalLayout {
	
	public TodasLasCharts() {
		setSizeFull();
		addStyleName("miScroll");
		Component charts = getAllCharts();
		
		addComponent(charts);
		
	}
	
	
	private Component getAllCharts() {
		VerticalLayout centrar = (VerticalLayout) getLayout();
		centrar.setSpacing(true);
		centrar.setSizeUndefined();
		Chart logaritmica = new Chart(Chart.LOGARITMICA,"ChartLogaritmica");
		Chart serieTiempo = new Chart(Chart.SERIETIEMPO, "Chart Serie Tiempo");
		Chart polar = new Chart(Chart.POLAR, "Chart Polar");
		Chart linea = new Chart(Chart.LINEA, "Chart Linea");
		Chart pasoArea = new Chart(Chart.PASOAREA, "Chart Paso Area");
		Chart area = new Chart(Chart.AREA, "Chart Area");
		Chart paso = new Chart(Chart.PASO, "Chart Paso");
		Chart dispercion = new Chart(Chart.DISPERSION, "Chart Dispercion");
		centrar.addComponents(logaritmica,serieTiempo,polar,linea,pasoArea,area,paso,dispercion);
		centrar.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		
		return centrar;
	}
	
	private Component getLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		
		
		return layout;
	}

}
