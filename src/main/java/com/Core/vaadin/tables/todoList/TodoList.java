package com.Core.vaadin.tables.todoList;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TodoList extends VerticalLayout {
	
	private Button botonTodoList = new Button("Add-todo");
	
	public TodoList() {
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		
		addComponent(botonTodoList);
		
		botonTodoList.addClickListener( e -> addTodo());
		/**
		 * FIXME
		 */
		botonTodoList.focus();
		
		
		
	}
	
	private void addTodo() {
		 TextField textField = new TextField();
		 textField.focus();
		 
		 textField.addValueChangeListener( e -> addTodo(textField));
		
		 addComponent(textField);
	}
	
	private void addTodo(TextField textField) {
		Button delete = new Button(FontAwesome.CHECK);
		delete.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		Label label = new Label(textField.getValue());
		
		HorizontalLayout todo = new HorizontalLayout(delete,label);
		todo.setSpacing(true);
		todo.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
		replaceComponent(textField , todo);
		
		botonTodoList.focus();
		delete.addClickListener( e -> delete(todo));
		
	}
	
	private void delete(HorizontalLayout todo) {
		removeComponent(todo);
		
		botonTodoList.focus();
	}
	
}
