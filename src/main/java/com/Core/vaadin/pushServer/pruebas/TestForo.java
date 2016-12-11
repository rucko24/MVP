package com.Core.vaadin.pushServer.pruebas;

import com.Core.vaadin.Core;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;

public class TestForo extends VerticalLayout {

	private ProgressBar workinIndicator;
	private Label contador = new Label("0");
	private int cont = 0;
	private boolean keepAlive = true;
	private boolean finishUpdating = false;
	private Core UI = Core.getCurrent();

	public TestForo() {
		setMargin(true);
		setSpacing(true);

		Label label = new Label("Worker is running");
		addComponent(label);

		workinIndicator = new ProgressBar(0.0f);
		workinIndicator.setIndeterminate(true);
		workinIndicator.setEnabled(true);
		addComponent(workinIndicator);

		contador.setImmediate(true);

		this.addDetachListener(new DetachListener() {

			
			public void detach(DetachEvent event) {
				System.out.println("····DETACHED·····");
				Notification.show("......Detached.....", Notification.Type.ERROR_MESSAGE);
				handleClose();
			}
		});

		/*JavaScript.getCurrent().addFunction("aboutToClose", new JavaScriptFunction() {

			@Override
			public void call(JsonArray arguments) throws JSONException {

				System.out.println("Got aboutToClose callback!!");
				handleClose();
			}

		});*/

		Page.getCurrent().getJavaScript().execute("window.onbeforeunload = function (e) { "
				+ "var e = e || window.event; " + "aboutToClose(); " + "return; };" + "");
		
		new SubProceso().start();

	}

	private void handleClose() {
		this.keepAlive = false;
		boolean loop = true;
		while (loop) {
			try {
				while (loop) {
					Thread.sleep(200);
					if (this.finishUpdating) {
						loop = false;
					}
				}
				System.out.println("PingerUI is finished updating, can now close...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		UI.close();
	}

	private void updateValues() {
		cont++;
		contador.setValue(String.valueOf(cont));
		System.out.println("Still updating");
	}

	public class SubProceso extends Thread {
		
		boolean loop = true;
		
		@Override
		public void run() {
			//boolean loop = true;
			try {
				while (loop) {
					Thread.sleep(5000);
					UI.access(new Runnable() {
						@Override
						public void run() {
							updateValues();
							if (!keepAlive)
								loop = false;
						}
					});
				}
				System.out.println("No longer pinging!!");
				finishUpdating = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
