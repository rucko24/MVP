package com.Core.vaadin.arduino;

import java.awt.List;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.vaadin.addon.JFreeChartWrapper;

import com.panamahitek.PanamaHitek_Arduino;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Arduino2 extends VerticalLayout {

	private JFreeChart jfreeChar;
	private XYSeries xySeries;
	private XYSeriesCollection xySeriesCollection;
	private SerialPortEventListener serialPortE;

	private int f;
	private static PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();

	public Arduino2() {
		
		setSizeUndefined();
		this.f = 0;
		xySeries = new XYSeries("Temperatura");
		xySeriesCollection = new XYSeriesCollection();

		serialPortE = new SerialPortEventListener() {

			public void serialEvent(SerialPortEvent arg0) {

				if (arduino.isMessageAvailable() == true) {

					try {
						f++;
						xySeries.add(f, Integer.valueOf(arduino.printMessage()));

					} catch (NumberFormatException ex) {
						Notification.show(ex.getMessage(), Notification.Type.ERROR_MESSAGE);
					}

				}

			}
		};

	}

	public void init() {

		try {
			arduino.arduinoRX("/dev/ttyS0", 9600, serialPortE);

		} catch (Exception e) {
			// Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null,
			// e );
			Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
		}

		xySeries.add(0, 0);
		xySeriesCollection.addSeries(xySeries);

		jfreeChar = ChartFactory.createXYLineChart("Temperatura vs Tiempo", "TIEMPO", "TEMPERATURA", xySeriesCollection,
				PlotOrientation.VERTICAL, true, true, false);

		addComponent(wrapper());
	}

	public JFreeChartWrapper wrapper() {
		return new JFreeChartWrapper(jfreeChar) {

			@Override
			public void attach() {
				super.attach();
				setResource("src", getSource());
			}
		};
	}

	public void stopConexion() {

		try {
			arduino.killArduinoConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
