package com.heetian.example3;

import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachineConfiguration;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

public class Main {
    
    public static void main(String[] args) {
        final SnakeModel gameModel = new SnakeModel();
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(SnakeController.class);
        builder.setStateMachineConfiguration(StateMachineConfiguration.create().enableRemoteMonitor(true));
        
        // define timed state
        builder.defineTimedState(SnakeController.SnakeState.UP, 0, GameConfigure.FRAME_TIME, SnakeController.SnakeEvent.MOVE_AHEAD, gameModel);
        builder.defineTimedState(SnakeController.SnakeState.DOWN, 0, GameConfigure.FRAME_TIME, SnakeController.SnakeEvent.MOVE_AHEAD, gameModel);
        builder.defineTimedState(SnakeController.SnakeState.RIGHT, 0, GameConfigure.FRAME_TIME, SnakeController.SnakeEvent.MOVE_AHEAD, gameModel);
        builder.defineTimedState(SnakeController.SnakeState.LEFT, 0, GameConfigure.FRAME_TIME, SnakeController.SnakeEvent.MOVE_AHEAD, gameModel);
        
        SnakeController controller = builder.newUntypedStateMachine(SnakeController.SnakeState.NEW);
        final SnakeGame game = new SnakeGame(controller, gameModel);
        controller.addTransitionCompleteListener(event -> {
            game.repaint();
            game.setTitle("Greedy Snake("+gameModel.length()+"): "+
                    event.getSourceState()+"--["+event.getCause()+"]--"+event.getTargetState());
        });
        controller.export();
        game.startGame();
    }
}
