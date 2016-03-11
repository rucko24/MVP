package com.Core.vaadin.barraCarga;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Flash;
import com.vaadin.ui.Image;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class Iconos2 extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TabSheet tab = new TabSheet();
	
	public Iconos2() {
		setSpacing(true);
		setMargin(true);
		
		Image imgFromTheme = new Image(null, new ThemeResource("../runo/icons/16/error.png"));
		//imgFromTheme.setSizeFull();
		
		Image imgFromClassPath = new Image(null, new ClassResource("globe.png"));
		imgFromClassPath.setSizeFull();
		
		BrowserFrame frameFromUrl = new BrowserFrame(null, new ExternalResource("http://alejandrodu.com"));
		frameFromUrl.setSizeFull();
		
		BrowserFrame frameFromFileSystem = new BrowserFrame(null, new FileResource(new File("/home/rubn/Readme")));
		
		StreamSource mySource = new StreamSource() {
			
			@Override
			public InputStream getStream() {
				// TODO Auto-generated method stub
				return new InputStream() {
					
					private int size = 20;
					
					public int read() throws IOException {
						
						if(size --> 0) {
							return 'V'; // we are returning 20 V' s
						}
						return -1;
					}
				};
			}
			
		};
		
		BrowserFrame frameFromStream = new BrowserFrame(null, new StreamResource(mySource,"hello.txt"));
		
		Flash flashFromUrl = new Flash(null, new ExternalResource("http://www.youtube.com/v/ALgCDkZvzeY&hl=en_US&fs=1"));
		//flashFromUrl.setSizeFull();
		
		VerticalLayout video = new VerticalLayout();
		video.setMargin(true);
		video.setSizeFull();
		video.addComponent(flashFromUrl);
		video.setComponentAlignment(flashFromUrl, Alignment.MIDDLE_CENTER);
		
		Deslisador slider = new Deslisador();
		
		
		tab.addTab(imgFromTheme,"Img tema runo");
		tab.addTab(imgFromClassPath,"Img Globo");
		tab.addTab(frameFromUrl,"Img from URL");
		tab.addTab(frameFromFileSystem,"Img from file system");
		tab.addTab(frameFromStream, "puras V");
		tab.addTab(video, "VIDEO");
		tab.addTab(slider,"Deslisador");
		
		addComponent(tab);
	}
}
