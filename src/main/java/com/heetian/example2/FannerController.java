package com.heetian.example2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;


@States({
        @State(name="Idle"),
        @State(name="A"),
        @State(name="B"),
        @State(name="C")
})
@Transitions({
        @Transit(from="A", to="Idle", on="Reset", callMethod="fromAnyToIdle"),
        @Transit(from="B", to="Idle", on="Reset", callMethod="fromAnyToIdle"),
        @Transit(from="C", to="Idle", on="Reset", callMethod="fromAnyToIdle"),

        @Transit(from="Idle", to="A", on="ToA", callMethod="fromAnyToA"),
        @Transit(from="B", to="A", on="ToA", callMethod="fromAnyToA"),
        @Transit(from="C", to="A", on="ToA", callMethod="fromAnyToA"),

        @Transit(from="Idle", to="B", on="ToB", callMethod="fromAnyToB"),
        @Transit(from="A", to="B", on="ToB", callMethod="fromAnyToB"),
        @Transit(from="C", to="B", on="ToB", callMethod="fromAnyToB"),

        @Transit(from="Idle", to="C", on="ToC", callMethod="fromAnyToC"),
        @Transit(from="A", to="C", on="ToC", callMethod="fromAnyToC"),
        @Transit(from="B", to="C", on="ToC", callMethod="fromAnyToC")
})
@StateMachineParameters(stateType=String.class, eventType=String.class, contextType=Integer.class)
public class FannerController extends AbstractUntypedStateMachine{

    private static final Logger logger = LoggerFactory.getLogger(FannerController.class);

    protected void fromAnyToIdle(String from, String to, String event, Integer context) {
        fannerUI.setSpeed(context);
        logger.info("from [{}] to [{}] event [{}] context [{}]",from,to,event,context);
    }

    protected void fromAnyToA(String from, String to, String event, Integer context) {
        fannerUI.setSpeed(context);
        logger.info("from [{}] to [{}] event [{}] context [{}]",from,to,event,context);
    }

    protected void fromAnyToB(String from, String to, String event, Integer context) {
        fannerUI.setSpeed(context);
        logger.info("from [{}] to [{}] event [{}] context [{}]",from,to,event,context);
    }

    protected void fromAnyToC(String from, String to, String event, Integer context) {
        fannerUI.setSpeed(context);
        logger.info("from [{}] to [{}] event [{}] context [{}]",from,to,event,context);
    }

    private FannerUI fannerUI;

    public FannerController() {
        this.fannerUI = new FannerUI(this);
    }

    public static void main(String[] args) {

        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(FannerController.class);

        UntypedStateMachine fsm = builder.newStateMachine("Idle");

        fsm.addTransitionCompleteListener(event -> logger.debug("current state ==>> {} ",event.getStateMachine().getCurrentState()));

        logger.info("Application is heartbeat! init state ==>> {}",fsm.getInitialState());

    }
}