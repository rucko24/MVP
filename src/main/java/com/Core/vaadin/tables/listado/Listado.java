package com.Core.vaadin.tables.listado;

import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Listado extends VerticalLayout {
	
	private CustomerService service = CustomerService.getInstance();
	private Grid grid = new Grid();
	private TextField textField = new TextField();
	private Button btnClear = new Button(FontAwesome.TIMES);
	private CssLayout cssLayout = new CssLayout();
	private CustomerForm customerForm = new CustomerForm(this);
	
	public Listado() {
		//Responsive.makeResponsive(this);
		//addStyleName(ValoTheme.UI_WITH_MENU);
		
		setMargin(true);
		setSpacing(true);
		
		textField.setInputPrompt("busqueda...");
		textField.addTextChangeListener( e -> {
			grid.setContainerDataSource(new BeanItemContainer<>(Customer.class,
					service.findAll(e.getText())));
			
		});
		
		btnClear.addClickListener( e -> {
			textField.clear();
			updateList();
		});
		// actualizar la lista de customer aqui
		grid.setColumns("firstName","lastName","birthDate");
		grid.setSizeFull();
		updateList();
		
		//con un click en la grilla escondemos el formulario
		customerForm.setVisible(false);
		
		grid.addSelectionListener( e -> {
			if(e.getSelected().isEmpty()) {
				customerForm.setVisible(true);
			}else {
				Customer customer = (Customer)e.getSelected().iterator().next();
				customerForm.setCustomer(customer);
			}
		});
		
		cssLayout.addComponents(textField,btnClear);
		cssLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		Button btnAddCustomer = new Button("Add new Customer");
		btnAddCustomer.addClickListener( e -> {
			grid.select(null);
			customerForm.setCustomer(new Customer());
		});
		
		HorizontalLayout top = new HorizontalLayout(cssLayout,btnAddCustomer);
		top.setSpacing(true);
		
		HorizontalLayout main = new HorizontalLayout(grid,customerForm);
		main.setSizeFull();
		main.setSpacing(true);
		main.setExpandRatio(grid, 1);
		
		addComponents(top,main);
	}
	
	public void updateList() {
		
		List<Customer> customer = service.findAll(textField.getValue());
		grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, customer));
	
	}
}
