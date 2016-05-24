package com.Core.vaadin.tabSheet.progressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.VerticalLayout;
/*
 * pagina 172 vaadin cookBook
 */
public class ProgressBarListener extends VerticalLayout implements Receiver, ProgressListener, StartedListener, FinishedListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ProgressBar indicator;
	
	public ProgressBarListener(ProgressBar indicator) {
		this.indicator = indicator;
		
	}
	
	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null;
		
		try {
			File file = new File("/home/rubn/Imágenes "+filename);
			fos = new FileOutputStream(file);
		}catch(FileNotFoundException e) {
			Notification.show(e.getMessage(),Notification.Type.ERROR_MESSAGE);
			return null;
		}
		return fos;
	}
	
	@Override
	public void updateProgress(long readBytes, long contentLength) {
		
		try {
			Thread.sleep(100);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		float newValue = readBytes / (float) contentLength;
		indicator.setValue(newValue);
	}

	@Override
	public void uploadStarted(StartedEvent event) {
		addComponent(indicator);
		Notification.show("Carga, iniciada", Notification.Type.TRAY_NOTIFICATION);
		
	}
	
	@Override
	public void uploadFinished(FinishedEvent event) {
		removeComponent(indicator);
		Notification.show("Carga, finalizada", Notification.Type.TRAY_NOTIFICATION);;
	}

	
	
}
