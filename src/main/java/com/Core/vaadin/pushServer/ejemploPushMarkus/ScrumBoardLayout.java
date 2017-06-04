package com.Core.vaadin.pushServer.ejemploPushMarkus;

import com.vaadin.data.Property;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.*;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.teemu.switchui.Switch;

/**
 * @author Marcus Hellberg (marcus@vaadin.com)
 */
public class ScrumBoardLayout extends VerticalLayout implements ScrumBoard.ScrumBoardListener {

    Map<Card.State, Column> stateColumnMap = new HashMap<Card.State, Column>();
    Map<Card, Column> cardColumnMap = new HashMap<Card, Column>();
    Map<Card, DragAndDropWrapper> cardToWrapperMap = new HashMap<Card, DragAndDropWrapper>();

    public ScrumBoardLayout() {
        setSizeFull();
        setMargin(true);
        setSpacing(true);

        addHeader();
        addBoard();

        fillBoard();

        ScrumBoard.addScrumBoardListener(this);
    }

    private void addHeader() {
    	final Switch switchTemu  = new Switch();
        switchTemu.setImmediate(true);
        
    	final TextField taskNameField = new TextField();
        taskNameField.setInputPrompt("Enter new task");
        taskNameField.setImmediate(true);
        taskNameField.setWidth("300px");
        
        taskNameField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                ScrumBoard.addCard(taskNameField.getValue());

                taskNameField.removeValueChangeListener(this);
                taskNameField.setValue("");
                taskNameField.addValueChangeListener(this);
            }
        });
        
        addComponent(taskNameField);
    }

    private void addBoard() {
        HorizontalLayout boardLayout = new HorizontalLayout();
        boardLayout.setSizeFull();
        boardLayout.setSpacing(true);

        for (final Card.State state : Card.State.values()) {
            Column column = new Column();
            DragAndDropWrapper wrapper = new DragAndDropWrapper(column);
            wrapper.setSizeFull();
            wrapper.addStyleName("no-vertical-drag-hints");
            wrapper.addStyleName("no-horizontal-drag-hints");
            wrapper.setDropHandler(new DropHandler() {
                @Override
                public void drop(DragAndDropEvent event) {
                    Card card = (Card) ((DragAndDropWrapper) event.getTransferable().getSourceComponent()).getData();
                    ScrumBoard.updateCardState(card, state);
                }

                @Override
                public AcceptCriterion getAcceptCriterion() {
                    return AcceptAll.get();
                }
            });
            stateColumnMap.put(state, column);
            boardLayout.addComponent(wrapper);
        }
        addComponent(boardLayout);
        setExpandRatio(boardLayout, 1);
    }

    private void fillBoard() {
        for (Card card : ScrumBoard.getCards()) {
            stateColumnMap.get(card.getState()).addComponent(createWrapperForCard(card));
        }
    }

    @Override
    public void cardUpdated(final Card card) {
        UI.getCurrent().access(new Runnable() {
            @Override
            public void run() {
                Column oldColumn = cardColumnMap.get(card);
                DragAndDropWrapper wrapper = cardToWrapperMap.get(card);
                if (oldColumn != null && wrapper != null) {
                    oldColumn.removeComponent(wrapper);
                }

                if (wrapper == null) {
                    wrapper = createWrapperForCard(card);
                }

                Column newColumn = stateColumnMap.get(card.getState());
                newColumn.addComponent(wrapper);
                UI.getCurrent().push();
            }
        });
    }

    private DragAndDropWrapper createWrapperForCard(Card card) {
        CardLayout cardLayout = new CardLayout(card);
        DragAndDropWrapper wrapper = new DragAndDropWrapper(cardLayout);
        wrapper.setData(card);
        wrapper.setDragStartMode(DragAndDropWrapper.DragStartMode.COMPONENT);
        cardToWrapperMap.put(card, wrapper);
        return wrapper;
    }

    @Override
    public void detach() {
        ScrumBoard.removeScumBoardListener(this);
        super.detach();
    }
}
