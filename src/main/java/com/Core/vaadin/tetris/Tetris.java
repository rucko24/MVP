package com.Core.vaadin.tetris;

import org.vaadin.hezamu.canvas.Canvas;

import com.Core.vaadin.Core;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

@Title("Vaadin Tetris")
@Push
public class Tetris extends VerticalLayout {

    private static final int PAUSE_TIME_MS = 500;

    private static final long serialVersionUID = -152735180021558969L;

    // Tile size in pixels
    protected static final int TILE_SIZE = 30;

    // Playfield width in tiles
    private static final int PLAYFIELD_W = 10;

    // Playfield height in tiles
    private static final int PLAYFIELD_H = 20;

    // Playfield background color
    private static final String PLAYFIELD_COLOR = "#000";

    private VerticalLayout layout;
    private Canvas canvas;
    protected boolean running;
    protected Game game;

    private Label scoreLabel;
    private Core UI = Core.getCurrent();

    
    public Tetris() {
    	
        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
       // layout.addComponent(new About());
        
        Notification.show("Variable runninng: "+running,Type.ERROR_MESSAGE);
        
        // Button for moving left
        final Button leftBtn = new Button(FontAwesome.ARROW_LEFT);
        leftBtn.addClickListener(e -> {
            game.moveLeft();
            drawGameState();
        });
        leftBtn.setClickShortcut(KeyCode.ARROW_LEFT);

        // Button for moving right
        final Button rightBtn = new Button(FontAwesome.ARROW_RIGHT);
        rightBtn.addClickListener(e -> {
            game.moveRight();
            drawGameState();

        });
        rightBtn.setClickShortcut(KeyCode.ARROW_RIGHT);

        // Button for rotating clockwise
        final Button rotateCWBtn = new Button("[key down]",
                FontAwesome.ROTATE_RIGHT);
        rotateCWBtn.addClickListener(e -> {
            game.rotateCW();
            drawGameState();
        });
        rotateCWBtn.setClickShortcut(KeyCode.ARROW_DOWN);

        // Button for rotating counter clockwise
        final Button rotateCCWBtn = new Button("[key up]",
                FontAwesome.ROTATE_LEFT);
        rotateCCWBtn.addClickListener(e -> {
            game.rotateCCW();
            drawGameState();
        });
        rotateCCWBtn.setClickShortcut(KeyCode.ARROW_UP);

        // Button for dropping the piece
        final Button dropBtn = new Button("[space]", FontAwesome.ARROW_DOWN);
        dropBtn.addClickListener(e -> {
            game.drop();
            drawGameState();
        });
        dropBtn.setClickShortcut(KeyCode.SPACEBAR);

        // Button for restarting the game
        final Button restartBtn = new Button(FontAwesome.PLAY);
        restartBtn.addClickListener(e -> {
            running = !running;
            if (running) {
                game = new Game(10, 20);
                startGameThread();
                restartBtn.setIcon(FontAwesome.STOP);
                dropBtn.focus();
            } else {
                restartBtn.setIcon(FontAwesome.PLAY);
                gameOver();
            }
        });

       // HorizontalLayout top = new HorizontalLayout();
        
        layout.addComponent(new HorizontalLayout(
                restartBtn, leftBtn, rightBtn, rotateCCWBtn, rotateCWBtn,
                dropBtn
        ));

        // Canvas for the game
        canvas = new Canvas();
        layout.addComponent(canvas);
        canvas.setWidth((TILE_SIZE * PLAYFIELD_W) + "px");
        canvas.setHeight((TILE_SIZE * PLAYFIELD_H) + "px");
		// canvas.setBackgroundColor(PLAYFIELD_COLOR);

        // Label for score
        scoreLabel = new Label("");
        layout.addComponent(scoreLabel);
        this.addComponent(layout);
    }

    /**
     * Start the game thread that updates the game periodically.
     *
     */
    protected synchronized void startGameThread() {
        Thread t = new Thread() {
        	
            public void run() {

                // Continue until stopped or game is over
                while (running && !game.isOver()) {

                    // Draw the state
                    UI.access(new Runnable() {

                        @Override
                        public void run() {
                            drawGameState();
                        }
                    });

                    // Pause for a while
                    try {
                        sleep(PAUSE_TIME_MS);
                    } catch (InterruptedException igmored) {
                    }

                    // Step the game forward and update score
                    game.step();
                    updateScore();

                }

                // Notify user that game is over
                UI.access(new Runnable() {

                    @Override
                    public void run() {
                        gameOver();
                    }
                });
            }
        };
        t.start();

    }

    /**
     * Update the score display.
     *
     */
    protected synchronized void updateScore() {
        UI.access(new Runnable() {

            @Override
            public void run() {
                scoreLabel.setValue("Score: " + game.getScore());
            }
        });
    }

    /**
     * Quit the game.
     *
     */
    protected synchronized void gameOver() {
        running = false;
        Notification.show("Game Over", "Your score: " + game.getScore(),
                Type.HUMANIZED_MESSAGE);
    }

    /**
     * Draw the current game state.
     *
     */
    protected synchronized void drawGameState() {

        // Reset and clear canvas
        canvas.clear();
        canvas.setFillStyle(PLAYFIELD_COLOR);
        canvas.fillRect(0, 0, game.getWidth() * TILE_SIZE + 2, game.getHeight()
                * TILE_SIZE + 2);

        // Draw the tetrominoes
        Grid state = game.getCurrentState();
        for (int x = 0; x < state.getWidth(); x++) {
            for (int y = 0; y < state.getHeight(); y++) {

                int tile = state.get(x, y);
                if (tile > 0) {

                    String color = Tetromino.get(tile).getColor();
                    canvas.setFillStyle(color);
                    canvas.fillRect(x * TILE_SIZE + 1, y * TILE_SIZE + 1,
                            TILE_SIZE - 2, TILE_SIZE - 2);
                }

            }
        }
    }
    
    public Notification notificacion( String msg , Type error) {
    	Notification n = new Notification(msg,error);
    	n.setPosition(Position.BOTTOM_RIGHT);
    	n.show(Page.getCurrent());
    	
    	return n;
    }
}
