package com.Core.vaadin.pushServer.icePush;

import com.Core.vaadin.Core;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class MenuMessageBar extends HorizontalLayout {

	private MenuBar menuBar = new MenuBar();
	private MenuBar messageBar = new MenuBar();
	private MenuBar.MenuItem messageMenu;
	private Core coreUI = Core.getCurrent();
	private UI ui;
	
	// el menuCommand es un objeto usado
	// solo para propositos auxiliares por el menu item
	private Command menuCommand = new Command() {

		public void menuSelected(MenuItem selectedItem) {
			Notification.show("Acción realizada " + selectedItem.getText());
		}
	};

	/*
	 * full ancho, y full ancho x alto del menuBar el menu trabajara con el
	 * fillMenu, que crearemos todos los componentes, Refresher, es agredaro en
	 * una extension, y finalmente iniciamos el contador de mensajes
	 */
	public MenuMessageBar(UI ui) {
		this.ui = ui;
		
		setWidth("100%");
		menuBar.setSizeFull();
		fillMenu();
		addComponents(menuBar, messageBar);

		new ContadorMensajes().start();

	}

	private void fillMenu() {
		final MenuBar.MenuItem fileItem = menuBar.addItem("Archivo", null);
		final MenuBar.MenuItem newItem = fileItem.addItem("Nuevo", null);

		fileItem.addItem("Abrir archivo", menuCommand);
		fileItem.addSeparator();

		newItem.addItem("Archivo", menuCommand);
		newItem.addItem("Carpeta", menuCommand);
		newItem.addItem("Proyecto", menuCommand);

		fileItem.addItem("Salvar", menuCommand);

		final MenuBar.MenuItem editar = menuBar.addItem("Editar", null);
		editar.addItem("Cortar", menuCommand);
		editar.addItem("Copiar", menuCommand);
		editar.addItem("Pegar", menuCommand);

		messageMenu = messageBar.addItem("Nuevo mensaje: 0", null);

	}

	/*
	 * al final, crearemos la clase interna, Thread, que actualizara el texto en
	 * el mensaje del contador, por este proposito, crearemos un metodo que
	 * incrementara continuamente la variable ["count"] en un intervalo
	 * especifico ejemplo, el metodo por accederá a la base de datos o a otro
	 * sistema, que devolvera algun mensaje por el usuario , necesitamos el una
	 * variable contadora para nuevos mensajes nextTime, cuando el proximo
	 * momentos para los nuevos mensajes actualizado, y una constante, por el
	 * intervalo de tiempo entre los mensajes actualizados
	 * 
	 */

	public class ContadorMensajes extends Thread {
		private int contador;
		private long momentoProximo;
		private static final int INTERVALO_TIEMPO = 1000;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 * 
		 * este metodo sera llamado en un hilo separado por start() creamos un
		 * loop infinito, en el cual chekearemos si el contador de mensajes ha
		 * sido cambiado, si ha cambiado, actualizamos el menu de mensajes Nuevo
		 * mensaje, en el menu, nosotros empujaremos este cambio en la UI del
		 * lado del cliente usando un objeto pusher UI.getCurrent().push();,
		 * cuando la UI es actualizada por el SubProceso de fondo, tenemos que
		 * bloquear la aplicacion apropiadamente usando la session lock como
		 * sigue
		 * 
		 * 
		 */
		@Override
		public void run() {
			int contadorViejo = contador;
			while (true) {
				actualizarContadorDeMensajes();
				if (contadorViejo != contador) {
					if(ui != null) {
						//ui.getSession().getLockInstance().lock();
						try {
							Thread.sleep(INTERVALO_TIEMPO);
							contadorViejo = contador;
							ui.access(() -> {
								messageMenu.setText("Nuevo mensaje: " + contador);
							});
							//messageMenu.setText("Nuevo mensaje: " + contador);
							//ui.push();
						}catch(InterruptedException ex) {
							ex.printStackTrace();
			
						}
					}
					
				}

			}
		}

		/*
		 * es usado por proposito auxiliar, puede ser reemplazado por otro
		 * metodo, por ejemplo, con una acceso a una base de datos, en nuestro
		 * ejemplo, actualizaremos el contador de mensahes en el intervalo
		 * establecido
		 */
		private void actualizarContadorDeMensajes() {
			long tiempoActual = System.currentTimeMillis();
			if (tiempoActual > momentoProximo) {
				contador++;
				momentoProximo = tiempoActual + INTERVALO_TIEMPO;
			}
		}
	}
}
