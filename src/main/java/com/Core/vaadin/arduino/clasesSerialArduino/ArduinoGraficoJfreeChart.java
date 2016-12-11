package com.Core.vaadin.arduino.clasesSerialArduino;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.vaadin.addon.JFreeChartWrapper;

import com.Core.vaadin.Core;

import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ArduinoGraficoJfreeChart extends VerticalLayout {

	private JFreeChart jfreeChar;
	private XYSeries xySeries;
	private XYSeriesCollection xySeriesCollection;
	private SerialPortEventListener serialPortE;
	private Core ui = Core.getCurrent();
	private int f;
//	private PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
	private final Label labelArduino = new Label();

	public ArduinoGraficoJfreeChart() {

		setWidth("950px");
		setHeight("750px");
		this.f = 0;
		xySeries = new XYSeries("Luminosidad");
		xySeriesCollection = new XYSeriesCollection();

		labelArduino.addStyleName(ValoTheme.LABEL_COLORED);

		serialPortE = new SerialPortEventListener() {
			public void serialEvent(SerialPortEvent arg0) {

				try {
				//	if (arduino.isMessageAvailable() == true) {
					//	f++;

					
					
				} catch (NumberFormatException ex) {
					Notification.show("Error serial event :s " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
				} 

			}
		};

		addComponent(labelArduino);
	}

	static void println(String s) {
		System.out.println(s);
	}

	public void init() {

		try {
		//	arduino.arduinoRX("/dev/ttyS0", 9600, serialPortE);

		} catch (Exception e) {
			// Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null,
			// e );
			Notification.show("Error: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);

		}
		xySeries.add(0, 0);
		xySeriesCollection.addSeries(xySeries);

		jfreeChar = ChartFactory.createXYLineChart("Luminosidad vs Tiempo", "TIEMPO", "Luminosidad", xySeriesCollection,
				PlotOrientation.VERTICAL, true, true, false);

		// this.addComponent(wrapper());
	}

	public JFreeChartWrapper wrapper() {

		return new JFreeChartWrapper(jfreeChar) {
			private static final long serialVersionUID = 1L;

			@Override
			public void attach() {

				super.attach();
				setResource("src", getSource());

			}
		};
	}

	public void stopConexion() {

		try {
		//	arduino.killArduinoConnection();
		} catch (Exception e) {

			Notification.show("ERROR: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}

	}

}
