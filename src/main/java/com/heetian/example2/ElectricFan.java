package com.heetian.example2;

import org.squirrelframework.foundation.fsm.UntypedStateMachine;

import javax.swing.*;
import java.awt.*;

public class ElectricFan extends JFrame{

    private JTextPane msgPane;


    ElectricFan(UntypedStateMachine fsm) throws HeadlessException {

        setTitle("电风扇状态机");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);

        msgPane = new JTextPane();
        msgPane.setFont(new Font("微软雅黑", Font.BOLD,196));
        msgPane.setEditable(false);


        JPanel mainPanel = new JPanel();
        mainPanel.setSize(500,400);
        mainPanel.setLocation(0,0);
        mainPanel.add(msgPane);
        mainPanel.setBackground(new Color(255,255,255));
        add(mainPanel);

        JPanel btnGroup = new JPanel();

        btnGroup.setSize(500,200);
        btnGroup.setLocation(0,400);
        add(btnGroup);

        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> fsm.fire("Reset", 0));

        JButton btnA = new JButton("A");
        btnA.addActionListener(e -> fsm.fire("ToA", 1));

        JButton btnB = new JButton("B");
        btnB.addActionListener(e -> fsm.fire("ToB", 1));

        JButton btnC = new JButton("C");
        btnC.addActionListener(e -> fsm.fire("ToC", 1));

        btnGroup.add(reset);
        btnGroup.add(btnA);
        btnGroup.add(btnB);
        btnGroup.add(btnC);
        btnGroup.setBackground(new Color(255,255,255));

        setVisible(true);

    }


    public void setText(String text){
        msgPane.setText(text);
        this.repaint();
    }
}
