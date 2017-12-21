package com.heetian.example3;

import org.squirrelframework.foundation.fsm.StateMachinePerformanceMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnakeGame extends JFrame {

    private static final long serialVersionUID = 5135000138657306646L;
    
    private final SnakePanel panel;
    
    private final SnakeController gameController;
    
    public SnakeGame(final SnakeController controller, final SnakeModel gameModel) {
        super("Greedy Snake");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        this.gameController = controller;
        this.panel = new SnakePanel(gameController, gameModel);
        add(panel, BorderLayout.CENTER);

        final StateMachinePerformanceMonitor statModel = new StateMachinePerformanceMonitor(controller.getClass().getName());
        controller.addDeclarativeListener(statModel);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {

                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    gameController.fire(SnakeController.SnakeEvent.TURN_UP, gameModel);
                    break;

                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    gameController.fire(SnakeController.SnakeEvent.TURN_DOWN, gameModel);
                    break;

                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    gameController.fire(SnakeController.SnakeEvent.TURN_LEFT, gameModel);
                    break;

                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    gameController.fire(SnakeController.SnakeEvent.TURN_RIGHT, gameModel);
                    break;

                case KeyEvent.VK_P:
                    gameController.fire(SnakeController.SnakeEvent.PRESS_PAUSE, gameModel);
                    break;

                case KeyEvent.VK_ENTER:
                    gameController.fire(SnakeController.SnakeEvent.PRESS_START, gameModel);
                    break;
                }
            }

        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.removeDeclarativeListener(statModel);
                System.out.println(statModel.getPerfModel());
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Starts the game running.
     */
    public void startGame() {
        gameController.start();
    }
}
