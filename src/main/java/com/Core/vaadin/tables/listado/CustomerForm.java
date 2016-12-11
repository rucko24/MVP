package com.Core.vaadin.tables.listado;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class CustomerForm extends FormLayout {
	
	private static final long serialVersionUID = 1L;
	private TextField firstName = new TextField("First Name");
	private TextField lastName = new TextField("Last Name");
	private TextField email = new TextField("Email");
	private NativeSelect status = new NativeSelect("Status");
	private PopupDateField popUp = new PopupDateField("BirthDate");
	private Button btnSave = new Button("Save");
	private Button btnDelete = new Button("Delete");
	
	private CustomerService service = CustomerService.getInstance();
	private Customer customer;
	private Listado listado;
	
	public CustomerForm(Listado listado) {
		this.listado = listado;
		
		setSizeUndefined();
		
		status.addItems(CustomerStatus.values());
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.setClickShortcut(KeyCode.ENTER);
		
		btnSave.addClickListener( e ->save());
		btnDelete.addClickListener( e -> delete());
		btnDelete.addStyleName(ValoTheme.BUTTON_DANGER);
		
		HorizontalLayout botones = new HorizontalLayout(btnSave,btnDelete);
		botones.setSpacing(true);
		addComponents(firstName,lastName,email,status,popUp,botones);
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		BeanFieldGroup.bindFieldsUnbuffered(customer, this);
		
		btnDelete.setVisible(customer.isPersisted());
		setVisible(true);
		firstName.selectAll();
	}
		
	private void save() {
		service.save(customer);
		listado.updateList();
		setVisible(false);
	}
	private void delete() {
		service.delete(customer);
		listado.updateList();
		setVisible(false);
	}
}
