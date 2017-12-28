package com.heetian.example2;

import javax.swing.*;
import java.awt.*;

class Wind extends JPanel {
    int i = 0;

    public void paint(Graphics p) {
        super.paint(p);
        int x_1 = getWidth() / 2;
        int y_1 = getHeight() / 2;
        int a = (int) (Math.min(getWidth(), getHeight() * 0.4));
        int x = x_1 - a;
        int y = y_1 - a;

        p.setColor(Color.red);
        p.fillArc(x, y, 2 * a, 2 * a, i, 30);
        p.setColor(Color.yellow);
        p.fillArc(x, y, 2 * a, 2 * a, i + 90, 30);
        p.setColor(Color.green);
        p.fillArc(x, y, 2 * a, 2 * a, i + 180, 30);
        p.setColor(Color.blue);
        p.fillArc(x, y, 2 * a, 2 * a, i + 270, 30);
    }
}