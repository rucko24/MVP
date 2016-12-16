package com.Core.vaadin.tables.juegoPalabras;

import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class JuegoDePalabrasConTabla extends VerticalLayout implements ItemClickListener {

	private static final long serialVersionUID = 1L;
	private Game game = new Game(5);
	private Label header = new Label("<h2>Boxword-Game</h2>", ContentMode.HTML);
	private Label currentLetter = new Label("", ContentMode.HTML);
	private VerticalLayout messageLayout = new VerticalLayout();
	private Table table = new Table();

	public JuegoDePalabrasConTabla() {
		setSizeFull();
		setMargin(true);
		Component row = getRow();

		addComponents(header, row);
		setExpandRatio(row, 1);
		nextTurn();
	}

	@Override
	public void itemClick(ItemClickEvent e) {

		Property column = e.getItem().getItemProperty(e.getPropertyId());

		if ("...".equals(column.getValue())) {

			column.setValue(game.getCurrentLetter());

			nextTurn();

		} else {
			Notification.show("Error escoje una celda vacia", "", Type.HUMANIZED_MESSAGE);
		}

	}

	private void nextTurn() {
		if (game.over()) {
			gameOver();
		} else {
			currentLetter.setValue("Próxima letra: " + game.nextLetter());

		}
	}

	/*
	 * este metodo actualiza el juego si esta terminado o no
	 * 
	 */
	private void gameOver() {
		Collection<String> words = getWords();
		currentLetter.setValue("Tu puntaje es: " + game.getScore(words) + "!");

		messageLayout.addComponent(new Label("Words: "));
		for (String word : words) {
			String link = "http://www.merriam-webster.com/dictionary/" + word.toLowerCase();
			/*
			 * esta codigo es mierda asi pero eso es lo que hay
			 */
			String anchor = "<a href='" + link + "' target='_blank' style='margin-left: 10px;'>" + word.toLowerCase()
					+ "</a>";

			messageLayout.addComponent(new Label(anchor, ContentMode.HTML));

		}

	}

	private Collection<String> getWords() {
		ArrayList<String> words = new ArrayList<String>();
		
		for (int row = 0; row < game.getSize(); row++) {
			String line = "";
			for (int column = 0; column < game.getSize(); column++) {
				line += table.getItem(row).getItemProperty(column).getValue();

			}
			words.addAll(game.getWords(line));
		}
		
		for (int column = 0; column < game.getSize(); column++) {
			String line = "";
			for (int row = 0; row < game.getSize(); row++) {
				line += table.getItem(row).getItemProperty(column).getValue();
			}
			words.addAll(game.getWords(line));
		}

		return words;
	}

	// tabla mas label score
	private Component getRow() {
		HorizontalLayout row = new HorizontalLayout();
		row.setSpacing(true);
		row.setSizeFull();

		Component table = getTable();
		Component layoutScore = getLayoutScore();

		row.addComponents(table, layoutScore);
		row.setExpandRatio(layoutScore, 1);

		return row;
	}

	// Tabla
	private Component getTable() {

		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		table.addItemClickListener(this);
		table.setPageLength(0);
		table.setImmediate(true);

		for (int column = 0; column < game.getSize(); column++) {
			table.addContainerProperty(column, String.class, "...");

		}
		for (int row = 0; row < game.getSize(); row++) {
			table.addItem(row);
		}

		return table;
	}

	// layout con label y puntaje
	private Component getLayoutScore() {

		messageLayout.setSpacing(true);

		currentLetter.addStyleName(ValoTheme.LABEL_H2);
		currentLetter.addStyleName(ValoTheme.LABEL_COLORED);
		messageLayout.addComponent(currentLetter);

		return messageLayout;
	}
}
