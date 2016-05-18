package com.Core.vaadin.barraCarga;



import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

class Numeros {
	
	private int i;
	private int j;
	private int iteracion;
	private boolean iMenor;
	
	public Numeros( int iteracion, int i, int j, boolean iMenor) {
		this.iteracion = iteracion;
		this.i = i;
		this.j = j;
		this.iMenor = iMenor;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getIteracion() {
		return iteracion;
	}

	public void setIteracion(int iteracion) {
		this.iteracion = iteracion;
	}

	public boolean isiMenor() {
		return iMenor;
	}

	public void setiMenor(boolean iMenor) {
		this.iMenor = iMenor;
	}
	
}

public class ContextMenu extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Action action = new Action("Click derecho en esta tabla");
	private Table table = new Table();
	private BeanItemContainer<Numeros> container = new BeanItemContainer<Numeros>(Numeros.class);
	private Label label = new Label("<h2>for( int i=1, <strong>j = i+10;</strong> i < 5; i++, <strong>j=i*2</strong> )<h2>",ContentMode.HTML);
	
	
	public ContextMenu() {
		setSizeFull();
		setMargin(true);
		
		table.setSelectable(true);
		
		table.addContainerProperty("int i   ", Label.class, null);
		table.addContainerProperty("j = i+10", Label.class, null);
		table.addContainerProperty(" i < 5  ", Boolean.class, null);
		table.addContainerProperty("i++     ", Label.class, null);
		table.addContainerProperty("j= i*2  ", Integer.class, null);
		
		
		for( int i=1, j = i+10; i < 5; i++, j=i*2) {
			Integer itemID = new Integer(i);
		/*	Label jota = new Label(String.format("J es <b>$%04.2f</b>", new Object[]{new Double(Math.random()*1000)},ContentMode.HTML));
			Label jota2 = new Label(String.format("J es $%04.2f", new Object[]{new Double(Math.random()*1000)},ContentMode.HTML));
		
			Label jota3 = new Label(String.format("J es <b>$%04.2f</b>", new Object[]{new Double(Math.random()*1000)},ContentMode.HTML));
			Label jota4 = new Label(String.format("J es <b>$%04.2f</b>", new Object[]{new Double(Math.random()*1000)},ContentMode.HTML));
			*/
			
			//j=i+10;
			
			Label jota = new Label(""+i);
			Label jota2 = new Label(""+j);
			
			
			Label jota3 = new Label(""+i);
		//	Label jota4 = new Label(""+i*2);
			
			
		
			table.addItem(new Object[]{jota,jota2, i < 5 ,jota3, i*2}, itemID);
		}
			
		
		
		table.addActionHandler( new Handler() {
			public void handleAction(Action action, Object sender, Object target) {
				
				Notification.show("OK, aqui vamos hola");
			}

			@Override
			public Action[] getActions(Object target, Object sender) {
				// TODO Auto-generated method stub
				return new Action[] { action };
			}
		});
		
		table.setPageLength(table.size());
		markAsDirtyRecursive();
		addComponents(label,table);
	}
	
	
}

