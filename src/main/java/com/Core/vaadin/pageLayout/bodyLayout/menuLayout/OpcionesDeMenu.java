package com.Core.vaadin.pageLayout.bodyLayout.menuLayout;

import com.Core.vaadin.Calculador;
import com.Core.vaadin.Core;
import com.Core.vaadin.arduino.BotonSwitch;
import com.Core.vaadin.pageLayout.bodyLayout.contentLayout.ContentLayout;
import com.Core.vaadin.pushServer.NoticeBoard;
import com.Core.vaadin.pushServer.PruebaLabel;
import com.Core.vaadin.tabSheet.ContextMenu;
import com.Core.vaadin.tabSheet.Downloader;
import com.Core.vaadin.tabSheet.EjemploTema;
import com.Core.vaadin.tabSheet.Principal;
import com.Core.vaadin.tabSheet.progressBar.BarraCarga;
import com.Core.vaadin.tables.FieldFactory;
import com.Core.vaadin.tables.TreeTabla;
import com.Core.vaadin.tables.TreeTabla2;
import com.Core.vaadin.tables.Triangulos;
import com.Core.vaadin.workingWithForms.Main;
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
			//contentLayout.removeAllComponents();
			contentLayout.addComponent(ContentLayout.getLayout());
			
		}else if(MenuLayout.ARDUINO.equals(value)) {
			BotonSwitch layout = new BotonSwitch();
			contentLayout.addComponent(layout);
		}else if(MenuLayout.CONTENT2.equals(value)) {
			
			FieldFactory layout = new FieldFactory();
			contentLayout.addComponent(layout);
			
		}else if(MenuLayout.TREETABLA.equals(value)) {
			
			TreeTabla tree = new TreeTabla();
			contentLayout.addComponent(tree);
		
		}else if(MenuLayout.TREETABLA2.equals(value)) {
			
			TreeTabla2 tree = new TreeTabla2();
			contentLayout.addComponent(tree);
			
		}else if(MenuLayout.BARRA.equals(value)) {
			
			BarraCarga barra = new BarraCarga();
			contentLayout.addComponent(barra);
			
		}else if(MenuLayout.ICONO2.equals(value)) {
			
			Principal icono = new Principal();
			contentLayout.addComponent(icono);
			
		}else if(MenuLayout.DOWNLOADER.equals(value)) {
			
			Downloader downloader = new Downloader();
			contentLayout.addComponent(downloader);
			
		}else if(MenuLayout.CLICKENTABLE.equals(value)) {
			
			ContextMenu clickTable = new ContextMenu();
			contentLayout.addComponent(clickTable);
			
		}else if(MenuLayout.TIPOSDETEMAS.equals(value)) {
				EjemploTema  temas = new EjemploTema();
				contentLayout.addComponent(temas);
		}else if(MenuLayout.TRIANGULO.equals(value)) {
			Triangulos tri = new Triangulos();
			contentLayout.addComponent(tri);
		}else if(MenuLayout.CALCULADOR.equals(value)) {
			Calculador calc = new Calculador();
			contentLayout.addComponent(calc);
		
		}else if(MenuLayout.WORKINGWITHFORM.equals(value)) {
			Main validar = new Main();
			contentLayout.addComponent(validar);
			
		}else if(MenuLayout.PUSH_SERVER.equals(value)) {
			NoticeBoard nota = new NoticeBoard();
			contentLayout.addComponent(nota);
		}else if(MenuLayout.PRUEBA_LABEL.equals(value)) {
			PruebaLabel pruebaLabel = new PruebaLabel();
			contentLayout.addComponent(pruebaLabel);
		}
	}

}
