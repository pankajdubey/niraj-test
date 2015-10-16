/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitel.console;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;

public class InternalFrameUnMovable extends JFrame {

    JDesktopPane desktop;

    public InternalFrameUnMovable() {
        desktop = new JDesktopPane();
        getContentPane().add(desktop);

        desktop.add(createInternalFrame(1, Color.RED));
        desktop.add(createInternalFrame(2, Color.GREEN));
        desktop.add(createInternalFrame(3, Color.BLUE));
    }

    private JInternalFrame createInternalFrame(int number, Color background) {
        JInternalFrame internal
                = new JInternalFrame("Frame" + number, true, true, true, true);
        internal.setBackground(background);
        internal.setVisible(true);
        int location = 50 * number;
        internal.setBounds(location, location, 300, 300);
        return internal;
    }

    public static void main(String args[]) {
        InternalFrameUnMovable frame = new InternalFrameUnMovable();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

// Activate first internal frame
        try {
            JInternalFrame[] frames = frame.desktop.getAllFrames();
            frames[0].setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }

// Make first internal frame unmovable
        JInternalFrame[] frames = frame.desktop.getAllFrames();
        JInternalFrame f = frames[0];
        BasicInternalFrameUI ui = (BasicInternalFrameUI) f.getUI();

        Component north = ui.getNorthPane();
        MouseMotionListener[] actions
                = (MouseMotionListener[]) north.getListeners(MouseMotionListener.class);

        for (int i = 0; i < actions.length; i++) {
            north.removeMouseMotionListener(actions[i]);
        }

    }
}
