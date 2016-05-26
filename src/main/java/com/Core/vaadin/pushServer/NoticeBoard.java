package com.Core.vaadin.pushServer;

import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
/*
public class NoticeBoard extends VerticalLayout {
	
/*	private static List<Nota> notas = new ArrayList<>();
	private List<Window> windows = new ArrayList<>();
	private static int userCount;
	private int userId;
	private static int noteId = 1;
	private Refresher refresher = new Refresher();
	private static final int UPDATE_INTERVAL = 500;
	

	public NoticeBoard() {
		
		refresher.setRefreshInterval(UPDATE_INTERVAL);
		userId = userCount++;
		setSpacing(true);
		setMargin(true);
		addComponent(new Label("Logged as an User"+userId));
		
		Button addNotaBoton = new Button("agregar una nota");
		addNotaBoton.addClickListener(e -> {
			
			Nota nota = new Nota(noteId++);
			notas.add(nota);
			
			Window window = createWindow(nota);
			windows.add(window);
			UI.getCurrent().addWindow(window);
			
		});
		
		addComponent(addNotaBoton);
		addExtension(refresher);
		
		
		
		new NoticeBoardUpdater().start();
	}
	
	
	private Window createWindow(final Nota nota) {
		
		final Window window = new Window(nota.getCaption());
		Layout layout = new VerticalLayout();
		layout.addComponent(createContentNote(nota,window));
		window.setContent(layout);
		window.setResizable(false);
		window.setPositionX(nota.getPositionX());
		window.setPositionY(nota.getPositionY());
		window.setData(nota);
		window.addBlurListener(createBlurListener(window));
		window.addFocusListener(createFocusListener(window));
		
		return window;
	}
	
	private TextArea createContentNote(final Nota nota, final Window window ) {
		
		TextArea contentNote = new TextArea();
		contentNote.setSizeFull();
		contentNote.setValue(nota.getText());
		contentNote.setImmediate(true);
		contentNote.setTextChangeEventMode(TextChangeEventMode.EAGER);
		contentNote.addBlurListener(createBlurListener(window));
		contentNote.addFocusListener(createFocusListener(window));
		
		contentNote.addTextChangeListener(e -> {
			nota.setText(e.getText());
		});
		
		return contentNote;
	}
	
	private BlurListener createBlurListener(final Window window) {
		
		return new BlurListener() {

			@Override
			public void blur(BlurEvent event) {
				
				unlockNote(window);
			}
		};
	}
	
	private FocusListener createFocusListener(final Window window) {
		
		return new FocusListener(){

			@Override
			public void focus(FocusEvent event) {
				lockNote(window);
				
			}
			
		};
	}
	
	private void lockNote(Window window) {
		
		Nota nota = (Nota) window.getData();
		nota.setLockedByUser(userId);
		String caption = "Locked by User" + userId;
		nota.setCaption(caption);
		window.setCaption(caption);
	
	}
	
	private void unlockNote(Window window) {
		
		Nota nota = (Nota) window.getData();
		nota.setLockedByUser(-1);
		nota.setPositionX(window.getPositionX());
		nota.setPositionY(window.getPositionY());
		nota.setCaption("Nota "+nota.getId());
		window.setCaption("Nota "+nota.getId());
	}
	
	public void updateNoticeBoard() {
		
		for(Nota nota : notas) {
			Window window = getWindow(nota);
			updateTextArea(window, nota);
			
			if(window == null) {
				window = createWindow(nota);
				windows.add(window);
				UI.getCurrent().addWindow(window);
			}
			
			if(nota.getLockedByUser() > -1 && nota.getLockedByUser() != userId) {
				window.setEnabled(false);
			}else {
				window.setEnabled(true);
			}
			
			if(nota.getLockedByUser() == userId) {
				Nota notaFocused = (Nota) window.getData();
				notaFocused.setPositionX(window.getPositionX());
				notaFocused.setPositionY(window.getPositionY());
				notaFocused.setCaption(window.getCaption());
				
			}else {
				window.setPositionX(nota.getPositionX());
				window.setPositionY(nota.getPositionY());
				window.setCaption(nota.getCaption());
				
			}
			
		}
	}
	
	private void updateTextArea(Window window, Nota nota) {
		if(window == null) {
			return;
		}	
			Layout layout = (Layout) window.getContent();
			
			TextArea area = (TextArea) layout.iterator().next();
			area.setValue(nota.getText());
	}
	
	private Window getWindow(Nota nota) {
		for(Window window : windows) {
			if(window.getData().equals(nota)) {
				
				return window;
			}	
		}
		return null;
	}
	
	
	public class NoticeBoardUpdater extends Thread {
		
		@Override
		public void run() {
			
			while(true) {
				try {
					Thread.sleep(UPDATE_INTERVAL);
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				
				Core ui = Core.getCurrent();
				
				ui.getSession().getLockInstance().lock();
				
				try {
					updateNoticeBoard();
				}finally {
					
					ui.getSession().getLockInstance().unlock();
				}
			}
		
		
		}
		
		
	}
	
	
}*/
