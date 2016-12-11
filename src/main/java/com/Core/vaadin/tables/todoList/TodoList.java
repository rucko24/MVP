package com.Core.vaadin.tables.todoList;

import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public class TodoList extends VerticalLayout {
	
	private Button btn = new Button("Add-todo");
	
	public TodoList() {
		
		addComponent(btn);
	}
	
}
