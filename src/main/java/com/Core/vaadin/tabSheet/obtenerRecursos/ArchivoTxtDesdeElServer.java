package com.Core.vaadin.tabSheet.obtenerRecursos;

import java.io.IOException;
import java.io.InputStream;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

public class ArchivoTxtDesdeElServer extends VerticalLayout {

	/***
	 * FIXME, esta shit no funca
	 */
	private BrowserFrame frameFromStream;

	public ArchivoTxtDesdeElServer() {
		
		
		addComponent(getFrameFromFileSystem());
	}

	private Component getFrameFromFileSystem() {
		VerticalLayout layout = (VerticalLayout) getLayout();
		StreamSource mySource = new StreamSource() {

			@Override
			public InputStream getStream() {
				
				return new InputStream() {

					private int size = 20;

					public int read() throws IOException {

						if (size-- > 0) {
							return 'V'; // we are returning 20 V' s
						}
						return -1;
					}
				};
			}

		};
		frameFromStream = new BrowserFrame(null, new StreamResource(mySource, "hello.txt"));
		layout.addComponent(frameFromStream);

		return layout;
	}

	private Component getLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();

		return layout;
	}
}
