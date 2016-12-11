package com.Core.vaadin.pageLayout.bodyLayout.menuLayout;


import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.PrincipalTabArduino;
import com.Core.vaadin.pageLayout.bodyLayout.contentLayout.ContentLayout;
import com.Core.vaadin.pushServer.PrincipalTab;
import com.Core.vaadin.pushServer.pruebas.PruebaLabel2;
import com.Core.vaadin.tabSheet.Principal;
import com.Core.vaadin.tabSheet.obtenerRecursos.DownloaderPdf;
import com.Core.vaadin.tabSheet.progressBar.BarraCarga;
import com.Core.vaadin.tables.PrincipalTablas;
import com.Core.vaadin.tetris.Tetris;
import com.Core.vaadin.workingWithForms.Calculador;
import com.Core.vaadin.workingWithForms.PrincipalFormularios;
import com.Core.vaadin.workingWithForms.triangulos.Triangulos;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

public class OpcionesDeMenu implements ItemClickListener {
	
	private static final long serialVersionUID = 1L;
	private Core getUI = Core.getCurrent();
	
	@Override
	public void itemClick(ItemClickEvent event) {
		
		Object value = event.getItemId();
	
		ContentLayout contentLayout = getUI.getPageLayout().getBodyLayout().getContentLayout();
		
		contentLayout.removeAllComponents();
		
		if(MenuLayout.HOME.equals(value)) {
			
			contentLayout.addComponent(contentLayout.getLayout());
			
		}else if(MenuLayout.SERIALPORT.equals(value)) {
			PrincipalTabArduino tab = new PrincipalTabArduino();
			contentLayout.addComponent(tab);
			
		}else if(MenuLayout.TABSHEET.equals(value)) {
			
			Principal tabs = new Principal();
			contentLayout.addComponent(tabs);
			
		}else if(MenuLayout.FORMULARIOS.equals(value)) {
			PrincipalFormularios validar = new PrincipalFormularios();
			contentLayout.addComponent(validar);
			
		}else if(MenuLayout.PUSH_SERVER.equals(value)) {
			PrincipalTab main = new PrincipalTab();
			contentLayout.addComponent(main);
			
		}else if(MenuLayout.PRUEBA_LABEL.equals(value)) {
			PruebaLabel2 pruebaLabel = new PruebaLabel2();
			contentLayout.addComponent(pruebaLabel);
			
		}else if(MenuLayout.TABLAS.equals(value)) {
			PrincipalTablas tablas = new PrincipalTablas();
			contentLayout.addComponent(tablas);
			
		}else if(MenuLayout.TETRIS.equals(value)) {
			Tetris tetris = new Tetris();
			contentLayout.addComponent(tetris);
		}
	}

}
