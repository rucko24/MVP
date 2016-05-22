package com.Core.vaadin.pushServer;


import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
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
	private Tree tree = new Tree();
	private Tree tree2 = new Tree();
	
	public PruebaLabel() {
		
		setSpacing(true);
		setMargin(false);
		setWidth("35%");
		setHeight("600px");
		
		for(int f=0; f<=50; f++) {
			tree.addItem(f);
			tree2.addItem(f);
		}
		
		Label label = new Label("شروع جدید",ContentMode.HTML);
		label.addStyleName("labelMenu");
		ThemeResource iconoArdu = new ThemeResource("img/ardu1.svg");
		label.setIcon(iconoArdu);
		
		label.setSizeUndefined();
		VerticalLayout vLabel = new VerticalLayout(label);
		vLabel.setMargin(true);
		
		VerticalLayout vFirst = new VerticalLayout(vLabel,tree);// verticallayout dentro de spliPanel, sin sizeFull 
		vFirst.setCaption("verticalLAyout 1");				
		vFirst.addStyleName("menu");
		vFirst.setComponentAlignment(vLabel, Alignment.BOTTOM_CENTER);
		
		VerticalLayout vSecon = new VerticalLayout(tree2);
		vSecon.setCaption("verticalLAyout 2");											//para que aparesca el scroll
		
		
		
		
		/*SplitPane con 100% de alto, y component padre con px,   , 
		ahora componente en spliPanel sin medida
		 * 
		 */
		float f = 18f;
		//horizontal.addStyleName("miScroll");
		//horizontal.setLocked(true);   
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

