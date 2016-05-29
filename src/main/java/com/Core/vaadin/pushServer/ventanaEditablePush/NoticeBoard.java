package com.Core.vaadin.pushServer.ventanaEditablePush;

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

public class NoticeBoard extends VerticalLayout {
	
	/*
	 * 
	 */
	private static List<Nota> notas = new ArrayList<Nota>();
	private List<Window> windows = new ArrayList<Window>();
	private static int contadorUsuario;
	private int userId;
	private static int noteId = -1;
	private Refresher refresher = new Refresher();
	private static final int INTERVALO_ACTUALIZACION = 2000;
	private Core ui = Core.getCurrent();
	
	public NoticeBoard() {
		refresher.setRefreshInterval(INTERVALO_ACTUALIZACION);
		userId = contadorUsuario++;
		setSpacing(true);
		setMargin(true);
		addComponent(new Label("Usuario registrado "+userId));
		Button btnAgregarNota = new Button("Agragar Nota");
		btnAgregarNota.addClickListener( e -> {
			Nota nota = new Nota(noteId++);
			notas.add(nota);
			Windows window = createWindow(nota);
			windows.add(window);
			ui.addWindow(window);
		});
		
		addComponent(btnAgregarNota);
		addExtension(refresher);
		
		new ActualizadorDeNotas().start();
	}
	
	/* este metodo se usa para crear una nueva ventana
	 * 
	 */
	private Window createWindow(final Nota nota) {
		final Window window = new Window(nota.getCaption());
		Layout layout = new VerticalLayout();
		layout.addComponent(crearContenidoNotas(note, window));
		
	}
	
}