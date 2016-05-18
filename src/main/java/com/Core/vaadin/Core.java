package com.Core.vaadin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.Core.vaadin.tables.Arduino;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Push
@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("com.Core.vaadin.MyAppWidgetset")
public class Core extends UI {
	
	private PageLayout pageLayout;
	private MenuLayout menuLayout;
	
	//private static Arduino arduino = new Arduino();

	//este flag te va servir para el estado del bombillo
	private static boolean switchOn = false;
	private static List<BotonSwitch> botones = new ArrayList<>(); 
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	pageLayout = new PageLayout();
    	
    	Navigator navigator = new Navigator(this,this);
    	
    	navigator.addView(Login.LOGIN_VIEW, new Login());
    	navigator.addView(PageLayout.PAGELAYOUT_VIEW, pageLayout);
    	
    	navigator.navigateTo(Login.LOGIN_VIEW);
    	setContent(pageLayout);
    	
    }
    
   public PageLayout getPageLayout() {
	   
	   return pageLayout;
   }

   public static Core getCurrent() {
	   
	   return (Core) UI.getCurrent();
	  
   }
	//metodo para cambiar de estado la variable switchOn basta con un solo metodo
   public static void changeSwitch(){
	switchOn = !switchOn;
	for(BotonSwitch tmpBtn : botones){
		tmpBtn.changeButtonOnOff();
	}
	
   }

   //metodo para chequear valor de swtichOn
   public static boolean isSwitchOn(){
	return switchOn;
   }

//con este metodo agrego botones a la lista botones
  public static void atachListening(BotonSwitch boton){
	  botones.add(boton);		
  }

//con este metodo remuevo botones a la lista botones
  public static void detachListening(BotonSwitch boton){
	  botones.remove(boton);		
  }

  /* public static Arduino getArduino() {
	   
	   return arduino;
   }*/
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Core.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    
}
