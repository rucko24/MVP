package com.Core.vaadin.pushServer;


import com.github.wolfie.refresher.Refresher;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
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
		
		setSizeFull();
		setMargin(true);
		
		Tree tree = new Tree();
		tree.setHeight("100%");
		tree.setWidth("100%");
		Tree tree2 = new Tree();
		
		for(int f=0; f<=20; f++) {
			
			tree.addItem(f);
			tree2.addItem(f);
		}
		
		VerticalLayout vFirst = new VerticalLayout(tree);// verticallayout dentro de spliPanel, sin sizeFull 
														//para que aparesca el scroll
		
		
		VerticalLayout vSecon = new VerticalLayout(tree2);
		//vSecon.setHeight("100%");
		
		float f = 18f;
		horizontal.setLocked(true);
		horizontal.setHeight(100, Unit.PIXELS);
		horizontal.setFirstComponent(vFirst);
		horizontal.setSecondComponent(vSecon);
		horizontal.setSplitPosition(f);
		
		addComponent(horizontal);
		
		showBorder();
	}
	
	private void showBorder() {
		String style = "v-border";
		horizontal.addStyleName(style);
	}
	
}

