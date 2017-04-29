package com.Core.vaadin.arduino.grafica;

import com.vaadin.data.Validator;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Panel;

import at.downdrown.vaadinaddons.highchartsapi.HighChart;
import at.downdrown.vaadinaddons.highchartsapi.HighChartFactory;
import at.downdrown.vaadinaddons.highchartsapi.exceptions.HighChartsException;
import at.downdrown.vaadinaddons.highchartsapi.model.Axis;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartConfiguration;
import at.downdrown.vaadinaddons.highchartsapi.model.ChartType;
import at.downdrown.vaadinaddons.highchartsapi.model.series.LineChartSeries;

public class HighChartsPanel extends Panel {

	private ChartConfiguration config = new ChartConfiguration();
	private Axis xAxis = new Axis(Axis.AxisType.xAxis);
	private Axis yAxis = new Axis(Axis.AxisType.yAxis);
	private HighChart highChart = new HighChart();
	private LineChartSeries datos = new LineChartSeries("Sensor LDR");
	
	public HighChartsPanel() {
		setWidth("650px");
		setHeight("400px");
		setImmediate(true);

		config.setTitle("Luminosidad vs Tiempo");
		config.setChartType(ChartType.LINE);
		config.setBackgroundColor(Color.WHITE);
		config.setLegendEnabled(true);
		config.setTooltipEnabled(true);
		
		
		datos.addData(1);
		datos.addData(500);
		datos.addData(100);
		datos.addData(50);
		datos.addData(10);
		datos.addData(90);
		datos.addData(700);
		datos.addData(300);
		datos.addData(450);
		datos.addData(100);
		datos.addData(50);
		datos.addData(10);
		datos.addData(300);
		datos.addData(450);
		datos.addData(10);
		datos.addData(90);
		datos.addData(700);
		datos.addData(300);
		
		config.getSeriesList().add(datos);

	//	xAxis.setLineColor(Color.RED);
	//	xAxis.setLineWidth(1);
	//	yAxis.setLineColor(Color.RED);

		xAxis.setTickLength(30);
		xAxis.setTitle("Tiempo");
		xAxis.setAllowDecimals(false);
		yAxis.setTitle("Luminosidad");
		config.setxAxis(xAxis);
		config.setyAxis(yAxis);

		try {
			highChart = HighChartFactory.renderChart(config);
			highChart.setSizeFull();
			setContent(highChart);
			

		} catch (HighChartsException e) {

			e.printStackTrace();
		}

	}

	public void setValue(int datos) {
		this.datos.addData(datos);
	}

}
