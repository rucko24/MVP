package com.Core.vaadin.pushServer;


import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class PruebaLabel extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	private Refresher refresher = new Refresher();
	private TextArea texto = new TextArea("");
	private ThemeResource spinner = new ThemeResource("img/712.gif");
	private Label label = new Label("Now: ");
	private HorizontalSplitPanel horizontal = new HorizontalSplitPanel();
	
	public PruebaLabel() {
		
		setSpacing(true);
		setWidth("700px");
		setHeight("600px");
		
		Tree tree = new Tree();
		tree.setHeight("100%");
		
		Tree tree2 = new Tree();
		
		
		
		for(int f=0; f<=50; f++) {
			
			tree.addItem(f);
			tree2.addItem(f);
		}
		
		VerticalLayout vFirst = new VerticalLayout(tree);// verticallayout dentro de spliPanel, sin sizeFull 
		vFirst.setCaption("verticalLAyout 1");											//para que aparesca el scroll
		vFirst.setHeight("500px");
		
		VerticalLayout vSecon = new VerticalLayout(tree2);
		vSecon.setCaption("verticalLAyout 2");											//para que aparesca el scroll
		//vSecon.setHeight("200%");
		
		
		
		/*SplitPane con 100% de alto, y component padre con px,   , 
		ahora componente en spliPanel sin medida
		 * 
		 */
		float f = 18f;
		horizontal.setLocked(true);   
		horizontal.setHeight("100%");
		horizontal.setWidth("600px");
		
		horizontal.setFirstComponent(vFirst);
		horizontal.setSecondComponent(vSecon);
		horizontal.setSplitPosition(f);
		
		addComponent(horizontal);
		setComponentAlignment(horizontal, Alignment.BOTTOM_CENTER);
		showBorder();
	}
	
	private void showBorder() {
		String style = "v-border";
		horizontal.addStyleName(style);
	}
	
}

