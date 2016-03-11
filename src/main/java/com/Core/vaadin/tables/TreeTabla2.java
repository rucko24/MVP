package com.Core.vaadin.tables;

import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

public class TreeTabla2 extends VerticalLayout {

		private TreeTable treeTabla = new TreeTable();
		
		public TreeTabla2() {
		
			setMargin(true);
			setSpacing(true);
			setSizeFull();
			
			treeTabla.setPageLength(0);
			treeTabla.setWidth("350px");
			
			treeTabla.addContainerProperty("Familias", String.class, "");
			treeTabla.addContainerProperty("Miembros", Integer.class, 0);
			
			treeTabla.addItem(new Object[]{"Familia",null},0);
			treeTabla.addItem(new Object[]{"Espinoza",10},1);
			treeTabla.addItem(new Object[]{"Rojas",50},2);
			
			treeTabla.setParent(1,0);
			treeTabla.setParent(2,0);		
			
			
			addComponent(treeTabla);
		
		}
}
