package com.heetian.example1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

import java.util.Scanner;


public class BinaryStateMachine {

    private static final Logger logger = LoggerFactory.getLogger(BinaryStateMachine.class);

    @States({
            @State(name="S1"),
            @State(name="S2")
    })
    @Transitions({
            @Transit(from="S1", to="S2", on="0", callMethod="fromS1ToS2"),
            @Transit(from="S2", to="S1", on="0", callMethod="fromS2ToS1"),
    })
    @StateMachineParameters(stateType=String.class, eventType=String.class, contextType=Integer.class)
    static class StateMachineSample extends AbstractUntypedStateMachine {

        protected void fromS1ToS2(String from, String to, String event, Integer context) {
            logger.info("from [{}] to [{}] on [{}]",from,to,event);
        }

        protected void fromS2ToS1(String from, String to, String event, Integer context) {
            logger.info("from [{}] to [{}] on [{}]",from,to,event);
        }

    }

    public static void main(String[] args) {

        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(StateMachineSample.class);

        UntypedStateMachine fsm = builder.newStateMachine("S1");

        fsm.addTransitionCompleteListener(event -> logger.debug("current state ==>> {} ",event.getStateMachine().getCurrentState()));

        logger.info("Application is heartbeat! init state ==>> {}",fsm.getInitialState());
        String line;
        Scanner sc = new Scanner(System.in);

        while ((line = sc.nextLine()) != null){
            if(line.length() == 0){
                continue;
            }
            String[] words = line.split("");
            for (String word : words) {
                fsm.fire(word);
            }

        }
    }
}