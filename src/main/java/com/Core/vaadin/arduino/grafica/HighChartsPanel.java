package com.Core.vaadin.arduino.grafica;

import org.vaadin.highcharts.HighChart;

import com.vaadin.ui.Panel;

public class HighChartsPanel extends Panel {
	
	private HighChart highChart = new HighChart();
	
	public HighChartsPanel() {
		setWidth("650px");
		setHeight("400px");
		
		highChart.addValue(0);
		highChart.setImmediate(true);
		highChart.setSizeFull();
		
		setContent(highChart);
	}
	
	public void setValue(int value) {
		highChart.addValue(value);
	}
	
}
