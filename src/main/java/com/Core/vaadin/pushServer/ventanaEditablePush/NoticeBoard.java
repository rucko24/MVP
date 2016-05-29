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
			Window window = createWindow(nota);
			windows.add(window);
			ui.addWindow(window);
		});
		
		addComponent(btnAgregarNota);
		addExtension(refresher);
		
		new ActualizadorDeNotas().start();
	}
	
	/* este metodo se usa para crear una nueva ventana
	 * acorde a la informacion del objeto nota, nosotros 
	 * establecemos el contenido, desHabilitamos la redimension
	 * y establecemos la posicion de la ventana en el diseño de noticia
	 * , luego agregamos 2 listener 
	 * 	BlurListener= que sera llamado despues de perder el foco de la nota de 
	 * la ventana,
	 * FocusListener= que se llamara cuando la ventana de notas tenga el foco
	 */
	private Window createWindow(final Nota nota) {
		final Window window = new Window(nota.getCaption());
		Layout layout = new VerticalLayout();
		layout.addComponent(crearContenidoNotas(nota, window));
		window.setContent(layout);
		window.setResizable(false);
		window.setPositionX(nota.getPosicionX());
		window.setPositionY(nota.getPosicionY());
		window.setData(nota);
		
		window.addBlurListener(createBlurListener(window));
		window.addFocusListener(createFocusListener(window));
		
		return window;
	}
	
	/*en este metodo creamos el contenido de las notas
	 * es un simple textArea, el valor del contenido, es 
	 * establecido acorde a el texto del valor de las notas
	 * tambien agregaremos los 2 listeners del metodo anterior
	 * 
	 */
	private TextArea crearContenidoNotas(final Nota nota, final Window window) {
		TextArea contenidoNota = new TextArea();
		contenidoNota.setSizeFull();
		contenidoNota.setImmediate(true);
		contenidoNota.setTextChangeEventMode(TextChangeEventMode.EAGER);
		
		contenidoNota.addBlurListener(createBlurListener(window));
		contenidoNota.addFocusListener(createFocusListener(window));
		
		contenidoNota.addTextChangeListener( e -> {
				//getText es de TextArea, y obtenemos el texto
			//y lo guardamos en la variable texto
				nota.setTexto(e.getText());
			
		});
		
		return contenidoNota;
	}
	/*insertamos 2 metodos para los listeners
	 * BlurListener, es llamado cuando el componente pierde el foco
	 * despues de este evento, nosotros desBLOQUEAREMOS la nota
	 */
	
	private BlurListener createBlurListener(final Window window) {
		
		return new BlurListener() {
			
			@Override
			public void blur(BlurEvent e) {
				desbloquearNota(window);
			}
		};
	}
	
	/*el metodo FocusLister es llamado cuando el componente
	 * obtiene el foco, en este caso nosotros bloquearemos la 
	 * nota para el actual usuario logueado
	 */
	private FocusListener createFocusListener(final Window window) {
		return new FocusListener() {

			@Override
			public void focus(FocusEvent event) {
				bloquearNota(window);
			}
			
		};
	}
	
	/*ahora crearemos el metodo [bloquearNota] para el usuario
	 * actualmente registrado, en la nota, almacenaremos el 
	 * user Id, que bloqueara la nota, y nosotros informaremos
	 * a los otros usuarios por el texto en el caption, que esta nota
	 * esta bloqueada. 
	 * 
	 */
	private void bloquearNota(Window window) {
		Nota nota = (Nota) window.getData();
		nota.setBloquedoPorElUsuario(userId);
		String caption = "Bloqueado por Usuario: "+userId;
		nota.setCaption(caption);
		window.setCaption(caption);
	}
	
	/*ahora creamos el metodo [desbloquearaNota]
	 * establecemos el user ID que bloquea la nota a -1
	 * tenemos una copia actual de la posicion de la ventana del objeto nota
	 * porque nosotros necesitamos almacenar las posiciones pasadas de la ventana
	 * y tambien informamos a los otros usuarios por el texto en el caption
	 * que esta nota esta desbloqueada  
	 */
	private void desbloquearNota(Window window) {
		Nota nota = (Nota) window.getData();
		nota.setBloquedoPorElUsuario(-1);
		nota.setCaption("Nota");
		nota.setPosicionX(window.getPositionX());
		nota.setPosicionY(window.getPositionY());
	}
	
	
	
	
	
	
}