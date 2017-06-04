package com.Core.vaadin.pushServer.ejemploPushMarkus;

import java.util.List;
import java.util.Vector;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ScrumBoard {

    private static List<ScrumBoardListener> listeners = new Vector<ScrumBoardListener>();
    private static List<Card> cards = new Vector<Card>();

    public interface ScrumBoardListener {
        public void cardUpdated(Card card);
    }

    public static synchronized void addCard(String taskName) {
        Card card = new Card(Card.State.TODO, taskName);
        cards.add(card);
        fireChangeEvent(card);
    }

    public static synchronized void updateCardState(Card card, Card.State newState) {
        Card.State oldState = card.getState();
        card.setState(newState);

        fireChangeEvent(card);
    }

    public static List<Card> getCards() {
        return cards;
    }

    public static synchronized void addScrumBoardListener(ScrumBoardListener listener) {
        System.out.println("Added listener for " + listener);
        listeners.add(listener);
    }

    public static synchronized void removeScumBoardListener(ScrumBoardListener listener) {
        listeners.remove(listener);
    }

    private static void fireChangeEvent(Card card) {
        for (ScrumBoardListener listener : listeners) {
            listener.cardUpdated(card);
        }
    }
}
