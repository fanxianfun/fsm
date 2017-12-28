package com.heetian.example1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

import java.util.Scanner;


/**
 * 判断一个二进制串是否能被 3 整除。
 */
public class BinaryStateMachine2 {

    @States({
            @State(name="0"),
            @State(name="1"),
            @State(name="2")
    })
    @Transitions({
            @Transit(from="0", to="1", on="1", callMethod="from0To1"),
            @Transit(from="1", to="0", on="1", callMethod="from1To0"),
            @Transit(from="1", to="2", on="0", callMethod="from1To2"),
            @Transit(from="2", to="1", on="0", callMethod="from2To1"),
    })
    @StateMachineParameters(stateType=String.class, eventType=String.class, contextType=Integer.class)
    static class StateMachineSample extends AbstractUntypedStateMachine {

        protected void from0To1(String from, String to, String event, Integer context) {
            logger.info("from [{}] to [{}] on [{}]",from,to,event);
        }

        protected void from1To0(String from, String to, String event, Integer context) {
            logger.info("from [{}] to [{}] on [{}]",from,to,event);
            logger.warn("能整除");
        }

        protected void from1To2(String from, String to, String event, Integer context) {
            logger.info("from [{}] to [{}] on [{}]",from,to,event);
        }

        protected void from2To1(String from, String to, String event, Integer context) {
            logger.info("from [{}] to [{}] on [{}]",from,to,event);
        }

    }

    private static final Logger logger = LoggerFactory.getLogger(BinaryStateMachine2.class);

    public static void main(String[] args) {

        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(StateMachineSample.class);

        UntypedStateMachine fsm = builder.newStateMachine("0");

        fsm.addTransitionCompleteListener(event -> logger.debug("current state ==>> {} ",event.getStateMachine().getCurrentState()));

        logger.info("Application is heartbeat! init state ==>> {}",fsm.getInitialState());
        String line;
        Scanner sc = new Scanner(System.in);

        while ((line = sc.nextLine()) != null){
            if(line.length() == 0){
                continue;
            }
            break;
        }

        assert line != null;
        for (String word : line.split("")) {
            fsm.fire(word);
        }
    }
}