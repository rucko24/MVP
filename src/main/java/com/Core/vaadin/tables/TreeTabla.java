package com.Core.vaadin.tables;

import java.io.File;

import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Tree;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;

public class TreeTabla extends VerticalLayout {
	
	private Tree tree = new Tree();
	private TreeTable treeTabla = new TreeTable();
	
	public TreeTabla() {
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		
		
		tree.addItem("Familias");
		tree.addItem("ruben");
		tree.addItem("andrea");
		
		tree.setParent("ruben", "Familias");
		tree.setParent("andrea","Familias");
		//tree.setParent("andrea","Familias");
		
		tree.setChildrenAllowed("ruben", false);
		tree.setChildrenAllowed("andrea", false);	
		
		
		treeTabla.setSizeFull();
		treeTabla.setPageLength(0);
		
		File folder = VaadinService.getCurrent().getBaseDirectory();
		
		FilesystemContainer container = new FilesystemContainer(folder);
		
		treeTabla.setContainerDataSource(container);
		
		
		addComponents(tree,treeTabla);
	}
}
