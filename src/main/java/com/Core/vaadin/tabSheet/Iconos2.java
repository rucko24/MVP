package com.Core.vaadin.tabSheet;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.jfree.chart.ChartFactory;

import com.Core.vaadin.tabSheet.graficos.Chart;
import com.Core.vaadin.tabSheet.graficos.JfreeGrafico;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Flash;
import com.vaadin.ui.Image;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class Iconos2 extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TabSheet tab = new TabSheet();
	
	public Iconos2() {
		
		setHeight("580px");;
		tab.setSizeFull();
		
		Image imgFromTheme = new Image(null, new ThemeResource("../runo/icons/16/error.png"));
		//imgFromTheme.setSizeFull();
		
		Image imgFromClassPath = new Image(null, new ClassResource("globe.png"));
		imgFromClassPath.setSizeFull();
		
		BrowserFrame frameFromUrl = new BrowserFrame(null, new ExternalResource("http://alejandrodu.com"));
		frameFromUrl.setSizeFull();
		VerticalLayout lPagina = new VerticalLayout(frameFromUrl);
		lPagina.setSizeFull();
		lPagina.setMargin(true);
		
		BrowserFrame frameFromFileSystem = new BrowserFrame(null, new FileResource(new File("/home/rubn/Readme")));
		
		StreamSource mySource = new StreamSource() {
			
			@Override
			public InputStream getStream() {
				// TODO Auto-generated method stub
				return new InputStream() {
					
					private int size = 20;
					
					public int read() throws IOException {
						
						if(size --> 0) {
							return 'V'; // we are returning 20 V' s
						}
						return -1;
					}
				};
			}
			
		};
		
		BrowserFrame frameFromStream = new BrowserFrame(null, new StreamResource(mySource,"hello.txt"));
		
		Flash flashFromUrl = new Flash(null, new ExternalResource("http://www.youtube.com/v/ALgCDkZvzeY&hl=en_US&fs=1"));
		flashFromUrl.setWidth("580px");
		flashFromUrl.setHeight("400px");
		VerticalLayout video = new VerticalLayout();
		
		video.setSizeFull();
		video.addComponent(flashFromUrl);
		video.setComponentAlignment(flashFromUrl, Alignment.MIDDLE_CENTER);
		
		Deslisador slider = new Deslisador();
		
		JfreeGrafico o = new JfreeGrafico();
		
		
		
		Chart dispercion = new Chart(Chart.DISPERSION, "Chart Dispercion");
		Chart area = new Chart(Chart.AREA, "Chart Area");
		Chart paso = new Chart(Chart.PASO, "Chart Paso");
		Chart pasoArea = new Chart(Chart.PASOAREA, "Chart Paso Area");
		Chart linea = new Chart(Chart.LINEA, "Chart Linea");
		Chart polar = new Chart(Chart.POLAR, "Chart Polar");
		Chart serieTiempo = new Chart(Chart.SERIETIEMPO, "Chart Serie Tiempo");
		Chart logaritmica = new Chart(Chart.LOGARITMICA, "Chart logaritmica");
		VerticalLayout centrar = new VerticalLayout(dispercion,area,paso, pasoArea, linea, polar, serieTiempo, logaritmica);
		centrar.setSizeUndefined();
		centrar.setSpacing(true);
		
		VerticalLayout layoutChart = new VerticalLayout(centrar);
		layoutChart.setComponentAlignment(centrar, Alignment.BOTTOM_CENTER);
		
		tab.addTab(imgFromTheme,"Img tema runo");
		tab.addTab(imgFromClassPath,"Img Globo");
		tab.addTab(lPagina,"WEB");
		tab.addTab(frameFromFileSystem,"Img from file system");
		tab.addTab(frameFromStream, "puras V");
		tab.addTab(video, "VIDEO");
		tab.addTab(slider,"Deslisador");
		tab.addTab(o, "JFreeChart");
		tab.addTab(layoutChart, "JFreeChart 2");
		
		addComponent(tab);
	}
}
