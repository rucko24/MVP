package com.Core.vaadin.tabSheet.obtenerRecursos;

import com.Core.vaadin.tabSheet.progressBar.ProgressBarListener;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

public class UploadImage extends VerticalLayout {
	
	private ProgressBar indicator = new ProgressBar(0.0f);
	private ProgressBarListener  progressBarListener = new ProgressBarListener(indicator);
	private Upload upload = new Upload("Cargar Imagen", progressBarListener);
	
	public UploadImage() {
		setSpacing(true);
		setMargin(true);
		/**
		 * FIXME BARRA DE CARGAR CON IMAGEN, MODIFICAR, NO AGREGA EL INDICADOR DE SUBIDA, PROGRESSBAR
		 */
		upload.setButtonCaption("cargar");
		upload.addProgressListener(progressBarListener);
		upload.addFinishedListener(progressBarListener);
		upload.addStartedListener(progressBarListener);
		
		
		addComponent(upload);
		
	}
	
}
