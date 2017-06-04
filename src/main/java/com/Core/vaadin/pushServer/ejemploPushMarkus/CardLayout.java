package com.Core.vaadin.pushServer.ejemploPushMarkus;

import com.vaadin.ui.Label;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class CardLayout extends Label {
    private Card card;

    public CardLayout(Card card) {
        super(card.getTaskName());
        this.card = card;

        addStyleName("card");
        setSizeUndefined();
    }

    public Card getCard() {
        return card;
    }
}
