package com.heetian.example2;

import org.squirrelframework.foundation.fsm.UntypedStateMachine;

import javax.swing.*;
import java.awt.*;

public class FannerUI extends JFrame{

    private Wind wind;
    private Integer speed = 0;

    FannerUI(UntypedStateMachine fsm) throws HeadlessException {

        setTitle("电风扇");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);

        wind = new Wind();
        wind.setSize(500,400);
        wind.setLocation(0,0);
        wind.setBackground(new Color(255,255,255));
        add(wind);

        JPanel btnGroup = new JPanel();

        btnGroup.setSize(500,200);
        btnGroup.setLocation(0,400);
        add(btnGroup);

        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> fsm.fire("Reset", 0));

        JButton btnA = new JButton("A");
        btnA.addActionListener(e -> fsm.fire("ToA", 10));

        JButton btnB = new JButton("B");
        btnB.addActionListener(e -> fsm.fire("ToB", 20));

        JButton btnC = new JButton("C");
        btnC.addActionListener(e -> fsm.fire("ToC", 50));

        btnGroup.add(reset);
        btnGroup.add(btnA);
        btnGroup.add(btnB);
        btnGroup.add(btnC);
        btnGroup.setBackground(new Color(255,255,255));

        setVisible(true);

        new Timer(50, e -> {
            wind.i += speed;
            wind.repaint();
        }).start();
    }

    public void setSpeed(Integer speed){
        this.speed = speed;
    }

}
