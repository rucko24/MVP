package com.Core.vaadin.tabSheet;


import com.Core.vaadin.tabSheet.graficos.JfreeGrafico;
import com.Core.vaadin.tabSheet.graficos.TodasLasCharts;
import com.Core.vaadin.tabSheet.obtenerRecursos.ArchivoTxtDesdeElServer;
import com.Core.vaadin.tabSheet.obtenerRecursos.DownloaderPdf;
import com.Core.vaadin.tabSheet.obtenerRecursos.PaginaWebExterna;
import com.Core.vaadin.tabSheet.obtenerRecursos.UploadImage;
import com.Core.vaadin.tabSheet.obtenerRecursos.VideoExterno;
import com.Core.vaadin.tabSheet.progressBar.BarraCarga;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.Flash;
import com.vaadin.ui.Image;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class Principal extends TabSheet {

	private static final long serialVersionUID = 1L;
	
	public Principal() {

		
		addTab(getImagenTemaRuno());
		addTab(getImagenGlobo());
		addTab(getSlider());
		addTab(getJFreeChartFixMe());
		addTab(getJFreeChart_Varias());
		addTab(getWebPage());
		addTab(getVideo());
		addTab(getCargarImagen());
		addTab(getBarraDeCarga());
		addTab(getPdfDesdeServidor());
		addTab(getArchivoTexto());
		
	}
	
	private Component getArchivoTexto() {
		VerticalLayout layout = (VerticalLayout) getLayout("Download Texto-fixme");
		ArchivoTxtDesdeElServer archivo = new ArchivoTxtDesdeElServer();
		layout.addComponent(archivo);
		
		return layout;
	}
	
	private Component getBarraDeCarga() {
		VerticalLayout layout = (VerticalLayout) getLayout("Barra de carga-fixme");
		BarraCarga barra = new BarraCarga();
		layout.addComponent(barra);
		
		return layout;
	}
	
	private Component getPdfDesdeServidor() {
		VerticalLayout layout = (VerticalLayout) getLayout("Img from file system-fixme");
		DownloaderPdf downLoadPdf = new DownloaderPdf();
		layout.addComponent(downLoadPdf);
		
		return layout;
	}
	private Component getCargarImagen() {
		VerticalLayout layout = (VerticalLayout) getLayout("Upload Image-fixme");
		UploadImage img = new UploadImage();
		layout.addComponent(img);
		
		return layout;
	}
	
	private Component getJFreeChart_Varias() {
		
		VerticalLayout layout = (VerticalLayout) getLayout("JFreeChart-Varias");
		TodasLasCharts todasLasCharts = new TodasLasCharts();
		layout.addComponent(todasLasCharts);
		
		return layout;
	}
	
	private Component getJFreeChartFixMe() {
		VerticalLayout layout = (VerticalLayout) getLayout("JFreeChart-FixMe");
		JfreeGrafico jfreeChart = new JfreeGrafico();
		layout.addComponent(jfreeChart);
		
		return layout;
	}
	
	private Component getSlider() {
		VerticalLayout layout = (VerticalLayout) getLayout("Deslisador");
		Deslisador slider = new Deslisador();
		layout.addComponent(slider);
		
		return layout;
	}
	
	private Component getVideo() {
		
		VerticalLayout layout = (VerticalLayout) getLayout("Video-FixMe");
		VideoExterno video = new VideoExterno();
		layout.addComponent(video);
	
		
		return layout;
	}
	
	private Component getWebPage() {
		VerticalLayout layout = (VerticalLayout) getLayout("Web page");
		PaginaWebExterna frameFromUrl = new PaginaWebExterna();
		layout.addComponent(frameFromUrl);
		
		
		return layout;
	}
	
	private Component getImagenGlobo() {
		VerticalLayout layout = (VerticalLayout) getLayout("Imagen GLOBO-fixme");
		Image imgFromClassPath = new Image(null, new ClassResource("globe.png"));
		layout.addComponent(imgFromClassPath);
		
		return layout;
	}
	
	private Component getImagenTemaRuno() {
		VerticalLayout layout = (VerticalLayout)getLayout("Imagen Tema Runo");
		Image imgFromTheme = new Image(null, new ThemeResource("../runo/icons/16/error.png"));
		//imgFromTheme.setSizeFull();
		layout.addComponent(imgFromTheme);
		
		return layout;
		
	}
	
	private Component getLayout(String caption) {
		VerticalLayout layout = new VerticalLayout();
		layout.setCaption(caption);
		layout.setSizeFull();
		layout.setSpacing(true);
		layout.setMargin(true);
		
		return layout;
	}
}
