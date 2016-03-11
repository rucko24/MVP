package com.Core.vaadin;

import javax.servlet.annotation.WebServlet;

import com.Core.vaadin.tables.Arduino;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Push
@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("com.Core.vaadin.MyAppWidgetset")
public class MyUI extends UI {
	
	private PageLayout pageLayout;
	private MenuLayout menuLayout;
	//private Arduino arduino;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	//arduino = new Arduino();
    	pageLayout = new PageLayout();
    	
    	setContent(pageLayout);
    	
    }
    
   public PageLayout getPageLayout() {
	   
	   return pageLayout;
   }

   
   public static MyUI getCurrent() {
	   
	   return (MyUI) UI.getCurrent();
	  
   }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    
}
