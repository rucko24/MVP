package com.Core.vaadin.pushServer.ventanaEditablePush;

import java.util.ArrayList;
import java.util.List;

import com.Core.vaadin.Core;
import com.github.wolfie.refresher.Refresher;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class NoticeBoard extends VerticalLayout {

	/**
	 * @author rubn
	 */
	private static final long serialVersionUID = 1L;

	private static List<Nota> notas = new ArrayList<Nota>();
	private List<Window> windows = new ArrayList<Window>();
	private static int contadorUsuario;
	private int userId;
	private static int noteId = -1;
	private Refresher refresher = new Refresher();
	private static final int INTERVALO_ACTUALIZACION = 500;
	private Core ui = Core.getCurrent();

	public NoticeBoard() {

		refresher.setRefreshInterval(INTERVALO_ACTUALIZACION);
		userId = contadorUsuario++;
		setSpacing(true);
		setMargin(true);
		addComponent(new Label("Usuario registrado " + userId));
		Button btnAgregarNota = new Button("Agregar Nota");
		btnAgregarNota.setClickShortcut(KeyCode.ENTER);
		btnAgregarNota.addStyleName("primary");
		btnAgregarNota.addClickListener(e -> {

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

	/*
	 * este metodo se usa para crear una nueva ventana acorde a la informacion
	 * del objeto nota, nosotros establecemos el contenido, desHabilitamos la
	 * redimension y establecemos la posicion de la ventana en el diseño de
	 * noticia , luego agregamos 2 listener BlurListener= que sera llamado
	 * despues de perder el foco de la nota de la ventana, FocusListener= que se
	 * llamara cuando la ventana de notas tenga el foco
	 */
	private Window createWindow(final Nota nota) {

		final Window window = new Window(nota.getCaption());
		Layout layout = new VerticalLayout();
		layout.setHeight("100%");
		layout.addComponent(crearContenidoNotas(nota, window));
		window.setContent(layout);
		window.setResizable(false);
		window.center();
		window.setWidth("250px");
		window.setHeight("200px");
		window.setPositionX(nota.getPosicionX());
		window.setPositionY(nota.getPosicionY());
		window.setData(nota);

		window.addBlurListener(createBlurListener(window));
		window.addFocusListener(createFocusListener(window));

		return window;

	}

	/*
	 * en este metodo creamos el contenido de las notas es un simple textArea,
	 * el valor del contenido, es establecido acorde a el texto del valor de las
	 * notas tambien agregaremos los 2 listeners del metodo anterior
	 * 
	 */
	private TextArea crearContenidoNotas(final Nota nota, final Window window) {

		TextArea contenidoNota = new TextArea();
		contenidoNota.setSizeFull();
		contenidoNota.setImmediate(true);
		contenidoNota.setTextChangeEventMode(TextChangeEventMode.EAGER);

		contenidoNota.addBlurListener(createBlurListener(window));
		contenidoNota.addFocusListener(createFocusListener(window));

		contenidoNota.addTextChangeListener(e -> {
			// getText es de TextArea, y obtenemos el texto
			// y lo guardamos en la variable texto
			nota.setTexto(e.getText());

		});

		return contenidoNota;

	}
	/*
	 * insertamos 2 metodos para los listeners BlurListener, es llamado cuando
	 * el componente pierde el foco despues de este evento, nosotros
	 * desBLOQUEAREMOS la nota
	 */

	private BlurListener createBlurListener(final Window window) {

		return new BlurListener() {

			@Override
			public void blur(BlurEvent e) {
				desbloquearNota(window);
			}
		};
	}

	/*
	 * el metodo FocusLister es llamado cuando el componente obtiene el foco, en
	 * este caso nosotros bloquearemos la nota para el actual usuario logueado
	 */
	private FocusListener createFocusListener(final Window window) {
		return new FocusListener() {

			@Override
			public void focus(FocusEvent event) {
				bloquearNota(window);
			}

		};
	}

	/*
	 * ahora crearemos el metodo [bloquearNota] para el usuario actualmente
	 * registrado, en la nota, almacenaremos el user Id, que bloqueara la nota,
	 * y nosotros informaremos a los otros usuarios por el texto en el caption,
	 * que esta nota esta bloqueada.
	 * 
	 */
	private void bloquearNota(Window window) {

		Nota nota = (Nota) window.getData();
		nota.setBloquedoPorElUsuario(userId);
		String caption = "Bloqueado por Usuario: " + userId;
		nota.setCaption(caption);
		window.setCaption(caption);

	}

	/*
	 * ahora creamos el metodo [desbloquearaNota] establecemos el user ID que
	 * bloquea la nota a -1 tenemos que copiar la posicion actual de la ventana
	 * del objeto nota porque nosotros necesitamos almacenar las posiciones
	 * pasadas de la ventana y tambien informamos a los otros usuarios por el
	 * texto en el caption que esta nota esta desbloqueada
	 */
	private void desbloquearNota(Window window) {
		Nota nota = (Nota) window.getData();
		nota.setBloquedoPorElUsuario(-1);
		nota.setPosicionX(window.getPositionX());
		nota.setPosicionY(window.getPositionY());
		nota.setCaption("Nota " + nota.getId());
		window.setCaption("Nota " + nota.getId());

	}

	/*
	 * ahora agregamos la el metodo clave para actualizar el noticeBoard este
	 * metodo, sera llamado por el [ActualizadorDeNotas] Thread aqui iteramos y
	 * compartimos las notas y actualizar la interfaz de usuario de ventanas
	 * privadas en consecuencia.
	 * 
	 */
	// update notice board
	private void actualizarTableroDeAnuncio() {

		for (Nota tmpNota : notas) {
			/*
			 * primero nosotros obtendremos un objeto window que este enlazado
			 * con la nota y actualizaremos el contenido del textArea en esta
			 * ventana
			 * 
			 * En un primer momento , se obtiene un objeto ventana que está
			 * enlazado con la nota y que se actualice el contenido del área de
			 * texto en esta ventana .
			 */
			Window window = getWindow(tmpNota);
			actualizarTextArea(window, tmpNota);
			/*
			 * Si no hay ninguna ventana de la nota seleccionada , entonces se
			 * crea uno y enlazarlo con la nota
			 */
			if (window == null) {
				window = createWindow(tmpNota);
				windows.add(window);
				ui.addWindow(window);
			}
			/*
			 * Si la nota está bloqueado por otro usuario, a continuación,
			 * desHabilitamos esta ventana . Otras ventanas seran habilitadas.
			 */
			if (tmpNota.getBloqueadoPorElUsuario() > -1 && tmpNota.getBloqueadoPorElUsuario() != userId) {
				window.setEnabled(false);
			} else {
				window.setEnabled(true);
			}
			/*
			 * si la nota seleccionada esta bloqueada por el mismo usuario que
			 * esta logueado, eso significa que trabajaremos con la nota con el
			 * foco obtenemos la nota unida desde la ventana actual, y
			 * copiaremos la informacion apropiada a la ventana de la nota, si
			 * no es una [nota enfocada], copiamos la información apropiada
			 * desde la nota de la ventana
			 * 
			 */
			if (tmpNota.getBloqueadoPorElUsuario() == userId) {
				Nota notaEnfocada = (Nota) window.getData();
				notaEnfocada.setPosicionX(window.getPositionX());
				notaEnfocada.setPosicionY(window.getPositionY());
				notaEnfocada.setCaption(window.getCaption());
			} else {
				window.setPositionX(tmpNota.getPosicionX());
				window.setPositionY(tmpNota.getPosicionY());
				window.setCaption(tmpNota.getCaption());
			}
		}
	}

	/*
	 * A continuación, insertamos un método para actualizar el área de texto
	 * dentro de la ventana . Se establece el valor de esta área de texto de
	 * acuerdo con el valor de texto en la nota.
	 * 
	 */
	private void actualizarTextArea(Window window, Nota nota) {
		if(window == null) 
			return;
		Layout layout = (Layout) window.getContent();
		TextArea area = (TextArea) layout.iterator().next();
		area.setValue(nota.getTexto());
	}
	
	/*el getWindow() itera todas las ventantas actuales y encuentra la 
	 * ventana enlazada con la nota seleccionada
	 */
	private Window getWindow(Nota nota) {
		for(Window tmpWindow: windows) {
			if(tmpWindow.getData().equals(nota)) {
				return tmpWindow;
			}
		}
		return null;
	}
	
	/*ahora viene lo mas esperado de todo la clase interna
	 * ActualizadorDeNotas,esto actualiza el tablero de anuncios un buble 
	 * infinito, despues de un intervalo especifico, todas las notas
	 * en el tablero son actualizadas, los cambios 
	 * 
	 */
	public class ActualizadorDeNotas extends Thread {
		
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(INTERVALO_ACTUALIZACION);
					
				}catch(InterruptedException e) {
					e.printStackTrace();
					Notification.show(e.getMessage(),Notification.Type.ERROR_MESSAGE);
				}
				//ui.getSession().getLockInstance().lock();
				
				try {
					Thread.sleep(10);
					ui.access(new Runnable() {
						@Override
						public void run() {
							actualizarTableroDeAnuncio();
						}
					});	
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}