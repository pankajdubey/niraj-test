package com.mitel.console;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ModalityDemo extends JFrame {

    boolean flag = false;
    int cnt = 0;
        
    public ModalityDemo() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocation(200, 300);
        setSize(200, 300);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                
                setLocation(200, 300);
                if (flag && cnt == 0) {
                    cnt++;
                    JOptionPane.showMessageDialog(null, "Dragging not allowed.", "Not Allowed", 1);
                } else
                    System.out.println("add mouse listeners again...");
            }
        });
        flag = false;
        
        
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                flag = true;
                removeMouseListener(this);
                System.out.println("entered......");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 System.out.println("exited......");
            }

        });
        setVisible(true);

    }

    public static void main(String[] args) {
        ModalityDemo modalityDemo = new ModalityDemo();
    }
    
}
