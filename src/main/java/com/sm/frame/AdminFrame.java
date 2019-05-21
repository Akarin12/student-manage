package com.sm.frame;

import javax.swing.*;
import java.awt.*;


public class AdminFrame extends JFrame{
    private JPanel rootPanel;

    public AdminFrame() {
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle bounds = new Rectangle(screenSize);
        setBounds(bounds);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setVisible(true);

    }

}