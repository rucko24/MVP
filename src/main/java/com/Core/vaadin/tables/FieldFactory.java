package com.Core.vaadin.tables;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

class UserFieldFactory implements TableFieldFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
		
		if("Password".equals(propertyId)) {
			
			return new PasswordField();
		} 
			
		return new TextField();
	}
	
}

public class FieldFactory extends VerticalLayout {
	
	private Table table = new Table("Usuarios");
	private Table table2 = new Table("Componentes en Tabla");
	private HorizontalLayout row = new HorizontalLayout();
	
	public FieldFactory() {
		
		setMargin(true);
		setSpacing(true);
		row.setSpacing(true);
		
		table.setPageLength(0);
		table.setEditable(true);
		table.setTableFieldFactory(new UserFieldFactory());
		
		table.addContainerProperty("Login", String.class, "");
		table.addContainerProperty("Password", String.class, "");
		
		table.addItem();
		table.addItem();
		table.addItem();
		
		table2.setPageLength(0);
		table2.addContainerProperty("TEXT-FIELD", TextField.class, "");
		table2.addContainerProperty("CheckBox", CheckBox.class, "");
		
		table2.addItem(new Object[]{new TextField(), new CheckBox() },1);
		
		row.addComponents(table,table2);
		
		final HorizontalLayout row2 = new HorizontalLayout();
		setSpacing(true);
		
		Table table3 = new Table() {
			
			@Override
			protected String formatPropertyValue(Object rowId, Object colId, Property<?> property) {
				
				Integer a = (Integer) property.getValue();
				
				if(a<0) {
					return "(" + (-a) + ")";
				}
				
				return "" +a; 
			}
		};
		
		table3.setPageLength(0);
		table3.setEditable(true);
		table3.setColumnCollapsingAllowed(true);
		table3.setColumnCollapsible("A + B", true);
		table3.setColumnReorderingAllowed(true);
		
		
		table3.addContainerProperty("A", Integer.class, 0);
		table3.addContainerProperty("B", Integer.class, 0);
		
		table3.addItem();
		table3.addItem();
		table3.addItem();
		
		table3.setFooterVisible(true); //para agregar Pies de Pagina.
		
		
		
		table3.addGeneratedColumn("A + B", new ColumnGenerator() {
			
			@Override
			public Object generateCell(Table source, Object itemId, Object columnId) {
				
				Integer a = (Integer)source.getItem(itemId).getItemProperty("A").getValue();
				
				Integer b = (Integer) source.getItem(itemId).getItemProperty("B").getValue();
				
				
				return new Label(" "+(a+b));
				//return new TextField(" "+(a + b));
			}
		});
		
		
		
		
		row2.addComponent(table3);
		addComponents(row,row2);
	}

}
