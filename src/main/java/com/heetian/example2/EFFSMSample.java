package com.heetian.example2;

import org.squirrelframework.foundation.fsm.*;
import org.squirrelframework.foundation.fsm.annotation.*;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;


public class EFFSMSample {
    

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
    static class StateMachineSample extends AbstractUntypedStateMachine {

        protected void fromAnyToIdle(String from, String to, String event, Integer context) {
            System.out.println(String.format("from [%s] to [%s] event [%s] context [%d]",from,to,event,context));
        }

        protected void fromAnyToA(String from, String to, String event, Integer context) {
            System.out.println(String.format("from [%s] to [%s] event [%s] context [%d]",from,to,event,context));
        }

        protected void fromAnyToB(String from, String to, String event, Integer context) {
            System.out.println(String.format("from [%s] to [%s] event [%s] context [%d]",from,to,event,context));
        }

        protected void fromAnyToC(String from, String to, String event, Integer context) {
            System.out.println(String.format("from [%s] to [%s] event [%s] context [%d]",from,to,event,context));
        }

    }

    public static void main(String[] args) {

        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(StateMachineSample.class);

        UntypedStateMachine fsm = builder.newStateMachine("Idle");

        ElectricFan electricFan = new ElectricFan(fsm);

        fsm.addTransitionCompleteListener(event -> {

            System.out.println("Current state is "+event.getStateMachine().getCurrentState());
            electricFan.setText(event.getStateMachine().getCurrentState()+"");
        });


        fsm.fire("Reset", 0);
        electricFan.setText(fsm.getCurrentState()+"");
        System.out.println("Start Current state is "+fsm.getCurrentState());
    }
}